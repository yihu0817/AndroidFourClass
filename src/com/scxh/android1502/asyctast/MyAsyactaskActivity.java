package com.scxh.android1502.asyctast;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class MyAsyactaskActivity extends Activity {
	private ProgressBar mProgressBar;
	private final static int HANDER_PROGRESS = 1;
	private final static int HANDER_PROGRESS_OVER = 2;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDER_PROGRESS:
				int progress = msg.arg1;
				mProgressBar.setProgress(progress);
				break;
			case HANDER_PROGRESS_OVER:
				Toast.makeText(MyAsyactaskActivity.this, "异步任务进度加载完成!",
						Toast.LENGTH_SHORT).show();
				break;
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_asysactask_layout);
		mProgressBar = (ProgressBar) findViewById(R.id.asyack_prgressbar);

//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				for (int i = 1; i <= 100; i++) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//
//					Message msg = Message.obtain();
//					msg.what = HANDER_PROGRESS;
//					msg.arg1 = i;
//					mHandler.sendMessage(msg);
//
//				}
//
//				mHandler.sendEmptyMessage(HANDER_PROGRESS_OVER);
//			}
//		}).start();

		new AsyncTask<String, Integer, Boolean>() {

			protected void onPreExecute() {
				Logs.v("onPreExecute >>>>>>>>>>>");
			};
			
			@Override
			protected Boolean doInBackground(String... params) {
				Logs.v("onPreExecute >>>>>>>>>>>");
				String m1= params[0];
				String m2= params[1];
				String m3= params[2];
				Logs.v("m1 :"+m1+ " \n m2 :"+m2+ " \n m3 :"+m3);
				// 写后台执行耗时操作代码
				for (int i = 1; i <= 100; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					publishProgress(i);
				}

				return false;
			}
			/**
			 * onProgressUpdate方法不是必须执行的，通过publishProgress()方法调用执行
			 */
			protected void onProgressUpdate(Integer... values) {
				Logs.v("onProgressUpdate >>>>>>>>>>>");
				int progress = values[0];
				mProgressBar.setProgress(progress);
			};
			
			protected void onPostExecute(Boolean result) {
				Logs.v("onPostExecute >>>>>>>>>>>");
				if(result){
					Toast.makeText(MyAsyactaskActivity.this, "异步任务进度加载完成!",
							Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(MyAsyactaskActivity.this, "异步任务进度加载失败!",
							Toast.LENGTH_SHORT).show();
				}
				
			};
			
		}.execute("http://it.warmtel.com/servlet","http://it.warmtel.com/mm1.jpg","http://it.warmtel.com/mm2.png");
	}

}
