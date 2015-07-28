package com.scxh.android1502.media.mp3;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.MusicBean;
import com.scxh.android1502.media.mp3.MusicPlayerService.IPlayerMusicServicer;

public class UIMusicPlayerActivity extends Activity implements OnClickListener {
	private ImageView mPlayerBtn, mPrePlayerBtn, mNextPlayerBtn;
	private SeekBar mSeekBar;
	private TextView mCurrentTimeTxt, mDurationTimeTxt;
	private boolean isQuit = true;

	private IPlayerMusicServicer mIPlayerMusicServicer;
	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mIPlayerMusicServicer = (IPlayerMusicServicer) service;
		}
	};
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ui_musicplayer_layout);

		mPlayerBtn = (ImageView) findViewById(R.id.music_player);
		mPrePlayerBtn = (ImageView) findViewById(R.id.music_pre_player);
		mNextPlayerBtn = (ImageView) findViewById(R.id.music_next_player);

		mSeekBar = (SeekBar) findViewById(R.id.music_seekbar);
		mCurrentTimeTxt = (TextView) findViewById(R.id.current_time);
		mDurationTimeTxt = (TextView) findViewById(R.id.duration_time);

		mPlayerBtn.setOnClickListener(this);
		mPrePlayerBtn.setOnClickListener(this);
		mNextPlayerBtn.setOnClickListener(this);

		//=========获取音乐播放列表资源=============
		Intent intent = getIntent();
		ArrayList<MusicBean> mMusicList = intent.getParcelableArrayListExtra("MUSIC_LIST");
		int mCurrentPostion = intent.getIntExtra("CURRENT_POSTION", 0);
		

		Intent service = new Intent(this, MusicPlayerService.class);
		service.putParcelableArrayListExtra("MUSIC_LIST", mMusicList);
		service.putExtra("CURRENT_POSTION", mCurrentPostion);
		
		startService(service);
		bindService(service, mServiceConnection, BIND_AUTO_CREATE);
		mPlayerBtn.setImageResource(R.drawable.player_pause);

		new Thread(new UpdateUIThread()).start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.music_player:
			boolean isPlayer = mIPlayerMusicServicer.iPlayerMusic();
			if (isPlayer) {
				mPlayerBtn.setImageResource(R.drawable.player_pause);
			} else {
				mPlayerBtn.setImageResource(R.drawable.player_play);
			}

			break;
		case R.id.music_next_player:
			mIPlayerMusicServicer.iPlayerNextMusic();
			break;
		case R.id.music_pre_player:
			mIPlayerMusicServicer.iPlayerPreMusic();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mServiceConnection != null)
			unbindService(mServiceConnection);
		isQuit = false;
	}

	class UpdateUIThread implements Runnable {

		@Override
		public void run() {
			while (isQuit) {
				if (mIPlayerMusicServicer != null) {
					mHandler.post(new Runnable() {
						@Override
						public void run() {

							int duration = mIPlayerMusicServicer.iPlayerDuration();
							int currentPostion = mIPlayerMusicServicer.iPlayerCurrentPostion();

							mSeekBar.setMax(duration);
							mSeekBar.setProgress(currentPostion);

							int pos = currentPostion;
							int min = (pos / 1000) / 60;
							int sec = (pos / 1000) % 60;

							if (sec < 10)
								mCurrentTimeTxt.setText(min + ":0" + sec);
							else
								mCurrentTimeTxt.setText(min + ":" + sec);

							// ===========================
							int maxPos = duration;
							int maxMin = (maxPos / 1000) / 60;
							int maxSec = (maxPos / 1000) % 60;

							if (maxSec < 10) {
								mDurationTimeTxt.setText(maxMin + ":0" + maxSec);
							} else {
								mDurationTimeTxt.setText(maxMin + ":" + maxSec);
							}
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

}
