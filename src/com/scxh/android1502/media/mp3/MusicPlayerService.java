package com.scxh.android1502.media.mp3;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.scxh.android1502.bean.MusicBean;
import com.scxh.android1502.util.Logs;

public class MusicPlayerService extends Service {
	private MediaPlayer mMediaPlayer;

	private ArrayList<MusicBean> mMusicList;
	private int mCurrentPostion = -1;

	interface IPlayerMusicServicer {
		public boolean iPlayerMusic();

		public void iPlayerNextMusic();

		public void iPlayerPreMusic();

		public int iPlayerDuration();

		public int iPlayerCurrentPostion();
	}

	class MusicServiceBinder extends Binder implements IPlayerMusicServicer {

		@Override
		public boolean iPlayerMusic() {
			return playerMusic();
		}

		@Override
		public void iPlayerNextMusic() {
			playerNextMusic();
		}

		@Override
		public void iPlayerPreMusic() {
			playerPreMusic();
		}

		@Override
		public int iPlayerDuration() {
			if (mMediaPlayer != null) {
				return mMediaPlayer.getDuration();
			} else {
				return 0;
			}
		}

		@Override
		public int iPlayerCurrentPostion() {
			if (mMediaPlayer != null) {
				return mMediaPlayer.getCurrentPosition();
			} else {
				return 0;
			}
		}

	}

	private MusicServiceBinder mMusicServiceBinder = new MusicServiceBinder();

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mMusicList = intent.getParcelableArrayListExtra("MUSIC_LIST");
		mCurrentPostion = intent.getIntExtra("CURRENT_POSTION", 0);
		
		MusicBean music = mMusicList.get(mCurrentPostion);
		String musicFile = "file://"+music.getMusicPath();
		String musicName = music.getMusicName();
		
		initMusic(musicFile);

		Logs.v("onStartCommand >>>>> musicPath  :" + musicFile);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mMusicServiceBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
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

//			 if(mMediaPlayer.isPlaying()){
//				 mMediaPlayer.reset();
//			 }
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

	/**
	 * 播放下一首音乐
	 */
	private void playerNextMusic() {
		if (++mCurrentPostion < mMusicList.size()) {
			playerNewMusic(mCurrentPostion);
		} else {
			mCurrentPostion = mMusicList.size() - 1;
			Toast.makeText(this, "已到最后一首", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 播放上一首音乐
	 */
	private void playerPreMusic() {
		if (--mCurrentPostion >= 0) {
			playerNewMusic(mCurrentPostion);
		} else {
			mCurrentPostion = 0;
			Toast.makeText(this, "已到第一首", Toast.LENGTH_SHORT).show();
		}
	}

	private void playerNewMusic(int mCurrentPostion) {
		MusicBean music = mMusicList.get(mCurrentPostion);
		String musicFile = "file://" + music.getMusicPath();
		String musicName = music.getMusicName();
		mMediaPlayer.reset();
		initMusic(musicFile);
	}

}
