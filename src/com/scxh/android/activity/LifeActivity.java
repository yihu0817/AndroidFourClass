package com.scxh.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class LifeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("LifeActivity   onCreate >>>>>>>>>");

		TextView textView = new TextView(this);
		textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		textView.setGravity(Gravity.CENTER);
		textView.setText("Activity生命周学习");
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

		setContentView(textView);

	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("LifeActivity   onStart >>>>>>>>>");
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("LifeActivity   onResume >>>>>>>>>");
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("LifeActivity   onPause >>>>>>>>>");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("LifeActivity   onStop >>>>>>>>>");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("LifeActivity   onDestroy >>>>>>>>>");
	}

}
