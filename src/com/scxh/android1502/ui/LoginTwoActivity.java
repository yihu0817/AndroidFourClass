package com.scxh.android1502.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.http.HttpExampleActivity;

public class LoginTwoActivity extends Activity {
	private Button mLoginBtn, mRegistBtn;
	private EditText mUserNameEdit, mPassWordEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logintwo_layout);

		mLoginBtn = (Button) findViewById(R.id.login_btn);
		mRegistBtn = (Button) findViewById(R.id.regist_btn);
		mUserNameEdit = (EditText) findViewById(R.id.user_name_edit);
		mPassWordEdit = (EditText) findViewById(R.id.password_edit);

		mLoginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				
				DownloadWebpageText downloadWebpageText = new DownloadWebpageText();
				
				downloadWebpageText.execute("http://192.168.1.203/web1502/login_servlet");
			}
		});

		mRegistBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println(">>>>>>>>>>>注册>>>>>>>>>>>>>>>>");

			}
		});

	}
	
	private class DownloadWebpageText extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			// 参数从execute()方法传入, params[0]表示url.
			try {
				return downloadUrl(urls[0]);
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		// onPostExecute方法中处理AsyncTask返回的结果
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(LoginTwoActivity.this, result, Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(LoginTwoActivity.this,HttpExampleActivity.class);
			startActivity(intent);
			
		}

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
}
