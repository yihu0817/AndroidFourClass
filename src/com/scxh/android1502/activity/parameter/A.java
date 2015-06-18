package com.scxh.android1502.activity.parameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.scxh.android1502.R;
import com.scxh.android1502.util.ParamterUtil;

public class A extends Activity {
	private Button mParamterBtn,mOneParamterBtn,mTwoParamterBtn,mThreeParamterBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a_layout);
		
		mParamterBtn = (Button) findViewById(R.id.parameter_btn);
		mOneParamterBtn = (Button) findViewById(R.id.parameter1_btn);
		mTwoParamterBtn = (Button) findViewById(R.id.parameter2_btn);
		mThreeParamterBtn = (Button) findViewById(R.id.parameter3_btn);
		
		mParamterBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendParamterByIntent();
			}
		});
		mOneParamterBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendParamterByBundle();
			}
		});
		mTwoParamterBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendParamterBySerializable();
			}
		});
		mThreeParamterBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendParameterByParcelable();
			}
		});
	}
	/**
	 * 通过Intent直接传参
	 */
	public void sendParamterByIntent(){
		Intent intent = new Intent(A.this,B.class);
		intent.putExtra("TYPE", ParamterUtil.Paramter.INTENT);
		intent.putExtra("USER_NAME", "张三");
		intent.putExtra("AGE", 19);
		intent.putExtra("sex", "男");
		
		startActivity(intent);
	}
	/**
	 * 通过Bundle对象传参
	 */
	public void sendParamterByBundle(){
		
		Bundle bundle = new Bundle();
		bundle.putString("USER_NAME", "张三");
		bundle.putInt("AGE", 19);
		bundle.putString("SEX", "男");
		
		Intent intent = new Intent(A.this,B.class);
		intent.putExtra("TYPE", ParamterUtil.Paramter.BUNDLE);
		intent.putExtra("USER", bundle);
		startActivity(intent);
	}
	/**
	 * 传递序列化对象
	 */
	public void sendParamterBySerializable(){
	
		User user = new User();
		user.setId(1001);
		user.setUserName("李四");
		user.setAge(18);
		user.setSex("女");
		
		Intent intent = new Intent(A.this,B.class);
		intent.putExtra("TYPE", ParamterUtil.Paramter.Serializable);
		intent.putExtra("USER", user);
		startActivity(intent);
	}
	/**
	 * 传递对象通过Parcelable
	 */
	public void sendParameterByParcelable(){
		UserParce user = new UserParce();
		user.setId(1002);
		user.setUserName("莉莉");
		user.setAge(18);
		user.setSex("女");
		
		Intent intent = new Intent(A.this,B.class);
		intent.putExtra("TYPE", ParamterUtil.Paramter.Parcelable);
		intent.putExtra("USER", user);
		startActivity(intent);
	}
}
