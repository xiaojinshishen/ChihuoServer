package com.model;

public class DishHistory implements java.io.Serializable{

	private String user_id;
	private int dish_id;
	private String date_time;
	
	public DishHistory()
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

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((date_time == null) ? 0 : date_time.hashCode());
		result = prime * result + dish_id;
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DishHistory other = (DishHistory) obj;
		if (date_time == null) {
			if (other.date_time != null)
				return false;
		} else if (!date_time.equals(other.date_time))
			return false;
		if (dish_id != other.dish_id)
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
	
	
}
