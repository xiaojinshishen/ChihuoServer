package com.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.code.OC;
import com.model.DishLabelRecord;
import com.util.hibernateUtil;

public class DishLabelRecordDao {
	
	public int Insert(DishLabelRecord dr)
	{
		try
		{



		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(dr);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			return OC.FAILIED;
		}
	}
	
	public DishLabelRecord getByDishAndLabel(int dish_id,String label)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		Criteria cr = session.createCriteria(DishLabelRecord.class);  //创建Criteria对象 
		java.util.List list = cr.add(Restrictions.eq("labelName", label)).add(Restrictions.eq("dishId", dish_id)).list();   //添加查询条件，并获取结果集
		  
		DishLabelRecord dr = (DishLabelRecord)list.get(0);
		
		return dr;
		}catch(Exception e)
		{
			return null;
		}
	}

}
