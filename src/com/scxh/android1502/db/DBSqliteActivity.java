package com.scxh.android1502.db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class DBSqliteActivity extends Activity {
	public SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DatabaseHelper dbHelper = DatabaseHelper.getInstanceDatabaseHelper(this);
		db = dbHelper.getReadableDatabase();
		
		
		
		Toast.makeText(this, "执行结束", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (db != null) {
			if (db.isOpen()) {
				db.close();
				db = null;
			}
		}
	}

	/**
	 * 数据库操作方法一
	 */
	public void operOrCreateDataBase() {
		SQLiteDatabase db = openOrCreateDatabase("scxh1502.db", MODE_PRIVATE,
				null);

		db.execSQL("create table student (id INTEGER NOT NULL,name TEXT,PRIMARY KEY(id))");

		db.execSQL("insert into student (id,name) values (1001,'张三')");

	}
}
