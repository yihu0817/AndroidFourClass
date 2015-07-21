package com.scxh.android1502.service;

import com.scxh.android1502.util.Logs;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
/**
 * 实现客户端与服务端 交互
 * 1.定义交互接口方法
 * 2.继承Binder类实现交互接口方法
 * 3.实例化Binder对象，通过onBinder方法返回
 */
public class MyService extends Service {
	private int mCount;
	private boolean mQuit = true;
	/**
	 * 1.定义交互接口方法
	 *
	 */
	public interface ICountService{
		public void setCount(int count);
		public int getCount();
		public void stopQuitThread(boolean flag);
	}
	/**
	 * 2.继承Binder类实现交互接口方法
	 *
	 */
	public class ServiceBinder extends Binder implements ICountService{

		@Override
		public void setCount(int count) {
			mCount = count;
		}

		@Override
		public int getCount() {
			return mCount;
		}

		@Override
		public void stopQuitThread(boolean flag) {
			mQuit = flag;
		}
		
	}
	
	private ServiceBinder mServiceBinder = new ServiceBinder();
	@Override
	public void onCreate() {
		super.onCreate();
		Logs.v("Service  onCreate>>>");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(mQuit){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					mCount++ ;
					Logs.v("Service  MyService  >>> : "+mCount);
				}
				
//				stopSelf();//停止当前正在运行服务，只有startService启动服务时，才起作用
			}
		}).start();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Logs.v("Service  onStartCommand>>>");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Logs.v("Service  onBind>>>");
		return mServiceBinder;
	}

	
	@Override
	public boolean onUnbind(Intent intent) {
		Logs.v("Service  onUnbind>>>");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		mQuit = false;
		
		Logs.v("Service  onDestroy>>>");
	}
}
