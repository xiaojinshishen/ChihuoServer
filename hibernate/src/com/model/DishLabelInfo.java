package com.model;

public class DishLabelInfo implements java.io.Serializable{

	
	private int dish_id;
	private String dish_label;
	private int dish_label_count=0;
	private int dish_label_score=0;
	private double dish_label_average=0;
	private String date_time;
	
	
	public DishLabelInfo()
	{
		
	}


	public int getDish_id() {
		return dish_id;
	}


	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}


	public String getDish_label() {
		return dish_label;
	}


	public void setDish_label(String dish_label) {
		this.dish_label = dish_label;
	}


	public int getDish_label_count() {
		return dish_label_count;
	}


	public void setDish_label_count(int dish_label_count) {
		this.dish_label_count = dish_label_count;
	}


	public int getDish_label_score() {
		return dish_label_score;
	}


	public void setDish_label_score(int dish_label_score) {
		this.dish_label_score = dish_label_score;
	}


	public double getDish_label_average() {
		return dish_label_average;
	}


	public void setDish_label_average(double dish_label_average) {
		this.dish_label_average = dish_label_average;
	}


	public String getDate_time() {
		return date_time;
	}


	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}



	
	
}
