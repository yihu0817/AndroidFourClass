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
	private LayoutInflater mInflater;  //把xml布局文件转换成View对象

	public MyBaseAdapter(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}
	
	/**
	 * 设置数据源，刷新适配器
	 * 
	 * @param list
	 */
	public void setData(List<ContentBean> list) {
		mList = list;
		notifyDataSetChanged();//通知刷新适配器数据
	}

	/**
	 * 返回容器中元素个数
	 */
	@Override
	public int getCount() {
		return mList.size();
	}
	/**
	 * 返回容器中指定位置的数据项
	 */
	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}
	/**
	 * 返回容器中指定位置的ID
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	/**
	 * 返回表示行的view
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Logs.v("getView >>>>>>>>>>>>>>postion " + position + " convertView :"
				+ convertView);

		ViewHolder viewHodler = null;
		if (convertView == null) {
			//将第一个参数指定的布局文件转换成View对象,如果第二参数ViewGroup不为空，则把view对象添加到该ViewGroup中
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
		
		//更新第一项中数据
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
