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
		UserInfoDao userInfoDao = new UserInfoDao();
		jsonObject.put("OC", userInfoDao.insert(id, psw));
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
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfo userInfo = userInfoDao.getById(id);
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
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfo userInfo = userInfoDao.getById(id);
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		if (userInfo.getUser_password() != psw) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
			return;
		}
		jsonObject.put("OC", OC.SUCCESS);
		jsonObject.put("user_info", userInfo);
	}

}
