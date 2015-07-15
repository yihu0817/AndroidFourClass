package com.scxh.android1502.preference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.scxh.android1502.R;

public class SharePreferenceActivity extends Activity {
	private EditText mUserNameEdit;
	private Button mConfirmBtn;
	private static final String PREFERENCE_NAME = "com.scxh.android1502_PREFERENCE_USER_FILE_KEY";
	private SharedPreferences mPreference;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_preference1_layout);

		mUserNameEdit = (EditText) findViewById(R.id.preference_name_edit);
		mConfirmBtn = (Button) findViewById(R.id.preference_comfirm_btn);

		//获取SharedPreferences对象
		mPreference = getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
		
		
		mConfirmBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//写数据到sharepreference
				SharedPreferences.Editor editor = mPreference.edit();
				editor.putString("USER_NAME", mUserNameEdit.getText().toString());
				editor.commit();
			}
		});
		
		//从sharepreference读取数据
		String value = mPreference.getString("USER_NAME", "");
		if(!value.equals("")){
			mUserNameEdit.setText(value);
		}
	}
}
