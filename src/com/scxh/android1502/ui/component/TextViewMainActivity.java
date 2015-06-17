package com.scxh.android1502.ui.component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scxh.android1502.R;

public class TextViewMainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_textview_layout);
	}

	public void onTextViewCodeClickView(View v) {
		Intent intent = new Intent(this, TextViewActivity.class);
		startActivity(intent);
	}

	public void onTextViewLayoutClickView(View v) {
		Intent intent = new Intent(this, TextViewTwoAcitivity.class);
		startActivity(intent);
	}
}
