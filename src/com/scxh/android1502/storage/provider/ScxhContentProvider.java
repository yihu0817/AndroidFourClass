package com.scxh.android1502.storage.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.scxh.android1502.storage.db.DataColumn;
/**
 * 1.定义一个类继承ContentProvider,选用一种数据存储方式实现接口方法(SQLite)
 * 2.声明URI,对应此ContentProvider.
 * 3.注册ContentProvider
 *
 */
public class ScxhContentProvider extends ContentProvider {
	public static final String AUTHORITY = "com.scxh.android1502.student";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    
    public static final String  DB_NAME = "scxh_provider.db";
    public static final int VERSION = 1;
	public SQLiteDatabase db;
	public class DataBaseHelper extends SQLiteOpenHelper{

		public DataBaseHelper(Context context) {
			super(context, DB_NAME, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			String sql = "create table "+ DataColumn.User.TABLE_NAME
					+" (" 
					+ DataColumn.User._ID+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ DataColumn.User.COLUMN_NAME_USER_NAME + " TEXT ," 
					+ DataColumn.User.COLUMN_NAME_PASSWORD + " TEXT " 
					+ ")";

			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
		
	}
	
	
	@Override
	public boolean onCreate() {
		DataBaseHelper dbHelper = new DataBaseHelper(getContext());
		db = dbHelper.getReadableDatabase(); // 如果数据库不存在则创建数据库，否则直接打开数据库

		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		return db.query(DataColumn.User.TABLE_NAME, projection, selection, selectionArgs, null,null, sortOrder);
		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		db.insert(DataColumn.User.TABLE_NAME, null, values);

		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

}
