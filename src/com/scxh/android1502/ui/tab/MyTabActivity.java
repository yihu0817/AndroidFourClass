package com.scxh.android1502.ui.tab;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.ui.component.RadioButtonActivity;
import com.scxh.android1502.ui.layout.LinerLayoutAcitiy;
import com.scxh.android1502.ui.listview.SimpleListActivity;

public class MyTabActivity extends TabActivity implements OnTabChangeListener {
	private Context context;
	private LayoutInflater mLayoutInflater;
	private static final int HOME_TAB = 1;    //主页
	private static final int CONTENT_TAB = 2; //商家
	private static final int MORE_TAB = 3;    //更多
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tab1_layout);

		mLayoutInflater = LayoutInflater.from(this);

		TabHost tabHost = getTabHost();

		TabSpec tabSpec1 = tabHost.newTabSpec("home");
		tabSpec1.setIndicator(createIndicatorView(HOME_TAB));
		tabSpec1.setContent(new Intent(context, SimpleListActivity.class));

		TabSpec tabSpec2 = tabHost.newTabSpec("conent");
		tabSpec2.setIndicator(createIndicatorView(CONTENT_TAB));
		tabSpec2.setContent(new Intent(context, LinerLayoutAcitiy.class));

		TabSpec tabSpec3 = tabHost.newTabSpec("more");
		tabSpec3.setIndicator(createIndicatorView(MORE_TAB));
		tabSpec3.setContent(new Intent(context, RadioButtonActivity.class));

		tabHost.addTab(tabSpec1);
		tabHost.addTab(tabSpec2);
		tabHost.addTab(tabSpec3);

		tabHost.setOnTabChangedListener(this);

	}

	private View createIndicatorView(int tab) {
		View view = mLayoutInflater.inflate(R.layout.view_tab_item_layout, null);

		TextView titleTxt = (TextView) view.findViewById(R.id.tab_title);
		ImageView iconImg = (ImageView) view.findViewById(R.id.tab_icon);

		switch(tab){
		case HOME_TAB:
			titleTxt.setText("首页");
			iconImg.setImageResource(R.drawable.selector_drawable_home_tab);
			break;
		case CONTENT_TAB:
			titleTxt.setText("商家");
			iconImg.setImageResource(R.drawable.selector_drawable_content_tab);
			break;
		case MORE_TAB:
			titleTxt.setText("更多");
			iconImg.setImageResource(R.drawable.selector_drawable_set_tab);
			break;
		}
		
		return view;
	}

	@Override
	public void onTabChanged(String tabId) {
		// Toast.makeText(context, "tabId "+tabId, Toast.LENGTH_SHORT).show();
	}
}
