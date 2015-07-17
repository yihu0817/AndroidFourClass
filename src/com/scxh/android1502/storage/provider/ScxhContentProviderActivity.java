package com.scxh.android1502.storage.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.scxh.android1502.R;
import com.scxh.android1502.storage.db.DataColumn;

public class ScxhContentProviderActivity extends Activity implements
		OnClickListener {
	private Button mInsertBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scxhprovider_layout);

		mInsertBtn = (Button) findViewById(R.id.provider_insert_btn);
		mInsertBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.provider_insert_btn:

			ContentValues values = new ContentValues();
			values.put(DataColumn.User.COLUMN_NAME_USER_NAME, "张三");
			values.put(DataColumn.User.COLUMN_NAME_PASSWORD, "123456");
			
			getContentResolver().insert(ScxhContentProvider.CONTENT_URI, values);
			
			break;
		}

	}
}
