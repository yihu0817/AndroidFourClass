package com.scxh.android1502.storage.db;

import android.provider.BaseColumns;

public final class DataColumn {
	public static final class Student implements BaseColumns{
		public static final String TABLE_NAME = "student";  
		public static final String COLUMN_NAME_NAME = "name";  //姓名
		public static final String COLUMN_NAME_NUMBER = "number"; //学号
		public static final String COLUMN_NAME_AGE = "age";  //年龄
		public static final String COLUMN_NAME_SCORE = "score";  //成绩
	}
	public static final class Tearch implements BaseColumns{
		public static final String TABLE_NAME = "tearch";
		public static final String COLUMN_NAME_NAME = "name";
	}
	
	public static final class User implements BaseColumns{
		public static final String TABLE_NAME = "user";
		public static final String COLUMN_NAME_USER_NAME = "username";
		public static final String COLUMN_NAME_PASSWORD = "password";
		
		
	}
	
}
