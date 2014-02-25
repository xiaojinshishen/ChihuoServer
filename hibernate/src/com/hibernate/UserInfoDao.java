package com.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.code.OC;
import com.model.UserInfo;
import com.util.hibernateUtil;

public class UserInfoDao {
	
	public int insert(String id,String psw)   
	{
		try
		{
		UserInfo user = new UserInfo();
		user.setUser_id(id);
	
		user.setUser_password(psw);
		//user.setUser_type("0");  
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			
			
		
			return OC.FAILIED;
		}
	}
	
	public UserInfo getById(String id)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Criteria cr = session.createCriteria(UserInfo.class);  //创建Criteria对象 
		java.util.List list = cr.add(Restrictions.eq("user_id", id)).list();   //添加查询条件，并获取结果集
		  
		UserInfo user = (UserInfo)list.get(0);
		
		return user;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	public int update(UserInfo user)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	

}
