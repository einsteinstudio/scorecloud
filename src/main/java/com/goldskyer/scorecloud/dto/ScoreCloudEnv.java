package com.goldskyer.scorecloud.dto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScoreCloudEnv
{
	static private ThreadLocal<ScoreCloudEnv> param = new ThreadLocal<ScoreCloudEnv>();
	private String schId;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public static ThreadLocal<ScoreCloudEnv> getParam()
	{
		return param;
	}

	public static void put(ScoreCloudEnv parameter)
	{
		param.set(parameter);
	}

	public void setSessionAttr(String key, Object object)
	{
		getRequest().getSession().setAttribute(key, object);
	}

	public void clearSession()
	{
		getRequest().getSession().invalidate();
	}

	public static ScoreCloudEnv get()
	{
		return param.get();
	}

	public void clear()
	{
		param.remove();
	}

	public static void setParam(ThreadLocal<ScoreCloudEnv> param)
	{
		ScoreCloudEnv.param = param;
	}

	public String getSchId()
	{
		return schId;
	}

	public void setSchId(String schId)
	{
		this.schId = schId;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}

}
