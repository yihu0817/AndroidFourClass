package com.scxh.android1502;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.scxh.android1502.activity.LifeActivity;
import com.scxh.android1502.activity.MyListActivity;
import com.scxh.android1502.activity.OneActivity;
import com.scxh.android1502.activity.StateActvity;
import com.scxh.android1502.activity.launchmode.FirstActivity;
import com.scxh.android1502.activity.parameter.A;
import com.scxh.android1502.asyctast.MyAsyactaskActivity;
import com.scxh.android1502.dataparse.xml.XmlParserPullActivity;
import com.scxh.android1502.dataparsejson.JsonBaseActivity;
import com.scxh.android1502.http.HttpConnectActivity;
import com.scxh.android1502.http.HttpExampleActivity;
import com.scxh.android1502.http.image.BitmapGridViewHttpAct;
import com.scxh.android1502.http.image.HttpGridViewActivity;
import com.scxh.android1502.http.image.HttpImageActivity;
import com.scxh.android1502.media.mp3.BaseMp3Activity;
import com.scxh.android1502.media.mp3.MusicListActivity;
import com.scxh.android1502.media.mp3.mediastore.MusicPlayerList;
import com.scxh.android1502.notification.MyNotificationActivity;
import com.scxh.android1502.receiver.systemreceiver.MyReceiverActivity;
import com.scxh.android1502.service.DownLoadActivity;
import com.scxh.android1502.service.StartMyServiceActivity;
import com.scxh.android1502.storage.db.DBSqliteActivity;
import com.scxh.android1502.storage.file.MyFileActivity;
import com.scxh.android1502.storage.file.browse.FileExplorerActivity;
import com.scxh.android1502.storage.preference.SharePreferenceActivity;
import com.scxh.android1502.storage.provider.ContactcsProviderActivity;
import com.scxh.android1502.storage.provider.ScxhContentProviderActivity;
import com.scxh.android1502.ui.InfalterAcitivty;
import com.scxh.android1502.ui.LoginListActivity;
import com.scxh.android1502.ui.autocomplettext.AutoCompleteTextViewActivity;
import com.scxh.android1502.ui.component.EditTextActivity;
import com.scxh.android1502.ui.component.ImageViewActivity;
import com.scxh.android1502.ui.component.ImageViewTwoActivity;
import com.scxh.android1502.ui.component.RadioButtonActivity;
import com.scxh.android1502.ui.component.TextViewMainActivity;
import com.scxh.android1502.ui.dialog.DialogActivity;
import com.scxh.android1502.ui.gridview.GridViewActivity;
import com.scxh.android1502.ui.layout.CodeLayoutAcitivity;
import com.scxh.android1502.ui.listview.ArrayListsActivity;
import com.scxh.android1502.ui.listview.MyBaseActivity;
import com.scxh.android1502.ui.listview.SimpleListActivity;
import com.scxh.android1502.ui.menu.MainMenuActivity;
import com.scxh.android1502.ui.png9.Png9Activity;
import com.scxh.android1502.ui.popupwindow.PopupWindowActivity;
import com.scxh.android1502.ui.progressbar_seekbar.ProgressBarActivity;
import com.scxh.android1502.ui.progressbar_seekbar.ProgressBarListViewActivity;
import com.scxh.android1502.ui.progressbar_seekbar.SeekBarActivity;
import com.scxh.android1502.ui.shape.ShapeActivity;
import com.scxh.android1502.ui.spinners.SpinnersActivity;
import com.scxh.android1502.ui.tab.MyTabActivity;
import com.scxh.android1502.ui.tab.RadioTabActivity;
import com.scxh.android1502.ui.unit.UtilActivity;
import com.scxh.android1502.ui.viewpager.ViewPagerActivity;
import com.scxh.android1502.ui.webview.WebViewCacheActivity;
import com.scxh.android1502.ui.webview.WedingActivity;

