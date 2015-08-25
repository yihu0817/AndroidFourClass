package com.scxh.android1502.ui.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.scxh.android1502.R;

public class NewsFragment extends Fragment {
	private ListView mListView;
	private String[] mArrays = { "张三", "李四", "王二", "麻子" };
	private Button mSwitchBtn;
	public static Fragment newInstance() {
		return new NewsFragment();
	}

	public OnSlidingMenuNewFragmentListener mSlidingMenuListener;

	interface OnSlidingMenuNewFragmentListener {
		public void switchBtnFramgent();
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mSlidingMenuListener = (OnSlidingMenuNewFragmentListener) activity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.slidingmenu_news_layout, container,
				false);

		mListView = (ListView) v.findViewById(R.id.myslideing_menu_listview);
		mSwitchBtn = (Button) v.findViewById(R.id.sliding_switch_btn);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mArrays);

		mListView.setAdapter(adapter);

		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mSwitchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSlidingMenuListener.switchBtnFramgent();
			}
		});
	}
	
	

}
