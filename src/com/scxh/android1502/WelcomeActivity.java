package com.scxh.android1502;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Window;

public class WelcomeActivity extends Activity {
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			startActivity(new Intent(WelcomeActivity.this,
					MainListActivity.class));
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome_layout);

		startActivityMessageDelayedByHandler();
		
		
		
	}

	/**
	 * 普通线程方式延迟启动Activity
	 */
	public void startActivityDelayedByThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				startActivity(new Intent(WelcomeActivity.this,
						MainListActivity.class));
				finish();
			}
		}).start();
	}

	/**
	 * Handler PostDelayed方式延迟启动Activity
	 */
	public void startActivityDelayedByHandler() {

		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(new Intent(WelcomeActivity.this,
						MainListActivity.class));
				finish();
			}
		}, 2000);

	}

	/**
	 * Handler SendMessageDelayed方式延迟启动Activity
	 */
	public void startActivityMessageDelayedByHandler() {

		Message message = Message.obtain();

		mHandler.sendMessageDelayed(message, 2000);

	}

	public class LooperThread extends Thread {
		Handler mLooperHander1,mLooperHander2;

		@Override
		public void run() {
			Looper.prepare();
			
			mLooperHander1 = new Handler();
			mLooperHander2 = new Handler();

			Looper.loop();

		}
	}

}
