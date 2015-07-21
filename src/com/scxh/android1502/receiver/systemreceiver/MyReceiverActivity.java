package com.scxh.android1502.receiver.systemreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.storage.file.browse.FileExplorerActivity;
import com.scxh.android1502.util.Logs;

public class MyReceiverActivity extends Activity implements OnClickListener{
	public static final String ACTION_SCXH = "com.scxh.android1502.receiver_ACTION_SCXH";
	public static final String ACTION_SCXH_DONGTAI = "com.scxh.android1502.receiver_ACTION_SCXH_DONGTAI";
	private Button mSendReceiverBtn,mSendDongtaiReceiverBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_receiver1_layout);
		mSendReceiverBtn = (Button) findViewById(R.id.receiver_send_btn);
		mSendReceiverBtn.setOnClickListener(this);
		mSendDongtaiReceiverBtn = (Button) findViewById(R.id.receiver_send_dongtai_btn);
		mSendDongtaiReceiverBtn.setOnClickListener(this);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(MyReceiverActivity.ACTION_SCXH_DONGTAI);
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		registerReceiver(receiver, filter);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.receiver_send_btn:
			Intent intent = new Intent(ACTION_SCXH);
			sendBroadcast(intent);
			
			break;
		case R.id.receiver_send_dongtai_btn:
			intent = new Intent(ACTION_SCXH_DONGTAI);
			sendBroadcast(intent);
			
		    break;
		}
		
	}
	
	private BroadcastReceiver receiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			//系统短消息广播接收者
			if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {
				
				Toast.makeText(context, "动态短消息接收到", Toast.LENGTH_SHORT).show();
				Logs.v("动态接收到消息 >>>>>>>>>>>>>>>> ");
				
//				abortBroadcast();//中止有序广播向下传播
				
//				context.startActivity(new Intent(context,FileExplorerActivity.class));

				//自定义广播接收者
			} else if (action.equals(MyReceiverActivity.ACTION_SCXH_DONGTAI)) {

				Toast.makeText(context, "动态自定义广播接收到", Toast.LENGTH_SHORT).show();
				Logs.v("动态自定义广播 >>>>>>>>>>>>>>>> ");
				
			}
			
		}
		
	};
	
	protected void onPause() {
		if(receiver != null)
			unregisterReceiver(receiver);
	};
}
