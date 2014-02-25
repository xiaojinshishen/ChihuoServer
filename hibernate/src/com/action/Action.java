package com.action;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.code.RC;


import net.sf.json.JSONObject;

public abstract class Action {
	protected boolean debug = true;
	protected HttpServletRequest request;
	protected JSONObject jsonObject = new JSONObject();

	public JSONObject getResult(HttpServletRequest request) {
		jsonObject.element("RC", RC.SUCCESS);
		jsonObject.element("action_class", request.getParameter("action_class"));
		this.request = request;
		try {
			Method method = this.getClass().getDeclaredMethod(request.getParameter("action"));
			method.invoke(this);
			jsonObject.element("action", request.getParameter("action"));
		} catch (Exception e) {
			jsonObject.element("RC", RC.NO_SUCH_METHOD);
		}
		return jsonObject;
	}
}
