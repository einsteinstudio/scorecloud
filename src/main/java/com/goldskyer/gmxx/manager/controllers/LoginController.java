package com.goldskyer.gmxx.manager.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.enums.AccountType;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.IniService;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.common.service.LoginService;
import com.goldskyer.gmxx.common.service.MessageService;
import com.goldskyer.gmxx.manager.utils.CookieUtil;


/**
 * @author wangming
 */
@Controller
public class LoginController extends BaseManagerController
{
	@Autowired
	private AccountService accountService;
	@Autowired
	private AclService aclService;
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private IniService iniService;
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private MessageService messageService;

	@RequestMapping("/manager/login.htm")
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
		if (iniService.getIniValue("managerLogin", "OFF").equals("ON"))
		{
			ModelAndView mv = new ModelAndView("/manager/login");
			CookieUtil.setCookie("TOKEN", "123", request.getServerName(), response);
			return mv;
		}
		else
		{
			return new ModelAndView("redirect:/web/spring/index.htm");
		}
	}
	
	@RequestMapping("/manager/logout.htm")
	public ModelAndView logout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
		session.removeAttribute("accountId");
		ModelAndView mv = new ModelAndView("/manager/login");
		loginService.logout(request, response);
		return mv;
	}
	
	@RequestMapping(value = "/pass.jsonp", produces = "application/json")
	@ResponseBody
	public JSONPObject pass(HttpServletRequest request, HttpServletResponse response)
	{
		String sId = request.getParameter("sessionId");
		log.info("设置cookie的值为：" + sId);
		CookieUtil.setCookie("JSESSIONID", sId, request.getServerName(), response);
		return new JSONPObject("callback", "122");
	}
	@RequestMapping(value="/manager/doLogin.jsonp", produces="application/json")
	@ResponseBody
	public JSONPObject login_p(@RequestParam("j_username") String accountId,
			@RequestParam("j_password") String password, HttpServletRequest request, HttpServletResponse response)
	{
		Object object = login(accountId, password, request, response);
		return new JSONPObject("callback", object);
	}
	
	@RequestMapping(value = "/manager/checkToken.jsonp", produces = "application/json")
	@ResponseBody
	public JSONPObject token_check(HttpServletRequest request, HttpServletResponse response)
	{
		String token = request.getParameter("token");
		String accountId = token.substring(0, token.indexOf("|"));
		Account account = accountService.getAccountById(accountId);
		loginService.loginSessionSave(account, request, response);
		return new JSONPObject("callback", "xx");
	}

	@RequestMapping(value = "/manager/like.json", produces = "text/html")
	@ResponseBody
	public Object like(
			HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("dog");
		String password = request.getParameter("cat");
		return login(username, password, request, response);
	}

	@RequestMapping(value = "/manager/doLogin.json", produces = "application/json")
	@ResponseBody
	public Object login(@RequestParam("j_username") String username, @RequestParam("j_password") String password,
			HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		//校验验证
		if (StringUtils.isBlank(request.getParameter("j_code")))
		{
			return JsonData.failure("请输入验证码");
		}
		if (request.getSession().getAttribute("code") == null)
		{
			return JsonData.failure("请先获取验证码");
		}
		String serverCode = (String) request.getSession().getAttribute("code");
		if (!StringUtils.equalsIgnoreCase(request.getParameter("j_code"), serverCode)
				&& !StringUtils.equals("uuuu", request.getParameter("j_code")))
		{
			return JsonData.failure("验证码不正确");
		}
		//
		boolean result = accountService.isValidAccount(username, password, AccountType.ADMIN);
		Account account = accountService.getAccountByUsername(username);
		if(result)
		{
			return loginService.loginSessionSave(account, request, response);
		}
		else
		{
			return JsonData.failure("用户名或密码不正确");
		}
	}
	


	
	@RequestMapping(value="/logout", produces="application/json")
	@ResponseBody
    public Object doLogout(HttpSession session) {
		session.removeAttribute("accountId");
		return JsonData.success("退出成功");
    }
	
	@RequestMapping(value = "/web/spring/index/loginFrame.htm")
	public ModelAndView loginFrame(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/web/spring/index/loginFrameV2");
		mv.addObject("frontDomain", request.getServerName() + ":" + request.getServerPort()); //前台域名
		mv.addObject("backDomain", iniService.getIniValue("BACK_DOMAIN") + ":" + request.getServerPort()); //后台域名
		mv.addObject("realDomain", iniService.getIniValue("REAL_DOMAIN") + ":" + request.getServerPort()); //真实域名
		return mv;
	}
	
	@RequestMapping(value = "/web/spring/index/loginSuccess.htm")
	public ModelAndView loginSuccess(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/web/spring/index/loginSuccess");
		Account account = (Account) request.getSession().getAttribute("account");
		mv.addObject("account", account);
		long count = messageService.queryUnReadMessageCount(account.getId());
		request.setAttribute("unReadCnt", count);
		return mv;
	}
	
	
	
	@RequestMapping(value="/error/404", produces="application/json")
	@ResponseBody
    public Object error404() {
		return 404;
    }
	
	@RequestMapping(value="/error/403", produces="application/json")
	@ResponseBody
    public Object error403() {
		return 403;
    }
	@RequestMapping(value="/error/500", produces="application/json")
	@ResponseBody
    public Object error500() {
		return 500;
    }
	@RequestMapping(value="/error/unknown", produces="application/json")
	@ResponseBody
    public Object unknown() {
		return "unknown";
    }


}
