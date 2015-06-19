package com.scxh.android1502.activity.parameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;
import com.scxh.android1502.util.ParamterUtil;

public class B extends Activity {
	private TextView mParamterTxt;
	private Button mBackBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_b_layout);
		
		mParamterTxt = (TextView) findViewById(R.id.paramter_txt);
		mBackBtn = (Button) findViewById(R.id.b_back_btn);
		
		int type = getIntent().getIntExtra("TYPE",0);
		switch(type){
		case ParamterUtil.Paramter.INTENT:
			receiverParamterByIntent();
			break;
		case ParamterUtil.Paramter.BUNDLE:
			receiverParamterByBundle();
			break;
		case ParamterUtil.Paramter.Serializable:
			receiverParamterBySerializable();
			break;
		case ParamterUtil.Paramter.Parcelable:
			receiverParamterByParcelable();
			break;
		}
		
		mBackBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = getIntent();
				intent.putExtra("MESSAGE","我是来自B Activity的内容.");
				int resultCode = 1;
				setResult(resultCode, intent);
				
				finish();
			}
		});
	}
	
	public void receiverParamterByIntent(){
		Intent intent = getIntent();
		String userName = intent.getStringExtra("USER_NAME");
		int age = intent.getIntExtra("AGE", 0);
		String sex = intent.getStringExtra("SEX");
		
		mParamterTxt.setText("我的名字是:"+userName+" 年龄是:"+age + " 性别是 "+sex);
	}
	
	public void receiverParamterByBundle(){
		Bundle bundle = getIntent().getBundleExtra("USER");
		
		String userName = bundle.getString("USER_NAME");
		int age = bundle.getInt("AGE");
		String sex = bundle.getString("SEX");
		
		mParamterTxt.setText("我的名字是:"+userName+" 年龄是:"+age + " 性别是 "+sex);
	}
	
	public void receiverParamterBySerializable(){
		Intent intent = getIntent();
		User user = (User) intent.getSerializableExtra("USER");
		
		String userName = user.getUserName();
		int age = user.getAge();
		String sex = user.getSex();
		
		mParamterTxt.setText("我的名字是:"+userName+" 年龄是:"+age + " 性别是 "+sex);
	}
	
	public void receiverParamterByParcelable(){
		Intent intent = getIntent();
		UserParce user = intent.getParcelableExtra("USER");
		
		String userName = user.getUserName();
		int age = user.getAge();
		String sex = user.getSex();
		
		mParamterTxt.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
		mParamterTxt.setText("我的名字是:"+userName+" 年龄是:"+age + " 性别是 "+sex);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Logs.v("B  onStop >>>>>>>>>>>");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Logs.v("B  onDestroy >>>>>>>>>>>");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Intent intent = getIntent();
			intent.putExtra("MESSAGE","我是来自B Activity的内容.");
			int resultCode = 1;
			setResult(resultCode, intent);
			
			finish();
		}
		
		return true;
	}
}
