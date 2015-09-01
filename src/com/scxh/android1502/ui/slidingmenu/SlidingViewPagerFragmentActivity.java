package com.scxh.android1502.ui.slidingmenu;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.fragment.tab.FragmentMain;
import com.scxh.android1502.fragment.tab.FragmentSearch;
import com.scxh.android1502.fragment.tab.FragmentSetting;
import com.scxh.android1502.util.Logs;
import com.warmtel.slidingmenu.lib.SlidingMenu;
import com.warmtel.slidingmenu.lib.app.SlidingFragmentActivity;

public class SlidingViewPagerFragmentActivity extends SlidingFragmentActivity implements OnClickListener{
	private ViewPager mViewPager;
	private TextView mTabMainTxt, mTabSearchTxt,mTabSettingTxt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.slidingmenu_viewpager_fragment_layout);
		mViewPager = (ViewPager) findViewById(R.id.fragment_viewpager);
		mTabMainTxt = (TextView) findViewById(R.id.tab_main);
		mTabSearchTxt = (TextView) findViewById(R.id.tab_search);
		mTabSettingTxt = (TextView) findViewById(R.id.tab_setting);
		
		mTabMainTxt.setOnClickListener(this);
		mTabSearchTxt.setOnClickListener(this);
		mTabSettingTxt.setOnClickListener(this);
		
		mTabMainTxt.setBackgroundColor(getResources().getColor(R.color.red_bg));
		
		FragmentMain mainFragment = (FragmentMain) FragmentMain.newInstance();
		FragmentSearch searchFragment = (FragmentSearch) FragmentSearch.newInstance();
		FragmentSetting settingFragment = (FragmentSetting) FragmentSetting.newInstance();
		ArrayList<Fragment> list = new ArrayList<Fragment>();
		list.add(mainFragment);
		list.add(searchFragment);
		list.add(settingFragment);
		
		Logs.v("getSupportFragmentManager()  :"+getSupportFragmentManager());
		MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
		
		Logs.v("adapter()  :"+adapter);
		mViewPager.setAdapter(adapter);
		
		mViewPager.setOffscreenPageLimit(1);//控件ViewPagwer默认加载当前页后几页
		
		adapter.setData(list);
		
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				switch(arg0){
				case 0:
					onTabMainPager();
					break;
				case 1:
					onTabSearchPager();
					break;
				case 2:
					onTabSettingPager();
					break;
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		
		setBehindContentView(R.layout.slidingmenu_item_layout);
		getSupportFragmentManager().beginTransaction().add(
				R.id.slidingmenu_item_layout, FragmentMenu.newInstance()).commit();
		
		SlidingMenu sm = getSlidingMenu();
		sm.setSlidingEnabled(true);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setBehindScrollScale(0);
		sm.setFadeDegree(0.25f);
		
	}
	
	public class MyFragmentAdapter extends FragmentPagerAdapter{
		ArrayList<Fragment> mList = new ArrayList<Fragment>();
		
		public MyFragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		public void setData(ArrayList<Fragment> list){
			mList = list;
			notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			return mList.size();
		}
		
		@Override
		public Fragment getItem(int postion) {
			return mList.get(postion);
		}

	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tab_main:
			onTabMainPager();
			mViewPager.setCurrentItem(0);
			break;
		case R.id.tab_search:
			onTabSearchPager();
			mViewPager.setCurrentItem(1);
			break;
		case R.id.tab_setting:
			onTabSettingPager();
			mViewPager.setCurrentItem(2);
			break;
		}
		
	}
	
	private void onTabMainPager(){
		mTabMainTxt.setBackgroundColor(getResources().getColor(R.color.red_bg));
		mTabSearchTxt.setBackgroundColor(getResources().getColor(android.R.color.white));
		mTabSettingTxt.setBackgroundColor(getResources().getColor(android.R.color.white));
	}
	private void onTabSearchPager(){
		mTabMainTxt.setBackgroundColor(getResources().getColor(android.R.color.white));
		mTabSearchTxt.setBackgroundColor(getResources().getColor(R.color.red_bg));
		mTabSettingTxt.setBackgroundColor(getResources().getColor(android.R.color.white));
	}
	private void onTabSettingPager(){
		mTabMainTxt.setBackgroundColor(getResources().getColor(android.R.color.white));
		mTabSearchTxt.setBackgroundColor(getResources().getColor(android.R.color.white));
		mTabSettingTxt.setBackgroundColor(getResources().getColor(R.color.red_bg));
	}
	
}
