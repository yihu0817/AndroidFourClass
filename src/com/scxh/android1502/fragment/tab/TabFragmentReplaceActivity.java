package com.scxh.android1502.fragment.tab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.scxh.android1502.R;

public class TabFragmentReplaceActivity extends FragmentActivity {
	private RadioGroup mRadioGroup;
	
	private FragmentMain mainFragment;
	private FragmentSearch searchFragment;
	private FragmentSetting settingFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_tabs_replace_act);
		mRadioGroup = (RadioGroup) findViewById(R.id.bottomRg);

		mainFragment = (FragmentMain) FragmentMain.newInstance();
		searchFragment = (FragmentSearch) FragmentSearch.newInstance();
		settingFragment = (FragmentSetting) FragmentSetting.newInstance();
		
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
				switch (checkedId) {
				case R.id.rbOne:
					if(mainFragment == null){
						mainFragment = (FragmentMain) FragmentMain.newInstance();
					}
					fragmentTransaction.replace(R.id.fragments_content, mainFragment);
					break;
				case R.id.rbTwo:
					if(searchFragment == null){
						searchFragment = (FragmentSearch) FragmentSearch.newInstance();
					}
					fragmentTransaction.replace(R.id.fragments_content, searchFragment);
					break;
				case R.id.rbThree:
					if(settingFragment == null){
						settingFragment = (FragmentSetting) FragmentSetting.newInstance();
					}
					fragmentTransaction.replace(R.id.fragments_content, settingFragment);
					break;
				}
				fragmentTransaction.commit();
			}
		});
		((RadioButton) mRadioGroup.getChildAt(0)).toggle(); // 默认选中第一项
	}
}
