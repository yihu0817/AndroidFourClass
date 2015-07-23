package com.scxh.android1502.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

import com.scxh.android1502.R;
import com.scxh.android1502.storage.file.browse.FileExplorerActivity;
import com.scxh.android1502.ui.component.RadioButtonActivity;
import com.scxh.android1502.ui.listview.MyBaseActivity;
import com.scxh.android1502.ui.tab.RadioTabActivity;

public class MyNotificationActivity extends Activity implements OnClickListener {
	private Button mSendNotificationBtn, mUpdateNotificationBtn,
			mCancelNotificationBtn, mProgressDeterminateBtn,
			mProgressNoDeterminateBtn, mMyNotificationBtn, mBigStyleBtn;

	private NotificationManager manger;
	int notificationId = 11;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mynotification_layout);
		mSendNotificationBtn = (Button) findViewById(R.id.notification_send_msg_btn);
		mSendNotificationBtn.setOnClickListener(this);
		mUpdateNotificationBtn = (Button) findViewById(R.id.notification_update_msg_btn);
		mUpdateNotificationBtn.setOnClickListener(this);
		mCancelNotificationBtn = (Button) findViewById(R.id.notification_cancel_msg_btn);
		mCancelNotificationBtn.setOnClickListener(this);
		mProgressDeterminateBtn = (Button) findViewById(R.id.notification_updateprogress_btn);
		mProgressDeterminateBtn.setOnClickListener(this);
		mProgressNoDeterminateBtn = (Button) findViewById(R.id.notification_update_no_progress_btn);
		mProgressNoDeterminateBtn.setOnClickListener(this);
		mMyNotificationBtn = (Button) findViewById(R.id.notification_my_remoteview_btn);
		mMyNotificationBtn.setOnClickListener(this);
		mBigStyleBtn = (Button) findViewById(R.id.notification_bigstyle_btn);
		mBigStyleBtn.setOnClickListener(this);

		manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.notification_send_msg_btn:
			NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
			builder.setSmallIcon(R.drawable.m4);
			builder.setContentTitle("通知标题");
			builder.setContentText("1条新消息");
			builder.setTicker("收到1条新消息");
			builder.setNumber(1);
			builder.setDefaults(Notification.DEFAULT_ALL);// requires VIBRATE permission
			builder.setWhen(System.currentTimeMillis());
//			builder.setSound(uri);
			
			Intent intent = new Intent(this, MyBaseActivity.class);
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
			stackBuilder.addParentStack(MyBaseActivity.class);
			// Adds the Intent to the top of the stack
			stackBuilder.addNextIntent(intent);
			// Gets a PendingIntent containing the entire back stack
			PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
					PendingIntent.FLAG_UPDATE_CURRENT);

			// PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
			// intent, PendingIntent.FLAG_UPDATE_CURRENT);

			builder.setContentIntent(pendingIntent);
			builder.setAutoCancel(true); // 点击取消通知

			manger.notify(notificationId, builder.build());

			break;
		case R.id.notification_update_msg_btn:
			builder = new NotificationCompat.Builder(this);
			builder.setSmallIcon(R.drawable.m3);
			builder.setContentTitle("上课通知");
			builder.setContentText("2条消息");
			builder.setTicker("收到2条新消息");
			builder.setNumber(2);
			builder.setWhen(System.currentTimeMillis());

			intent = new Intent(this, FileExplorerActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TASK);

			pendingIntent = PendingIntent.getActivity(this, 0, intent,
					PendingIntent.FLAG_UPDATE_CURRENT);

			builder.setContentIntent(pendingIntent);

			manger.notify(notificationId, builder.build());
			break;
		case R.id.notification_cancel_msg_btn:

			// manger.cancel(notificationId);
			manger.cancelAll();
			break;
		case R.id.notification_updateprogress_btn:

			final int notificationId = 13;
			final NotificationCompat.Builder builders = new NotificationCompat.Builder(
					this);
			builders.setSmallIcon(R.drawable.m8);
			builders.setContentTitle("图片操作");
			builders.setContentText("图片下载中...");
			builders.setTicker("图片下载");

			new Thread(new Runnable() {

				@Override
				public void run() {

					for (int progress = 0; progress < 100; progress++) {

						builders.setProgress(100, progress, false); // 更新通知进度条值

						mHandler.post(new Runnable() {

							@Override
							public void run() {
								manger.notify(notificationId, builders.build());// 更新通知
							}
						});

						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					mHandler.post(new Runnable() {

						@Override
						public void run() {
							builders.setProgress(0, 0, false); // 移除通知进度条
							builders.setContentText("图片下载完成");
							builders.setTicker("图片下载完成");
							manger.notify(notificationId, builders.build());// 更新通知
						}
					});

				}
			}).start();

			break;

		case R.id.notification_update_no_progress_btn:
			int notificationIds = 15;
			NotificationCompat.Builder buildersno = new NotificationCompat.Builder(
					this);
			buildersno.setSmallIcon(R.drawable.m8);
			buildersno.setContentTitle("图片操作");
			buildersno.setContentText("图片下载中...");
			buildersno.setTicker("图片下载");

			buildersno.setProgress(0, 0, true);

			manger.notify(notificationIds, buildersno.build());// 更新通知
			break;
		case R.id.notification_my_remoteview_btn:

			createCustomNotifiaction();
			break;
		case R.id.notification_bigstyle_btn:
			createNotifcationStyle();
			break;
		}
	}

	public void createNotifcationStyle() {

		Intent snoozeIntent = new Intent(this, RadioButtonActivity.class);
		PendingIntent piSnooze = PendingIntent.getActivity(this, 0,snoozeIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

		// Constructs the Builder object.
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this)
				.setSmallIcon(R.drawable.m4)
				.setContentTitle("Bigstyle")
				.setContentText("BigStyleContent")
				.setDefaults(Notification.DEFAULT_ALL)
				// requires VIBRATE permission

				.setStyle(
						new NotificationCompat.BigTextStyle().bigText("构造big view"))
						
				.addAction(android.R.drawable.ic_input_get, "确定", piSnooze)
				.addAction(android.R.drawable.ic_delete, "取消", piSnooze);
		
		
		manger.notify(131, builder.build());// 更新通知
	}

	/**
	 * 创建一个自定义Notification
	 */
	public void createCustomNotifiaction() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setSmallIcon(R.drawable.m8);
		mBuilder.setTicker("自定义通知，你有新消息");
		mBuilder.setAutoCancel(true);

		// ------自定义notification界面
		RemoteViews view = new RemoteViews(getPackageName(),R.layout.view_notificaction_layout);
		mBuilder.setContent(view);

		// ----自定义notification事件处理
		Intent intent = new Intent(this, RadioTabActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 01,intent, PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.player_notification, pendingIntent);

		Notification notification = mBuilder.build();
		int notificationId = 29;

		NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mNotifyManager.notify(notificationId, notification);
	}

}
