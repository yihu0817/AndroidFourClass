package com.scxh.android1502.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.scxh.android1502.R;

public class MyListFragment extends Fragment {
	private ListView mListView;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_mylist_layout, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mListView = (ListView) getView().findViewById(R.id.mylistview);
		
		String[] from = new String[] { "title", "content", "icon" };
		int[] to = new int[] { R.id.title_txt, R.id.content_txt, R.id.icon_img };
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(),R.layout.item_simple_listview1_layout, from, to);

		mListView.setAdapter(adapter);
	}
	
	
	
	/**
	 * 获取数据源
	 * @return
	 */
	public List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("title", "避风塘");
		item.put("content", "[53店通用] 代金券，每桌最多可用3张！除购");
		item.put("icon", R.drawable.list1);
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "必胜客");
		item.put("content", "[70店通用] 代金券，全场通用，可叠加，不限");
		item.put("icon", R.drawable.list2);
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("title", "伊秀寿司");
		item.put("content", "[14店通用] 代金券，全场通用，可叠加，不限");
		item.put("icon", R.drawable.list4);
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("title", "小辉哥火锅");
		item.put("content", "[40店通用] 代金券，全场通用，可叠加，免费");
		item.put("icon", R.drawable.list3);
		list.add(item);
		
		

		return list;
	}
}
