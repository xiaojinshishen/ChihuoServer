package com.action.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


import net.sf.json.JSONArray;

import com.action.Action;
import com.code.OC;
import com.code.RC;
import com.hibernate.DishHistoryDao;
import com.hibernate.DishInfoDao;
import com.hibernate.DishLabelInfoDao;
import com.hibernate.RestaurantInfoDao;
import com.hibernate.UserInfoDao;
import com.hibernate.UserLabelDao;
import com.model.DishInfo;
import com.model.DishLabelInfo;
import com.model.Location;
import com.model.RestaurantInfo;
import com.model.UserInfo;
import com.model.UserLabel;

public class RestaurantInfoAction extends Action {

	public void insert() {
		String user_id, user_password, restaurant_name;
		double longitude, latitude;
		try {
			user_id = request.getParameter("user_id").trim();
			user_password = request.getParameter("user_password").trim();
			restaurant_name = request.getParameter("restaurant_name").trim();
			longitude = Double.valueOf(request.getParameter("longitude").trim());
			latitude = Double.valueOf(request.getParameter("latitude").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		UserInfo userInfo;
		try {
			userInfo = new UserInfoDao().getById(user_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (userInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
		} else if (!userInfo.getUser_password().equals(user_password)) {
			jsonObject.put("OC", OC.WRONG_PASSWORD);
		} else if (userInfo.getUser_type().equals("customer")) {
			jsonObject.put("OC", OC.INSUFFICIENT_PRIVILEGES);
		} else {
			RestaurantInfo restaurantInfo = new RestaurantInfo();
			restaurantInfo.setRestaurant_name(restaurant_name);
			restaurantInfo.setLongitude(longitude);
			restaurantInfo.setLatitude(latitude);
			restaurantInfo.setUser_id(user_id);

			try {
				jsonObject.put("OC", new RestaurantInfoDao().Insert(restaurantInfo));
			} catch (Exception e) {
				jsonObject.put("RC", RC.SQL_EXCEPTION);
				return;
			}
		}
	}

	public void getRestaurantByLocation() {
		double longitude, latitude;
		try {
			longitude = Double.valueOf(request.getParameter("longitude").trim());
			latitude = Double.valueOf(request.getParameter("latitude").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		Location location = new Location();
		location.setLongitude(longitude);
		location.setLatitude(latitude);

		List<RestaurantInfo> list;
		try {
			list = new RestaurantInfoDao().getByLocation(location);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (list == null) {
			jsonObject.put("OC", OC.FAILIED);
			return;
		} else {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("restaurant_count", list.size());
			jsonObject.put("restaurants", JSONArray.fromObject(list));
		}
	}

	public void getDishInfo() {
		int restaurant_id;
		try {
			restaurant_id = Integer.valueOf(request.getParameter("restaurant_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		RestaurantInfo restaurantInfo;
		try {
			restaurantInfo = new RestaurantInfoDao().getById(restaurant_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (restaurantInfo == null) {
			jsonObject.put("OC", OC.UNKNOWN_RESTAURANT_ID);
			return;
		}

		List<DishInfo> list;
		try {
			list = new DishInfoDao().getByRestaurantId(restaurant_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (list == null) {
			jsonObject.put("OC", OC.FAILIED);
		} else {
			jsonObject.put("OC", OC.SUCCESS);
			jsonObject.put("dish_count", list.size());
			jsonObject.put("dishes", JSONArray.fromObject(list));
		}
	}

	public void getRecomemdDishInfo() {
		String user_id;
		int restaurant_id;
		try {
			user_id = request.getParameter("user_id");
			restaurant_id = Integer.valueOf(request.getParameter("restaurant_id").trim());
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		UserLabel userLabel;
		try {
			userLabel = new UserLabelDao().getById(user_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (userLabel == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}

		List<DishInfo> dishList;
		List<Integer> dishIdList = new ArrayList<Integer>();
		try {
			dishList = new DishInfoDao().getByRestaurantId(restaurant_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (dishList == null) {
			jsonObject.put("OC", OC.FAILIED);
			return;
		} else {
			DishLabelInfoDao dishLabelInfoDao = new DishLabelInfoDao();
			for (DishInfo dishInfo : dishList) {
				dishIdList.add(dishLabelInfoDao.getByDishAndLabel(dishInfo.getDish_id(), userLabel.getUser_label1()).getDish_id());
				dishIdList.add(dishLabelInfoDao.getByDishAndLabel(dishInfo.getDish_id(), userLabel.getUser_label2()).getDish_id());
				dishIdList.add(dishLabelInfoDao.getByDishAndLabel(dishInfo.getDish_id(), userLabel.getUser_label3()).getDish_id());
				dishIdList.add(dishLabelInfoDao.getByDishAndLabel(dishInfo.getDish_id(), userLabel.getUser_label4()).getDish_id());
				dishIdList.add(dishLabelInfoDao.getByDishAndLabel(dishInfo.getDish_id(), userLabel.getUser_label5()).getDish_id());
			}
		}
		jsonObject.put("OC", OC.SUCCESS);
		jsonObject.put("dish_id_count", dishIdList.size());
		jsonObject.put("dish_ids", JSONArray.fromObject(dishIdList));
	}

	public void getRecomemdDishInfoByRestaurantIdList() {
		String user_id;
		int RI_count;
		String RIs;	//restaurant_id list with json format.
		List<Integer> RIList;	//restaurant_id list
		try {
			user_id = request.getParameter("user_id").trim();
			RI_count = Integer.valueOf(request.getParameter("RI_count").trim());
			RIs = request.getParameter("RTs").trim();
			//convert a json parameter to a list.
			RIList = JSONArray.toList(JSONArray.fromObject(RIs), Integer.class);
		} catch (Exception e) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}
		if (RI_count == 0) {
			jsonObject.put("RC", RC.PARAMETER_ERROR);
			return;
		}

		//get userLabel from DB.
		UserLabel userLabel;
		try {
			userLabel = new UserLabelDao().getById(user_id);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (userLabel == null) {
			jsonObject.put("OC", OC.UNKNOWN_USER_ID);
			return;
		}

		//get user label map.
		Map<String, Integer> userLabelMap = getUserLabelMap(userLabel);
		if (userLabelMap.isEmpty()) {
			jsonObject.put("OC", OC.EMPTY_USER_LABEL);
			return;
		}

		//get dish info list.
		List<DishInfo> dishInfoList;
		try {
			dishInfoList = getDishInfoList(RIList);
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		if (dishInfoList.isEmpty()) {
			jsonObject.put("OC", OC.EMPTY_DISH_INFO);
			return;
		}

		//recommend map with dish_id and weight.
		Map<Integer, Double> dishMap;
		try {
			dishMap = calcWeightOfDishes(userLabelMap, dishInfoList);
		} catch (Exception e) {
			jsonObject.put("OC", OC.FAILIED);
			return;
		}

		//sort in decrease.
		List<Map.Entry<Integer, Double>> recommendList = new ArrayList<Entry<Integer, Double>>(dishMap.entrySet());
		Collections.sort(recommendList, new Comparator<Map.Entry<Integer, Double>>() {
			public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		List<Integer> dishList;
		try {
			//compare with dish history.
			dishList = compareWithHistory(user_id, recommendList);
		} catch (Exception e) {
			jsonObject.put("OC", OC.FAILIED);
			return;
		}
		
		//recommend dishinfo list
		List<DishInfo> recommendDishList = new ArrayList<DishInfo>();
		DishInfoDao dishInfoDao;
		try {
			dishInfoDao = new DishInfoDao();
		} catch (Exception e) {
			jsonObject.put("RC", RC.SQL_EXCEPTION);
			return;
		}
		DishInfo dishInfo;
		for (Integer dish_id : dishList) {
			dishInfo = dishInfoDao.getById(dish_id);
			if(dishInfo != null)
				recommendDishList.add(dishInfo);
		}
		
		jsonObject.put("OC", OC.SUCCESS);
		jsonObject.put("dish_info_count", recommendDishList.size());
		jsonObject.put("dish_infos", JSONArray.fromObject(recommendDishList));
	}

	//recording the user label, return a Map with label and it's value.
	private Map<String, Integer> getUserLabelMap(UserLabel userLabel) {

		Map<String, Integer> userLabelMap = new HashMap<String, Integer>();

		String label;
		int value;
		try {
			label = userLabel.getUser_label1();
			value = Integer.valueOf(userLabel.getUser_label_value1());
			if (label != null && !label.isEmpty() && value != 0)
				userLabelMap.put(label, value);
		} catch (Exception e) {
		}
		try {
			label = userLabel.getUser_label2();
			value = Integer.valueOf(userLabel.getUser_label_value2());
			if (label != null && !label.isEmpty() && value != 0)
				userLabelMap.put(label, value);
		} catch (Exception e) {
		}
		try {
			label = userLabel.getUser_label3();
			value = Integer.valueOf(userLabel.getUser_label_value3());
			if (label != null && !label.isEmpty() && value != 0)
				userLabelMap.put(label, value);
		} catch (Exception e) {
		}
		try {
			label = userLabel.getUser_label4();
			value = Integer.valueOf(userLabel.getUser_label_value4());
			if (label != null && !label.isEmpty() && value != 0)
				userLabelMap.put(label, value);
		} catch (Exception e) {
		}
		try {
			label = userLabel.getUser_label5();
			value = Integer.valueOf(userLabel.getUser_label_value5());
			if (label != null && !label.isEmpty() && value != 0)
				userLabelMap.put(label, value);
		} catch (Exception e) {
		}

		return userLabelMap;
	}

	//get a dishinfo list by restaurant_id list.
	private List<DishInfo> getDishInfoList(List<Integer> RIList) throws Exception {
		List<DishInfo> dishInfoList = new ArrayList<DishInfo>();
		DishInfoDao dishInfoDao = new DishInfoDao();

		List<DishInfo> list;
		for (Integer RI : RIList) {
			list = dishInfoDao.getByRestaurantId(RI);
			if (list != null && !list.isEmpty())
				dishInfoList.addAll(list);
		}
		return dishInfoList;
	}

	//calc weight of dishes
	private Map<Integer, Double> calcWeightOfDishes(Map<String, Integer> userLabelMap
			, List<DishInfo> dishInfoList) throws Exception  {
		Set<String> labelSet = userLabelMap.keySet();
		Map<Integer, Double> dishMap = new TreeMap<Integer, Double>();
		double weight;
		String label;
		int value;
		DishLabelInfo dishLabelInfo;
		DishLabelInfoDao dishLabelInfoDao = new DishLabelInfoDao();

		//traversal dishes and get their labels.
		for (DishInfo dishInfo : dishInfoList) {
			weight = 0;
			//traversal user labels, calc the weight.
			for (Iterator<String> it=labelSet.iterator(); it.hasNext();) {
				label = it.next();
				value = userLabelMap.get(label);
				dishLabelInfo = dishLabelInfoDao.getByDishAndLabel(dishInfo.getDish_id(), label);
				if (dishLabelInfo != null) {
					//calc weight, dish_label_score plus user_label_value.
					weight += dishLabelInfo.getDish_label_score() * value;
				}
			}
			//add answer to recommend dish map.
			dishMap.put(dishInfo.getDish_id(), weight);
		}

		return dishMap;
	}

	//compare with history, remove the repeat dish.
	private List<Integer> compareWithHistory(String user_id
			, List<Map.Entry<Integer, Double> > recommendList) throws Exception {

		List<Integer> dishList = new ArrayList<Integer>();
		DishHistoryDao dishHistoryDao = new DishHistoryDao();

		List<?> list;
		for (Entry<Integer, Double> entry : recommendList) {
			list = dishHistoryDao.getByUserIdAndDishId(user_id, entry.getKey());
			if (list == null || list.isEmpty())
				dishList.add(entry.getKey());
		}
		if (dishList.size() >= 10)
			dishList = dishList.subList(0, 10);
		return dishList;
	}

}
