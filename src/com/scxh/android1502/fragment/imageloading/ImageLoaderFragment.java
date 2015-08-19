package com.scxh.android1502.fragment.imageloading;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.scxh.android1502.R;
import com.scxh.android1502.util.AsyncImageLoader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageLoaderFragment extends Fragment {
	private static final String IMAGE_URL = "IMAGE_URL";
	private ImageView mImageView;
	private ProgressBar mProgressBar;
	
	private String mImagerUrl;
	
	private AsyncImageLoader mAsyncImageLoader;
	
	public static Fragment newInstace(String imageUrl){
		ImageLoaderFragment fragment = new ImageLoaderFragment();
		
		Bundle bundle = new Bundle();
		bundle.putString(IMAGE_URL, imageUrl);
		fragment.setArguments(bundle);
		
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImagerUrl = getArguments() != null? getArguments().getString(IMAGE_URL):"";
		mAsyncImageLoader = AsyncImageLoader.getInstace();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_image_loader_layout, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mImageView = (ImageView) getView().findViewById(R.id.image_loader_img);
		mProgressBar = (ProgressBar) getView().findViewById(R.id.image_loader_progrebar);
		
//		mProgressBar.setVisibility(View.VISIBLE);
//		Bitmap bitmap = mAsyncImageLoader.loadBitmap(mImagerUrl, new ImageCallbackForBitmap() {
//			
//			@Override
//			public void imageLoaded(Bitmap bitmap, String imageUrl) {
//				mImageView.setImageBitmap(bitmap);
//				mProgressBar.setVisibility(View.GONE);
//			}
//		});
//		if(bitmap != null){
//			mImageView.setImageBitmap(bitmap);
//			mProgressBar.setVisibility(View.GONE);
//		}
		
		
//		new AsyncTask<String, Void, Bitmap>(){
//
//			@Override
//			protected void onPreExecute() {
//				super.onPreExecute();
//				mProgressBar.setVisibility(View.VISIBLE);
//			}
//			
//			@Override
//			protected Bitmap doInBackground(String... params) {
//				String imageUrl = params[0];

//				return downLoadBitmap(imageUrl);
//			}
//			@Override
//			protected void onPostExecute(Bitmap result) {
//				super.onPostExecute(result);
//				mProgressBar.setVisibility(View.GONE);
//				if(result != null){
//					mImageView.setImageBitmap(result);
//				}
//			}
//		}.execute(mImagerUrl);
		
		
		Picasso.with(getActivity()).load(mImagerUrl).into(mImageView);
		
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
