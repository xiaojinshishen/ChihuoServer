package com.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.code.OC;
import com.model.DishInfo;

import com.util.hibernateUtil;

public class DishInfoDao {

	
	public int Insert(DishInfo dish_info)
	{
		try
		{


		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(dish_info);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	public DishInfo getById(int dish_id)
	{
		try{
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();   
			Criteria cr = session.createCriteria(DishInfo.class);  //创建Criteria对象 
			java.util.List list = cr.add(Restrictions.eq("dish_id", dish_id)).list();   //添加查询条件，并获取结果集
			  
			DishInfo dish = (DishInfo)list.get(0);
			
			return dish;
			}catch(Exception e)
			{
				return null;
			}
	}
	
	public List<DishInfo> getByRestaurantId(int restaurant_id)
	{
		try{
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();   
			Criteria cr = session.createCriteria(DishInfo.class);  //创建Criteria对象 
			List<DishInfo> list = cr.add(Restrictions.eq("restaurant_id", restaurant_id)).list();   //添加查询条件，并获取结果集
			  
		
			
			return list;
			}catch(Exception e)
			{
				return null;
			}
	}
	
	public int Update(DishInfo dish_info)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(dish_info);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	
	public int Delete(int dish_id)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		DishInfo df = new DishInfo();
		df = (DishInfo)session.get(DishInfo.class, dish_id);
		session.delete(df);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	public int deleteRestaurantDish(int restaurant_id)
	{
		try{
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();   
			session.beginTransaction();
			Query query = session.createQuery("delete DishInfo where restaurant_id=?");   
			query.setParameter(0, restaurant_id);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			
			
			return OC.SUCCESS;
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
}
