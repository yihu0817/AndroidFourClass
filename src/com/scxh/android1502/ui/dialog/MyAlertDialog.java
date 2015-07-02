package com.scxh.android1502.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scxh.android1502.R;

public class MyAlertDialog extends AlertDialog {
	private Button mOkBtn, mCancelBtn;
	private Context mContext;

	protected MyAlertDialog(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.view_my_dialog_layout);
		mOkBtn = (Button) findViewById(R.id.ok_btn);
		mCancelBtn = (Button) findViewById(R.id.cancel_btn);

		mOkBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "确定成功>>>", Toast.LENGTH_SHORT).show();
				dismiss();
			}
		});

		mCancelBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "取消成功>>>", Toast.LENGTH_SHORT).show();
				dismiss();
			}
		});

	}
}
