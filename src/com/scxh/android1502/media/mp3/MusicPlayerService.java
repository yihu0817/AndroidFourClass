package com.scxh.android1502.media.mp3;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.MusicBean;
import com.scxh.android1502.util.Logs;

public class MusicPlayerService extends Service {
	public static final String ACTION_PAUSE = "com.scxh.android.MUSIC_PAUSE";//音乐暂停广播
	public static final String ACTION_PRE = "com.scxh.android.MUSIC_PRE";//上一首广播
	public static final String ACTION_NEXT = "com.scxh.android.MUSIC_NEXT";//下一首广播
	private MediaPlayer mMediaPlayer;

	private ArrayList<MusicBean> mMusicList = null;
	private int mCurrentPostion = -1;
	public String mCurrentMusicName = "";
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
		
		if(mMusicList == null){
			mMusicList = intent.getParcelableArrayListExtra("MUSIC_LIST");
			mCurrentPostion = intent.getIntExtra("CURRENT_POSTION", 0);
		}
		
		MusicBean music = mMusicList.get(mCurrentPostion);
		String musicFile = "file://"+music.getMusicPath();
		String musicName = music.getMusicName();
		mCurrentMusicName = musicName;
		initMusic(musicFile);

		Logs.v("onStartCommand >>>>> musicPath  :" + musicFile);

		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_PAUSE);
		filter.addAction(ACTION_NEXT);
		filter.addAction(ACTION_PRE);
		registerReceiver(musicPlayerReceiver, filter);
		
		
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
		
		if(musicPlayerReceiver !=null)
			unregisterReceiver(musicPlayerReceiver);
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
			notificationMusicPlayer();
			return true;
		} else {
			mMediaPlayer.pause();
			notificationMusicPlayer();
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
		mCurrentMusicName = musicName;
		mMediaPlayer.reset();
		initMusic(musicFile);
	}

	private void notificationMusicPlayer(){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.m8);
		builder.setTicker("音乐播放开始");
		
		RemoteViews views = new RemoteViews(getPackageName(), R.layout.notification);
		views.setTextViewText(R.id.title, mCurrentMusicName);
		views.setTextViewText(R.id.text, "未知音乐家");
		views.setImageViewResource(R.id.image, R.drawable.m3);
		
		if(!mMediaPlayer.isPlaying()){
			views.setImageViewResource(R.id.iv_pause, R.drawable.widget4x1_play);
		}else{
			views.setImageViewResource(R.id.iv_pause, R.drawable.nc_pause);
		}
		Intent pauseIntent = new Intent(ACTION_PAUSE);
		PendingIntent pausePendingIntent = PendingIntent.getBroadcast(this, 1, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.iv_pause, pausePendingIntent);
		
		
		builder.setContent(views);
		
		Intent intent = new Intent(this,UIMusicPlayerActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(pendingIntent);
		
		NotificationManager manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int id = 1000;
		Notification notification = builder.build();
		
		manger.notify(id, notification);
	
	}
	
	/**
	 * 定义广播接收者
	 */
	BroadcastReceiver musicPlayerReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equals(ACTION_PAUSE)){
				
				playerMusic();
				
			}else if(action.equals(ACTION_PRE)){

				playerPreMusic();
				
			}else if(action.equals(ACTION_NEXT)){
				
				playerNextMusic();
				
			}
		}
		
	};
	
	
}
