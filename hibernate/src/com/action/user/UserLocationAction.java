package com.action.user;


import java.util.List;

import net.sf.json.JSONObject;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.UserInfoDao;
import com.hibernate.UserLocationDao;
import com.model.Location;
import com.model.UserInfo;

public class UserLocationAction extends Action {

	public void insertLocation() {
		String user_id;
		try {
			user_id = request.getParameter("user_id").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		Location location = new Location();
		try {
			location.setLongitude(Double.valueOf(request.getParameter("longitude").trim()));
			location.setLatitude(Double.valueOf(request.getParameter("latitude").trim()));
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		jsonObject.put("OC", new UserLocationDao().insert(user_id, location));
	}

	public void getLastLocation() {
		String user_id;
		try {
			user_id = request.getParameter("user_id").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		Location location = new UserLocationDao().getLastLocation(user_id);
		if (location != null) {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("location", JSONObject.fromObject(location));
		}
	}

	public void getTrajectory() {
		String user_id;
		try {
			user_id = request.getParameter("user_id").trim();
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		List<?> list = new UserLocationDao().getTrajectory(user_id);
		if (list != null) {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("trajectory_count", list.size());
			jsonObject.put("trajectory", JSONObject.fromObject(list));
		}
	}

	public void deleteAllLocation() {
		String user_id, user_password;
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
		} else if (userInfo.getUser_password() != user_password) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
		} else {
			jsonObject.put("OC", OC.FAILIED);
			jsonObject.put("RC", RC.DEVELOPING);
		}
	}
}
