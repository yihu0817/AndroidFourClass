package com.scxh.android1502.http.image;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.util.AsyncMemoryCacheImageLoader;
import com.scxh.android1502.util.imageloader.AsyncMemoryFileCacheImageLoader;

public class BitmapGridViewHttpAct extends Activity implements
		OnItemClickListener {
	private GridView mGridView;

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
	};

	private String[] mStrings = { "美食1", "美食2", "美食3", "美食4", "美食5", "美食6",
			"美食7", "美食8", "美食9",
			
			"美食1", "美食2", "美食3", "美食4", "美食5", "美食6",
			"美食7", "美食8", "美食9",
			
			
			"美食1", "美食2", "美食3", "美食4", "美食5", "美食6",
			"美食7", "美食8", "美食9",
	};
	private MyGirdViewAdapter mAdapter;

	private AsyncMemoryFileCacheImageLoader mAsycnHttpImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_gridview_layout);

		mAsycnHttpImageView = AsyncMemoryFileCacheImageLoader.getInstanceAsycnHttpImageView(this);
		mAsycnHttpImageView.setThreadPoolExecutor();
		
		mGridView = (GridView) findViewById(R.id.gridview1);

		mAdapter = new MyGirdViewAdapter(this);

		mGridView.setAdapter(mAdapter);

		mAdapter.setData(imageThumbUrls, mStrings);

		mGridView.setOnItemClickListener(this);

	}

	public class MyGirdViewAdapter extends BaseAdapter {
		private String[] imageArrays = new String[] {};
		private String[] stringArrays = new String[] {};
		private Context context;
		private LayoutInflater inflater;

		public MyGirdViewAdapter(Context contexts) {
			context = contexts;
			inflater = LayoutInflater.from(context);
		}

		public void setData(String[] images, String[] strings) {
			imageArrays = images;
			stringArrays = strings;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return imageArrays.length;
		}

		@Override
		public Object getItem(int position) {
			return stringArrays[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final HolderView holderView;
			if (convertView == null) {
				holderView = new HolderView();
				convertView = inflater.inflate(R.layout.item_gridview_layout,null);
				holderView.iconImageView = (ImageView) convertView.findViewById(R.id.grid_imageview);
				holderView.nameTxt = (TextView) convertView.findViewById(R.id.grid_textview);
				convertView.setTag(holderView);
			} else {
				holderView = (HolderView) convertView.getTag();
			}
			holderView.nameTxt.setText(stringArrays[position]);

			mAsycnHttpImageView.loadBitmap(getResources(),imageArrays[position], holderView.iconImageView,
					R.drawable.m1, 0, 0);

			return convertView;
		}

		class HolderView {
			ImageView iconImageView;
			TextView nameTxt;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String item = (String) parent.getAdapter().getItem(position);
		Toast.makeText(this, item, Toast.LENGTH_SHORT).show();

	}
}
