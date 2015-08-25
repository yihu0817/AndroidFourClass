package com.scxh.android1502.ui.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.ContentBean;
import com.scxh.android1502.util.Logs;

public class MyBaseTypeActivity extends Activity {
	private ListView mListView;
	MyBaseAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_mylistview_layout);
		
		mListView = (ListView) findViewById(R.id.mylistview);
		
		mAdapter = new MyBaseAdapter(this);
		mListView.setAdapter(mAdapter);
		
		mAdapter.setData(getDataContent());
		
	}
	
	public class MyBaseAdapter extends BaseAdapter {
		private final static int TYPE_ONE = 0;  //类型序号必须从0开始
		private final static int TYPE_TWO = 1;
		private final static int TYPE_COUNT = 2; //listview类型个数
		
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
		
		@Override
		public int getViewTypeCount() {
			return TYPE_COUNT;
		}
		
		@Override
		public int getItemViewType(int position) {
			ContentBean typeBean = (ContentBean) getItem(position);
			String title = typeBean.getTitle();
			if(title.contains("必胜客")){
				return TYPE_ONE;
			}else{
				return TYPE_TWO;
			}
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(getItemViewType(position) == TYPE_ONE){
				return getTypeOneView(position,convertView,parent);
			}else{
				return getTwoTypeView(position,convertView,parent);
			}
		}
		
		public View getTypeOneView(int position, View convertView, ViewGroup parent) {
			
			TextView contentTxt;
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.item_type_one_layout, null);
				TextView textView = (TextView) convertView.findViewById(R.id.type_one_txt);
				convertView.setTag(textView);
			}
			contentTxt = (TextView) convertView.getTag();
			
			ContentBean typeBean = (ContentBean) getItem(position);
			String title = typeBean.getTitle();
			contentTxt.setText(title);
			
			return convertView;
		}
		
		
		public View getTwoTypeView(int position, View convertView, ViewGroup parent) {
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
	public List<ContentBean> getDataContent(){
		List<ContentBean> list = new ArrayList<ContentBean>();
		
		ContentBean content = new ContentBean();
		content.setIcon(R.drawable.list1);
		content.setTitle("1 避风塘");
		content.setContent("[53店通用] 代金券，每桌最多可用3张！除购");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("2 必胜客");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("3 伊秀寿司");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("4 必胜客1");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("5 伊秀寿司1");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("6 必胜客1");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("7 伊秀寿司2");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("8 必胜客4");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("9 伊秀寿司9");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		content = new ContentBean();
		content.setIcon(R.drawable.list2);
		content.setTitle("10 必胜客2");
		content.setContent("[70店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		
		content = new ContentBean();
		content.setIcon(R.drawable.list3);
		content.setTitle("11 伊秀寿司6");
		content.setContent("[14店通用] 代金券，全场通用，可叠加，不限");
		list.add(content);
		
		return list;
	}
}
