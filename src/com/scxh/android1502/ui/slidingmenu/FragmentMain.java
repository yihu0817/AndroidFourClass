package com.scxh.android1502.ui.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class FragmentMain extends Fragment {
	private TextView tv;
	
	public static Fragment newInstance(){
		return new FragmentMain();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Logs.i("FragmentMain onCreateView >>>>");
		
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		return v;
		
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tv = (TextView) getView().findViewById(R.id.titleTv);
		tv.setText("首页");
		
		Logs.i("FragmentMain onActivityCreated >>>>");
	}

	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Logs.i("FragmentMain onAttach >>>>");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logs.d("FragmentMain onCreate >>>>");
	}
	
	@Override
	public void onStart() {
		Logs.e("FragmentMain onStart >>>>");
		super.onStart();
	}
	@Override
	public void onResume() {
		Logs.i("FragmentMain onResume >>>>");
		super.onResume();
	}
	@Override
	public void onPause() {
		Logs.v("FragmentMain onPause >>>>");
		super.onPause();
	}
	@Override
	public void onStop() {
		Logs.d("FragmentMain onStop >>>>");
		super.onStop();
	}
	@Override
	public void onDestroyView() {
		Logs.w("FragmentMain onDestroyView >>>>");
		super.onDestroyView();
	}
	@Override
	public void onDestroy() {
		Logs.e("FragmentMain onDestroy >>>>");
		super.onDestroy();
	}
	@Override
	public void onDetach() {
		Logs.i("FragmentMain onDetach >>>>");
		super.onDetach();
	}
}
