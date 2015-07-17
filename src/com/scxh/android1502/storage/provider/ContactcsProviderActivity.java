package com.scxh.android1502.storage.provider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;

import com.scxh.android1502.util.Logs;

/**
 *  1.什么是ContentProvider,特点.
 *  2. ContentResolver 
 *  3.Uri 
 *  4.权限
 * 
 */
public class ContactcsProviderActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		insertContacts();

	}

	public void insertContacts() {
		ContentResolver contentResolver = getContentResolver();

		ContentValues values = new ContentValues();
		values.put(Contacts.People.NAME, "李四");

		String urlStr = "content://contacts/people";
		Uri uri = Uri.parse(urlStr);

		contentResolver.insert(uri, values);
		// contentResolver.insert(Contacts.People.CONTENT_URI, values);

		SelectContacts();
	}

	public void SelectContacts() {
		ContentResolver contentResolver = getContentResolver();

		Cursor cursor = contentResolver.query(Contacts.People.CONTENT_URI,
				null, null, null, null);

		Logs.v("count :" + cursor.getCount());

		while (cursor.moveToNext()) {
			String displayName = cursor.getString(cursor
					.getColumnIndex(Contacts.People.DISPLAY_NAME));
			String name = cursor.getString(cursor
					.getColumnIndex(Contacts.PeopleColumns.NAME));

			Logs.v("displayName :" + displayName);
			Logs.v("name :" + name);
		}
	}
}
