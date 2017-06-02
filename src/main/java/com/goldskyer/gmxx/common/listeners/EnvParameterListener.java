package com.goldskyer.gmxx.common.listeners;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.core.SpringContextHolder;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.CmsService;
import com.goldskyer.core.service.IniService;
import com.goldskyer.gmxx.common.constants.IniConstant;
import com.goldskyer.gmxx.common.dtos.Ssn;
import com.goldskyer.gmxx.common.helpers.LangHelper;
import com.goldskyer.gmxx.common.service.LoginService;
import com.goldskyer.gmxx.manager.utils.CookieUtil;

@SuppressWarnings("rawtypes")

public class EnvParameterListener extends HttpServlet implements Filter
{

	private Log log = LogFactory.getLog(EnvParameterListener.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException
	{

		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		if (!StringUtils.equals("/", request.getRequestURI()) && !request.getRequestURI().endsWith(".htm")
				&& !request.getRequestURI().endsWith(".json")
				&& !request.getRequestURI().endsWith(".jsonp"))
		{
			chain.doFilter(request, response);
			return;
		}

		EnvParameter parameter = new EnvParameter();
		EnvParameter.put(parameter);
		log.info("request info:" + request.getServerName() + ",current sId:" + request.getSession().getId());
		IniService iniService = SpringContextHolder.getBean("iniService");

		//目前没有系统配置，暂时使用固定的写法表示，系统配置与DOMAIN无关，是关于DOMAIN的配置
		if ("|xcxx.goldskyer.com|xcxxtest.goldskyer.com|xcxx.szgm.edu.cn|xcxx.goldskyer.com|xcxxback.goldskyer.com|192.168.18.9|110.65.138.131|192.168.0.122"
				.contains("|" + request.getServerName() + "|"))
		{
			parameter.setDomain("xcxx.goldskyer.com");
		}
		else if ("|gmxxwgw.szgm.edu.cn|".contains("|" + request.getServerName() + "|"))
		{
			parameter.setDomain("gmxx.goldskyer.com");
		}
		else
		{
			parameter.setDomain(request.getServerName());
		}
		log.info("current domain:" + parameter.getDomain() + ",cur Url:" + request.getRequestURI());

		//		if (request.getRequestURI().contains("/manager/assets"))
		//		{
		//			response.setHeader("Cache-Control", "max-age=" + 3600 * 24 * 1000);
		//			response.setDateHeader("Last-Modified", new Date().getTime());
		//			long relExpiresInMillis = System.currentTimeMillis() + (1000 * 259200);
		//			response.setDateHeader("Expires", new Date().getTime() + (1000 * 259200));
		//
		//		}
		//如果访问特定的js，则使用缓存
		//域名映射


		//		IniService iniService = SpringContextHolder.getBean("iniService");
		//		iniService.getIniValue("DOMAIN_MAP", "");
		//兼容本地测试环境
		if (parameter.getDomain() == null || parameter.getDomain().startsWith("127.0.0.1")
				|| parameter.getDomain().startsWith("192.168.0.122")
				|| parameter.getDomain().startsWith("localhost") || parameter.getDomain().startsWith("10.242.27.93"))
		{
			parameter.setDomain("xcxx.goldskyer.com");
			parameter.setEnv("dev");

		}
		//登陆用户覆盖默认的domian
		if (request.getSession().getAttribute("accountId") != null)
		{
			EnvParameter.get().setAccountId((String) request.getSession().getAttribute("accountId"));
			EnvParameter.get().setAccount((Account)request.getSession().getAttribute("account"));
			EnvParameter.get().setDomain((String) request.getSession().getAttribute("domain"));
			EnvParameter.get().setLogined(true);
		}
		else
		{
			Ssn ssn = CookieUtil.getSsnFromCookie(request);
			if (StringUtils.isNotBlank(ssn.getAccountId())
					&& (System.currentTimeMillis() - ssn.getLoginDate().getTime()) < (30l * 24 * 3600 * 1000))
			{
				LoginService  loginService=SpringContextHolder.getBean("loginService");
				EnvParameter.get().setAccountId(ssn.getAccountId());
				EnvParameter.get().setDomain(ssn.getDomain());
				AccountService accountService = SpringContextHolder.getBean("accountServiceImp");
				Account account=accountService.getAccountById(ssn.getAccountId());
				EnvParameter.get().setAccount(account);
				EnvParameter.get().setLogined(true);
				loginService.loginSessionSave(account, request, response);
			}
		}
			
		parameter.setIp(request.getRemoteAddr());
		parameter.setRequest(request);
		parameter.setResponse(response);
		//设置全局域名
		request.setAttribute("realDomain", parameter.getDomain() + ":" + request.getServerPort());
		request.setAttribute("frontDomain", request.getServerName() + ":" + request.getServerPort());
		request.setAttribute("backDomain",
				iniService.getIniValue(IniConstant.BACK_DOMAIN) + ":" + request.getServerPort());
		log.info("realDomain:" + parameter.getDomain() + ",frontDomain:" + request.getServerName() + ",backDomain:"
				+ iniService.getIniValue(IniConstant.BACK_DOMAIN));
		//设置语言
		String n18 = CookieUtil.getCookieValue(request, "n18");
		if (StringUtils.isNotBlank(n18))
			parameter.setN18(n18);
		request.setAttribute("n18", parameter.getN18());
		EnvParameter parameter2 = EnvParameter.get();
		CmsService cmsService =SpringContextHolder.getBean("cmsService");
		request.setAttribute("cms", cmsService.queryCurrentRequestCmsMap());
		//debug
		SpringContextHolder.sc.setAttribute("lang", LangHelper.buildLangMap());
		//log.info("当前Env参数：" + EnvParameter.get().toString());
		if (StringUtils.equals("dev", parameter.getEnv()))
		{
			request.setAttribute("cdnDomain", "test.goldskyer.com");
			request.setAttribute("storageDomain", "test.goldskyer.com");
		}
		else
		{
			request.setAttribute("cdnDomain", iniService.getIniValue("cdnDomain"));
			request.setAttribute("storageDomain", iniService.getIniValue("storageDomain"));
		}

		chain.doFilter(request, response);
		EnvParameter.get().clear();
	}

	public static String getGMTTimeString(long milliSeconds)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'");
		return sdf.format(new Date(milliSeconds));
	}


}
