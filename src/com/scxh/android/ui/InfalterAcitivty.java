package com.scxh.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.scxh.android.R;

public class InfalterAcitivty extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView tx = new TextView(this);
		
//		FrameLayout layout = (FrameLayout)findViewById(android.R.id.content);
//		layout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
		
		
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		
		View view = layoutInflater.inflate(R.layout.button_layout, null);
		
		setContentView(view);
	}
}
