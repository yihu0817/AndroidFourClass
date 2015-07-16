package com.scxh.android1502.storage.db;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.Student;

public class DBListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MyBaseAdapter adapter = new MyBaseAdapter(this);
		setListAdapter(adapter);
		
		ArrayList<Student> list = getIntent().getParcelableArrayListExtra("DB_LIST");
		
		adapter.setData(list);

	}

	public class MyBaseAdapter extends BaseAdapter {
		private ArrayList<Student> list = new ArrayList<Student>();
		private LayoutInflater mInflater;

		public MyBaseAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		public void setData(ArrayList<Student> list){
			this.list = list;
			notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.view_db1_item_layout, null);
				
				viewHolder = new ViewHolder();
				viewHolder.idTxt = (TextView) convertView.findViewById(R.id.db_id_txt);
				viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.db_username_txt);
				viewHolder.numberTxt = (TextView) convertView.findViewById(R.id.db_number_txt);
				
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			Student student = (Student) getItem(position);
			viewHolder.idTxt.setText(String.valueOf(student.getId()));
			viewHolder.nameTxt.setText(student.getName());
			viewHolder.numberTxt.setText(student.getNumber());
			
			return convertView;
			
		}
		
		public class ViewHolder{
			TextView idTxt;
			TextView nameTxt;
			TextView numberTxt;
		}

	}
}
