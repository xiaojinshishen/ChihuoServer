package com.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.model.myTest;
import com.model.userInfo;
import com.util.hibernateUtil;

public class userInfoDao {
	
	public int Insert(String id,String psw)   
	{
		try
		{
		userInfo user = new userInfo();
		user.setUser_id(id);
	
		user.setUser_password(psw);
		//user.setUser_type("0");  
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		return 0;
		
		}catch(Exception e)
		{
			
			
		
			return 1;
		}
	}
	
	public userInfo getById(String id)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Criteria cr = session.createCriteria(userInfo.class);  //创建Criteria对象 
		java.util.List list = cr.add(Restrictions.eq("user_id", id)).list();   //添加查询条件，并获取结果集
		  
		userInfo user = (userInfo)list.get(0);
		
		return user;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	public int Update(userInfo user)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
		
		return 0;
		
		}catch(Exception e)
		{
			return 1;
		}
	}
	
	

}
