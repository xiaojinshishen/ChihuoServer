package com.action.user;


import net.sf.json.JSONObject;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.UserInfoDao;
import com.model.UserInfo;


public class UserInfoAction extends Action {

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
		if (userInfo.getUser_password().equals(psw)) {
			jsonObject.put("OC", OC.SUCCESS);
		} else {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
		}
		jsonObject.put("OC", userInfo.getUser_password());
	}

	public void getUserInfo() {
		String id;
		try {
			id = request.getParameter("user_id").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfo userInfo = new UserInfoDao().getById(id);
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		jsonObject.put("OC", OC.SUCCESS);
		jsonObject.put("user_id", userInfo.getUser_id());
		jsonObject.put("user_name", userInfo.getUser_name());
		jsonObject.put("user_type", userInfo.getUser_type());
		jsonObject.put("user_sex", userInfo.getUser_sex());
		jsonObject.put("user_birthday", userInfo.getUser_birthday());
		jsonObject.put("date_time", userInfo.getDate_time());
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
