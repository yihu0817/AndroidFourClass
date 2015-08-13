package com.scxh.android1502.dataparsejson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxh.android1502.dataparse.json.bean.MessageBean;
import com.scxh.android1502.dataparse.json.bean.User;
import com.scxh.android1502.dataparse.json.bean.arounds.Around;
import com.scxh.android1502.dataparse.json.bean.arounds.AroundInfo;
import com.scxh.android1502.dataparse.json.bean.arounds.AroundMerchantBean;
import com.scxh.android1502.util.Logs;

public class JsonTest extends AndroidTestCase {
	public void _testJsonObjectToStr() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", "张三");
		map.put("passWord", "123456");

		JSONObject jsonObject = new JSONObject(map);

		String jsonStr = jsonObject.toString();

		Logs.v("jsonStr :" + jsonStr); // {userName:张三,passWord:123456}

	}

	public void testFastJsonObjectToStr() {
		User user = new User();
		user.setUserName("张三");
		user.setPassWord("123456");
		// {"userName":"张三","passWord":"123456"}
		String jsonStr = JSON.toJSONString(user);
		Logs.v(" >>>  :" + jsonStr);

		User user1 = JSON.parseObject(jsonStr, User.class);

		Logs.v("userName :" + user1.getUserName() + " , passWord :"
				+ user1.getPassWord());
	}

	public void _testJsonObjectTwoToStr() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("userName", "李四");
			jsonObject.put("passWord", "123456");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		String jsonStr = jsonObject.toString();

		Logs.v("jsonStr>>> :" + jsonStr);

	}

	/**
	 * 错误的Json格式:["{age=24, name=zhangsan}","{age=25, name=lisi}"]
	 */
	public void _testJsonArrayToStr() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "zhangsan");
		map1.put("age", 24);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "lisi");
		map2.put("age", 25);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		list.add(map2);

		JSONArray array = new JSONArray(list);
		Logs.v(array.toString());

	}

	public void _testJsonArrayTwoToStr() throws JSONException {

		Logs.d("=========生成json数组字符串========================");
		JSONObject j1 = new JSONObject();
		j1.put("userName", "张三");
		j1.put("passWord", "123456");

		JSONObject j2 = new JSONObject();
		j2.put("userName", "李四");
		j2.put("passWord", "abcd");

		JSONArray array = new JSONArray();
		array.put(0, j1);
		array.put(1, j2);
		Logs.v(array.toString());

		Logs.d("=========解析json数组========================");
		for (int i = 0; i < array.length(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			String userName = jsonObject.getString("userName");
			String passWord = jsonObject.getString("passWord");
			Logs.v("userName :" + userName + ", passWord :" + passWord);

		}

	}

	public void _testJsonStrToObject() throws IOException, JSONException {
		InputStream is = getContext().getAssets().open("json_array");
		String jsonStr = readIt(is);

		Logs.v("jsonStr >>> :" + jsonStr);

		ArrayList<MessageBean> list = new ArrayList<MessageBean>();
		JSONArray jsonArray = new JSONArray(jsonStr);
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String title = jsonObject.getString("title");
			String description = jsonObject.getString("description");
			String image = jsonObject.getString("image");

			MessageBean message = new MessageBean();
			message.setTitle(title);
			message.setDescription(description);
			message.setImage(image);

			list.add(message);
		}

		for (MessageBean message : list) {
			Logs.v("title :" + message.getTitle() + " \n description :"
					+ message.getDescription() + " \n" + message.getImage());
		}

		String str = JSON.toJSONString(list);
		Logs.v("str >> :" + str);

	}

	public void _testAroundJson() throws IOException, JSONException {
		InputStream is = getContext().getAssets().open("around");
		String jsonStr = readIt(is);

		Logs.v("jsonStr >>> :" + jsonStr);

		JSONObject rootJsonObject = new JSONObject(jsonStr);
		int resultCode = rootJsonObject.getInt("resultCode");
		String resultInfo = rootJsonObject.getString("resultInfo");
		JSONObject jsonObjectInfo = rootJsonObject.getJSONObject("info");
		
		JSONObject jsonObjectPagerInfo = jsonObjectInfo.getJSONObject("pageInfo");
		String pageSize = jsonObjectPagerInfo.getString("pageSize");
		
		JSONArray jsonMerchantKeyArray = jsonObjectInfo.getJSONArray("merchantKey");
		int length = jsonMerchantKeyArray.length();
		for(int i = 0; i < length; i++){
			JSONObject item = jsonMerchantKeyArray.getJSONObject(i);
			String name = item.getString("name");
			Logs.v("name :"+name);
		}
		
	}
	
	public void _testGsonAround() throws IOException{
		InputStream is = getContext().getAssets().open("around");
		String jsonStr = readIt(is);

		Logs.v("testGsonAround >>> :" + jsonStr);
		
		Gson gson = new Gson();
		Around around = gson.fromJson(jsonStr, Around.class);
		
		AroundInfo info = around.getInfo();
		List<AroundMerchantBean> list = info.getMerchantKey();
		
		for(AroundMerchantBean aroundBean : list){
			Logs.v("name :"+aroundBean.getName()+ " ,Location :"+aroundBean.getLocation());
		}
	}
	
	
	public void testFastJsonAround() throws IOException{
		InputStream is = getContext().getAssets().open("around");
		String jsonStr = readIt(is);

		Logs.v("testFastJsonAround >>> :" + jsonStr);
		
		Around around = JSON.parseObject(jsonStr, Around.class);
		
		AroundInfo info = around.getInfo();
		List<AroundMerchantBean> list = info.getMerchantKey();
		
		for(AroundMerchantBean aroundBean : list){
			Logs.v("name :"+aroundBean.getName()+ " ,Location :"+aroundBean.getLocation());
		}
	}
	

	/**
	 * 将InputStream转换成String返回
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public String readIt(InputStream stream) throws IOException,
			UnsupportedEncodingException {
		Reader reader = new InputStreamReader(stream, "UTF-8");
		// 创建包装流
		BufferedReader br = new BufferedReader(reader);
		// 定义String类型用于储存单行数据
		String line = null;
		// 创建StringBuffer对象用于存储所有数据
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();

	}
}
