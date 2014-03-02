package com.action.user;


import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.UserLocationDao;
import com.model.UserLocation;

public class UserLocationAction extends Action {

	public void insert() {
		UserLocation userLocation = new UserLocation();
		try {
			userLocation.setUser_id(request.getParameter("user_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		try {
			userLocation.setLongitude(Double.valueOf(longitude));
			userLocation.setLatitude(Double.valueOf(latitude));
		} catch (NumberFormatException e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		;;;;;;;;;;
		jsonObject.put("OC", OC.FAILIED);
	}
}
