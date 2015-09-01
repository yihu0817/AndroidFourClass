package com.scxh.android1502.ui.slidingmenu;

import android.os.Bundle;
import android.view.Window;

import com.scxh.android1502.R;
import com.scxh.android1502.ui.slidingmenu.MenuFragment.SlidingMenuListener;
import com.scxh.android1502.ui.slidingmenu.NewsFragment.OnSlidingMenuNewFragmentListener;
import com.warmtel.slidingmenu.lib.SlidingMenu;
import com.warmtel.slidingmenu.lib.app.SlidingFragmentActivity;

public class SlidingMenuDemoActivity extends SlidingFragmentActivity implements
		SlidingMenuListener, OnSlidingMenuNewFragmentListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.slidingmenu_main_layout);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.sliding_menu_main_layout, NewsFragment.newInstance())
				.commit();

		// ----------------------------------
		setBehindContentView(R.layout.slidingmenu_item_layout);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.slidingmenu_item_layout, MenuFragment.newInstance())
				.commit();

		SlidingMenu sm = getSlidingMenu();
		sm.setSlidingEnabled(true);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setBehindScrollScale(0);
		sm.setFadeDegree(0.25f);
	}

	@Override
	public void toSettingFragment() {
		getSupportFragmentManager()
				.beginTransaction()
				.replace(
						R.id.sliding_menu_main_layout,
						com.scxh.android1502.ui.slidingmenu.FragmentMain
								.newInstance()).commit();

		getSlidingMenu().showContent();
	}

	@Override
	public void toNewsFragment() {
		getSupportFragmentManager()
				.beginTransaction()
				.replace(
						R.id.sliding_menu_main_layout,
						com.scxh.android1502.ui.slidingmenu.NewsFragment
								.newInstance()).commit();

		getSlidingMenu().showContent();

	}

	@Override
	public void switchBtnFramgent() {
		toggle();
	}
}
