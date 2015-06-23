package com.scxh.android1502;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scxh.android1502.activity.LifeActivity;
import com.scxh.android1502.activity.OneActivity;
import com.scxh.android1502.activity.StateActvity;
import com.scxh.android1502.activity.UtilActivity;
import com.scxh.android1502.activity.launchmode.FirstActivity;
import com.scxh.android1502.activity.parameter.A;
import com.scxh.android1502.ui.InfalterAcitivty;
import com.scxh.android1502.ui.LoginActivity;
import com.scxh.android1502.ui.LoginTwoActivity;
import com.scxh.android1502.ui.component.EditTextActivity;
import com.scxh.android1502.ui.component.ImageViewActivity;
import com.scxh.android1502.ui.component.ImageViewTwoActivity;
import com.scxh.android1502.ui.component.RadioButtonActivity;
import com.scxh.android1502.ui.component.TextViewMainActivity;
import com.scxh.android1502.ui.layout.CodeLayoutAcitivity;
import com.scxh.android1502.ui.listview.ArrayListsActivity;
import com.scxh.android1502.ui.listview.SimpleListActivity;
import com.scxh.android1502.util.Logs;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ScxhApplication scxhAPP = (ScxhApplication) getApplication();
		Logs.e("MainActivity >>>>>>>>>>>>  :"+scxhAPP.mName);
	}

	/**
	 * InflaterLayout学习
	 * 
	 * @param v
	 */
	public void onInfalterClickView(View v) {
		Intent intent = new Intent(this, InfalterAcitivty.class);
		startActivity(intent);
	}

	/**
	 * 代码布局
	 * 
	 * @param v
	 */
	public void onCodeClickView(View v) {
		Intent intent = new Intent(this, CodeLayoutAcitivity.class);
		startActivity(intent);
	}

	/**
	 * 文本框控件学习
	 * 
	 * @param v
	 */
	public void onTextViewClickView(View v) {
		Intent intent = new Intent(this, TextViewMainActivity.class);
		startActivity(intent);
	}

	/**
	 * 输入框控件学习
	 * 
	 * @param v
	 */
	public void onEditTextClickView(View v) {
		Intent intent = new Intent(this, EditTextActivity.class);
		startActivity(intent);
	}

	/**
	 * 登录实例，布局综合练习
	 * 
	 * @param v
	 */
	public void onLoginClickView(View v) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	public void onLoginTwoClickView(View v) {
		Intent intent = new Intent(this, LoginTwoActivity.class);
		startActivity(intent);
	}

	public void onImageViewClickView(View v) {
		Intent intent = new Intent(this, ImageViewActivity.class);
		startActivity(intent);
	}

	public void onImageViewTwoClickView(View v) {
		Intent intent = new Intent(this, ImageViewTwoActivity.class);
		startActivity(intent);
	}

	public void onRadioButtonClickView(View v) {
		Intent intent = new Intent(this, RadioButtonActivity.class);
		startActivity(intent);
	}

	public void onLifeClickView(View v) {
		Intent intent = new Intent(this, LifeActivity.class);
		startActivity(intent);
	}
	public void onUtilClickView(View v) {
		Intent intent = new Intent(this, UtilActivity.class);
		startActivity(intent);
	}
	
	public void onStartActivtyClickView(View v){
		Intent intent = new Intent(this, OneActivity.class);
		startActivity(intent);
		
	}
	public void onstateClickView(View v){
		Intent intent = new Intent(this, StateActvity.class);
		startActivity(intent);
	}
	public void onLaunchModelClickView(View v){
		Intent intent = new Intent(this, FirstActivity.class);
		startActivity(intent);
	}
	public void onParamterClickView(View v){
		Intent intent = new Intent(this, A.class);
		startActivity(intent);
	}
	public void onIntentClickView(View v){
		Intent intent = new Intent(this, com.scxh.android1502.activity.intent.OneActivity.class);
		startActivity(intent);
	}
	public void onListViewClickView(View v){
		Intent intent = new Intent(this, ArrayListsActivity.class);
		startActivity(intent);
	}
	public void onSimpleListClickView(View v){
		Intent intent = new Intent(this, SimpleListActivity.class);
		startActivity(intent);
	}
}
