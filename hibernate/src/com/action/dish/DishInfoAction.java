package com.action.dish;


import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.DishInfoDao;
import com.hibernate.RestaurantInfoDao;
import com.hibernate.UserInfoDao;
import com.model.DishInfo;
import com.model.RestaurantInfo;
import com.model.UserInfo;

public class DishInfoAction extends Action {

	public void insert() {
		String dish_name,user_id;
		int restaurant_id;
		try {
			dish_name = request.getParameter("dish_name").trim();
			user_id = request.getParameter("user_id").trim();
			restaurant_id = Integer.valueOf(request.getParameter("restaurant_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		RestaurantInfo restaurantInfo = new RestaurantInfoDao().getById(restaurant_id);
		if (restaurantInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_RESTAURANT_ID);
			return;
		}
		UserInfo userInfo = new UserInfoDao().getById(user_id);
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		} else if(userInfo.getUser_type().equals("customer")) {
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
		jsonObject.put("OC", new DishInfoDao().Insert(dishInfo));
	}

	public void getByDishId() {
		int dish_id;
		try {
			dish_id = Integer.valueOf(request.getParameter("dish_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		DishInfo dishInfo = new DishInfoDao().getById(dish_id);
		if (dishInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_DISH_ID);
			return;
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
	
	public void update() {
		int dish_id;
		String dish_name;
		double dish_price;
		try {
			dish_id = Integer.valueOf(request.getParameter("dish_id").trim());
			dish_name = request.getParameter("dish_name");
			dish_price = Double.valueOf(request.getParameter("dish_price").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		DishInfoDao dishInfoDao = new DishInfoDao();
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
