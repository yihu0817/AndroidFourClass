package com.scxh.android1502.ui.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scxh.android1502.R;

public class ProgressBarActivity extends Activity {
	private ProgressBar mProgressBar;
	private Button mLoadBtn;
	int progress = 0;
	MyHandler mHandler = new MyHandler();

	class MyHandler extends Handler {
	
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(ProgressBarActivity.this, "进度加载完成", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_progressbar1_layout);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		mLoadBtn = (Button) findViewById(R.id.load1_btn);
		
		mLoadBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				mProgressBar.setProgress(++progress);
				mProgressBar.incrementProgressBy(10);
			}
		});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 10; i++){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mProgressBar.incrementProgressBy(10);
//					mProgressBar.setProgress(i);
				}
				
				mHandler.sendEmptyMessage(0);
			}
		}).start();
		
	}
}
