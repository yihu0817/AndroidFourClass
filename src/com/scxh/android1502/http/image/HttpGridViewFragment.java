package com.scxh.android1502.http.image;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.scxh.android1502.R;

public class HttpGridViewFragment extends Fragment {
	public final static String[] imageThumbUrls = new String[] {
		"http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg",
        
        "http://img.my.csdn.net/uploads/201407/26/1406383264_8243.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383248_3693.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383242_3127.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383242_9576.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383242_1721.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383219_5806.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383214_7794.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383213_4418.jpg", 
        
        "http://img.my.csdn.net/uploads/201407/26/1406383213_3557.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383210_8779.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383172_4577.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383166_3407.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383166_2224.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383166_7301.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383165_7197.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383150_8410.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383131_3736.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383130_5094.jpg",  
        
        "http://img.my.csdn.net/uploads/201407/26/1406383130_7393.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383129_8813.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383100_3554.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383093_7894.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383092_2432.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383092_3071.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383091_3119.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383059_6589.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383059_8814.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383059_2237.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383058_4330.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406383038_3602.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382942_3079.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382942_8125.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382942_4881.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382941_4559.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382941_3845.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382924_8955.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382923_2141.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382923_8437.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382922_6166.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382922_4843.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382905_5804.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382904_3362.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382904_2312.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382904_4960.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382900_2418.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382881_4490.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382881_5935.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382880_3865.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382880_4662.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382879_2553.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382862_5375.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382862_1748.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382861_7618.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382861_8606.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382861_8949.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382841_9821.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382840_6603.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382840_2405.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382840_6354.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382839_5779.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382810_7578.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382810_2436.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382809_3883.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382809_6269.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382808_4179.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382790_8326.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382789_7174.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382789_5170.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382789_4118.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382788_9532.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382767_3184.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382767_4772.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382766_4924.jpg",  
        "http://img.my.csdn.net/uploads/201407/26/1406382766_5762.jpg",  
        //"http://img.my.csdn.net/uploads/201407/26/1406382765_7341.jpg" 
        };

	private GridView mGridView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.activity_http_gridview_layout, container, false);
		mGridView = (GridView) v.findViewById(R.id.http_gridview);

		MyGridViewAdapter adapter = new MyGridViewAdapter(getActivity(), imageThumbUrls);
		mGridView.setAdapter(adapter);
		
		return v;
	}

	public class MyGridViewAdapter extends BaseAdapter {
		private String[] list;
		private LayoutInflater mInflater;

		public MyGridViewAdapter(Context context, String[] arrays) {
			this.list = arrays;
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list.length;
		}

		@Override
		public Object getItem(int position) {
			return list[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			final ImageView loaderImageView;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_my_gridview_layout, null);

				loaderImageView = (ImageView) convertView.findViewById(R.id.my_gridview_img);
				convertView.setTag(loaderImageView);
			} else {
				loaderImageView = (ImageView) convertView.getTag();
			}

			String itemUrl = (String) getItem(position);

			new AsyncTask<String, Void, Bitmap>() {

				@Override
				protected Bitmap doInBackground(String... params) {
					String httpUrl = params[0];
					//根据httpUrl地址加载网络图片
					Bitmap bitmap = downLoadBitmap(httpUrl); 
					
					return bitmap;
				}

				@Override
				protected void onPostExecute(Bitmap result) {

					if (result != null)
						loaderImageView.setImageBitmap(result);

				}
			}.execute(itemUrl);
			
			//mAsyncLoaderImage.loadBitmap(getResources(),itemUrl,loaderImageView);
			
			return convertView;
		}

	}
	
	public Bitmap downLoadBitmap(String httpUrl) {
		InputStream ins = null;
		try {
			URL url = new URL(httpUrl);
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
