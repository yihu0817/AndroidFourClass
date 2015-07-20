package com.scxh.android1502.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.service.MyService.ICountService;
import com.scxh.android1502.util.Logs;
/**
 * 绑定客户操作
 * 1.声明交互接口类
 * 2.实现ServiceConnection,通过onServiceConnected方法获取 交互接口类对象
 * 3.解绑时 释放交互接口对象
 */
public class StartMyServiceActivity extends Activity {
	private Button mStartServiceBtn,mBindServiceBtn,mSetCountBtn,mGetCountBtn;
	private EditText mSetCountEdit;
	
	private MyService.ICountService mICountService; //1.声明交互接口类
	private ServiceConnection  mServiceConnection  = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Logs.v("onServiceDisconnected >>>> ");
			mICountService = null;
		}
		/**
		 * 2.实现ServiceConnection,通过onServiceConnected方法获取 交互接口类对象
		 */
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mICountService = (ICountService) service;
			
			Logs.v("onServiceDisconnected >>>>  IBinder  "+service);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myservice_layout);
		mStartServiceBtn = (Button) findViewById(R.id.service_my_btn);
		mBindServiceBtn = (Button) findViewById(R.id.service_bindservice_btn);
		mSetCountBtn = (Button) findViewById(R.id.service_setcount_btn);
		mGetCountBtn = (Button) findViewById(R.id.service_getcount_btn);
		mSetCountEdit = (EditText) findViewById(R.id.service_count_edit);
		
		mStartServiceBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartMyServiceActivity.this,MyService.class);
				startService(intent);
			}
		});
		
		mBindServiceBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartMyServiceActivity.this,MyService.class);
				bindService(intent, mServiceConnection,BIND_AUTO_CREATE);
			}
		});
		
		mSetCountBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String counts = mSetCountEdit.getText().toString();
				int count = Integer.parseInt(counts);
				mICountService.setCount(count);
			}
		});
		
		mGetCountBtn.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int count = mICountService.getCount();
				
				mICountService.stopQuitThread(false);
				
				Toast.makeText(StartMyServiceActivity.this, ""+count, Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mServiceConnection != null)
			unbindService(mServiceConnection);
	}
	
}
