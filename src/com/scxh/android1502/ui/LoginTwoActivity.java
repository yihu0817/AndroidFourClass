package com.scxh.android1502.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.scxh.android1502.R;

public class LoginTwoActivity extends Activity {
	private Button mLoginBtn, mRegistBtn;
	private EditText mUserNameEdit, mPassWordEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logintwo_layout);

		mLoginBtn = (Button) findViewById(R.id.login_btn);
		mRegistBtn = (Button) findViewById(R.id.regist_btn);
		mUserNameEdit = (EditText) findViewById(R.id.user_name_edit);
		mPassWordEdit = (EditText) findViewById(R.id.password_edit);

		mLoginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		});

		mRegistBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println(">>>>>>>>>>>注册>>>>>>>>>>>>>>>>");

			}
		});

	}

}
