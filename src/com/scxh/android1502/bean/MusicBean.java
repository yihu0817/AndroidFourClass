package com.scxh.android1502.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicBean implements Parcelable {
	private String musicName;
	private String musicPath;

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(musicName);
		dest.writeString(musicPath);
	}

	public static final Parcelable.Creator<MusicBean> CREATOR = new Parcelable.Creator<MusicBean>() {

		@Override
		public MusicBean createFromParcel(Parcel source) {
			MusicBean music =  new MusicBean();
			music.musicName = source.readString();
			music.musicPath = source.readString();
			return music;
		}

		@Override
		public MusicBean[] newArray(int size) {
			return new MusicBean[size];
		}
	};
}
