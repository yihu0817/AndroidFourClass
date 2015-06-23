package com.scxh.android1502;

import com.scxh.android1502.util.Logs;

import android.app.Application;
/**
 * 1.初始化操作
 * 2.定义的变量在其它Activity类都可以访问。
 *
 */
public class ScxhApplication extends Application {
	public String mName= "你好";
	@Override
	public void onCreate() {
		super.onCreate();
		Logs.v("ScxhApplication >>>>>  ");
	}
	
}
