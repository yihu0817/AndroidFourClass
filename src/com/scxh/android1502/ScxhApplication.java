package com.scxh.android1502;

import com.scxh.android1502.util.Logs;

import android.app.Application;

public class ScxhApplication extends Application {
	public String name= "";
	@Override
	public void onCreate() {
		super.onCreate();
		Logs.v("ScxhApplication >>>>>  ");
	}
}
