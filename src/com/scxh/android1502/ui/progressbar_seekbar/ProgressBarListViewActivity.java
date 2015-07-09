package com.scxh.android1502.ui.progressbar_seekbar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.ContentBean;
import com.scxh.android1502.util.Logs;

public class ProgressBarListViewActivity extends Activity {
	private LinearLayout mProgressBarLayout;
	private ListView mListView;
	private ProgressBar mProgressBar;
	private final int HANDER_LIST_TYPE = 0; // 从网络获取数据
	private MyBaseAdapter mAdapter;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			int type = msg.what;
			switch (type) {
			case HANDER_LIST_TYPE:
				// mProgressBarLayout.setVisibility(View.GONE);
				mAdapter.setData((List<ContentBean>) msg.obj);
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_progressbar_listview_layout);

		mListView = (ListView) findViewById(R.id.progressbar_list);
		mProgressBar = (ProgressBar) findViewById(R.id.my_progressbar);
		mProgressBarLayout = (LinearLayout) findViewById(R.id.progressbar_layout);
		
		mAdapter = new MyBaseAdapter(this);
		mListView.setAdapter(mAdapter);
		
		mListView.setEmptyView(mProgressBarLayout);// 根据ListView适配器数据来确定是否显示setEmptyView方法中View;
		
		// 线程作用，模拟从网络获取数据源
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				List<ContentBean> data = loadDataByNet();

				Message msg = Message.obtain();
				msg.obj = data;
				msg.what = HANDER_LIST_TYPE;
				
				mHandler.sendMessage(msg);

			}
		}).start();
	}

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public List<ContentBean> loadDataByNet() {
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

	class MyBaseAdapter extends BaseAdapter {
		private Context mContext;
		private List<ContentBean> mList = new ArrayList<ContentBean>();
		private LayoutInflater mInflater; // 把xml布局文件转换成View对象

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
			Logs.v("getView >>>>>>>>>>>>>>postion " + position
					+ " convertView :" + convertView);

			ViewHolder viewHodler = null;
			if (convertView == null) {
				// 将第一个参数指定的布局文件转换成View对象,如果第二参数ViewGroup不为空，则把view对象添加到该ViewGroup中
				convertView = mInflater.inflate(
						R.layout.item_simple_listview1_layout, null);

				viewHodler = new ViewHolder();
				viewHodler.iconImg = (ImageView) convertView
						.findViewById(R.id.icon_img);
				viewHodler.titleTxt = (TextView) convertView
						.findViewById(R.id.title_txt);
				viewHodler.contentTxt = (TextView) convertView
						.findViewById(R.id.content_txt);

				convertView.setTag(viewHodler);

				Logs.e("重新创建View对象");
			} else {
				viewHodler = (ViewHolder) convertView.getTag();

				Logs.e("复用以前View对象");
			}

			ContentBean content = (ContentBean) getItem(position);

			// 更新第一项中数据
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

}
