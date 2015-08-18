package com.scxh.android1502.fragment.life;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class LifeFragment extends Fragment {
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Logs.i("onAttach >>>>");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logs.d("onCreate >>>>");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Logs.v("onCreateView >>>>");
		View v = inflater.inflate(R.layout.activity_radio_layout, container,
				false);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Logs.w("onActivityCreated >>>>");
		
		
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		Logs.e("onStart >>>>");
		super.onStart();
	}
	@Override
	public void onResume() {
		Logs.i("onResume >>>>");
		super.onResume();
	}
	@Override
	public void onPause() {
		Logs.v("onPause >>>>");
		super.onPause();
	}
	@Override
	public void onStop() {
		Logs.d("onStop >>>>");
		super.onStop();
	}
	@Override
	public void onDestroyView() {
		Logs.w("onDestroyView >>>>");
		super.onDestroyView();
	}
	@Override
	public void onDestroy() {
		Logs.e("onDestroy >>>>");
		super.onDestroy();
	}
	@Override
	public void onDetach() {
		Logs.i("onDetach >>>>");
		super.onDetach();
	}
}
