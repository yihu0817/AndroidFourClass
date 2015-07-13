package com.scxh.android1502.db;

import com.scxh.android1502.util.Logs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper是SQLiteDatabase的一个帮助类，用来管理数据库的创建和版本的更新。一般是建立一个类继承它，
 * 并实现它的onCreate和onUpgrade方法。
 * 
 * 
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "scxh1502.db";
	private static final int DB_VERSION = 1;

	private static DatabaseHelper DB_HELPER = null;

	public static DatabaseHelper getInstanceDatabaseHelper(Context context) {
		if (DB_HELPER == null) {
			DB_HELPER = new DatabaseHelper(context);
		}
		return DB_HELPER;
	}

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		Logs.v("DatabaseHelper  >>>>>>> ");
	}

	/**
	 * 数据库创建时执行
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Logs.v("onCreate  >>>>>>> ");

		String sql = "create table student (id INTEGER NOT NULL,name TEXT, number TEXT,PRIMARY KEY(id))";
		String sql1 = "create table tearch (id INTEGER NOT NULL,name TEXT,PRIMARY KEY(id))";

		db.execSQL(sql);
		db.execSQL(sql1);
	}

	/**
	 * 数据库版本发生变化
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Logs.v("onUpgrade >>>>>> oldVersion :" + oldVersion + " newVersion :"+ newVersion);
	}

}