package com.scxh.android1502.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.ContentBean;
import com.scxh.android1502.ui.listview.MyBaseAdapter;

public class MyListActivity extends ListActivity {
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.activity_sys_listview_layout);
//		
//		mListView = getListView();
//		mListView.setBackgroundColor(getResources().getColor(R.color.my_blue_bright));
		
		MyBaseAdapter adapter = new MyBaseAdapter(this);
		
		setListAdapter(adapter);
		adapter.setData(getDataContent());
	}
	
	public List<ContentBean> getDataContent(){
		List<ContentBean> list = new ArrayList<ContentBean>();
		
		ContentBean content = new ContentBean();
		content.setIcon(R.drawable.list1);
		content.setTitle("1 避风塘");
		content.setContent("[53店通用] 代金券，每桌最多可用3张！除购");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("2 必胜客");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("3 伊秀寿司");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("4 必胜客1");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("5 伊秀寿司1");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("6 必胜客1");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("7 伊秀寿司2");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("8 必胜客4");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("9 伊秀寿司9");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("10 必胜客2");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("11 伊秀寿司6");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		return list;
	}
}
