package com.scxh.android1502.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;

import com.scxh.android1502.http.ConnectionUtils;
import com.scxh.android1502.http.ConnectionUtils.CallConnectionInterface;
import com.scxh.android1502.util.Logs;

public class JSONTest extends AndroidTestCase {

	public void _test_myjson() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "李四");
		jsonObject.put("password", "1234556");
		jsonObject.put("sex", "男");

		Logs.v("jsonStr :" + jsonObject.toString());
	}

	public void _test_Json() {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", "李四");
		parameters.put("password", "abcd");
		parameters.put("sex", "男");

		JSONObject jsonObject = new JSONObject(parameters);
		String jsonStr = jsonObject.toString(); // {username:"李四",password:"abcd",sex:"男"}

		Logs.v("jsonStr :" + jsonStr);

	}

	public void _test_myjsonarray() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "李四");
		jsonObject.put("password", "1234556");
		jsonObject.put("sex", "男");

		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("username", "张三");

		JSONArray jsonArray = new JSONArray();
		jsonArray.put(0, jsonObject);
		jsonArray.put(1, jsonObject1);

		Logs.v("jsonStr :" + jsonArray.toString());

	}

	// 如何生成JsonObject
	// 生成JSONArray

	// 解析JsonObject

	/**
	 * 测试HttpConnectUtil网络工具类接收请求响应数据是否正常
	 */
	public void test_AndroidHttpClientGetClick() {
		String httpUrls = "http://www.weather.com.cn/adat/sk/101010100.html";
		Logs.v("test_AndroidHttpClientGetClick");
		ConnectionUtils httpConnectUtil = new ConnectionUtils();
		httpConnectUtil.asyncTaskConnection(httpUrls, null,
				ConnectionUtils.Method.GET, new CallConnectionInterface() {

					@Override
					public void executeResult(String result) {
						Logs.v("executeResult >>> ");
						
						Logs.v("result :"+result);

					}
				});
	}
}
