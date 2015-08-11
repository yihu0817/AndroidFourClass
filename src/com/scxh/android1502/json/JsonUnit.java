package com.scxh.android1502.json;

import java.util.List;

import android.test.AndroidTestCase;

import com.alibaba.fastjson.JSON;
import com.scxh.android1502.json.bean.CityBean;
import com.scxh.android1502.json.bean.CityMessage;
import com.scxh.android1502.util.Logs;
import com.scxh.android1502.util.ReadAssetsFile;

public class JsonUnit extends AndroidTestCase {
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

		CityMessage cityMessage = JSON.parseObject(json, CityMessage.class);
		List<CityBean> cityList = cityMessage.getInfo();
		for(int i = 0; i< cityList.size(); i++){
			CityBean city = cityList.get(i);
			String cityName = city.getValue();
			String cityCode = city.getCode();
			
			Logs.v("cityName :"+cityName + " cityCode :"+cityCode);
		}
		
	}
	
	// {"id":712,"name":"android班","users":[{"id":12,"userName":"gm","password":"","email":""},{"id":12,"userName":"ldj","password":"","email":""},{"id":12,"userName":"tmb","password":"","email":""}]}
	public void testFastJsonObjectToJsonString() {
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

}
