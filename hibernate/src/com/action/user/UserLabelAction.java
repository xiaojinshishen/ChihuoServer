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

		UserLabel userLabel;
		try {
			userLabel = new UserLabelDao().getById(id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (userLabel == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		jsonObject.put("OC", OC.SUCCESS);
		jsonObject.put("user_id", userLabel.getUser_id());
		if (userLabel.getUser_label1() != null) {
			jsonObject.put("user_label1", userLabel.getUser_label1());
			jsonObject.put("user_label_value1", userLabel.getUser_label_value1());
		}
		if (userLabel.getUser_label2() != null) {
			jsonObject.put("user_label2", userLabel.getUser_label2());
			jsonObject.put("user_label_value2", userLabel.getUser_label_value2());
		}
		if (userLabel.getUser_label3() != null) {
			jsonObject.put("user_label3", userLabel.getUser_label3());
			jsonObject.put("user_label_value3", userLabel.getUser_label_value3());
		}
		if (userLabel.getUser_label4() != null) {
			jsonObject.put("user_label4", userLabel.getUser_label4());
			jsonObject.put("user_label_value4", userLabel.getUser_label_value4());
		}
		if (userLabel.getUser_label5() != null) {
			jsonObject.put("user_label5", userLabel.getUser_label5());
			jsonObject.put("user_label_value5", userLabel.getUser_label_value5());
		}
	}

	public void update() {
		UserLabel userLabel = new UserLabel();
		String user_password;
		try {
			userLabel.setUser_id(request.getParameter("user_id").trim());
			user_password = request.getParameter("user_password").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		UserInfo userInfo;
		try {
			userInfo = new UserInfoDao().getById(userLabel.getUser_id());
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		if (!userInfo.getUser_password().equals(user_password)) {
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

		try {
			jsonObject.put("OC", new UserLabelDao().update(userLabel));
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
	}
	
	public void updateValueOnly() {
		UserLabel userLabel = new UserLabel();
		String user_password;
		try {
			userLabel.setUser_id(request.getParameter("user_id").trim());
			user_password = request.getParameter("user_password").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		UserInfo userInfo;
		try {
			userInfo = new UserInfoDao().getById(userLabel.getUser_id());
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}
		if (!userInfo.getUser_password().equals(user_password)) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
			return;
		}
		//set stable user label.
		userLabel.setUser_label1("酸");
		userLabel.setUser_label2("甜");
		userLabel.setUser_label3("苦");
		userLabel.setUser_label4("辣");
		userLabel.setUser_label5("咸");
		userLabel.setUser_label_value1(request.getParameter("user_label_value1"));
		userLabel.setUser_label_value2(request.getParameter("user_label_value2"));
		userLabel.setUser_label_value3(request.getParameter("user_label_value3"));
		userLabel.setUser_label_value4(request.getParameter("user_label_value4"));
		userLabel.setUser_label_value5(request.getParameter("user_label_value5"));

		try {
			jsonObject.put("OC", new UserLabelDao().update(userLabel));
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
	}
}
