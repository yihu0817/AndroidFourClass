package com.scxh.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.scxh.android.R;

public class LoginActivity extends Activity implements OnClickListener {
	private Button mLoginBtn;
	private EditText mUserNameEdit, mPassWordEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_layout);

		mLoginBtn = (Button) findViewById(R.id.loginone_btn);
		mUserNameEdit = (EditText) findViewById(R.id.user_name_edit);
		mPassWordEdit = (EditText) findViewById(R.id.password_edit);

		mLoginBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			System.out.println(">>>>>>>登录>>>>>>>>>>>>>>>>>>>>");
			break;

		}

	}
	
}
