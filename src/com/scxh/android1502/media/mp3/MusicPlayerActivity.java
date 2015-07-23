package com.scxh.android1502.media.mp3;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.scxh.android1502.R;

public class MusicPlayerActivity extends Activity implements OnClickListener {
	private Button  mResetBtn;
	private ImageView mPlayerBtn;
	private SeekBar mSeekBar;
	private TextView mCurrentTime, mDurationTime;

	private String musicRoot = Environment.getExternalStoragePublicDirectory(
			Environment.DIRECTORY_MUSIC).getPath();
	private String musicFile = "file://" + musicRoot + "/androidmp3.mp3";
	private MediaPlayer mMediaPlayer;

	private Handler mHandler = new Handler();
	private boolean isQuit = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_music_player_layout);
		mPlayerBtn = (ImageView) findViewById(R.id.music_player);
		mResetBtn = (Button) findViewById(R.id.music_reset);

		mSeekBar = (SeekBar) findViewById(R.id.music_seekbar);
		mCurrentTime = (TextView) findViewById(R.id.current_time);
		mDurationTime = (TextView) findViewById(R.id.duration_time);

		mPlayerBtn.setOnClickListener(this);
		mResetBtn.setOnClickListener(this);

		initMusic();

		new Thread(new UpdateUIThread()).start();
	}

	private void initMusic() {
		try {
			if (mMediaPlayer == null)
				mMediaPlayer = new MediaPlayer();

			mMediaPlayer.setDataSource(musicFile);

			mMediaPlayer.prepare();
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

		case R.id.music_reset:
			resetMusic();
			break;
		}
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

	private void resetMusic() {
		mMediaPlayer.reset();
		initMusic();
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
								mCurrentTime.setText(min + ":0" + sec);
							else
								mCurrentTime.setText(min + ":" + sec);

							// ===========================
							int maxPos = mMediaPlayer.getDuration();
							int maxMin = (maxPos / 1000) / 60;
							int maxSec = (maxPos / 1000) % 60;

							if (maxSec < 10) {
								mDurationTime.setText(maxMin + ":0" + maxSec);
							} else {
								mDurationTime.setText(maxMin + ":" + maxSec);
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
