package com.scxh.android1502.activity.parameter;

import android.os.Parcel;
import android.os.Parcelable;

public class UserParce implements Parcelable {
	private int id;
	private String userName;
	private int age;
	private String sex;
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int arg1) {
		parcel.writeInt(id);
		parcel.writeString(userName);
		parcel.writeInt(age);
		parcel.writeString(sex);
	}

	/**
	 * 必须用 public static final 修饰符  
	 * 对象必须用 CREATOR 
	 */
	public static final  Parcelable.Creator<UserParce> CREATOR = new Parcelable.Creator<UserParce>() {

		@Override
		public UserParce createFromParcel(Parcel source) {
			UserParce user = new UserParce();
			user.id = source.readInt();
			user.userName = source.readString();
			user.age = source.readInt();
			user.sex = source.readString();
			return user;
		}

		@Override
		public UserParce[] newArray(int size) {
			return new UserParce[size];
		}
	
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
