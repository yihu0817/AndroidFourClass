package com.scxh.android1502.ui.webview;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.MusicBean;
import com.scxh.android1502.media.mp3.MusicPlayerService;

@SuppressLint("JavascriptInterface") 
public class WedingActivity extends Activity {
	private WebView mWebView;
	private String url = "http://192.168.1.203/html/index.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_weding_layout);

		mWebView = (WebView) findViewById(R.id.weding_webview);

		mWebView.loadUrl(url);
		
		/**第一步 设置WebView支持 JavaScript*/
		WebSettings webSeting = mWebView.getSettings();
		webSeting.setJavaScriptEnabled(true);
		
		/**第三步*/
		mWebView.addJavascriptInterface(new WebViewJavaScript(), "musicServiceInterfaceName");


	}
	/**第二步 定义交互接类和方法*/
	class WebViewJavaScript{
		public void playMusic(){
			//播放音乐
			Toast.makeText(WedingActivity.this, "html代码调用原生应用代码方法", Toast.LENGTH_SHORT).show();
			
			String musicRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath();
			String musicFile = musicRoot + "/qianlvyouhun.mp3";
			
			ArrayList<MusicBean> mMusicList = new ArrayList<MusicBean>();
			MusicBean music = new MusicBean();
			music.setMusicName("倩女幽魂");
			music.setMusicPath(musicFile);
			
			mMusicList.add(music);
			
			int mCurrentPostion = 0;
			
			Intent service = new Intent(WedingActivity.this, MusicPlayerService.class);
			service.putParcelableArrayListExtra("MUSIC_LIST", mMusicList);
			service.putExtra("CURRENT_POSTION", mCurrentPostion);
			
			startService(service);
		}
		
		public String jsToNativeGetParameter(){
			Toast.makeText(WedingActivity.this, "跳转到指定页面", Toast.LENGTH_SHORT).show();
			
			String url = "http://it.warmtel.com";
			return url;
		}
		
		public void startGridViewHttp(){
			mWebView.loadUrl("javascript:toHttpWeb()");
		}
	}

}
