package com.goldskyer.gmxx.manager.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.IniService;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.acl.service.RoleMenuService;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private IniService iniService;
	@Autowired
	@Qualifier("cachedMenuService")
	private CachedMenuService cachedMenuService;

	@Autowired
	private RoleMenuService roleMenuService;

	@Autowired
	private AclService aclService;

	private Log log = LogFactory.getLog(AuthInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.info("AuthInterceptors在Action执行前:" + request.getRequestURI());
		String openUrl = ";/manager/logout.htm;/manager/like.json;/manager/login.htm;"
				+ iniService.getIniValue("OPEN_URL") + ";";
		if(!openUrl.contains(";"+request.getRequestURI()+";"))
		{
			if(!StringUtils.isNotBlank((String)request.getSession().getAttribute("accountId")))
			{
				response.sendRedirect("/manager/login.htm");
				return false;
			}
		}
		request.setAttribute("rightMap", request.getSession().getAttribute("rightMap"));
		request.setAttribute("authMenuMap", request.getSession().getAttribute("authMenuMap"));
		Object sObject = request.getSession().getAttribute("roledMenu");
		request.setAttribute("roledMenu", request.getSession().getAttribute("roledMenu")); //
		request.setAttribute("roledEnMenu", request.getSession().getAttribute("roledEnMenu")); //
		return super.preHandle(request, response, handler);
	}
}
