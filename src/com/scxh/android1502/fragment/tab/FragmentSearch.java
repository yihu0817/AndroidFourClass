package com.scxh.android1502.fragment.tab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class FragmentSearch extends Fragment {
	private TextView tv;
	public static Fragment newInstance(){
		return new FragmentSearch();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Logs.e("FragmentSearch onCreateView >>>>");
		return inflater.inflate(R.layout.fragment_search, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tv = (TextView) getView().findViewById(R.id.titleTv);
		tv.setText("搜索");
		
		Logs.e("FragmentSearch onActivityCreated >>>>");

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Logs.i("FragmentSearch onAttach >>>>");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logs.d("FragmentSearch onCreate >>>>");
	}
	
	@Override
	public void onStart() {
		Logs.e("FragmentSearch onStart >>>>");
		super.onStart();
	}
	@Override
	public void onResume() {
		Logs.i("FragmentSearch onResume >>>>");
		super.onResume();
	}
	@Override
	public void onPause() {
		Logs.v("FragmentSearch onPause >>>>");
		super.onPause();
	}
	@Override
	public void onStop() {
		Logs.d("FragmentSearch onStop >>>>");
		super.onStop();
	}
	@Override
	public void onDestroyView() {
		Logs.w("FragmentSearch onDestroyView >>>>");
		super.onDestroyView();
	}
	@Override
	public void onDestroy() {
		Logs.e("FragmentSearch onDestroy >>>>");
		super.onDestroy();
	}
	@Override
	public void onDetach() {
		Logs.i("FragmentSearch onDetach >>>>");
		super.onDetach();
	}
}
