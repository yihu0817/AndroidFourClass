package com.scxh.android1502.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.scxh.android1502.R;
import com.scxh.android1502.fragment.life.LifeFragment;

public class MainSearchActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_search_layout);

		// FragmentManager fragmentManager = getFragmentManager();
		// FragmentTransaction transaction = fragmentManager.beginTransaction();
		//
		// transaction.add(R.id.title_fragment_layout, new TitleFragment());
		// transaction.add(R.id.content_fragment_layout, new ContentFragment());
		//
		// transaction.commit();

		getFragmentManager().beginTransaction()
				.add(R.id.content_fragment_layout, new LifeFragment())
				.commit();
//		getFragmentManager().beginTransaction()
//		.add(R.id.content_fragment_layout, new ContentFragment())
//		.commit();

		getFragmentManager().beginTransaction()
				.add(R.id.title_fragment_layout, new TitleFragment()).commit();

	}

	public void onReplaceFragmentClickView(View v) {
		getFragmentManager().beginTransaction()
				.replace(R.id.content_fragment_layout, new MyListFragment())
				.commit();
	}
}
