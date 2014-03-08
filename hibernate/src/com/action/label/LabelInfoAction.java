package com.action.label;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.LabelInfoDao;
import com.hibernate.UserInfoDao;
import com.model.UserInfo;

public class LabelInfoAction extends Action {
	
	public void addLabel() {
		String label_name, user_id;
		try {
			label_name = request.getParameter("label_name").trim();
			user_id = request.getParameter("user_id").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		UserInfo userInfo = new UserInfoDao().getById(user_id);
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
		} else {
			jsonObject.put("OC", new LabelInfoDao().Insert(label_name, user_id));
		}
	}
}
