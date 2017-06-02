package com.goldskyer.gmxx.common.interceptors;

/**
 * AppID(应用ID):wxd78675ef4a833a68
 * AppSecret(应用密钥) a2c77f37a4d879ab9847999c2cd2d571
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class GmxxInterceptor extends HandlerInterceptorAdapter {
	private static Log log = LogFactory.getLog(GmxxInterceptor.class);
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		Object aObject = handler;
		System.out.println(handler);
	}
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("在请求结束后:" + response);
	}
}
