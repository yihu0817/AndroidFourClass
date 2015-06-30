package com.scxh.android1502.ui.menu;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.scxh.android1502.R;

public class MainMenuActivity extends ListActivity {
	private static final int MENU_SETTING = 0;
	private static final int MENU_SEARCH = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getData());
		setListAdapter(adapter);
		
		ListView listView = getListView();
		
		registerForContextMenu(listView);//注册上下文莱单到 ListView
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayAdapter adpater = (ArrayAdapter) parent.getAdapter();
				Toast.makeText(MainMenuActivity.this, "长按事件 :  "+adpater.getItem(position), Toast.LENGTH_SHORT).show();
				return false;
			}
			
		});
	}
	/**
	 * 数据源
	 * @return
	 */
	public ArrayList<String> getData(){
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
	// =======================选项莱单===========================

	
	//=======================上下文莱单===========================
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
				
		getMenuInflater().inflate(R.menu.main_context_menu, menu);
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
	//=======================上下文莱单===========================
	
}
