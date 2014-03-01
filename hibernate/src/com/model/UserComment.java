package com.model;

public class UserComment implements java.io.Serializable{
	
	private int comment_id;
	private String user_id;
	private int dish_id;
	private String visible="show";
	private String user_comment;
	private String date_time;
	
	
	public String getDate_time() {
		return date_time;
	}


	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}


	public int getComment_id() {
		return comment_id;
	}


	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}


	


	public String getUser_comment() {
		return user_comment;
	}


	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}


	public UserComment()
	{
		
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public int getDish_id() {
		return dish_id;
	}


	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}


	public String getVisible() {
		return visible;
	}


	public void setVisible(String visible) {
		this.visible = visible;
	}
	

}
