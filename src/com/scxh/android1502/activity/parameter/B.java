package com.scxh.android1502.activity.parameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.util.ParamterUtil;

public class B extends Activity {
	private TextView mParamterTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_b_layout);
		
		mParamterTxt = (TextView) findViewById(R.id.paramter_txt);
		
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
}
