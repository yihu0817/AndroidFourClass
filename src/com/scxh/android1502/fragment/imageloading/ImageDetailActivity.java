/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.scxh.android1502.fragment.imageloading;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.scxh.android1502.R;

public class ImageDetailActivity extends FragmentActivity {
	private ImagePagerAdapter mAdapter;
	private ViewPager mPager;
	public final static String[] imageThumbUrls = new String[] {
		"http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
		"http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
		"http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
		"http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
		"http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
		"http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
		"http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
		"http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
		"http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg", };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.image_detail_pager);
		mPager = (ViewPager) findViewById(R.id.viewpagerone);
		
		mAdapter = new ImagePagerAdapter(getSupportFragmentManager(),imageThumbUrls);
		mPager.setAdapter(mAdapter);
		
//		mPager.setOffscreenPageLimit(3);//ViewPager预加载页设置
	}

	private class ImagePagerAdapter extends FragmentStatePagerAdapter {
		private final int mSize;
		private String[] mImageUrls;
		public ImagePagerAdapter(FragmentManager fm, String[] imageUrls) {
			super(fm);
			mImageUrls = imageUrls;
			mSize = mImageUrls.length;
		}

		@Override
		public int getCount() {
			return mSize;
		}

		@Override
		public Fragment getItem(int position) {
			return ImageDetailFragment.newInstance(mImageUrls[position]);
		}
	}
	
	
}
