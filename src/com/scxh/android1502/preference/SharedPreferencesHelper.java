package com.scxh.android1502.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.scxh.android1502.bean.User;

/**
 * 1.获取SharedPreferences对象 2.写入对象数据 3.读取对象数据 4.清空
 * 
 */
public class SharedPreferencesHelper {
	private static final String PREFERENCE_NAME = "PREFERENCE_USER_FILE_KEY";
	private static final String ID = "ID";
	private static final String NAME = "NAME";
	private static final String PASSWORD = "PASSWORD";
	private SharedPreferences mSharedPreferences;

	private static SharedPreferencesHelper PREFERENCE_HELPER = null;
	public static SharedPreferencesHelper getInstancePreferencesHelper(Context context) {
		if (PREFERENCE_HELPER == null) {
			PREFERENCE_HELPER = new SharedPreferencesHelper(context);
		}
		return PREFERENCE_HELPER;
	}
	
	private SharedPreferencesHelper(Context context) {
		mSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
	}
	
	/**
	 * 写入user对象数据
	 * @param user
	 */
	public void saveUserToPreferences(User user){
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putInt(ID, user.getId());
		editor.putString(NAME, user.getName());
		editor.putString(PASSWORD, user.getPassWord());
		editor.commit();
	}
	/**
	 * 读取user对象数据
	 * @return
	 */
	public User getUserFromPreferences(){
		int id = mSharedPreferences.getInt(ID, 0);
		String name = mSharedPreferences.getString(NAME, "");
		String passWord = mSharedPreferences.getString(PASSWORD, "");
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassWord(passWord);
		
		return user;
	}
	/**
	 * 清空数据
	 */
	public void clear(){
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.clear();
		editor.commit();
	}
}
