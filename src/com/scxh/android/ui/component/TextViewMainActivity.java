package com.scxh.android.ui.component;

import com.scxh.android.R;
import com.scxh.android.ui.layout.FrameLayoutActivity;
import com.scxh.android.ui.layout.TalbleLayoutActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
