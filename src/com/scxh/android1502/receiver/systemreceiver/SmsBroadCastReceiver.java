package com.scxh.android1502.receiver.systemreceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.scxh.android1502.util.Logs;

/**
 *  BroadcastReceiver作用 ：接收过滤响应广播(intent对象)
 * 实现步骤
 * 1.定义BroadcastReceiver
 * 2.注册BroadcastReceiver
 *
 * 
 */
public class SmsBroadCastReceiver extends BroadcastReceiver {
	private static boolean incomingFlag = false;
	private static String incoming_number = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {
			Toast.makeText(context, "接收短消息静态广播", Toast.LENGTH_SHORT).show();
			Logs.v("接收短消息静态广播 >>>>>>>>>>>>>>>> ");

		} else if (action.equals(MyReceiverActivity.ACTION_SCXH)) {

			Toast.makeText(context, "自定义静态广播接收到", Toast.LENGTH_SHORT).show();
			Logs.v("自定义静态广播 >>>>>>>>>>>>>>>> ");
			
		} else if (action.equals("android.intent.action.NEW_OUTGOING_CALL")) { 
			//如果是拨打电话
			incomingFlag = false;
			String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Logs.i("拨打电话:" + phoneNumber);
			
		} else if (action.equals("android.intent.action.PHONE_STATE")) {
			// 如果是来电
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

			switch (tm.getCallState()) {
			case TelephonyManager.CALL_STATE_RINGING:
				incomingFlag = true;// 标识当前是来电
				incoming_number = intent.getStringExtra("incoming_number");
				Logs.i("来电  :" + incoming_number);
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				if (incomingFlag) {
					Logs.i("摘机（正在通话中） :" + incoming_number);
				}
				break;

			case TelephonyManager.CALL_STATE_IDLE:
				if (incomingFlag) {
					Logs.i("空闲 >>>>>");
				}
				break;
			}

		}

	}

}
