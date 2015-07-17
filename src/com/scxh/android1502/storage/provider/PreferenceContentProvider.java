package com.scxh.android1502.storage.provider;

import com.scxh.android1502.storage.db.DataColumn;
import com.scxh.android1502.util.Logs;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class PreferenceContentProvider extends ContentProvider {
	public static final String AUTHORITY = "com.scxh.android1502.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    
    private static final String PREFERENS_NAME = "preferecens_provider";
    private SharedPreferences mSharedPreferences;
	@Override
	public boolean onCreate() {
		
		mSharedPreferences = getContext().getSharedPreferences(PREFERENS_NAME, getContext().MODE_PRIVATE);
		return false;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		String userName = values.getAsString(DataColumn.User.COLUMN_NAME_USER_NAME);
		String passWord = values.getAsString(DataColumn.User.COLUMN_NAME_PASSWORD);
		
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putString(DataColumn.User.COLUMN_NAME_USER_NAME, userName);
		editor.putString(DataColumn.User.COLUMN_NAME_PASSWORD, passWord);
		editor.commit();
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
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		String userName = mSharedPreferences.getString(DataColumn.User.COLUMN_NAME_USER_NAME, "");
		String passWord = mSharedPreferences.getString(DataColumn.User.COLUMN_NAME_PASSWORD, "");
		
		String[] columnValues = new String[]{userName,passWord};
		
		MatrixCursor mc = new MatrixCursor(new String[]{DataColumn.User.COLUMN_NAME_USER_NAME,DataColumn.User.COLUMN_NAME_PASSWORD});
		mc.addRow(columnValues);
		
		return mc;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}
}
