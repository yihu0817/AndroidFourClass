package com.scxh.android1502.ui.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.scxh.android1502.R;

public class MenuFragment extends Fragment {
	private Button mSettingBtn, mNewsBtn;

	public static Fragment newInstance() {
		return new MenuFragment();
	}

	public SlidingMenuListener mSlidingMenuListener;

	interface SlidingMenuListener {
		public void toSettingFragment();

		public void toNewsFragment();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mSlidingMenuListener = (SlidingMenuListener) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.slidingmenu_menu_layout, container,
				false);

		mSettingBtn = (Button) v.findViewById(R.id.sliding_setting_btn);
		mNewsBtn = (Button) v.findViewById(R.id.sliding_main_btn);

		return v;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mSettingBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mSlidingMenuListener.toSettingFragment();
			}
		});
		mNewsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mSlidingMenuListener.toNewsFragment();
			}
		});

	}
}
