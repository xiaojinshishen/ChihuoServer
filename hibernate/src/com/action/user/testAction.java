/*test*/
package com.action.user;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.action.Action;
import com.hibernate.UserCommentDao;
import com.hibernate.UserInfoDao;
import com.hibernate.UserLocationDao;
import com.model.Location;
import com.model.UserComment;
import com.model.UserInfo;
import com.model.UserLocation;


public class testAction extends Action{

	public testAction() {
		this.jsonObject = new JSONObject();
		jsonObject.element("RC", "-2");
	}

	public JSONObject register() {
		String id = request.getParameter("dish_id");
		UserCommentDao uc = new UserCommentDao();
		//UserComment ut = new UserComment();
		//ut.setDish_id(Integer.parseInt(id));
	//	ut.setUser_comment("test");
	//	ut.setUser_id("xiaojin");
		
		//uc.Insert(ut);
		
		List<UserComment> list = new ArrayList<UserComment>();
		list = uc.getByDishId(Integer.parseInt(id), "2014-03-01 21:39:16");

		System.out.println(list.get(0).getDate_time());
		
		
		
		


		
		JSONObject jb = new JSONObject();
		
		return jb;
	}

	public JSONObject login() {
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
		return jsonObject;
	}
	
	
	
	
}
