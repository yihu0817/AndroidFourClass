package com.scxh.android1502.ui.popupwindow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scxh.android1502.R;

public class PopupWindowActivity extends Activity {
	private Button mPopupBtn;
	private PopupWindow mPopwindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_popwindow_layout);
		mPopupBtn = (Button) findViewById(R.id.popwindow_btn);

		View contentView = getPopWindowContentView();

		mPopwindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		mPopupBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mPopwindow.isShowing()) {
					mPopwindow.dismiss();
				} else {
					mPopwindow.showAsDropDown(v, 10, 0);
				}
			}
		});
	}

	/**
	 * 获取弹窗View对象
	 * 
	 * @return
	 */
	public View getPopWindowContentView() {
		LayoutInflater mLayoutInflater = LayoutInflater.from(this);
		View v = mLayoutInflater.inflate(R.layout.view_popwindow_layout, null);

		ListView listView = (ListView) v.findViewById(R.id.popwindow_listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());
		listView.setAdapter(adapter);

		return v;
	}

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		list.add("线型布局");
		list.add("相对布局");
		list.add("单帧布局");
		list.add("表格布局");
		list.add("网络布局");

		return list;
	}
}
