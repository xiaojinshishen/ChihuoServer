package com.action.user;

import java.util.List;

import net.sf.json.JSONObject;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.UserCommentDao;
import com.model.UserComment;

public class UserCommentAction extends Action {

	public void addCommet() {
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

	public void getCommentByDishId() {
		int dish_id;
		try {
			dish_id = Integer.valueOf(request.getParameter("dish_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		String last_time = request.getParameter("last_time");
		
		List<UserComment> comments;
		if (last_time == null) {
			comments = new UserCommentDao().getByDishId(dish_id);
		} else {
			comments = new UserCommentDao().getByDishId(dish_id, last_time);
		}
		
		if (comments == null) {
			jsonObject.put("OC", OC.FAILIED);
		} else {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("comment_count", comments.size());
			jsonObject.put("comments", JSONObject.fromObject(comments));
		}
	}
}
