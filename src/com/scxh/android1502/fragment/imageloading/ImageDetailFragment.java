package com.scxh.android1502.fragment.imageloading;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.scxh.android1502.R;
import com.scxh.android1502.util.AsyncImageLoader;
import com.scxh.android1502.util.Logs;
import com.scxh.android1502.util.AsyncImageLoader.ImageCallbackForBitmap;

public class ImageDetailFragment extends Fragment {
	private static final String IMAGE_DATA_EXTRA = "extra_image_data";
	private String mImageUrl;
	private ImageView mImageView;
	private ProgressBar mProgressBar;
	private AsyncImageLoader asyncImageLoader;
	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString(IMAGE_DATA_EXTRA, imageUrl);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString(IMAGE_DATA_EXTRA) : null;
		asyncImageLoader = AsyncImageLoader.getInstace();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.image_detail_fragment,
				container, false);
		mImageView = (ImageView) v.findViewById(R.id.imageView);
		mProgressBar = (ProgressBar) v.findViewById(R.id.progressBarone);
		return v;
	}
 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mProgressBar.setVisibility(View.VISIBLE);
		Bitmap bitmap = asyncImageLoader.loadBitmap(mImageUrl,
				new ImageCallbackForBitmap() {

					@Override
					public void imageLoaded(Bitmap bitmap, String imageUrl) {
						mImageView.setImageBitmap(bitmap);
						mProgressBar.setVisibility(View.GONE);
					}
				});
		if (bitmap != null) {
			mImageView.setImageBitmap(bitmap);
			mProgressBar.setVisibility(View.GONE);
		}
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mImageView != null) {
			mImageView.setImageDrawable(null);
		}
	}
}
