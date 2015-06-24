package com.scxh.android1502.ui.listview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.ContentBean;
import com.scxh.android1502.util.Logs;

public class MyBaseAdapter extends BaseAdapter {
	private Context mContext;
	private List<ContentBean> mList = new ArrayList<ContentBean>();
	private LayoutInflater mInflater;

	public MyBaseAdapter(Context context, List<ContentBean> list) {
		mContext = context;
		mList = list;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Logs.v("getView >>>>>>>>>>>>>>postion " + position + " convertView :"
				+ convertView);

		ViewHolder viewHodler = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_simple_listview1_layout, null);
			
			viewHodler = new ViewHolder();
			viewHodler.iconImg = (ImageView) convertView.findViewById(R.id.icon_img);
			viewHodler.titleTxt = (TextView) convertView.findViewById(R.id.title_txt);
			viewHodler.contentTxt = (TextView) convertView.findViewById(R.id.content_txt);
			convertView.setTag(viewHodler);
			
			Logs.e("重新创建View对象");
		} else {
			viewHodler = (ViewHolder) convertView.getTag();
			
			Logs.e("复用以前View对象");
		}

		ContentBean content = (ContentBean) getItem(position);
		
		viewHodler.iconImg.setBackgroundResource(content.getIcon());
		viewHodler.titleTxt.setText(content.getTitle());
		viewHodler.contentTxt.setText(content.getContent());

		return convertView;
	}

	class ViewHolder {
		ImageView iconImg;
		TextView titleTxt;
		TextView contentTxt;
	}

}
