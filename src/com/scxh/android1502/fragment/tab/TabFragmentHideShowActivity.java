package com.scxh.android1502.fragment.tab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.scxh.android1502.R;

public class TabFragmentHideShowActivity extends FragmentActivity {
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

		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
				
				hideFragments(fragmentTransaction);
				
				switch (checkedId) {
				case R.id.rbOne:
					if(mainFragment == null){
						mainFragment = new FragmentMain();
						fragmentTransaction.add(R.id.fragments_content, mainFragment);
					}else{
						fragmentTransaction.show(mainFragment);
					}
					
					break;
				case R.id.rbTwo:
					if(searchFragment == null){
						searchFragment = new FragmentSearch();
						fragmentTransaction.add(R.id.fragments_content, searchFragment);
					}else{
						fragmentTransaction.show(searchFragment);
					}
					
					break;
				case R.id.rbThree:
					if(settingFragment == null){
						settingFragment = new FragmentSetting();
						fragmentTransaction.add(R.id.fragments_content, settingFragment);
					}else{
						fragmentTransaction.show(settingFragment);
					}
					
					break;
				}
				fragmentTransaction.commit();
			}
		});
		((RadioButton) mRadioGroup.getChildAt(0)).toggle(); // 默认选中第一项
	}
  
    /** 
     * 将所有的Fragment都置为隐藏状态。 
     *  
     * @param transaction 
     *            用于对Fragment执行操作的事务 
     */  
    private void hideFragments(FragmentTransaction transaction) {  
        if (mainFragment != null) {  
            transaction.hide(mainFragment);  
        }  
        if (searchFragment != null) {  
            transaction.hide(searchFragment);  
        }  
        if (settingFragment != null) {  
            transaction.hide(settingFragment);  
        }  
    }  
}
