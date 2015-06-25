package com.scxh.android1502;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.scxh.android1502.activity.LifeActivity;
import com.scxh.android1502.activity.OneActivity;
import com.scxh.android1502.activity.StateActvity;
import com.scxh.android1502.activity.UtilActivity;
import com.scxh.android1502.activity.launchmode.FirstActivity;
import com.scxh.android1502.activity.parameter.A;
import com.scxh.android1502.ui.InfalterAcitivty;
import com.scxh.android1502.ui.LoginActivity;
import com.scxh.android1502.ui.LoginTwoActivity;
import com.scxh.android1502.ui.component.EditTextActivity;
import com.scxh.android1502.ui.component.ImageViewActivity;
import com.scxh.android1502.ui.component.ImageViewTwoActivity;
import com.scxh.android1502.ui.component.RadioButtonActivity;
import com.scxh.android1502.ui.component.TextViewMainActivity;
import com.scxh.android1502.ui.gridview.GridViewActivity;
import com.scxh.android1502.ui.layout.CodeLayoutAcitivity;
import com.scxh.android1502.ui.listview.ArrayListsActivity;
import com.scxh.android1502.ui.listview.MyBaseActivity;
import com.scxh.android1502.ui.listview.SimpleListActivity;

public class MainListActivity extends Activity implements OnItemClickListener {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_list_layout);

		mListView = (ListView) findViewById(R.id.main_listsview);

		String[] from = new String[] { "title" };
		int[] to = new int[] { android.R.id.text1 };

		SimpleAdapter adapter = new SimpleAdapter(this, getDataMap(),
				android.R.layout.simple_list_item_1, from, to);

		mListView.setAdapter(adapter);

		mListView.setOnItemClickListener(this);
	}

	public List<HashMap<String, Object>> getDataMap() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("title", "ListView实现布局学习");
		item.put("intent", new Intent(this, ArrayListsActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "代码布局");
		item.put("intent", new Intent(this, CodeLayoutAcitivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "InflaterLayout学习");
		item.put("intent", new Intent(this, InfalterAcitivty.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "文本控件(TextView)");
		item.put("intent", new Intent(this, TextViewMainActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "输入框控件（EditText）");
		item.put("intent", new Intent(this, EditTextActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "登录实例1");
		item.put("intent", new Intent(this, LoginActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "登录实例2");
		item.put("intent", new Intent(this, LoginTwoActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "图片控件(ImageView)");
		item.put("intent", new Intent(this, ImageViewTwoActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "按钮控件(Button)自定义");
		item.put("intent", new Intent(this, ImageViewActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "单选、多选控件");
		item.put("intent", new Intent(this, RadioButtonActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "生命周期");
		item.put("intent", new Intent(this, LifeActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "单位dp、sp");
		item.put("intent", new Intent(this, UtilActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "Activity 启动");
		item.put("intent", new Intent(this, OneActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "状态保存");
		item.put("intent", new Intent(this, StateActvity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "启动模式");
		item.put("intent", new Intent(this, FirstActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "参数传递");
		item.put("intent", new Intent(this, A.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "Intent意图");
		item.put("intent", new Intent(this,
				com.scxh.android1502.activity.intent.OneActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "SimpleListView");
		item.put("intent", new Intent(this, SimpleListActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "MyBaseActivity");
		item.put("intent", new Intent(this, MyBaseActivity.class));
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("title", "GridView网络控件");
		item.put("intent", new Intent(this, GridViewActivity.class));
		list.add(item);
		
		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();
		HashMap<String, Object> item = (HashMap<String, Object>) adapter
				.getItem(position);
		Intent intent = (Intent) item.get("intent");

		startActivity(intent);

	}
}