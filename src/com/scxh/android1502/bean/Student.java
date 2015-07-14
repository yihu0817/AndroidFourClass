package com.scxh.android1502.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable{
	private int id;
	private String name;
	private String number;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(number);
	}
	
	/**
	 * 必须用 public static final 修饰符  
	 * 对象必须用 CREATOR 
	 */
	public static final  Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {

		@Override
		public Student createFromParcel(Parcel source) {
			Student student = new Student();
			student.id = source.readInt();
			student.name = source.readString();
			student.number = source.readString();
			return student;
		}

		@Override
		public Student[] newArray(int size) {
			return new Student[size];
		}
	
	};
}
