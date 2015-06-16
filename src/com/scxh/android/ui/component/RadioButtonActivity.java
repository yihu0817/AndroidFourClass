package com.scxh.android.ui.component;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.scxh.android.R;

public class RadioButtonActivity extends Activity {
	private RadioGroup mSexRadioGroup;
	private CheckBox mMusicCheckBox;
	private ToggleButton mToggleButton;
	private Switch mSwitch;
	private LinearLayout mLinearLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radiobutton_layout);

		mLinearLayout = (LinearLayout)findViewById(R.id.linearLayout);
		mMusicCheckBox = (CheckBox) findViewById(R.id.music_checkbox);
//		mSexRadioGroup = (RadioGroup) findViewById(R.id.sex_radiogroup);
		mSexRadioGroup = (RadioGroup) mLinearLayout.getChildAt(1);	//通过父布局获取子元素实例
		mToggleButton = (ToggleButton) findViewById(R.id.toggel_btn);
		mSwitch = (Switch) findViewById(R.id.switch_btn);
		
		
		mSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.sex_man){
					Toast.makeText(RadioButtonActivity.this, " 你选择的是 :男", Toast.LENGTH_SHORT).show();
					
				}else if(checkedId == R.id.sex_woman){
					Toast.makeText(RadioButtonActivity.this, " 你选择的是 :女", Toast.LENGTH_SHORT).show();
	
				}
			}
		});
		
		mMusicCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Toast.makeText(RadioButtonActivity.this, "你已选中", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(RadioButtonActivity.this, "取消选中", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		
		mToggleButton.setChecked(true);
		mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Toast.makeText(RadioButtonActivity.this, "打开", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(RadioButtonActivity.this, "关闭", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
		mSwitch.setChecked(true);
		mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Toast.makeText(RadioButtonActivity.this, "打开1", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(RadioButtonActivity.this, "关闭1", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
}
