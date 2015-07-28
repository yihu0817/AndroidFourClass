package com.scxh.android1502.media.mp3.mediastore;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.scxh.android1502.util.Logs;

public class MusicStorePlayerService extends Service {
	private MediaPlayer mMediaPlayer;


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		String musicFile = intent.getStringExtra("MUSIC_PATH");

		initMusic(musicFile);

		Logs.v("onStartCommand >>>>> musicPath  :" + musicFile);

		return super.onStartCommand(intent, flags, startId);
	}
	/**
	 * 释放MediaPlayer资源
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();

		if (mMediaPlayer != null) {
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.stop();
			}
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	/**
	 * 初始化音乐资源，进行播放
	 * 
	 * @param musicFile
	 */
	private void initMusic(String musicFile) {
		try {
			if (mMediaPlayer == null) {
				mMediaPlayer = new MediaPlayer();
			}

			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.stop();
				mMediaPlayer.reset();
			}
			if (!mMediaPlayer.isPlaying()) {
				mMediaPlayer.setDataSource(musicFile);
				mMediaPlayer.prepare();
				playerMusic();
			}

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

	/**
	 * 音乐播放或暂停
	 */
	private boolean playerMusic() {
		if (!mMediaPlayer.isPlaying()) {
			mMediaPlayer.start();
			return true;
		} else {
			mMediaPlayer.pause();
			return false;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
