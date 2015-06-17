package com.scxh.android1502.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.scxh.android1502.R;

public class OneActivity extends Activity {
	private Button mStartTwoBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_one_layout);
		
		mStartTwoBtn = (Button) findViewById(R.id.start_activity_btn);
	
		mStartTwoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(OneActivity.this,TwoActivity.class);
				startActivity(intent);
//				startActivityForResult(intent, 1);
				finish();  //销毁一个Actvity
			}
		});
	}
	

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("OneActivity   onStart >>>>>>>>>");
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("OneActivity   onResume >>>>>>>>>");
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("OneActivity   onPause >>>>>>>>>");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("OneActivity   onStop >>>>>>>>>");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("OneActivity   onDestroy >>>>>>>>>");
	}
}
