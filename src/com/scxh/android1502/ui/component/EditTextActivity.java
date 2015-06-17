package com.scxh.android1502.ui.component;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.scxh.android1502.R;

public class EditTextActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_edittext_layout);

		EditText editText = (EditText) findViewById(R.id.input_edit);
		TextView inputTxt = (TextView) findViewById(R.id.input_txt);

		String inputStr = editText.getText().toString();

		System.out.println("输入内容是 :" + inputStr);

		inputTxt.setText(inputStr);
	}
}
