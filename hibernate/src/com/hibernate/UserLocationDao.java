package com.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.code.OC;
import com.model.Location;
import com.model.UserInfo;
import com.model.UserLabel;
import com.model.UserLocation;
import com.util.hibernateUtil;

public class UserLocationDao {
	
	public int insert(String id,Location location)   
	{
		try
		{

	    UserLocation ul = new UserLocation();
	    ul.setUser_id(id);
	    ul.setLongitude(location.getLongitude());
	    ul.setLatitude(location.getLatitude());
	    
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(ul);
		session.getTransaction().commit();
		session.close();
		
		return OC.SUCCESS;
		
		}catch(Exception e)
		{
			
			
		
			return OC.FAILIED;
		}
	}
	
	public Location getLastLocation(String id)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		String sql = "select * from user_location where user_id='"+id+"'order by date_time desc limit 1";
		Query query = session.createSQLQuery(sql).addEntity(UserLocation.class);
		List list = query.list();  
		UserLocation ul = (UserLocation)list.get(0);
		
		Location location = new Location();
		location.setLatitude(ul.getLatitude());
		location.setLongitude(ul.getLongitude());
		
		session.close();
		
		
		return location;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	public List getTrajectory(String id)
	{
		try{
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();   
		String sql = "select * from user_location where user_id='"+id+"' order by date_time desc limit 5";
		List list = session.createSQLQuery(sql).addEntity(UserLocation.class).list();
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
