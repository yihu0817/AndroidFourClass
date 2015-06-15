package com.scxh.android.ui.component;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.scxh.android.R;

public class TextViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Resources res = getResources();
		
		String msg = res.getString(R.string.user_name_txt);

		System.out.println("msg >>> :"+msg);
		
		TextView userNameTxt = new TextView(this);
		userNameTxt.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		userNameTxt.setText(msg);
		
		setContentView(userNameTxt);
		
	}
}
