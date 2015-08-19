package com.scxh.android1502.http.image;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.scxh.android1502.R;

public class HttpGridViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_http_gridview_fragment_layout);

		getFragmentManager()
				.beginTransaction()
				.add(R.id.http_gridview_fragment_layout,
						new HttpGridViewFragment()).commit();
	}

}
