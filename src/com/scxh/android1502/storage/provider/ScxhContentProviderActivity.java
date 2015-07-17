package com.scxh.android1502.storage.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.storage.db.DataColumn;
import com.scxh.android1502.util.Logs;

public class ScxhContentProviderActivity extends Activity implements
		OnClickListener {
	private Button mInsertBtn,mQueryBtn,mPreferenceInserBtn,mPreferenceQueryBtn;
	private TextView mMessageTxt;
	private EditText mUserNameEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scxhprovider_layout);

		mInsertBtn = (Button) findViewById(R.id.provider_insert_btn);
		mInsertBtn.setOnClickListener(this);
		
		mPreferenceInserBtn = (Button) findViewById(R.id.provider_preference_insert_btn);
		mPreferenceInserBtn.setOnClickListener(this);
		
		mPreferenceQueryBtn = (Button) findViewById(R.id.provider_preference_query_btn);
		mPreferenceQueryBtn.setOnClickListener(this);
		
		mQueryBtn = (Button) findViewById(R.id.provider_query_btn);
		mQueryBtn.setOnClickListener(this);
		
		mMessageTxt = (TextView) findViewById(R.id.provider_message_text);
		
		mUserNameEdit = (EditText) findViewById(R.id.provider_message_edit);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.provider_insert_btn:

			String name = mUserNameEdit.getText().toString();
			
			ContentValues values = new ContentValues();
			values.put(DataColumn.User.COLUMN_NAME_USER_NAME, "张"+name);
			values.put(DataColumn.User.COLUMN_NAME_PASSWORD, "123456");
			
			getContentResolver().insert(ScxhContentProvider.CONTENT_URI, values);
			Toast.makeText(this, "插入数据成功!", Toast.LENGTH_SHORT).show();
			break;
		case R.id.provider_preference_insert_btn:
			
			name = mUserNameEdit.getText().toString();
			values = new ContentValues();
			values.put(DataColumn.User.COLUMN_NAME_USER_NAME, "李"+name);
			values.put(DataColumn.User.COLUMN_NAME_PASSWORD, "654321");
			
			getContentResolver().insert(PreferenceContentProvider.CONTENT_URI, values);
			Toast.makeText(this, "插入数据成功!", Toast.LENGTH_SHORT).show();
			break;
		case R.id.provider_preference_query_btn:
			Cursor cursor = getContentResolver().query(PreferenceContentProvider.CONTENT_URI, null, null, null, null);
			StringBuilder sb = new StringBuilder("来自SharePreferences  :");
			
			while(cursor.moveToNext()){
				String userName = cursor.getString(cursor.getColumnIndex(DataColumn.User.COLUMN_NAME_USER_NAME));
				String passWord = cursor.getString(cursor.getColumnIndex(DataColumn.User.COLUMN_NAME_PASSWORD));
				
				Logs.v("userName :"+userName+ " passWord :"+passWord);
				
				sb.append("用户名 :");
				sb.append(userName);
				sb.append("\n密码 :");
				sb.append(passWord);
				sb.append("\n");
			}
			
			mMessageTxt.setText(sb.toString());
			
			break;
			
		case R.id.provider_query_btn:
			sb = new StringBuilder("来自SQLite  :");
			cursor = getContentResolver().query(ScxhContentProvider.CONTENT_URI, null, null, null, null);
			while(cursor.moveToNext()){
				String userName = cursor.getString(cursor.getColumnIndex(DataColumn.User.COLUMN_NAME_USER_NAME));
				String passWord = cursor.getString(cursor.getColumnIndex(DataColumn.User.COLUMN_NAME_PASSWORD));
				
				Logs.v("userName :"+userName+ " passWord :"+passWord);
				
				sb.append("用户名 :");
				sb.append(userName);
				sb.append("\n密码 :");
				sb.append(passWord);
				sb.append("\n");
			}
			
			mMessageTxt.setText(sb.toString());
			
			break;
		}

	}
}
