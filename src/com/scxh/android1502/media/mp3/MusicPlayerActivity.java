package com.scxh.android1502.media.mp3;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.MusicBean;

public class MusicPlayerActivity extends Activity implements OnClickListener {
	private ImageView mPlayerBtn,mPrePlayerBtn,mNextPlayerBtn;
	private SeekBar mSeekBar;
	private TextView mCurrentTimeTxt, mDurationTimeTxt;
	private TextView mMusicNameTxt;
	
	private MediaPlayer mMediaPlayer;
	private Handler mHandler = new Handler();
	
	private ArrayList<MusicBean> mMusicList;
	private int mCurrentPostion = 0;
	private boolean isQuit = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_music_player_layout);
		
		mPlayerBtn = (ImageView) findViewById(R.id.music_player);
		mPrePlayerBtn = (ImageView) findViewById(R.id.music_pre_player);
		mNextPlayerBtn = (ImageView) findViewById(R.id.music_next_player);

		mSeekBar = (SeekBar) findViewById(R.id.music_seekbar);
		mCurrentTimeTxt = (TextView) findViewById(R.id.current_time);
		mDurationTimeTxt = (TextView) findViewById(R.id.duration_time);
		mMusicNameTxt = (TextView) findViewById(R.id.music_name);
		
		mPlayerBtn.setOnClickListener(this);
		mPrePlayerBtn.setOnClickListener(this);
		mNextPlayerBtn.setOnClickListener(this);

		//=========获取音乐播放列表资源=============
		Intent intent = getIntent();
		mMusicList = intent.getParcelableArrayListExtra("MUSIC_LIST");
		mCurrentPostion = intent.getIntExtra("CURRENT_POSTION", 0);
		
		MusicBean music = mMusicList.get(mCurrentPostion);
		String musicFile = "file://"+music.getMusicPath();
		String musicName = music.getMusicName();
		
		//======音乐标题==========
		mMusicNameTxt.setText(musicName);
		
		//======初始化播放音乐==========
		initMusic(musicFile);
		
		//======播放进度线程=========
		new Thread(new UpdateUIThread()).start();
	}

	private void initMusic(String musicFile) {
		try {
			if (mMediaPlayer == null)
				mMediaPlayer = new MediaPlayer();

			mMediaPlayer.setDataSource(musicFile);

			mMediaPlayer.prepare();
			
			playerMusic();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.music_player:
			playerMusic();

			break;
		case R.id.music_next_player:
			if(++mCurrentPostion < mMusicList.size()){
				playerNewMusic(mCurrentPostion);
			}else{
				mCurrentPostion = mMusicList.size()-1;
				Toast.makeText(this, "已到最后一首", Toast.LENGTH_SHORT).show();
			}
			
			break;
		case R.id.music_pre_player:
			if(--mCurrentPostion >= 0){
				playerNewMusic(mCurrentPostion);
			}else{
				mCurrentPostion = 0;
				Toast.makeText(this, "已到第一首", Toast.LENGTH_SHORT).show();
			}
			
			break;
		}
	}

	private void playerNewMusic(int mCurrentPostion){
		MusicBean music = mMusicList.get(mCurrentPostion);
		String musicFile = "file://"+music.getMusicPath();
		String musicName = music.getMusicName();
		mMusicNameTxt.setText(musicName);
		mMediaPlayer.reset();
		initMusic(musicFile);
	}
	
	private void playerMusic() {
		if (!mMediaPlayer.isPlaying()) {
			mMediaPlayer.start();
			mPlayerBtn.setImageResource(R.drawable.player_pause);
		}else{
			mMediaPlayer.pause();
			mPlayerBtn.setImageResource(R.drawable.player_play);
		}

	}


	class UpdateUIThread implements Runnable {

		@Override
		public void run() {
			while (isQuit) {

				if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {

					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mSeekBar.setMax(mMediaPlayer.getDuration());
							mSeekBar.setProgress(mMediaPlayer
									.getCurrentPosition());

							int pos = mMediaPlayer.getCurrentPosition();
							int min = (pos / 1000) / 60;
							int sec = (pos / 1000) % 60;

							if (sec < 10)
								mCurrentTimeTxt.setText(min + ":0" + sec);
							else
								mCurrentTimeTxt.setText(min + ":" + sec);

							// ===========================
							int maxPos = mMediaPlayer.getDuration();
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mMediaPlayer != null){
			mMediaPlayer.stop();
			mMediaPlayer.release();
			isQuit = false;
			mMediaPlayer = null;
			
		}
	}

}
