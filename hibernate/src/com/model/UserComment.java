package com.model;

public class UserComment implements java.io.Serializable{
	
	private String user_id;
	private int dish_id;
	private String visible="show";
	private String comment;
	
	
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
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
