package com.model;

public class DishInfo implements java.io.Serializable{

	
	private int dish_id;
	private String dish_name;
	private double dish_price;
	private int restaurant_id;
	private String restaurant_name;
	private String user_id;
	private String date_time;
	
	
	public DishInfo()
	{
		
	}


	public int getDish_id() {
		return dish_id;
	}


	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}


	public String getDish_name() {
		return dish_name;
	}


	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}





	public double getDish_price() {
		return dish_price;
	}


	public void setDish_price(double dish_price) {
		this.dish_price = dish_price;
	}


	public int getRestaurant_id() {
		return restaurant_id;
	}


	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}


	public String getRestaurant_name() {
		return restaurant_name;
	}


	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getDate_time() {
		return date_time;
	}


	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	
	
}
