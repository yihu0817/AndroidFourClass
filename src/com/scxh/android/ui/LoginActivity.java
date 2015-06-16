package com.scxh.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scxh.android.R;

public class LoginActivity extends Activity implements OnClickListener {
	private Button mLoginBtn;
	private EditText mUserNameEdit, mPassWordEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_layout);

		mLoginBtn = (Button) findViewById(R.id.loginone_btn);
		mUserNameEdit = (EditText) findViewById(R.id.user_name_one_edit);
		mPassWordEdit = (EditText) findViewById(R.id.password_one_edit);

		mLoginBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginone_btn:
			String userName = mUserNameEdit.getText().toString();
			if(userName.equals("")){
//				Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
				
				mUserNameEdit.setError("用户名不能为空!");
			}
			break;
		default:
			Toast.makeText(this, "没有找到对应控件!", Toast.LENGTH_SHORT).show();
		}

	}
	
}
