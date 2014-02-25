package com.action.user;


import net.sf.json.JSONObject;

import com.action.Action;
import com.hibernate.UserInfoDao;
import com.model.UserInfo;


public class testAction extends Action{

	public testAction() {
		this.jsonObject = new JSONObject();
		jsonObject.element("RC", "-2");
	}

	public JSONObject register() {
		String id = request.getParameter("user_id");
		String psw = request.getParameter("user_password");
		UserInfo ui = new UserInfo();
		ui.setUser_id(id);
		ui.setUser_password(psw);
		ui.setUser_name("xiaojin");
		//ui.setUser_type("guest");
		UserInfoDao ud = new UserInfoDao();
		if(ud.update(ui)==0)
			System.out.print("更新成功");
		else
			System.out.print("失败");
		
		JSONObject jb = new JSONObject();
		
		return jb;
	}

	public JSONObject login() {
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
		return jsonObject;
	}
	
	
	
	
}
