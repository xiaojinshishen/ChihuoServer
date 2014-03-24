package com.action.dish;


import java.util.List;

import net.sf.json.JSONObject;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.DishInfoDao;
import com.hibernate.DishLabelInfoDao;
import com.hibernate.RestaurantInfoDao;
import com.hibernate.UserInfoDao;
import com.model.DishInfo;
import com.model.RestaurantInfo;
import com.model.UserInfo;

public class DishInfoAction extends Action {

	public void insert() {
		String dish_name,user_id,user_password;
		int restaurant_id;
		try {
			dish_name = request.getParameter("dish_name").trim();
			user_id = request.getParameter("user_id").trim();
			user_password = request.getParameter("user_password").trim();
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

		UserInfo userInfo;
		try {
			userInfo = new UserInfoDao().getById(user_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		} else if (!userInfo.getUser_password().equals(user_password)) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
			return;
		}else if (userInfo.getUser_type().equals("customer")) {
			jsonObject.put("OC", OC.INSUFFICIENT_PRIVILEGES);
			return;
		}

		DishInfo dishInfo = new DishInfo();
		dishInfo.setDish_name(dish_name);
		dishInfo.setRestaurant_id(restaurant_id);
		dishInfo.setRestaurant_name(restaurantInfo.getRestaurant_name());
		dishInfo.setUser_id(user_id);
		try {
			dishInfo.setDish_price(Double.valueOf(request.getParameter("dish_price").trim()));
		} catch (Exception e) {
			dishInfo.setDish_price(0);
		}

		try {
			jsonObject.put("OC", new DishInfoDao().Insert(dishInfo));
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
	}

	public void getByDishId() {
		int dish_id;
		try {
			dish_id = Integer.valueOf(request.getParameter("dish_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		DishInfo dishInfo;
		try {
			dishInfo = new DishInfoDao().getById(dish_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (dishInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_DISH_ID);
		} else {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("dish_id", dishInfo.getDish_id());
			jsonObject.put("dish_name", dishInfo.getDish_name());
			jsonObject.put("dish_price", dishInfo.getRestaurant_id());
			jsonObject.put("restaurant_id", dishInfo.getRestaurant_id());
			jsonObject.put("restaurant_name", dishInfo.getRestaurant_name());
			jsonObject.put("user_id", dishInfo.getUser_id());
			jsonObject.put("date_time", dishInfo.getDate_time());
		}
	}

	public void getDishLabelByDishId() {
		int dish_id;
		try {
			dish_id = Integer.valueOf(request.getParameter("dish_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		List<?> dishLabelList;
		try {
			dishLabelList = new DishLabelInfoDao().getByDishId(dish_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}

		if (dishLabelList == null) {
			jsonObject.put("OC", OC.FAILIED);
		} else {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("dish_label_count", dishLabelList.size());
			jsonObject.put("dish_labels", JSONObject.fromObject(dishLabelList));
		}

	}

	public void updateDishInfo() {
		int dish_id;
		String dish_name, user_id, user_password;
		double dish_price;
		try {
			dish_id = Integer.valueOf(request.getParameter("dish_id").trim());
			dish_name = request.getParameter("dish_name").trim();
			user_id = request.getParameter("user_id").trim();
			user_password = request.getParameter("user_password").trim();
			dish_price = Double.valueOf(request.getParameter("dish_price").trim());
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
			return;
		} else if (userInfo.getUser_password().equals(user_password)) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
			return;
		} else if (userInfo.getUser_type().equals("customer")) {
			jsonObject.put("OC", OC.INSUFFICIENT_PRIVILEGES);
			return;
		}

		DishInfoDao dishInfoDao;
		try {
			dishInfoDao = new DishInfoDao();
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		DishInfo dishInfo = dishInfoDao.getById(dish_id);
		if (dishInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_DISH_ID);
			return;
		}
		dishInfo.setDish_name(dish_name);
		dishInfo.setDish_price(dish_price);
		jsonObject.put("OC", dishInfoDao.Update(dishInfo));
	}
}
