package com.scxh.android1502.db;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.Student;
import com.scxh.android1502.util.Logs;

public class DBSqliteActivity extends Activity {
	public SQLiteDatabase db;
	private EditText mUserNameEdit;
	private Button mConfirmBtn, mUpdateBtn, mDeleteBtn, mFindBtn,mFindCursorBtn;
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_mydb_layout);
		mUserNameEdit = (EditText) findViewById(R.id.db_user_name_edit);
		mConfirmBtn = (Button) findViewById(R.id.db_ok_btn);
		mUpdateBtn = (Button) findViewById(R.id.db_update_btn);
		mDeleteBtn = (Button) findViewById(R.id.db_delete_btn);
		mFindBtn = (Button) findViewById(R.id.db_find_btn);
		mFindCursorBtn = (Button) findViewById(R.id.db_find_cursor_btn);
		mListView = (ListView) findViewById(R.id.db1_listview);

		DatabaseHelper dbHelper = DatabaseHelper.getInstanceDatabaseHelper(this);
		db = dbHelper.getReadableDatabase();  //如果数据库不存在则创建数据库，否则直接打开数据库

		mConfirmBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String userName = mUserNameEdit.getText().toString();

				ContentValues contentValues = new ContentValues();
//				contentValues.put(DataColumn.Student._ID, 1002);
				contentValues.put(DataColumn.Student.COLUMN_NAME_NAME, "李"+userName);
				contentValues.put(DataColumn.Student.COLUMN_NAME_NUMBER, "13"+userName);
				db.insert(DataColumn.Student.TABLE_NAME, null, contentValues);

				Toast.makeText(DBSqliteActivity.this, "插入数据成功",
						Toast.LENGTH_SHORT).show();
			}
		});

		mUpdateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put(DataColumn.Student.COLUMN_NAME_NAME, "张三");
				values.put(DataColumn.Student.COLUMN_NAME_NUMBER, "14");

				String whereClause = DataColumn.Student._ID + " = ?";
				String[] whereArgs = { "1001" };

				db.update(DataColumn.Student.TABLE_NAME, values, whereClause,
						whereArgs);

			}
		});

		mDeleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String whereClause = DataColumn.Student.COLUMN_NAME_NAME
						+ " = ? or " + DataColumn.Student.COLUMN_NAME_NUMBER
						+ " = ?";
				String[] whereArgs = { "张三", "14" };
				db.delete(DataColumn.Student.TABLE_NAME, whereClause, whereArgs);
			}
		});

		mFindBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Cursor cursor = db.query("student", new
				// String[]{"id","name","number"}, "id = ?", new
				// String[]{"1001"}, null, null, null, null);

				Cursor cursor = db.query(DataColumn.Student.TABLE_NAME, null,
						null, null, null, null, null, null);

				Logs.v("记录条数 ：" + cursor.getCount());

				ArrayList<Student> list = new ArrayList<Student>();
				while (cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex(DataColumn.Student._ID));
					String name = cursor.getString(cursor.getColumnIndex(DataColumn.Student.COLUMN_NAME_NAME));
					String number = cursor.getString(cursor.getColumnIndex(DataColumn.Student.COLUMN_NAME_NUMBER));

					Logs.v("id :" + id + " name :" + name + " number :"+ number);
					
					Student student = new Student();
					student.setId(id);
					student.setName(name);
					student.setNumber(number);
					
					list.add(student);
				}
				
				Intent intent = new Intent(DBSqliteActivity.this,DBListActivity.class);
				intent.putParcelableArrayListExtra("DB_LIST", list);
				
				
				startActivity(intent);
			}
		});
		
		
		
		mFindCursorBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Cursor cursor = db.query(DataColumn.Student.TABLE_NAME, null,null, null, null, null, null, null);
				
				MyCursorAdapter adapter = new MyCursorAdapter(DBSqliteActivity.this,cursor);
				mListView.setAdapter(adapter);
			}
		});
	}

	public class MyCursorAdapter extends CursorAdapter{
		private LayoutInflater mInflater;

		public MyCursorAdapter(Context context,Cursor cursor) {
			super(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER);
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			View view = mInflater.inflate(R.layout.view_db1_item_layout, null);
			return view;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView idTxt = (TextView) view.findViewById(R.id.db_id_txt);
			TextView nameTxt = (TextView) view.findViewById(R.id.db_username_txt);
			TextView numberTxt = (TextView) view.findViewById(R.id.db_number_txt);
			
			int id = cursor.getInt(cursor.getColumnIndex(DataColumn.Student._ID));
			String name = cursor.getString(cursor.getColumnIndex(DataColumn.Student.COLUMN_NAME_NAME));
			String number = cursor.getString(cursor.getColumnIndex(DataColumn.Student.COLUMN_NAME_NUMBER));
			
			idTxt.setText(""+id);
			nameTxt.setText(name);
			numberTxt.setText(number);
			
		}
		
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
