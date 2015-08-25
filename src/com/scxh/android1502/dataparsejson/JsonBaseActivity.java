package com.scxh.android1502.dataparsejson;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.util.ConnectionUtils;
import com.scxh.android1502.util.ConnectionUtils.CallConnectionInterface;
import com.scxh.android1502.util.ConnectionUtils.Method;
import com.scxh.android1502.util.Logs;

public class JsonBaseActivity extends Activity implements OnClickListener {
	private Button mGetJsonBtn, mAysnicJsonBtn;
	private TextView mShowJsonTxt, mShoAysnicJsonTxt;
	private ProgressBar mProgressBar;
	private String mJsonMessage = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json_base_layout);

		mGetJsonBtn = (Button) findViewById(R.id.json_getdata_connnection_btn);
		mAysnicJsonBtn = (Button) findViewById(R.id.json_analysis_btn);
		mShowJsonTxt = (TextView) findViewById(R.id.json_getdata_connnection_txt);
		mShoAysnicJsonTxt = (TextView) findViewById(R.id.json_analysis_txt);
		mProgressBar = (ProgressBar) findViewById(R.id.json_getdata_connnection_progressbar);

		mGetJsonBtn.setOnClickListener(this);
		mAysnicJsonBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.json_getdata_connnection_btn:
			String url = "http://www.weather.com.cn/adat/sk/101010100.html";

			mShowJsonTxt.setText("");
			mProgressBar.setVisibility(View.VISIBLE);

			ConnectionUtils connection = new ConnectionUtils();
			connection.asyncTaskConnection(url, null, Method.GET,
					new CallConnectionInterface() {

						@Override
						public void executeResult(String result) {
							mProgressBar.setVisibility(View.GONE);
							Logs.v("result :" + result);
							mShowJsonTxt.setText(result);
							
							mJsonMessage = result;
						}
					});

			break;
		case R.id.json_analysis_btn:
			try {
				JSONObject rootjsonObject = new JSONObject(mJsonMessage);
				JSONObject jsonObject = rootjsonObject.getJSONObject("weatherinfo");
				String cityName = jsonObject.getString("city");
				String wd = jsonObject.getString("WD");
				
				mShoAysnicJsonTxt.append("城市 :"+cityName);
				mShoAysnicJsonTxt.append("\n");
				mShoAysnicJsonTxt.append("风向 :"+wd);
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			} 
			
			
			break;
		}

	}
}
