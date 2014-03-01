package com.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.code.OC;

import com.model.UserComment;
import com.model.UserLocation;
import com.util.hibernateUtil;

public class UserCommentDao {
	
	public int Insert(UserComment uc)   
	{
		try
		{


		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(uc);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			
			
		
			return OC.FAILIED;
		}
	}
	
	public List<UserComment> getByDishId(int dish_id,String lasttime)
	{
		try{
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			
			String sql = "select * from user_comment where dish_id="+dish_id+" and date_time>'"+lasttime+"' order by date_time desc limit 10";
			List list = session.createSQLQuery(sql).addEntity(UserComment.class).list();
			//List<UserLocation> list = new ArrayList<UserLocation>();
			
			//list = query.list();  
			
			session.close();
			

			
			
			return list;
			}catch(Exception e)
			{
				return null;
			}
	}
	
	public List<UserComment> getByDishId(int dish_id)
	{
		try{
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			
			
			String sql = "select * from user_comment where dish_id="+dish_id+" order by date_time desc limit 10";
			List list = session.createSQLQuery(sql).addEntity(UserComment.class).list();
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