public class MainListActivity extends Activity implements OnItemClickListener {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_list_layout);

		mListView = (ListView) findViewById(R.id.main_listsview);

		String[] from = new String[] { "title" };
		int[] to = new int[] { android.R.id.text1 };

		SimpleAdapter adapter = new SimpleAdapter(this, getDataMap(),
				android.R.layout.simple_list_item_1, from, to);

		mListView.setAdapter(adapter);

		mListView.setOnItemClickListener(this);
		
		mListView.setSelection(adapter.getCount()-1);
	}

	public List<HashMap<String, Object>> getDataMap() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("title", "ListView实现布局学习");
		item.put("intent", new Intent(this, ArrayListsActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "代码布局");
		item.put("intent", new Intent(this, CodeLayoutAcitivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "InflaterLayout学习");
		item.put("intent", new Intent(this, InfalterAcitivty.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "文本控件(TextView)");
		item.put("intent", new Intent(this, TextViewMainActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "输入框控件（EditText）");
		item.put("intent", new Intent(this, EditTextActivity.class));
		list.add(item);

//		item = new HashMap<String, Object>();
//		item.put("title", "登录实例1");
//		item.put("intent", new Intent(this, LoginActivity.class));
//		list.add(item);
//
//		item = new HashMap<String, Object>();
//		item.put("title", "登录实例2");
//		item.put("intent", new Intent(this, LoginTwoActivity.class));
//		list.add(item);

		createItem(list,"登录实例",LoginListActivity.class);
		
		item = new HashMap<String, Object>();
		item.put("title", "图片控件(ImageView)");
		item.put("intent", new Intent(this, ImageViewTwoActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "按钮控件(Button)自定义");
		item.put("intent", new Intent(this, ImageViewActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "单选、多选控件");
		item.put("intent", new Intent(this, RadioButtonActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "生命周期");
		item.put("intent", new Intent(this, LifeActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "单位dp、sp");
		item.put("intent", new Intent(this, UtilActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "Activity 启动");
		item.put("intent", new Intent(this, OneActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "状态保存");
		item.put("intent", new Intent(this, StateActvity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "启动模式");
		item.put("intent", new Intent(this, FirstActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "参数传递");
		item.put("intent", new Intent(this, A.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "Intent意图");
		item.put("intent", new Intent(this,
				com.scxh.android1502.activity.intent.OneActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "SimpleListView");
		item.put("intent", new Intent(this, SimpleListActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "MyBaseActivity");
		item.put("intent", new Intent(this, MyBaseActivity.class));
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("title", "GridView网络控件");
		item.put("intent", new Intent(this, GridViewActivity.class));
		list.add(item);
	
		createItem(list,"Spinners控件",SpinnersActivity.class);
		createItem(list,"AutoCompletTextView",AutoCompleteTextViewActivity.class);
		createItem(list,"MyListActivity",MyListActivity.class);
		createItem(list,"ProgressBarActivity",ProgressBarActivity.class);
		createItem(list,"ProgressBarListViewActivity",ProgressBarListViewActivity.class);
		createItem(list,"SeekBarActivity",SeekBarActivity.class);
		createItem(list,"莱单",MainMenuActivity.class);
		createItem(list,"弹窗控件",PopupWindowActivity.class);
		createItem(list,"对话框",DialogActivity.class);
		createItem(list,"ViewPager",ViewPagerActivity.class);
		createItem(list,"MyTabActivity",MyTabActivity.class);
		createItem(list,"RadioTabActivity",RadioTabActivity.class);
		createItem(list,"Png9Activity",Png9Activity.class);
		createItem(list,"ShapeActivity",ShapeActivity.class);
		createItem(list,"DBSqliteActivity",DBSqliteActivity.class);
		createItem(list,"SharePreferenceActivity",SharePreferenceActivity.class);
		createItem(list,"MyFileActivity",MyFileActivity.class);
		createItem(list,"FileExplorer",FileExplorerActivity.class);
		createItem(list,"ContactcsProviderActivity",ContactcsProviderActivity.class);
		createItem(list,"ScxhContentProviderActivity",ScxhContentProviderActivity.class);
		createItem(list,"StartMyServiceActivity",StartMyServiceActivity.class);
		createItem(list,"DownLoadActivity",DownLoadActivity.class);
		createItem(list,"MyReceiverActivity",MyReceiverActivity.class);
		createItem(list,"MyNotificationActivity",MyNotificationActivity.class);
		createItem(list,"BaseMp3Activity",BaseMp3Activity.class);
//		createItem(list,"MusicPlayerActivity",MusicPlayerActivity.class);
		createItem(list,"音乐播放器_MusicListActivity",MusicListActivity.class);
//		createItem(list,"音乐播放器_UIMusicPlayerActivity",UIMusicPlayerActivity.class);
		createItem(list,"音乐播放器_MusicPlayerList",MusicPlayerList.class);
		createItem(list,"WedingActivity",WedingActivity.class);
		createItem(list,"WebViewCacheActivity",WebViewCacheActivity.class);
		createItem(list,"MyAsyactaskActivity",MyAsyactaskActivity.class);
		createItem(list,"HttpConnectActivity",HttpConnectActivity.class);
		createItem(list,"HttpExampleActivity",HttpExampleActivity.class);
		createItem(list,"JsonBaseActivity",JsonBaseActivity.class);
		createItem(list,"HttpImageActivity",HttpImageActivity.class);
		createItem(list,"BitmapGridViewHttpAct",BitmapGridViewHttpAct.class);
		createItem(list,"HttpGridViewActivity",HttpGridViewActivity.class);
		createItem(list,"XmlParserPullActivity",XmlParserPullActivity.class);
		
		return list;
	}

	public void createItem(List<HashMap<String, Object>> list,String title, Class<?> object) {
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("title", title);
		item.put("intent", new Intent(this, object));
		list.add(item);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();
		HashMap<String, Object> item = (HashMap<String, Object>) adapter
				.getItem(position);
		Intent intent = (Intent) item.get("intent");
		startActivity(intent);
	}
}
