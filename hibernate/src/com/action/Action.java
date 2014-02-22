package com.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public abstract class Action {
	protected HttpServletRequest request;
	protected JSONObject jsonObject;
	
	public abstract JSONObject getResult(HttpServletRequest request);

}
