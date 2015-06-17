package com.scxh.android1502.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.scxh.android1502.R;

public class StateActvity extends Activity {
	private EditText mMessageEdit;
	private TextView mMessageTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_state_layout);
		
		mMessageEdit = (EditText) findViewById(R.id.email_message_edit);
		mMessageTxt = (TextView) findViewById(R.id.message_txt);
		System.out.println("StateActvity onCreate >>>>>> savedInstanceState  :"+savedInstanceState);
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("StateActvity onStart >>>>>>");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("StateActvity onResume >>>>>>");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		System.out.println("StateActvity onSaveInstanceState >>>>>>");
		outState.putString("MSG", mMessageEdit.getText().toString());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("StateActvity onRestoreInstanceState >>>>>>"+savedInstanceState);
		
		if(savedInstanceState != null){
			String message = savedInstanceState.getString("MSG");
			mMessageEdit.setText(message);
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("StateActvity   onPause >>>>>>>>>");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("StateActvity   onStop >>>>>>>>>");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("StateActvity   onDestroy >>>>>>>>>");
	}
}
