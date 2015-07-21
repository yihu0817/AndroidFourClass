package com.scxh.android1502.service;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import com.scxh.android1502.util.Logs;

public class DownLoadFileIntentService extends IntentService {

	public DownLoadFileIntentService() {
		super("DownLoadFileIntentService");
	}
	@Override
	protected void onHandleIntent(Intent intent) {
		//模拟下载操作
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String fileName = intent.getStringExtra("filename");
		Logs.v("下载完成"+fileName);
		Toast.makeText(this, "下载完成"+fileName, Toast.LENGTH_SHORT).show();

	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		String fileName = intent.getStringExtra("filename");
		Logs.i("下载开始"+fileName);
		Toast.makeText(this, "下载开始"+fileName, Toast.LENGTH_SHORT).show();
		
		return super.onStartCommand(intent, flags, startId);
	}

}
