package com.scxh.android1502.ui.autocomplettext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleAdapter;

import com.scxh.android1502.R;

public class AutoCompleteTextViewActivity extends Activity {
	private AutoCompleteTextView mAutoCompleteTxt;
	private String[] arrays = { "zhangsang", "lisi", "zhangwu", "zhaoxiao",
			"fiveday", "sixday" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_auto_complet_textview_layout);

		mAutoCompleteTxt = (AutoCompleteTextView) findViewById(R.id.autocomplet_txt);

		mAutoCompleteTxt.setThreshold(1);// 输入一个字母就开始自动提示 

		mAutoCompleteTxt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				String key = s.toString();

				// 将key作为参数，从服务器获取相关数集

				if (key.equals("z")) {
					String[] array1 = { "zhangsang", "zhangwu", "zhaoxiao" };
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							AutoCompleteTextViewActivity.this,
							android.R.layout.simple_list_item_1, array1);

					mAutoCompleteTxt.setAdapter(adapter);
				} else if (key.equals("i")) {
					String[] array2 = { "iphone", "ipad", "imac", "itouch",
							"iwatch" };

					SimpleAdapter adapters = new SimpleAdapter(
							AutoCompleteTextViewActivity.this, getData(array2),
							R.layout.meituan_search_autocompleteview_three,
							new String[] { "title", "count" }, new int[] {
									R.id.auto_search_view,
									R.id.auto_search_count });
					mAutoCompleteTxt.setAdapter(adapters);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mAutoCompleteTxt.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Adapter adapter = parent.getAdapter();
				if(adapter instanceof SimpleAdapter){
					adapter = (SimpleAdapter) adapter;
					HashMap<String, Object> map = (HashMap<String, Object>) parent
							.getAdapter().getItem(position);
					mAutoCompleteTxt.setText((String) map.get("title"));
				}else if(adapter instanceof ArrayAdapter){
					adapter = (ArrayAdapter) adapter;
					mAutoCompleteTxt.setText((String)adapter.getItem(position));
				}
					
				
			}
		});
	}

	private List<Map<String, Object>> getData(String[] arrayData) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Random random = new Random();
		Map<String, Object> map = null;
		for (int i = 0; i < arrayData.length; i++) {
			int r = random.nextInt(10000);
			map = new HashMap<String, Object>();
			map.put("title", arrayData[i]);
			map.put("count", "约" + r + "团购");
			list.add(map);
		}
		return list;
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "i苹果手机");
		map.put("count", "约1000团购");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "i三星手机");
		map.put("count", "约1011团购");
		list.add(map);

		return list;
	}

}
