package com.scxh.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.scxh.android.R;

public class LoginActivity extends Activity implements OnClickListener {
	private Button mLoginBtn;
	private EditText mUserNameEdit, mPassWordEdit;
	private ImageView mClearUserNameImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_layout);

		mLoginBtn = (Button) findViewById(R.id.loginone_btn);
		mUserNameEdit = (EditText) findViewById(R.id.user_name_one_edit);
		mPassWordEdit = (EditText) findViewById(R.id.password_one_edit);
		mClearUserNameImg = (ImageView) findViewById(R.id.clear_img);
		
		
		mLoginBtn.setOnClickListener(this);

		mClearUserNameImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mUserNameEdit.setText("");
				
			}
		});
		
		mUserNameEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				if(s.length() > 0){
					mClearUserNameImg.setVisibility(View.VISIBLE);
				}else{
					mClearUserNameImg.setVisibility(View.INVISIBLE);
				}
				
				System.out.println("onTextChanged  s :"+s+ " start :"+start+" before :"+before+ " count :"+count);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
				System.out.println("beforeTextChanged  s :"+s+ " start :"+start+ " count :"+count + " after :"+after);
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				System.out.println("afterTextChanged  s :"+s);
				
			}
		});
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
