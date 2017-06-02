package com.goldskyer.scorecloud.listener;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.gmxx.common.dtos.Ssn;
import com.goldskyer.gmxx.manager.utils.CookieUtil;
import com.goldskyer.scorecloud.business.LoginBusiness;
import com.goldskyer.scorecloud.constant.SessionConstant;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;

public class ScoreCloudHandler extends HandlerInterceptorAdapter
{
	private Log log = LogFactory.getLog(ScoreCloudHandler.class);
	@Autowired
	private LoginBusiness loginBusiness;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		ScoreCloudEnv env = new ScoreCloudEnv();
		ScoreCloudEnv.put(env);
		env.setSchId("xcxx");
		env.setRequest(request);
		env.setResponse(response);
		EnvParameter.get().setDomain("xcxx.goldskyer.com");
		if (!checkLogin(request, response))
		{
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String openUrl = ";/scorecloud/login/xcxx.htm;/scorecloud/doLogin.json;" + "";
		log.info("requestURI:" + request.getRequestURI());
		if (!openUrl.contains(";" + request.getRequestURI() + ";"))
		{
			if (request.getSession().getAttribute(SessionConstant.USER_DTO) == null)
			{
				//判断cookie中是否有登陆信息
				Ssn ssn = CookieUtil.getSsnFromCookie(request);
				if (StringUtils.isNotBlank(ssn.getAccountId())
						&& (System.currentTimeMillis() - ssn.getLoginDate().getTime()) < (30l * 24 * 3600 * 1000))
				{
					loginBusiness.doLogin(ssn.getAccountId());
					return true;
				}
				else
				{
					String loginUrl = "/scorecloud/login/" + ScoreCloudEnv.get().getSchId() + ".htm";
					response.sendRedirect(loginUrl);
					return false;
				}
			}
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{
		ScoreCloudEnv.get().clear();
	}
}
