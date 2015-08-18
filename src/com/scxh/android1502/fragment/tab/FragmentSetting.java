package com.scxh.android1502.fragment.tab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class FragmentSetting extends Fragment {
	private ListView mListView;
	private TextView mTitleTxt;
	public static Fragment newInstance(){
		return new FragmentSetting();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_setting, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mTitleTxt = (TextView) getView().findViewById(R.id.titleTv);
		mListView = (ListView) getView().findViewById(R.id.listView1);
		mTitleTxt.setText("设置");
		CharSequence[] array = (CharSequence[]) getResources().getTextArray(R.array.arraylist);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_list_item_1, array);
		mListView.setAdapter(adapter);
		
	}
	

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
