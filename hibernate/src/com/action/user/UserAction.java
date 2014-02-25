package com.action.user;


import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.UserInfoDao;
import com.model.UserInfo;


public class UserAction extends Action {
	
	public void register() {
		String id = request.getParameter("user_id").trim();
		String psw = request.getParameter("user_password").trim();
		if (id.isEmpty() || psw.isEmpty()) {
			jsonObject.element("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfoDao userInfoDao = new UserInfoDao();
		jsonObject.element("OC", userInfoDao.insert(id, psw));
	}

	public void login() {
		String id = request.getParameter("user_id").trim();
		String psw = request.getParameter("user_password").trim();
		if (id.isEmpty() || psw.isEmpty()) {
			jsonObject.element("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfo userInfo = userInfoDao.getById(id);
		if (userInfo == null) {
			jsonObject.element("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		if (userInfo.getUser_password() != psw) {
			jsonObject.element("OC", OC.WRONG_PASSWORD);
			return;
		}
		jsonObject.element("OC", OC.SUCCESS);
	}

	public void getUserInfo() {
		String id = request.getParameter("user_id").trim();
		String psw = request.getParameter("user_password").trim();
		if (id.isEmpty() || psw.isEmpty()) {
			jsonObject.element("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfo userInfo = userInfoDao.getById(id);
		if (userInfo == null) {
			jsonObject.element("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		if (userInfo.getUser_password() != psw) {
			jsonObject.element("OC", OC.WRONG_PASSWORD);
			return;
		}
		jsonObject.element("OC", OC.SUCCESS);
		jsonObject.put("user_info", userInfo);
	}

}
