package com.scxh.android1502.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class HttpExampleActivity extends Activity {
	private static final String DEBUG_TAG = "HttpExample";
	private static final String POST = "POST";
	private static final String GET = "GETE";
	private EditText mUrlText;
	private TextView mShowMessageTxt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_httpconnection_layout);
		mUrlText = (EditText) findViewById(R.id.http_url_edit);
		mShowMessageTxt = (TextView) findViewById(R.id.http_content_txt);
	}

	/**
	 * 用户点击此按钮时执行异步任务，在执行前需检查网络是否可用.
	 * 
	 * @throws UnsupportedEncodingException
	 * 
	 */
	public void onConnectionClickHandler(View view)
			throws UnsupportedEncodingException {
		String stringUrl = mUrlText.getText().toString();// 获取请求的URL从EditText控件中.
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {

			String parameter = "?userName=" + URLEncoder.encode("张三", "UTF-8")
					+ "&passWord=123456";
			stringUrl = stringUrl + parameter;
			// "http://192.168.1.203/web1502/login_servlet?userName=张三&passWord=123456"
			Logs.v("get方式 url组装 :" + stringUrl);
			new DownloadWebpageText().execute(stringUrl, GET);
		} else {
			mShowMessageTxt.setText("网络连接不可用!");
		}
	}

	/**
	 * Post请求
	 * 
	 * @param view
	 */
	public void onConnectionPostClickHandler(View view) {

		String stringUrl = mUrlText.getText().toString();// 获取请求的URL从EditText控件中.
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			new DownloadWebpageText().execute(stringUrl, POST);
		} else {
			mShowMessageTxt.setText("网络连接不可用!");
		}
	}

	private class DownloadWebpageText extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			if (urls[1].equals(POST)) {

				return postDownLoadUrl(urls[0]);

			} else if (urls[1].equals(GET)) {
				// 参数从execute()方法传入, params[0]表示url.
				try {
					return downloadUrl(urls[0]);

				} catch (IOException e) {
					return "Unable to retrieve web page. URL may be invalid.";
				}
			} else {
				return "";
			}
		}

		// onPostExecute方法中处理AsyncTask返回的结果
		@Override
		protected void onPostExecute(String result) {
			mShowMessageTxt.setText(result);
		}
	}

	private String postDownLoadUrl(String postUrl) {

		// postUrl = http://192.168.1.203/web1502/login_servlet
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(postUrl);
			HttpParams httpParams = httpClient.getParams();

			HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
			HttpConnectionParams.setSoTimeout(httpParams, 5000);
			// =========================组装参数====================
			BasicNameValuePair userNamePair = new BasicNameValuePair(
					"userName", "李四");
			BasicNameValuePair passWordPair = new BasicNameValuePair(
					"passWord", "321");

			ArrayList<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			parameters.add(userNamePair);
			parameters.add(passWordPair);

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
					HTTP.UTF_8);

			httpPost.setEntity(entity);

			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			// =========================组装参数====================

			HttpResponse httpResponse = httpClient.execute(httpPost);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				String content = EntityUtils.toString(httpResponse.getEntity());
				return content;
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 根据给定的url地址，建立一个HttpUrlConnection连接它将以流的形式返回页面内容，将流转换成字符串返回
	 * 
	 * @param myurl
	 * @return
	 * @throws IOException
	 */
	private String downloadUrl(String myurl) throws IOException {
		InputStream is = null;

		try {
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */); // 读数据超时
			conn.setConnectTimeout(15000 /* milliseconds */);// 连接超时
			conn.setRequestMethod("GET"); // 请求方式
			conn.setDoInput(true);// 设置允许输入流

			conn.connect();

			int response = conn.getResponseCode();
			Log.d(DEBUG_TAG, "The response is: " + response);
			if (response == HttpURLConnection.HTTP_OK) {
				is = conn.getInputStream();

				String contentAsString = readIt(is);
				return contentAsString;
			}
			return null;
			// 关闭InputStream
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * 将InputStream转换成String返回
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public String readIt(InputStream stream) throws IOException,
			UnsupportedEncodingException {
		Reader reader = new InputStreamReader(stream, "UTF-8");
		// 创建包装流
		BufferedReader br = new BufferedReader(reader);
		// 定义String类型用于储存单行数据
		String line = null;
		// 创建StringBuffer对象用于存储所有数据
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();

	}

	enum Method {
		GET, POST
	}

	public void onConnectionAsyncTackClickHandler(View view) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", "张三");
		map.put("passWord", "123456");

		String url = "http://192.168.1.203/web1502/login_servlet";

		asyncTaskConnection(url,map,Method.POST);
	}
	
	
	
	public void asyncTaskConnection(final String url,
		 final HashMap<String, String> parameterMap, Method method){

		new AsyncTask<Void, Void, String>() {
			protected String doInBackground(Void[] params) {

				String result = "";
				try {
					result = getHttpConnection(url, parameterMap, Method.POST);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return result;

			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);

				mShowMessageTxt.setText(result);
			}
		}.execute();

	}
	

	public String getHttpConnection(String url,
			HashMap<String, String> parameterMap, Method method)
			throws ClientProtocolException, IOException {

		HttpClient httpClient = new DefaultHttpClient();

		HttpUriRequest request = getHttpUrlRequest(url,parameterMap,method);
		
		HttpResponse response = httpClient.execute(request);

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode == HttpStatus.SC_OK) {

			String content = EntityUtils.toString(response.getEntity());

			return content;
		}else{
			return "请服务端出错";
		}
	}

	public HttpUriRequest getHttpUrlRequest(String url,
			HashMap<String, String> parameterMap, Method method)
			throws ClientProtocolException, IOException {
		if (method == Method.POST) {
			HttpPost httpPost = new HttpPost(url);
			// =========================组装Post参数====================
			ArrayList<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();

			Set<String> set = parameterMap.keySet();
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String name = parameterMap.get(key);
				System.out.println("key " + key + "name :" + name);

				BasicNameValuePair namePair = new BasicNameValuePair(key, name);
				parameters.add(namePair);
			}

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
					HTTP.UTF_8);

			httpPost.setEntity(entity);

			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");

			return httpPost;

		} else {
			if (parameterMap != null) {
				// ================组装Get请求参数===============================
				// String url =
				// "http://192.168.1.203/web1502/login_servle?userName=张三&passWord=123";

				if (url.indexOf("?") < 0) {
					url = url + "?";
				}

				Set<String> set = parameterMap.keySet();
				Iterator<String> iterator = set.iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					String name = parameterMap.get(key);
					System.out.println("key " + key + "name :" + name);

					url = url + "&" + key + "=" + name;
				}

				Logs.v("URL :" + url);
				// ================组装Get请求参数===============================
			}

			HttpGet httpGet = new HttpGet(url);

			return httpGet;
		}
	}

}
