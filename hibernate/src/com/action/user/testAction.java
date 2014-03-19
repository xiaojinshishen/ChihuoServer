package com.action.user;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.action.Action;
import com.hibernate.DishHistoryDao;
import com.hibernate.DishLabelInfoDao;
import com.hibernate.RestaurantInfoDao;
import com.hibernate.UserCommentDao;
import com.hibernate.UserInfoDao;
import com.hibernate.UserLocationDao;
import com.model.DishHistory;
import com.model.DishLabelInfo;
import com.model.Location;
import com.model.RestaurantInfo;
import com.model.UserComment;
import com.model.UserInfo;
import com.model.UserLocation;


public class testAction extends Action{

	public testAction() {
		this.jsonObject = new JSONObject();
		jsonObject.element("RC", "-2");
	}

	public JSONObject register() {
		
		//DishLabelInfoDao dd = new DishLabelInfoDao(); 
		//DishLabelInfo di = new DishLabelInfo();
		//di.setDish_id(1);
	//	di.setDish_label("test1");
		
		//dd.Insert(di);
		//dd.Delete(1, "test1");
		//dd.Delete(1, "test");
     //   UserInfoDao ud = new UserInfoDao();
      //  UserInfo ui = new UserInfo();
     //   ui.setUser_id("hehe");
    //    ui.setUser_password("123456789");
    //    ud.update(ui);
     //   ui = ud.getById("hehe");
      //  System.out.print(ui.getUser_password());
		
		RestaurantInfoDao dd = new RestaurantInfoDao();
		
		Location location = new Location();
		location.setLatitude(2);
		location.setLongitude(1);
		List<RestaurantInfo> list = dd.getByLocation(location);
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getRestaurant_name());
		}

		
		


		
		JSONObject jb = new JSONObject();
		
		return jb;
	}

	public JSONObject login() {
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
		return jsonObject;
	}
	
	
	
	
}
