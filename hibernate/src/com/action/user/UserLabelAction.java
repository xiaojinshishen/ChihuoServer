package com.action.user;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.UserInfoDao;
import com.hibernate.UserLabelDao;
import com.model.UserInfo;
import com.model.UserLabel;

public class UserLabelAction extends Action {
	
	public void getUserLabel() {
		String id;
		try {
			id = request.getParameter("user_id").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserLabel userLabel = new UserLabelDao().getById(id);
		if (userLabel == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		jsonObject.put("OC", OC.SUCCESS);
		jsonObject.put("user_label", userLabel);
	}
	
	public void update() {
		UserLabel userLabel = new UserLabel();
		String psw;
		try {
			userLabel.setUser_id(request.getParameter("user_id").trim());
			psw = request.getParameter("user_password").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfo userInfo = new UserInfoDao().getById(userLabel.getUser_id());
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		} else if (userInfo.getUser_password() != psw) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
			return;
		}
		userLabel.setUser_label1(request.getParameter("user_label1"));
		userLabel.setUser_label2(request.getParameter("user_label2"));
		userLabel.setUser_label3(request.getParameter("user_label3"));
		userLabel.setUser_label4(request.getParameter("user_label4"));
		userLabel.setUser_label5(request.getParameter("user_label5"));
		userLabel.setUser_label_value1(request.getParameter("user_label_value1"));
		userLabel.setUser_label_value2(request.getParameter("user_label_value2"));
		userLabel.setUser_label_value3(request.getParameter("user_label_value3"));
		userLabel.setUser_label_value4(request.getParameter("user_label_value4"));
		userLabel.setUser_label_value5(request.getParameter("user_label_value5"));
		
		jsonObject.put("OC", new UserLabelDao().update(userLabel));
	}
}
