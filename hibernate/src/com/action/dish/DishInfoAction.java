package com.action.dish;

import com.action.Action;
import com.code.RC;
import com.model.DishInfo;

public class DishInfoAction extends Action {
	
	public void addDishInfo() {
		DishInfo dishInfo = new DishInfo();
		try {
			dishInfo.setDish_name(request.getParameter("dish_name").trim());
			dishInfo.setRestaurant_id(Integer.valueOf(request.getParameter("restaurant_id").trim()));
			dishInfo.setUser_id(request.getParameter("user_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		try {
			dishInfo.setDish_price(Double.valueOf(request.getParameter("dish_price")));
		} catch (Exception e) {
			dishInfo.setDish_price(0);
		}
	}
}
