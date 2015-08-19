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
		Logs.i(" FragmentSetting onCreateView >>>>");
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
		
		Logs.i(" FragmentSetting onActivityCreated >>>>");
	}
	

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Logs.i(" FragmentSetting onAttach >>>>");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logs.d("FragmentSetting onCreate >>>>");
	}
	
	@Override
	public void onStart() {
		Logs.e("FragmentSetting onStart >>>>");
		super.onStart();
	}
	@Override
	public void onResume() {
		Logs.i("FragmentSetting onResume >>>>");
		super.onResume();
	}
	@Override
	public void onPause() {
		Logs.v("FragmentSetting onPause >>>>");
		super.onPause();
	}
	@Override
	public void onStop() {
		Logs.d("FragmentSetting onStop >>>>");
		super.onStop();
	}
	@Override
	public void onDestroyView() {
		Logs.w("FragmentSetting onDestroyView >>>>");
		super.onDestroyView();
	}
	@Override
	public void onDestroy() {
		Logs.e("FragmentSetting onDestroy >>>>");
		super.onDestroy();
	}
	@Override
	public void onDetach() {
		Logs.i("FragmentSetting onDetach >>>>");
		super.onDetach();
	}
}
