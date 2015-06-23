package com.scxh.android1502.ui.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.ui.layout.FrameLayoutActivity;
import com.scxh.android1502.ui.layout.GridLayoutActivity;
import com.scxh.android1502.ui.layout.LinerLayoutAcitiy;
import com.scxh.android1502.ui.layout.RelativeLayoutActivity;
import com.scxh.android1502.ui.layout.TalbleLayoutActivity;

public class ArrayListsActivity extends Activity implements OnItemClickListener{
	private String[] arrays = {"线型布局","相对布局","单帧布局","表格布局","网络布局",}; 
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_lists_layout);
		
		mListView = (ListView) findViewById(R.id.listsview);
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.test_list_item, arrays);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				R.layout.item_listview1_layout, arrays);
		
		mListView.setAdapter(adapter);
		
//		mListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Toast.makeText(ListsActivity.this, "选中的是第"+(position)+"项", Toast.LENGTH_SHORT).show();
//			}
//		});
		
		
		mListView.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//		TextView textView = (TextView)view;
		
		String message  = (String) parent.getAdapter().getItem(position);
		
		Toast.makeText(ArrayListsActivity.this, "选中的是第"+(position)+"项"+ message, Toast.LENGTH_SHORT).show();
		
		switch(position){
		case 0:
			Intent intent = new Intent(this, LinerLayoutAcitiy.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(this, RelativeLayoutActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(this, FrameLayoutActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(this, TalbleLayoutActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent = new Intent(this, GridLayoutActivity.class);
			startActivity(intent);
			break;
		}
		
		
	}
}
