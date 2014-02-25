package com.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


import com.model.myTest;
import com.util.hibernateUtil;

public class testDao {
	
	public boolean Insert(int id,String name,String password) 
	{
		myTest arg = new myTest();
		arg.setId(id);
		arg.setName(name);
		arg.setPassword(password);
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(arg);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	public String getPassword(String name)
	{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Criteria cr = session.createCriteria(myTest.class);  //创建Criteria对象 
		java.util.List list = cr.add(Restrictions.eq("name", name)).list();   //添加查询条件，并获取结果集
		  
		myTest t = (myTest)list.get(0);
		
		return t.getPassword();
	}
	
	public List getUsers(int id0,int id1)
	{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Query query = session.createQuery("from myTest where id between ? and ?");   
		query.setParameter(0, id0);
		query.setParameter(1, id1);
		java.util.List list = query.list();  
		
		return list;
	}
	
	

}
