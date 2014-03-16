package com.action.user;


import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.UserInfoDao;
import com.hibernate.UserLabelDao;
import com.model.UserInfo;
import com.model.UserLabel;


public class UserInfoAction extends Action {

	public void register() {
		String user_id,user_password;
		try {
			user_id = request.getParameter("user_id").trim();
			user_password = request.getParameter("user_password").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		jsonObject.put("OC", new UserInfoDao().insert(user_id, user_password));
		try {
			UserLabel userLabel = new UserLabel();
			userLabel.setUser_id(user_id);
			new UserLabelDao().insert(userLabel);
		} catch(Exception e) {

		}
	}

	public void login() {
		String user_id,user_password;
		try {
			user_id = request.getParameter("user_id").trim();
			user_password = request.getParameter("user_password").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfo userInfo = new UserInfoDao().getById(user_id);
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		if (userInfo.getUser_password().equals(user_password)) {
			jsonObject.put("OC", OC.SUCCESS);
		} else {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
		}
	}

	public void getUserInfo() {
		String user_id;
		try {
			user_id = request.getParameter("user_id").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfo userInfo = new UserInfoDao().getById(user_id);
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
