package com.scxh.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scxh.android.activity.LifeActivity;
import com.scxh.android.activity.UtilActivity;
import com.scxh.android.ui.InfalterAcitivty;
import com.scxh.android.ui.LoginActivity;
import com.scxh.android.ui.LoginTwoActivity;
import com.scxh.android.ui.component.EditTextActivity;
import com.scxh.android.ui.component.ImageViewActivity;
import com.scxh.android.ui.component.ImageViewTwoActivity;
import com.scxh.android.ui.component.RadioButtonActivity;
import com.scxh.android.ui.component.TextViewMainActivity;
import com.scxh.android.ui.layout.CodeLayoutAcitivity;
import com.scxh.android.ui.layout.FrameLayoutActivity;
import com.scxh.android.ui.layout.GridLayoutActivity;
import com.scxh.android.ui.layout.LinerLayoutAcitiy;
import com.scxh.android.ui.layout.RelativeLayoutActivity;
import com.scxh.android.ui.layout.TalbleLayoutActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * 线型布局学习
	 * 
	 * @param v
	 */
	public void onLinerLayoutClickView(View v) {
		Intent intent = new Intent(this, LinerLayoutAcitiy.class);
		startActivity(intent);
	}

	/**
	 * 相对布局学习
	 * 
	 * @param v
	 */
	public void onRelativeLayoutClickView(View v) {
		Intent intent = new Intent(this, RelativeLayoutActivity.class);
		startActivity(intent);
	}

	/**
	 * 单帧布局学习
	 * 
	 * @param v
	 */
	public void onFrameLayoutClickView(View v) {
		Intent intent = new Intent(this, FrameLayoutActivity.class);
		startActivity(intent);
	}

	/**
	 * 表格布局
	 * 
	 * @param v
	 */
	public void onTableLayoutClickView(View v) {
		Intent intent = new Intent(this, TalbleLayoutActivity.class);
		startActivity(intent);
	}

	/**
	 * 网络布局
	 * 
	 * @param v
	 */
	public void onGridLayoutClickView(View v) {
		Intent intent = new Intent(this, GridLayoutActivity.class);
		startActivity(intent);
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
}
