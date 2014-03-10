package com.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.code.OC;
import com.model.DishLabelInfo;
import com.util.hibernateUtil;

public class DishLabelInfoDao {
	
	public int Insert(DishLabelInfo di)
	{
		try
		{



		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(di);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	
	
	public List getByDishId(int dish_id)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Criteria cr = session.createCriteria(DishLabelInfo.class);  //创建Criteria对象 
		java.util.List list = cr.add(Restrictions.eq("dish_id", dish_id)).list();   //添加查询条件，并获取结果集
		  
		
		
		return list;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	public List getByLabel(String label)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Criteria cr = session.createCriteria(DishLabelInfo.class);  //创建Criteria对象 
		java.util.List list = cr.add(Restrictions.eq("dish_label", label)).list();   //添加查询条件，并获取结果集
		  
		
		
		return list;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	public DishLabelInfo getByDishAndLabel(int dish_id,String label)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Criteria cr = session.createCriteria(DishLabelInfo.class);  //创建Criteria对象 
		java.util.List list = cr.add(Restrictions.eq("dish_label", label)).add(Restrictions.eq("dish_id", dish_id)).list();   //添加查询条件，并获取结果集
		  
		DishLabelInfo di = (DishLabelInfo)list.get(0);
		
		return di;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	
	public int Update(DishLabelInfo di)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(di);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	public int deleteByDishId(int dish_id)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete DishLabelInfo where dish_id=?");
		query.setInteger(0, dish_id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}

	
	public int deleteByLabel(String label)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete DishLabelInfo where dish_label=?");
		query.setString(0, label);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	public int Delete(int dish_id,String label)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete DishLabelInfo where dish_id=? and dish_label=?");
		query.setInteger(0, dish_id);
		query.setString(1, label);
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
