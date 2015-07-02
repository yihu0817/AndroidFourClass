package com.scxh.android1502.ui.viewpager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class ViewPagerActivity extends Activity {
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_viewpager_layout);
		mViewPager = (ViewPager) findViewById(R.id.my_viewpager);

		// 数据源
		LayoutInflater inflater = LayoutInflater.from(this);
		View view1 = inflater.inflate(R.layout.view_pager1_layout, null);
		View view2 = inflater.inflate(R.layout.view_pager2_layout, null);
		View view3 = inflater.inflate(R.layout.view_pager3_layout, null);

		ArrayList<View> dataList = new ArrayList<View>();
		dataList.add(view1);
		dataList.add(view2);
		dataList.add(view3);

		MyPagerAdapter adapter = new MyPagerAdapter();
		mViewPager.setAdapter(adapter);
		
		adapter.setData(dataList);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				Logs.v("onPageSelected >>> :"+arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Logs.v("onPageScrolled >>> :"+arg0);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Logs.v("onPageScrollStateChanged >>> :"+arg0);
			}
		});
		
	}

	public class MyPagerAdapter extends PagerAdapter {
		private ArrayList<View> list = new ArrayList<View>();

		public void setData(ArrayList<View> list) {
			this.list = list;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = list.get(position);
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = list.get(position);
			container.removeView(view);
		}

	}
}
