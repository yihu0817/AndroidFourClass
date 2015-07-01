package com.scxh.android1502.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.scxh.android1502.R;

public class DialogActivity extends Activity {
	private static final int ALERT_PROGRESS_DIALOG = 1;
	private static final int ALERT_DATEPICKER_DIALOG = 2;
	private static final int ALERT_OK_DIALOG = 3;
	private static final int ALERT_CHECK_DIALOG = 4;
	private static final int ALERT_MY_DIALOG = 5;
	private Button mDialogBtn, mDateDialogBtn, mOkDialogBtn, mCheckDialogBtn;
	private DatePickerDialog mDateDialog;
	private ProgressDialog mProgressDialog;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(DialogActivity.this, "下载完成", Toast.LENGTH_SHORT)
					.show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog1_layout);

		mDialogBtn = (Button) findViewById(R.id.dialog_btn);
		mDateDialogBtn = (Button) findViewById(R.id.date_dialog_btn);
		mOkDialogBtn = (Button) findViewById(R.id.ok_dialog_btn);
		mCheckDialogBtn = (Button) findViewById(R.id.check_dialog_btn);

		mDialogBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(ALERT_PROGRESS_DIALOG);

				new Thread(new Runnable() {

					@Override
					public void run() {

						for (int i = 1; i <= 100; i++) {
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							mProgressDialog.setProgress(i);
						}
						dismissDialog(ALERT_PROGRESS_DIALOG);
						mHandler.sendEmptyMessage(0);

					}
				}).start();
			}
		});

		mDateDialogBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(ALERT_DATEPICKER_DIALOG);
			}
		});

		mOkDialogBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(ALERT_OK_DIALOG);
			}
		});

		mCheckDialogBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(ALERT_CHECK_DIALOG);

			}
		});
	}

	public void onMyDialogViewClick(View v){
		showDialog(ALERT_MY_DIALOG);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ALERT_PROGRESS_DIALOG:
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("信息下载加载中....");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

			return mProgressDialog;
		case ALERT_DATEPICKER_DIALOG:
			mDateDialog = new DatePickerDialog(this, dateSetListener, 2015, 7,
					1);
			return mDateDialog;

		case ALERT_OK_DIALOG:
			Builder builder = new Builder(this);
			builder.setTitle("提示框");
			builder.setMessage("您确定退出吗?");
			builder.setIcon(R.drawable.ic_launcher);

			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogActivity.this, "确定成功",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogActivity.this, "取消成功",
									Toast.LENGTH_SHORT).show();
						}
					});

			builder.setNeutralButton("继续",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogActivity.this, "取消成功",
									Toast.LENGTH_SHORT).show();
						}
					});

			AlertDialog dialog = builder.create();
			return dialog;

		case ALERT_CHECK_DIALOG:
			String[] items = { "看书", "写代码", "打游戏", "看电影" };
			boolean[] checkedItems = { true, false, false, false };

			builder = new Builder(this);
			builder.setTitle("提示框");
			builder.setMultiChoiceItems(items, checkedItems,
					new OnMultiChoiceClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which,
								boolean isChecked) {

							Toast.makeText(
									DialogActivity.this,
									"which " + which + " isChecked :"
											+ isChecked, Toast.LENGTH_SHORT)
									.show();
						}
					});
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogActivity.this, "取消成功",
									Toast.LENGTH_SHORT).show();
						}
					});
			return builder.create();
			
		case ALERT_MY_DIALOG:
			
			View view = LayoutInflater.from(DialogActivity.this).inflate(R.layout.view_my_dialog_layout, null);
			builder = new Builder(this);
			builder.setView(view);
			return builder.create();
			
		default:
			return null;
		}

	}

	private OnDateSetListener dateSetListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			Toast.makeText(
					DialogActivity.this,
					"你选择的日期是 " + year + "年 " + monthOfYear + " 月 " + dayOfMonth
							+ " 日", Toast.LENGTH_SHORT).show();
		}
	};
}
