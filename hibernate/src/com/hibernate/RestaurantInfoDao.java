package com.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.code.OC;

import com.model.Location;
import com.model.RestaurantInfo;
import com.model.UserLabel;

import com.util.hibernateUtil;

public class RestaurantInfoDao {

	
	public int Insert(RestaurantInfo ri)
	{
		try
		{


		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(ri);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	public int update(RestaurantInfo ri)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(ri);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	public RestaurantInfo getById(int id)
	{
		try{
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();   
			Criteria cr = session.createCriteria(RestaurantInfo.class);  //创建Criteria对象 
			java.util.List list = cr.add(Restrictions.eq("restaurant_id", id)).list();   //添加查询条件，并获取结果集
			  
			RestaurantInfo ri = (RestaurantInfo)list.get(0);
			
			return ri;
			}catch(Exception e)
			{
				return null;
			}
		
	}
	
	public List getByLocation(Location location)
	{
		List list = null;
		return list;
	}
	
	
}
