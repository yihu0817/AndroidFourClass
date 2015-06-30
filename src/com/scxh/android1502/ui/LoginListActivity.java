package com.scxh.android1502.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LoginListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 });

		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		SimpleAdapter adapter = (SimpleAdapter) l.getAdapter();
		HashMap<String, Object> item = (HashMap<String, Object>) adapter.getItem(position);
		Intent intent = (Intent) item.get("intent");
		startActivity(intent);
	}

	public ArrayList<HashMap<String, Object>> getData() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("title", "登录实例1");
		item.put("intent", new Intent(this, LoginActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "登录实例2");
		item.put("intent", new Intent(this, LoginTwoActivity.class));
		list.add(item);

		return list;
	}
}
