package com.scxh.android1502;

import android.app.Application;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.scxh.android1502.util.Logs;

/**
 * 1.初始化操作 2.定义的变量在其它Activity类都可以访问。
 * 
 */
public class ScxhApplication extends Application {
	public String mName = "你好";

	@Override
	public void onCreate() {
		super.onCreate();
		Logs.v("ScxhApplication >>>>>  ");

		RequestManager.init(this);
		
//		receiverPhoneStateListener();
	}

	private void receiverPhoneStateListener(){
		// 获取电话通讯服务
		TelephonyManager tpm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		// 创建一个监听对象，监听电话状态改变事件
		tpm.listen(new MyPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);
	}
	class MyPhoneStateListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE: // 空闲
				Logs.d("空闲 >>>>>  ");
				break;
			case TelephonyManager.CALL_STATE_RINGING: // 来电
				Logs.v("来电 >>>>>  ");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // 摘机（正在通话中）
				Logs.w("摘机（正在通话中） >>>>>  ");
				break;
			}
		}

	}

}
