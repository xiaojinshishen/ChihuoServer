package com.action.user;


import net.sf.json.JSONObject;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.UserInfoDao;
import com.model.UserInfo;


public class UserAction extends Action {

	public void register() {
		String id,psw;
		try {
			id = request.getParameter("user_id").trim();
			psw = request.getParameter("user_password").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		jsonObject.put("OC", new UserInfoDao().insert(id, psw));
	}

	public void login() {
		String id,psw;
		try {
			id = request.getParameter("user_id").trim();
			psw = request.getParameter("user_password").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfo userInfo = new UserInfoDao().getById(id);
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		if (userInfo.getUser_password() != psw) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
			return;
		}
		jsonObject.put("OC", OC.SUCCESS);
	}

	public void getUserInfo() {
		String id,psw;
		try {
			id = request.getParameter("user_id").trim();
			psw = request.getParameter("user_password").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfo userInfo = new UserInfoDao().getById(id);
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		if (userInfo.getUser_password() != psw) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
			return;
		}
		jsonObject.put("OC", OC.SUCCESS);
		jsonObject.put("user_info", JSONObject.fromObject(userInfo));
	}
	
	public void updateUserInfo() {
		UserInfo userInfo = new UserInfo();
		try {
			userInfo.setUser_id(request.getParameter("user_id").trim());
			userInfo.setUser_password(request.getParameter("user_password").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		userInfo.setUser_name(request.getParameter("user_name"));
		userInfo.setUser_sex(request.getParameter("user_sex"));
		userInfo.setUser_birthday(request.getParameter("user_birthday"));
		userInfo.setUser_type(request.getParameter("customer"));
		
		jsonObject.put("OC", new UserInfoDao().update(userInfo));
	}

}
