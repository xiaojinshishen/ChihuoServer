package com.action.user;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.action.Action;


public class UserAction extends Action{

	public UserAction() {
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
		return jsonObject;
	}

	public JSONObject login() {
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
		return jsonObject;
	}
	
	
}
