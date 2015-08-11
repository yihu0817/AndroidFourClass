package com.scxh.android1502.http.image;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.scxh.android1502.R;

public class HttpImageActivity extends Activity implements OnClickListener {
	private Button mGetHttpImageBtn;
	private ImageView mShowImg;
	private ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_http_layout);

		mGetHttpImageBtn = (Button) findViewById(R.id.image_http_btn);
		mShowImg = (ImageView) findViewById(R.id.image_http_imageview);
		mProgressBar = (ProgressBar) findViewById(R.id.connnection_progressbar);

		mGetHttpImageBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.image_http_btn:
			// 从网络取图片 ,imageView显示图片
			String httpUrl = "http://d.hiphotos.baidu.com/image/h%3D360/sign=ba3bf5f839c79f3d90e1e2368aa1cdbc/f636afc379310a55c01f6ef3b54543a9822610f9.jpg";
			getAsyncTaskBitMap(httpUrl);

			break;
		}

	}

	public void getAsyncTaskBitMap(String httpUrl) {
		new AsyncTask<String, Void, Bitmap>() {
			protected void onPreExecute() {
				mProgressBar.setVisibility(View.VISIBLE);
			}

			@Override
			protected Bitmap doInBackground(String... params) {
				String httpUrl = params[0];
				Bitmap bitmap = getBitmapByHttp(httpUrl);

				return bitmap;
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				mProgressBar.setVisibility(View.GONE);
				if (result != null)
					mShowImg.setImageBitmap(result);

			}
		}.execute(httpUrl);
	}

	public Bitmap getBitmapByHttp(String httpUrl) {
		InputStream ins = null;
		try {
			URL url = new URL(httpUrl);
			// HttpURLConnection connect = (HttpURLConnection)
			// url.openConnection();
			// ins = connect.getInputStream();

			ins = url.openStream();

			Bitmap bitmap = BitmapFactory.decodeStream(ins);

			return bitmap;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}
