package com.action.dish;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.DishInfoDao;
import com.hibernate.RestaurantInfoDao;
import com.model.DishInfo;
import com.model.RestaurantInfo;

public class DishInfoAction extends Action {
	
	public void addDishInfo() {
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
		
		DishInfo dishInfo = new DishInfo();
		dishInfo.setDish_name(dish_name);
		dishInfo.setRestaurant_id(restaurant_id);
		dishInfo.setRestaurant_name(restaurantInfo.getRestaurant_name());
		dishInfo.setUser_id(user_id);
		try {
			dishInfo.setDish_price(Double.valueOf(request.getParameter("dish_price")));
		} catch (Exception e) {
			dishInfo.setDish_price(0);
		}
		jsonObject.put("OC", new DishInfoDao().Insert(dishInfo));
	}
}
