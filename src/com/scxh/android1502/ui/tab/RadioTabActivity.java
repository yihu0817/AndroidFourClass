package com.scxh.android1502.ui.tab;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.scxh.android1502.R;
import com.scxh.android1502.ui.component.RadioButtonActivity;
import com.scxh.android1502.ui.layout.LinerLayoutAcitiy;
import com.scxh.android1502.ui.listview.SimpleListActivity;

public class RadioTabActivity extends TabActivity {
	private Context context;
	private LayoutInflater mLayoutInflater;
	private RadioGroup mRadioGroup;
	private static final int HOME_TAB = 1; // 主页
	private static final int CONTENT_TAB = 2; // 商家
	private static final int MORE_TAB = 3; // 更多
	private TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tab_radio_layout);
		mRadioGroup = (RadioGroup) findViewById(R.id.tab_radio_groups);

		context = this;

		tabHost = getTabHost();

		TabSpec tabSpec1 = tabHost.newTabSpec("tab1");
		tabSpec1.setIndicator("页卡1");

		tabSpec1.setContent(new Intent(context, RadioButtonActivity.class));

		TabSpec tabSpec2 = tabHost.newTabSpec("tab2");
		tabSpec2.setIndicator("页卡2");
		tabSpec2.setContent(new Intent(context, LinerLayoutAcitiy.class));

		TabSpec tabSpec3 = tabHost.newTabSpec("tab3");
		tabSpec3.setIndicator("页卡3");
		tabSpec3.setContent(new Intent(context, SimpleListActivity.class));

		tabHost.addTab(tabSpec1);
		tabHost.addTab(tabSpec2);
		tabHost.addTab(tabSpec3);

		tabHost.setCurrentTabByTag("tab1");
		((RadioButton) mRadioGroup.getChildAt(0)).toggle();
		
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.tab_radio_main:
					tabHost.setCurrentTabByTag("tab1");
					break;
				case R.id.tab_radio_merchat:
					tabHost.setCurrentTabByTag("tab2");
					break;
				case R.id.tab_radio_more:
					tabHost.setCurrentTabByTag("tab3");
					break;
				}
			}
		});
	}

}
