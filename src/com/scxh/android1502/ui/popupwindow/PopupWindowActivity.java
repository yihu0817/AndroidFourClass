package com.scxh.android1502.ui.popupwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class PopupWindowActivity extends Activity {
	private Button mPopupBtn;
	private LinearLayout mLinearLayout;
	private PopupWindow mPopwindow;
	private boolean hasMeasured = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_popwindow_layout);
		mPopupBtn = (Button) findViewById(R.id.popwindow_btn);
		mLinearLayout = (LinearLayout) findViewById(R.id.pop_linerlayout);
		ViewTreeObserver vto = mPopupBtn.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				if (hasMeasured == false) {
					int btWidth = mPopupBtn.getMeasuredWidth();
					int btHeight = mPopupBtn.getMeasuredHeight();

					showPopupWindow(btHeight);
					hasMeasured = true;
				}
			}
		});

	}

	/**
	 * 显示弹出窗口
	 * 
	 * @param buttonHeight
	 */
	public void showPopupWindow(int buttonHeight) {
		View contentView = getPopWindowContentViewBySimpleAdatper();

		int margin = 10;// 弹窗边距

		int contentViewHeight = findViewById(android.R.id.content).getHeight(); // 屏幕内容区域高度

		int[] screenSize = getScreenSize();
		int screenWidth = screenSize[0];
		int screenHeigh = screenSize[1];

		int titleHeight = getTitleBarHeight();// getActionBar().getHeight();//
												// 获取Title高度,确保Title(ActionBar)没有隐藏
		int statusBarHeight = getStatusBarHeight();

		int popwindowWidth = screenWidth - 2 * margin;
		int popwindowHeight = contentViewHeight - buttonHeight - margin;
		// int popwindowHeight = screenHeigh - buttonHeight - titleHeight -
		// statusBarHeight - margin;

		mPopwindow = new PopupWindow(contentView, popwindowWidth,popwindowHeight);

		mPopupBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mPopwindow.isShowing()) {
					mPopwindow.dismiss();
				} else {
//					mPopwindow.showAsDropDown(v, 10, -5);
					
					int[] location = new int[2];  
			        v.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标  location [0]--->x坐标,location [1]--->y坐标
			        
			        Logs.v("location[0] :"+location[0] + " location[1] :"+location[1]);
			        
			        mPopwindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]+10, location[1]+v.getHeight());  
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

	public View getPopWindowContentViewBySimpleAdatper() {
		LayoutInflater mLayoutInflater = LayoutInflater.from(this);
		View v = mLayoutInflater.inflate(R.layout.view_popwindow_layout, null);

		ListView listView = (ListView) v.findViewById(R.id.popwindow_listview);

		SimpleAdapter adapter = new SimpleAdapter(this, getDataHashMap(),
				R.layout.item_simple_listview1_layout, new String[] { "title",
						"content", "icon" }, new int[] { R.id.title_txt,
						R.id.content_txt, R.id.icon_img });
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

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public List<HashMap<String, Object>> getDataHashMap() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("title", "避风塘");
		item.put("content", "[53店通用] 代金券，每桌最多可用3张！除购");
		item.put("icon", R.drawable.list1);
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "必胜客");
		item.put("content", "[70店通用] 代金券，全场通用，可叠加，不限");
		item.put("icon", R.drawable.list2);
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "伊秀寿司");
		item.put("content", "[14店通用] 代金券，全场通用，可叠加，不限");
		item.put("icon", R.drawable.list4);
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "小辉哥火锅");
		item.put("content", "[40店通用] 代金券，全场通用，可叠加，免费");
		item.put("icon", R.drawable.list3);
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "伊秀寿司");
		item.put("content", "[14店通用] 代金券，全场通用，可叠加，不限");
		item.put("icon", R.drawable.list4);
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "小辉哥火锅");
		item.put("content", "[40店通用] 代金券，全场通用，可叠加，免费");
		item.put("icon", R.drawable.list3);
		list.add(item);

		return list;
	}

	/**
	 * 获取手机屏幕尺寸 width,height
	 * 
	 * @return
	 */
	public int[] getScreenSize() {
		DisplayMetrics dm = new DisplayMetrics();
		// 获取屏幕信息
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeigh = dm.heightPixels;

		return new int[] { screenWidth, screenHeigh };
	}

	/**
	 * 状态栏高度
	 * 
	 * @return
	 */
	public int getStatusBarHeight() {
		Rect frame = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;

		return statusBarHeight;
	}

	/**
	 * 获取标题栏高度
	 * 
	 * @return
	 */
	public int getTitleBarHeight() {
		int statusBarHeight = getStatusBarHeight();
		int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT)
				.getTop();
		// statusBarHeight是上面所求的状态栏的高度
		int titleBarHeight = contentTop - statusBarHeight;
		return titleBarHeight;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mPopwindow != null) {
			if (mPopwindow.isShowing()) {
				mPopwindow.dismiss();
			}
		}
	}
}
