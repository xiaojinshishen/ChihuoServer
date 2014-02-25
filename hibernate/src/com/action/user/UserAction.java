package com.action.user;

import net.sf.json.JSONObject;

import com.action.Action;


public class UserAction extends Action{

	public UserAction() {
		this.jsonObject = new JSONObject();
		jsonObject.element("RC", "-2");
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
