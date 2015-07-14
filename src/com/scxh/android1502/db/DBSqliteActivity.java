package com.scxh.android1502.db;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class DBSqliteActivity extends Activity {
	public SQLiteDatabase db;
	private EditText mUserNameEdit;
	private Button mConfirmBtn,mUpdateBtn,mDeleteBtn,mFindBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_mydb_layout);
		mUserNameEdit = (EditText) findViewById(R.id.db_user_name_edit);
		mConfirmBtn = (Button) findViewById(R.id.db_ok_btn);
		mUpdateBtn = (Button) findViewById(R.id.db_update_btn);
		mDeleteBtn = (Button) findViewById(R.id.db_delete_btn);
		mFindBtn = (Button) findViewById(R.id.db_find_btn);

		DatabaseHelper dbHelper = DatabaseHelper.getInstanceDatabaseHelper(this);
		db = dbHelper.getReadableDatabase();

		mConfirmBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String userName = mUserNameEdit.getText().toString();

				ContentValues contentValues = new ContentValues();
				contentValues.put(DataColumn.Student._ID, 1002);
				contentValues.put(DataColumn.Student.COLUMN_NAME_NAME, "李四");
				contentValues.put(DataColumn.Student.COLUMN_NAME_NUMBER, "13");
				db.insert(DataColumn.Student.TABLE_NAME, null, contentValues);
				
				Toast.makeText(DBSqliteActivity.this, "插入数据成功",Toast.LENGTH_SHORT).show();
			}
		});
		
		
		mUpdateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put(DataColumn.Student.COLUMN_NAME_NAME, "张三");
				values.put(DataColumn.Student.COLUMN_NAME_NUMBER, "14");
				
				String whereClause = DataColumn.Student._ID+" = ?";
				String[] whereArgs = {"1001"};
				
				db.update(DataColumn.Student.TABLE_NAME, values, whereClause, whereArgs);
				
			}
		});

		
		mDeleteBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String whereClause = "name = ? or number=?";
				String[] whereArgs = {"张三","14"};
				db.delete(DataColumn.Student.TABLE_NAME, whereClause, whereArgs);
			}
		});
		
		mFindBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Cursor cursor = db.query("student", new String[]{"id","name","number"}, "id = ?", new String[]{"1001"}, null, null, null, null);
			
				Cursor cursor = db.query(DataColumn.Student.TABLE_NAME, null, null, null, null, null, null, null);
				
				Logs.v("记录条数 ："+cursor.getCount());
				
				while(cursor.moveToNext()){
					int id = cursor.getInt(cursor.getColumnIndex(DataColumn.Student._ID));
					String name = cursor.getString(cursor.getColumnIndex(DataColumn.Student.COLUMN_NAME_NAME));
					String number = cursor.getString(cursor.getColumnIndex(DataColumn.Student.COLUMN_NAME_NUMBER));
					
					Logs.v("id :"+id + " name :"+name+ " number :"+number);
				}
			}
		});
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
