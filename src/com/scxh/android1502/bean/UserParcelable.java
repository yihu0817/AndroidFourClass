package com.scxh.android1502.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class UserParcelable implements Parcelable {
	public String name;
	public Bitmap headIcon;
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		headIcon.writeToParcel(dest, 0);
	}

	public static final Parcelable.Creator<UserParcelable> CREATOR = new Parcelable.Creator<UserParcelable>() {

		@Override
		public UserParcelable createFromParcel(Parcel source) {
			UserParcelable user =  new UserParcelable();
			user.name = source.readString();
			user.headIcon = Bitmap.CREATOR.createFromParcel(source);
			return user;
		}

		@Override
		public UserParcelable[] newArray(int size) {
			return new UserParcelable[size];
		}
	};
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(Bitmap headIcon) {
		this.headIcon = headIcon;
	}
}
