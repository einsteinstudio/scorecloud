package com.goldskyer.scorecloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.enums.AccountType;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.scorecloud.business.LoginBusiness;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.exception.resp.RespCodeConstant;
import com.goldskyer.smartcloud.common.util.Assert;

@Controller("scorecloudLoginController")
@RequestMapping("/scorecloud")
public class LoginController extends BaseScoreCloudController
{
	@Autowired
	private AccountService accountService;

	@Autowired
	private LoginBusiness loginBusiness;

	@RequestMapping(value = "/doLogin.json", produces = "application/json")
	@ResponseBody
	public Object doLogin(@RequestParam("dog") String username, @RequestParam("cat") String password,
			@RequestParam("j_code") String code,
			HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");

		Assert.notEmpty(username, RespCodeConstant.USER_NAME_IS_EMPTY);
		Assert.notEmpty(password, RespCodeConstant.PWD_IS_EMPTY);
		boolean result = accountService.isValidAccount(username, password, AccountType.ADMIN);
		Account account = accountService.getAccountByUsername(username);
		if (result)
		{
			loginBusiness.doLogin(account.getId());
			return JsonData.success();
		}
		else
		{
			return JsonData.failure("用户名或密码不正确");
		}
	}
	
	@RequestMapping(value = "/logout.htm")
	public ModelAndView logout(HttpSession session)
	{
		ScoreCloudEnv.get().clearSession();
		ModelAndView mv = new ModelAndView("/scorecloud/login/" + ScoreCloudEnv.get().getSchId());
		return mv;
	}

	@RequestMapping(value = "/login/xcxx.htm")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/login/xcxx");
		return mv;
	}
}
