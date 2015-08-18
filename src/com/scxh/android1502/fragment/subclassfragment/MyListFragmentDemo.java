package com.scxh.android1502.fragment.subclassfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyListFragmentDemo extends ListFragment {
	private String[] mList = new String[] { "张三", "李四", "王二", "麻子" };
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mList);
		
		setListAdapter(adapter);
	}
}
