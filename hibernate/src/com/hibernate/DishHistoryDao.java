package com.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.code.OC;
import com.model.DishHistory;
import com.model.UserLocation;
import com.util.hibernateUtil;

public class DishHistoryDao {

	
	public int Insert(DishHistory dh)
	{
		try
		{


		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(dh);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	public List getByUserId(String user_id)
	{
		try{
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();   
			String sql = "select * from dish_history where user_id='"+user_id+"' order by date_time desc limit 10";
			List list = session.createSQLQuery(sql).addEntity(DishHistory.class).list();
			//List<UserLocation> list = new ArrayList<UserLocation>();
			
			//list = query.list();  
			
			session.close();
			

			
			
			return list;
			}catch(Exception e)
			{
				return null;
			}
	}

	public List getByUserIdAndDishId(String user_id, int dish_id)
	{
		try{
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();   
			String sql = "select * from dish_history where user_id='"+user_id+"' and dish_id='"+dish_id+"' and DATEDIFF(CURDATE(), date_time) <= 7 order by date_time desc";
			List list = session.createSQLQuery(sql).addEntity(DishHistory.class).list();
			//List<UserLocation> list = new ArrayList<UserLocation>();
			
			//list = query.list();  
			
			session.close();
			

			
			
			return list;
			}catch(Exception e)
			{
				return null;
			}
	}
}
