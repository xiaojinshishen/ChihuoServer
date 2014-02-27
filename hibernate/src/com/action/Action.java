package com.action;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.code.OC;
import com.code.RC;

import net.sf.json.JSONObject;

public abstract class Action {
	protected boolean debug = true;
	protected HttpServletRequest request;
	protected JSONObject jsonObject = new JSONObject();

	public JSONObject getResult(HttpServletRequest request) {
		jsonObject.put("RC", RC.SUCCESS);
		jsonObject.put("OC", OC.FAILIED);
		this.request = request;
		String action = request.getParameter("action");
		Method method;
		try {
			method = this.getClass().getDeclaredMethod(action);
			method.setAccessible(true);
			method.invoke(this);
		} catch (Exception e) {
			jsonObject.put("RC", RC.NO_SUCH_METHOD);
		}
		return jsonObject;
	}
}
