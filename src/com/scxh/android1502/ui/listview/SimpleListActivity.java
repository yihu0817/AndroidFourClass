package com.scxh.android1502.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.scxh.android1502.R;

public class SimpleListActivity extends Activity {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_lists_layout);
		mListView = (ListView) findViewById(R.id.listsview1);

		String[] from = new String[] { "title", "content", "icon" };
		int[] to = new int[] { R.id.title_txt, R.id.content_txt, R.id.icon_img };

		SimpleAdapter adapter = new SimpleAdapter(this, getData(),R.layout.item_simple_listview1_layout, from, to);

		mListView.setAdapter(adapter);
		
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();
				
				HashMap<String, Object> item = (HashMap<String, Object>) adapter.getItem(position);
				
				Toast.makeText(SimpleListActivity.this, ""+item.get("title"), Toast.LENGTH_SHORT).show();
			}
		});

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
