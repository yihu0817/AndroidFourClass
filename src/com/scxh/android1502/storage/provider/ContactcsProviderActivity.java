package com.scxh.android1502.storage.provider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import com.scxh.android1502.util.Logs;

/**
 * 1.什么是ContentProvider,特点. 2. ContentResolver 3.Uri 4.权限
 * 
 */
public class ContactcsProviderActivity extends Activity {
	private ContentResolver mContentResolver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContentResolver = getContentResolver();

		insertContactsTwo();

	}

	public void insertContactsTwo() {
		ContentValues values = new ContentValues();

		// 首先插入空值，再得到rawContactsId ，用于下面插值
		Uri rawContactUri = mContentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
		long rawContactId = ContentUris.parseId(rawContactUri);

		values.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID,rawContactId);
		values.put(ContactsContract.CommonDataKinds.StructuredName.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE);
		values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,"大卫.李");
		values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,"科比");
		values.put(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME,"布来恩特");

		mContentResolver.insert(ContactsContract.Data.CONTENT_URI, values);

		values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
		values.put(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
		values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "18956235623");
		values.put(ContactsContract.CommonDataKinds.Phone.TYPE,Phone.TYPE_MOBILE);
		
		mContentResolver.insert(ContactsContract.Data.CONTENT_URI, values);
		
	}

	public void getContactTwo(){
		/**
		 * 读取联系人 分为以下步骤： 
		 * 1、先读取contacts表，获取ContactsID；
		 * 2、再在raw_contacts表中根据ContactsID获取RawContactsID；
		 * 3、然后就可以在data表中根据RawContactsID获取该联系人的各数据了。
		 */
		// 查询contacts表的所有记录
		Cursor cur = mContentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		// 如果记录不为空
		if (cur.getCount() > 0) {
			while (cur.moveToNext()) {
				String rawContactsId = "";
				
				String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
				// str += "ID:" + id + "\n";二、对联系人的基本操作(4)
				// 读取rawContactsId
				Cursor rawContactsIdCur = mContentResolver.query(
						ContactsContract.RawContacts.CONTENT_URI, null, RawContacts.CONTACT_ID
								+ " = ?", new String[] { id }, null);
				// 该查询结果一般只返回一条记录，所以我们直接让游标指向第一条记录
				if (rawContactsIdCur.moveToFirst()) {
					// 读取第一条记录的RawContacts._ID列的值
					rawContactsId = rawContactsIdCur.getString(rawContactsIdCur.getColumnIndex(RawContacts._ID));
				}
				rawContactsIdCur.close();
				
				
//				Cursor dataCur = mContentResolver.query(
//						ContactsContract.Data.CONTENT_URI, null,ContactsContract.Data._ID
//								+ " = ?", new String[] { rawContactsId }, null);
//				
//				while(dataCur.moveToNext()){
//					String displayName = dataCur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
//					Logs.v("displayName :"+displayName);
//				}
				
				// 读取号码
				if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					// 根据查询RAW_CONTACT_ID查询该联系人的号码
					Cursor PhoneCur = mContentResolver
							.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
									null,
									ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID
											+ " = ?",
									new String[] { rawContactsId }, null);
					// 一个联系人可能有多个号码，需要遍历
					while (PhoneCur.moveToNext()) {
						// 号获取码
						String number = PhoneCur
								.getString(PhoneCur
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						// 获取号码类型
						String numberType = PhoneCur
								.getString(PhoneCur
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
						
						Logs.v("number :"+number);
					}
					PhoneCur.close();
				}
			}
		}
	}
	
	/**
	 * 读取联系人 分为以下步骤： 1、先读取contacts表，获取ContactsID；
	 * 2、再在raw_contacts表中根据ContactsID获取RawContactsID；
	 * 3、然后就可以在data表中根据RawContactsID获取该联系人的各数据了。
	 */
	public void getContact() {
		// 获得所有的联系人
		Cursor cur = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		// 循环遍历
		if (cur.moveToFirst()) {
			int idColumn = cur.getColumnIndex(ContactsContract.Contacts._ID);

			int displayNameColumn = cur
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			do {
				// 获得联系人的ID号
				String contactId = cur.getString(idColumn);
				Log.i("contactId", contactId);
				// 获得联系人姓名
				String disPlayName = cur.getString(displayNameColumn);
				Log.i("disPlayName", disPlayName);
				// 查看该联系人有多少个电话号码。如果没有这返回值为0
				int phoneCount = cur
						.getInt(cur
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				if (phoneCount > 0) {
					// 获得联系人的电话号码
					Cursor phones = getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactId, null, null);
					if (phones.moveToFirst()) {
						do {
							// 遍历所有的电话号码
							String phoneNumber = phones
									.getString(phones
											.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
							Log.i("phoneNumber", phoneNumber);
						} while (phones.moveToNext());
					}

				}

			} while (cur.moveToNext());
		}
	}

	public void insertContacts() {
		ContentResolver contentResolver = getContentResolver();

		ContentValues values = new ContentValues();
		values.put(Contacts.People.NAME, "李四");
		contentResolver.insert(Contacts.People.CONTENT_URI, values);

		// String urlStr = "content://contacts/people";
		// Uri uri = Uri.parse(urlStr);
		// contentResolver.insert(uri, values);

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
