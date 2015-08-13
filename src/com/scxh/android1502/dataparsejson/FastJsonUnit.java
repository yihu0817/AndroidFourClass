package com.scxh.android1502.dataparsejson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import android.test.AndroidTestCase;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxh.android1502.dataparse.json.bean.CityBean;
import com.scxh.android1502.dataparse.json.bean.CityMessage;
import com.scxh.android1502.dataparse.json.bean.Group;
import com.scxh.android1502.dataparse.json.bean.UpdateDao;
import com.scxh.android1502.dataparse.json.bean.User;
import com.scxh.android1502.dataparse.json.bean.UserMessage;
import com.scxh.android1502.dataparse.json.bean.arounds.Around;
import com.scxh.android1502.dataparse.json.bean.arounds.AroundInfo;
import com.scxh.android1502.dataparse.json.bean.arounds.AroundMerchantBean;
import com.scxh.android1502.util.Logs;
import com.scxh.android1502.util.ReadAssetsFile;

public class FastJsonUnit extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	// =====================================================
	public void _testfastjsonobject() {
		String json = ReadAssetsFile.readtxt(getContext(), "update");
		Logs.v(" json :" + json);

		UpdateDao mUpdateDao = JSON.parseObject(json.toString(),
				UpdateDao.class);

		Logs.v(" version :" + mUpdateDao.getInfo().getVersion());
	}

	public void _testFastJsonToUserOject() {
		String json = ReadAssetsFile.readtxt(getContext(), "user");
		Logs.v("json " + json);
		// {"id":100,"userName":"admin","password":"123456","email":"admin@xinhua.com"}
		UserMessage user = JSON.parseObject(json, UserMessage.class);

		Logs.v("userName : " + user.getUser().getUserName() + " message :"
				+ user.getMessage());

	}

	public void _test_FastJsonToCityMessage(){
		String json = ReadAssetsFile.readtxt(getContext(), "json_list_test");
		Logs.v("json >> :"+json);
		
		Gson gson = new Gson();
		CityMessage cityMessage = gson.fromJson(json, CityMessage.class);
		
		
//		CityMessage cityMessage = JSON.parseObject(json, CityMessage.class);
		List<CityBean> cityList = cityMessage.getInfo();
		for(int i = 0; i< cityList.size(); i++){
			CityBean city = cityList.get(i);
			String cityName = city.getValue();
			String cityCode = city.getCode();
			
			Logs.v("cityName :"+cityName + " cityCode :"+cityCode);
		}
		
	}
	
	// {"id":712,"name":"android班","users":[{"id":12,"userName":"gm","password":"","email":""},{"id":12,"userName":"ldj","password":"","email":""},{"id":12,"userName":"tmb","password":"","email":""}]}
	public void _testFastJsonObjectToJsonString() {
		Group group = new Group();
		group.setId(712);
		group.setName("android班");

		User guestUser = new User();
		guestUser.setId(11);
		guestUser.setUserName("gm");
		guestUser.setPassWord("1234");
		guestUser.setEmail("gm@xinhua.com");

		User rootUser = new User();
		rootUser.setId(12);
		rootUser.setUserName("tmb");
		rootUser.setPassWord("1234");
		rootUser.setEmail("tmb@xinhua.com");

		User ldjUser = new User();
		ldjUser.setId(13);
		ldjUser.setUserName("ldj");
		ldjUser.setPassWord("1234");
		ldjUser.setEmail("ldj@xinhua.com");

		group.getUsers().add(guestUser);
		group.getUsers().add(rootUser);
		group.getUsers().add(ldjUser);

		String jsonString = JSON.toJSONString(group);

		Logs.v(jsonString);
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
