package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.code.OC;
import com.model.LabelInfo;
import com.util.hibernateUtil;

public class LabelInfoDao {

	
	public int Insert(String label_name,String user_id)
	{
		try
		{

		LabelInfo li = new LabelInfo();	
		li.setLabel_name(label_name);
		li.setUser_id(user_id);

		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(li);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
}
