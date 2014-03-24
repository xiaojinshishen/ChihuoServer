package com.action.dish;

import java.util.List;

import net.sf.json.JSONObject;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.model.DishHistory;
import com.hibernate.DishHistoryDao;

public class DishHistoryAction extends Action {

	public void addDishHistory() {
		DishHistory dishHistory = new DishHistory();
		try {
			dishHistory.setDish_id(Integer.valueOf(request.getParameter("dish_id").trim()));
			dishHistory.setUser_id(request.getParameter("user_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		try {
			jsonObject.put("OC", new DishHistoryDao().Insert(dishHistory));
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
	}

	public void getDishHistory() {
		String user_id = request.getParameter("user_id");
		int dish_id;
		try {
			dish_id = Integer.valueOf(request.getParameter("dish_id"));
		} catch (Exception e) {
			dish_id = 0;
		}

		if (user_id == null && dish_id == 0) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
		} else if (user_id == null && dish_id != 0) {
			List<?> list;
			try {
				list = new DishHistoryDao().getByUserId(user_id);
			} catch (Exception e) {
				jsonObject.put("RC", RC.SQL_EXCEPTION);
				return;
			}
			if (list == null) {
				jsonObject.put("OC", OC.FAILIED);
			} else {
				jsonObject.put("OC", OC.SUCCESS);
				jsonObject.put("dish_history_count", list.size());
				jsonObject.put("dish_histories", JSONObject.fromObject(list));
			}
		}
	}
}
