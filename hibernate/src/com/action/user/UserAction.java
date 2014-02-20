package com.action.user;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.hibernate.testDao;

public class UserAction extends ActionInterface{
	public String getResult(HttpServletRequest request)
	{
		String res ="";
		String action = request.getParameter("action");
		if(action!=null&&action.equals("register"))
		{
			testDao td = new testDao();
			if(td.Insert(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), request.getParameter("password")))
			{
				res = "sucess";
			}
			else
				res = "false";
		}
		
		else if(action!=null&&action.equals("login"))
		{
			
		}
		
		return res;
			
		
		
	}

}
