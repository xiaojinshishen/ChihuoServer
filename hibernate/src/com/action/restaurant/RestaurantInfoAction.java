package com.action.restaurant;

import java.util.List;


import net.sf.json.JSONObject;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.DishInfoDao;
import com.hibernate.RestaurantInfoDao;
import com.hibernate.UserInfoDao;
import com.model.Location;
import com.model.RestaurantInfo;
import com.model.UserInfo;

public class RestaurantInfoAction extends Action {

	public void insert() {
		String user_id, user_password, restaurant_name;
		double longitude, latitude;
		try {
			user_id = request.getParameter("user_id").trim();
			user_password = request.getParameter("user_password").trim();
			restaurant_name = request.getParameter("restaurant_name").trim();
			longitude = Double.valueOf(request.getParameter("longitude").trim());
			latitude = Double.valueOf(request.getParameter("latitude").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		UserInfo userInfo;
		try {
			userInfo = new UserInfoDao().getById(user_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
		} else if (!userInfo.getUser_password().equals(user_password)) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
		} else if (userInfo.getUser_type().equals("customer")) {
			jsonObject.put("OC", OC.INSUFFICIENT_PRIVILEGES);
		} else {
			RestaurantInfo restaurantInfo = new RestaurantInfo();
			restaurantInfo.setRestaurant_name(restaurant_name);
			restaurantInfo.setLongitude(longitude);
			restaurantInfo.setLatitude(latitude);
			restaurantInfo.setUser_id(user_id);

			try {
				jsonObject.put("OC", new RestaurantInfoDao().Insert(restaurantInfo));
			} catch (Exception e) {
				jsonObject.put("RC", RC.SQL_EXCEPTION);
				return;
			}
		}
	}

	public void getRestaurantByLocation() {
		double longitude, latitude;
		try {
			longitude = Double.valueOf(request.getParameter("longitude").trim());
			latitude = Double.valueOf(request.getParameter("latitude").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		Location location = new Location();
		location.setLongitude(longitude);
		location.setLatitude(latitude);

		List<?> list;
		try {
			list = new RestaurantInfoDao().getByLocation(location);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (list == null) {
			jsonObject.put("OC", OC.FAILIED);
			return;
		} else {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("restaurant_count", list.size());
			jsonObject.put("restaurants", JSONObject.fromObject(list));
		}
	}

	public void getDishInfo() {
		int restaurant_id;
		try {
			restaurant_id = Integer.valueOf(request.getParameter("restaurant_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		RestaurantInfo restaurantInfo;
		try {
			restaurantInfo = new RestaurantInfoDao().getById(restaurant_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (restaurantInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_RESTAURANT_ID);
			return;
		}

		List<?> list;
		try {
			list = new DishInfoDao().getByRestaurantId(restaurant_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (list == null) {
			jsonObject.put("OC", OC.FAILIED);
		} else {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("dish_count", list.size());
			jsonObject.put("dishes", JSONObject.fromObject(list));
		}
	}
}
