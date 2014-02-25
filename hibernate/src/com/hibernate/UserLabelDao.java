package com.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.code.OC;
import com.model.UserLabel;
import com.util.hibernateUtil;

public class UserLabelDao {
	
	public int insert(UserLabel ub)   
	{
		try
		{

	
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(ub);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			
			
		
			return OC.FAILIED;
		}
	}
	
	public UserLabel getById(String id)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Criteria cr = session.createCriteria(UserLabel.class);  //创建Criteria对象 
		java.util.List list = cr.add(Restrictions.eq("user_id", id)).list();   //添加查询条件，并获取结果集
		  
		UserLabel user = (UserLabel)list.get(0);
		
		return user;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	public int update(UserLabel ub)
	{
		try
		{
		
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(ub);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}

}
