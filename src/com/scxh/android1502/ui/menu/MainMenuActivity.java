package com.scxh.android1502.ui.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.scxh.android1502.R;

public class MainMenuActivity extends Activity {
	private static final int MENU_SETTING = 0;
	private static final int MENU_SEARCH = 1;
	private ListView mListView;
	private Button mContextMenuBtn, mPopMenuBtn;
	private ArrayAdapter<String> mAdapter;
	private PopupMenu mPopMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu_layout);

		mListView = (ListView) findViewById(R.id.menu_listview);
		mContextMenuBtn = (Button) findViewById(R.id.context_menu_btn);
		mPopMenuBtn = (Button) findViewById(R.id.pop_menu_btn);

		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());

		mListView.setAdapter(mAdapter);

		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayAdapter adpater = (ArrayAdapter) parent.getAdapter();
				Toast.makeText(MainMenuActivity.this,
						"长按事件 :  " + adpater.getItem(position),
						Toast.LENGTH_SHORT).show();
				return false;
			}

		});
		// -===============注册上下文莱单==============
		registerForContextMenu(mContextMenuBtn);

		initPopMenu();

		mPopMenuBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 第三步:显示
				mPopMenu.show();
			}
		});

	}

	/**
	 * 初始化弹出莱单
	 */
	public void initPopMenu() {

		// 第一步: 实例化PopupMenu
		mPopMenu = new PopupMenu(this, mPopMenuBtn);

		// 第二步:装载莱单资源到莱单对象(popMenu.getMenu())上
		mPopMenu.inflate(R.menu.main_popup_menu);

		mPopMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.menu_news:
					Toast.makeText(MainMenuActivity.this, "新建",
							Toast.LENGTH_SHORT).show();
					break;
				case R.id.menu_exit:
					Toast.makeText(MainMenuActivity.this, "退出",
							Toast.LENGTH_SHORT).show();
					break;
				}

				return false;
			}
		});
	}

	/**
	 * 数据源
	 * 
	 * @return
	 */
	public ArrayList<String> getData() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("选项莱单");
		list.add("上下文莱单");
		list.add("弹出莱单");
		return list;
	}

	// =======================选项莱单===========================
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.main_option_menu, menu);
		menu.add(0, MENU_SETTING, 2, "设置");
		menu.add(0, MENU_SEARCH, 1, "退出");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_SETTING:
			Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
			break;
		case MENU_SEARCH:
			Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	// =======================上下文莱单===========================
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater menuInflater = getMenuInflater(); // 把莱单资源装载到莱单对象(ContextMenu)内
		menuInflater.inflate(R.menu.main_context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_setting:
			Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_search:
			Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
			break;
		}

		return super.onContextItemSelected(item);
	}

}
