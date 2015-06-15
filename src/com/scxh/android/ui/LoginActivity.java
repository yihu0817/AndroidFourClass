package com.scxh.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.scxh.android.R;

public class LoginActivity extends Activity implements OnClickListener {
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

		mLoginBtn.setOnClickListener(this);
		mRegistBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			System.out.println(">>>>>>>登录>>>>>>>>>>>>>>>>>>>>");
			break;
		case R.id.regist_btn:
			System.out.println(">>>>>>>注册>>>>>>>>>>>>>>>>>>>>");
			break;

		}

	}
	
	public void onCancelClickView(View v){
		System.out.println(">>>>>>>取消>>>>>>>>>>>>>>>>>>>>");
	}
	
}
