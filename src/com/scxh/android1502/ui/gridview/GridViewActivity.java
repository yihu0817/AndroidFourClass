package com.scxh.android1502.ui.gridview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.scxh.android1502.R;

public class GridViewActivity extends Activity {
	private int[] arrays = { R.drawable.list1, R.drawable.list2,
			R.drawable.list3, R.drawable.list4, R.drawable.list1, R.drawable.list2,
			R.drawable.list3,R.drawable.list1, R.drawable.list2};

	private GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview_layout);

		mGridView = (GridView) findViewById(R.id.my_gridview);

		MyGridViewAdapter adapter = new MyGridViewAdapter(this, arrays);
		mGridView.setAdapter(adapter);

	}

	public class MyGridViewAdapter extends BaseAdapter {
		private int[] list;
		private LayoutInflater mInflater;

		public MyGridViewAdapter(Context context, int[] arrays) {
			this.list = arrays;
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list.length;
		}

		@Override
		public Object getItem(int position) {
			return list[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ImageView contentImg;
			
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_my_gridview_layout, null);

				contentImg = (ImageView) convertView.findViewById(R.id.my_gridview_img);
				convertView.setTag(contentImg);
			} else {
				contentImg = (ImageView) convertView.getTag();
			}

			int item = (Integer)getItem(position);
			contentImg.setBackgroundResource(item);
			return convertView;
		}

	}
}
