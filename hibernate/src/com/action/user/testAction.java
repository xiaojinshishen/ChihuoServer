package com.action.user;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.action.Action;
import com.hibernate.userInfoDao;
import com.model.userInfo;


public class testAction extends Action{

	public testAction() {
		this.jsonObject = new JSONObject();
		jsonObject.element("RC", "-2");
	}
	public JSONObject getResult(HttpServletRequest request)
	{
		this.request = request;
		String action = request.getParameter("action");
		try {
			Method method = this.getClass().getMethod(action);
			jsonObject = (JSONObject)method.invoke(this);
			jsonObject.element("RC", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public JSONObject register() {
		this.request = request;
		String id = request.getParameter("user_id");
		String psw = request.getParameter("user_password");
		userInfo ui = new userInfo();
		ui.setUser_id(id);
		ui.setUser_password(psw);
		ui.setUser_name("xiaojin");
		//ui.setUser_type("guest");
		userInfoDao ud = new userInfoDao();
		if(ud.Update(ui)==0)
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
