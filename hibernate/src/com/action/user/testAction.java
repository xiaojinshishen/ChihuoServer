package com.action.user;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.action.Action;
import com.hibernate.UserInfoDao;
import com.hibernate.UserLocationDao;
import com.model.Location;
import com.model.UserInfo;
import com.model.UserLocation;


public class testAction extends Action{

	public testAction() {
		this.jsonObject = new JSONObject();
		jsonObject.element("RC", "-2");
	}

	public JSONObject register() {
		String id = request.getParameter("user_id");
		UserLocationDao ud = new UserLocationDao();
		
		List<UserLocation> list = new ArrayList<UserLocation>();
		list = ud.getTrajectory(id);

		System.out.println(list.get(0).getLatitude());
		System.out.println(list.get(1).getLatitude());
		
		
		


		
		JSONObject jb = new JSONObject();
		
		return jb;
	}

	public JSONObject login() {
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
		return jsonObject;
	}
	
	
	
	
}
