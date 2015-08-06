package com.scxh.android1502.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class HttpConnectActivity extends Activity {
	private WebView mContentTxt;
	private Button mGetHttpConnnectBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_httpclient_layout);
		mContentTxt = (WebView) findViewById(R.id.httpclient_content_txt);
		mGetHttpConnnectBtn = (Button) findViewById(R.id.get_connect_http_btn);

		mGetHttpConnnectBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String httpUrl ="http://192.168.1.203/web1502/login_servlet?userName='小明'&passWord='123456'";
//				String httpUrl = "http://192.168.1.203/html/index.html";
//				String httpUrl = "http://www.baidu.com";

				new AsyncTask<String, Void, String>() {

					@Override
					protected String doInBackground(String... params) {
						String url = params[0];

//						return getContentByHttpClient(url);
						return getDataByHttpUrlConnection(url);
					}

					protected void onPostExecute(String result) {
						if (result != null) {
							Logs.v("服务端返回的信息是  :" + result);
							
							mContentTxt.loadData(result, "text/html; charset=UTF-8", null);

						}
					}

				}.execute(httpUrl);

			}
		});

	}

	public String getContentByHttpClient(String httpUrl) {
		InputStream ins = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(httpUrl);
			HttpResponse response = httpClient.execute(httpGet);

			int statusCode = response.getStatusLine().getStatusCode();
			Logs.v("statusCode >>> :" + statusCode);
			
			if (statusCode == HttpURLConnection.HTTP_OK) {
				ins = response.getEntity().getContent();

				BufferedReader br = new BufferedReader(new InputStreamReader(ins,"utf-8"));

				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}finally{
			if(ins != null){
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 从网络获取数据通过HttpURLConnection方式实现 Get
	 * 
	 * @param url1
	 * @return
	 */
	private String getDataByHttpUrlConnection(String url1) {

		StringBuffer sb = null;

		InputStream is = null;
		BufferedReader br = null;
		HttpURLConnection conn = null;

		OutputStream os = null;
		BufferedWriter bw = null;
		try {
			// 封装访问服务器的地址
			URL url = new URL(url1);
			// 打开对服务器的连接
			conn = (HttpURLConnection) url.openConnection();
			// 设置输入输出流
			// conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			// 连接服务器
			conn.connect();

			/** 读入服务器数据的过程 **/
			// 得到输入流
			Logs.v("3333333333  getResponseCode  :" + conn.getResponseCode());
			is = conn.getInputStream();
			// 创建包装流
			br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			// 定义String类型用于储存单行数据
			String line = null;
			// 创建StringBuffer对象用于存储所有数据
			sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.disconnect();
				if (bw != null) {
					bw.close();
				}
				if (os != null) {
					os.close();
				}
				if (br != null) {
					br.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
