package com.action.user;

import java.util.List;

import com.action.Action;
import com.code.RC;
import com.hibernate.UserCommentDao;
import com.model.UserComment;

public class UserCommentAction extends Action {
	
	public void insert() {
		UserComment userComment = new UserComment();
		try {
			userComment.setUser_id(request.getParameter("user_id").trim());
			userComment.setDish_id(Integer.valueOf(request.getParameter("dish_id").trim()));
			userComment.setUser_comment(request.getParameter("user_comment").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		jsonObject.put("OC", new UserCommentDao().Insert(userComment));
	}
	
	public void getByDishId() {
		int dish_id;
		try {
			dish_id = Integer.valueOf(request.getParameter("dish_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		List<UserComment> comments = new UserCommentDao().getByDishId(dish_id);
	}
}