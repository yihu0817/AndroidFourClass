package com.scxh.android1502.db;

import android.provider.BaseColumns;

public final class DataColumn {
	public static final class Student implements BaseColumns{
		public static final String TABLE_NAME = "student.db";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_NUMBER = "number";
	}
	public static final class Tearch implements BaseColumns{
		public static final String TABLE_NAME = "tearch.db";

		public static final String COLUMN_NAME_NAME = "name";
	}
}
