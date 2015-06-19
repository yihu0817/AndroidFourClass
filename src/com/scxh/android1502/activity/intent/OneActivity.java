package com.scxh.android1502.activity.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.scxh.android1502.R;
import com.scxh.android1502.ui.LoginActivity;

public class OneActivity extends Activity {
	private Button mDerictBtn,mHintBtn,mTelBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_one_intent_layout);
		
		mDerictBtn = (Button) findViewById(R.id.dricteStartBtn);
		mHintBtn = (Button) findViewById(R.id.hintStartBtn);
		mTelBtn = (Button) findViewById(R.id.telStartBtn);
		
		mDerictBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				Intent intent = new Intent(OneActivity.this, LoginActivity.class);
				
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(OneActivity.this, LoginActivity.class));
			
				startActivity(intent);
			}
		});
		
		mHintBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.scxh.android.intent.action.View");
				
				startActivity(intent);
			}
		});
		
		mTelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:18015645745");
				
				Intent it = new Intent(); 
				it.setAction("android.intent.action.DIAL");
				it.setData(uri);
				
				startActivity(it);

			}
		});
		
	}
}
