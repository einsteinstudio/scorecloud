package com.goldskyer.gmxx.web.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.entities.Account;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.enums.MenuModule;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.gmxx.common.constants.CmsConstant;
import com.goldskyer.gmxx.common.service.LoginService;
import com.goldskyer.gmxx.front.business.BlogBusiness;
import com.goldskyer.gmxx.manager.utils.CookieUtil;
import com.goldskyer.gmxx.web.controllers.WebBaseController;

@Controller("web.spring.IndexController")
@RequestMapping("/web/spring/")
public class IndexController extends WebBaseController
{
	@Autowired
	private BlogBusiness blogBusiness;
	
	@Autowired
	private BlogService blogService;

	@Autowired
	private CachedMenuService cachedMenuService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private AccountService accountService;

	/**
	 * 访问中文页面，检测到en的cookie可以自动切换到英文版
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/index.htm")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		if (StringUtils.equals("logout", request.getParameter("action")))
		{
			loginService.logout(request, response);
		}
		if (StringUtils.equals("en", CookieUtil.getCookieValue(request, "n18")))
		{
			return new ModelAndView("redirect:/web/spring/index_en.htm");
		}
		//测试token
		if (StringUtils.isNotBlank(request.getParameter("token")))
		{
			String token = request.getParameter("token");
			String accountId = token.substring(0, token.indexOf("|"));
			Account account = accountService.getAccountById(accountId);
			loginService.loginSessionSave(account, request, response);
		}

		ModelAndView mv = new ModelAndView("/web/spring/index");
		initCommonBlock(mv);
		Menu cachedMenu = cachedMenuService.queryMenuByName("栏目管理");

		mv.addObject("cachedMenu", cachedMenu);
		initSideBar(mv);
		//公告三栏
		mv.addObject("notice1", cmsService.getCmsContent(CmsConstant.NOTICE1));
		mv.addObject("notice2", cmsService.getCmsContent(CmsConstant.NOTICE2));
		mv.addObject("notice3", cmsService.getCmsContent(CmsConstant.NOTICE3));
		List<Blog> notice1s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("notice1").toString(), null, 0, 6);
		mv.addObject("notice1s", notice1s);
		List<Blog> notice2s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("notice2").toString(), null, 0, 6);
		mv.addObject("notice2s", notice2s);
		List<Blog> notice3s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("notice3").toString(), null, 0, 6);
		mv.addObject("notice3s", notice3s);

		//顶层栏目
		mv.addObject("bannerTop", cmsService.getCmsContent(CmsConstant.BANNER_TOP));
		List<Blog> bannerTops = blogBusiness.queryBlogsWithNoContentByTypes(mv.getModel().get("bannerTop").toString(),
				0,
				7);
		if (StringUtils.isNotBlank(getCurrentAccountId()))
		{
			blogService.queryReadedBlogs(bannerTops, getCurrentAccountId());
		}
		mv.addObject("bannerTops", bannerTops);
		//顶层中间的图片动态
		List<Blog> bannerImages = blogBusiness.queryRecentImgaeBlogs(0, 5);
		mv.addObject("bannerImages", bannerImages);

		//中间一级栏目
		mv.addObject("banner01", cmsService.getCmsContent(CmsConstant.BANNER01));
		List<List<Blog>> banner01List = new ArrayList<>();
		Menu banner01Menu = cachedMenuService.queryMenuByName(mv.getModel().get("banner01").toString());
		mv.addObject("banner01Menu", banner01Menu);
		mv.addObject("banner01List", banner01List);
		for (int i = 0; i < banner01Menu.getChildren().size(); i++)
		{
			if (i < 4)
			{
				banner01List.add(
						blogBusiness.queryBlogsWithNoContent(banner01Menu.getChildren().get(i).getName(), null, 0,
						8));
			}
		}

		mv.addObject("banner02", cmsService.getCmsContent(CmsConstant.BANNER02));
		List<List<Blog>> banner02List = new ArrayList<>();
		Menu banner02Menu = cachedMenuService.queryMenuByName(mv.getModel().get("banner02").toString());
		mv.addObject("banner02Menu", banner02Menu);
		mv.addObject("banner02List", banner02List);
		for (int i = 0; i < banner02Menu.getChildren().size(); i++)
		{
			if (i < 4)
			{
				banner02List.add(
						blogBusiness.queryBlogsWithNoContent(banner02Menu.getChildren().get(i).getName(), null, 0, 8));
			}
		}

		//最底部的栏目
		mv.addObject("ground01", cmsService.getCmsContent(CmsConstant.GROUND01));
		List<Blog> ground01s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("ground01").toString(), null, 0,
				10);
		mv.addObject("ground01s", ground01s);
		mv.addObject("ground02", cmsService.getCmsContent(CmsConstant.GROUND02));
		List<Blog> ground02s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("ground02").toString(), null, 0,
				10);
		mv.addObject("ground02s", ground02s);
		mv.addObject("ground03", cmsService.getCmsContent(CmsConstant.GROUND03));
		List<Blog> ground03s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("ground03").toString(), null, 0,
				10);
		mv.addObject("ground03s", ground03s);
		mv.addObject("ground04", cmsService.getCmsContent(CmsConstant.GROUND04));
		List<Blog> ground04s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("ground04").toString(), null, 0,
				10);
		mv.addObject("ground04s", ground04s);

		//图片栏目
		mv.addObject("bannerPic01", cmsService.getCmsContent(CmsConstant.BANNER_PIC01));
		List<Blog> bannerPic01s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerPic01").toString(),
				null,
				0, 10);
		mv.addObject("bannerPic01s", bannerPic01s);
		mv.addObject("bannerPic02", cmsService.getCmsContent(CmsConstant.BANNER_PIC02));
		List<Blog> bannerPic02s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerPic02").toString(),
				null,
				0, 10);
		mv.addObject("bannerPic02s", bannerPic02s);
		mv.addObject("bannerPic03", cmsService.getCmsContent(CmsConstant.BANNER_PIC03));
		List<Blog> bannerPic03s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerPic03").toString(),
				null,
				0, 10);
		mv.addObject("bannerPic03s", bannerPic03s);

		//左侧栏目
		mv.addObject("bannerLeft1", cmsService.getCmsContent(CmsConstant.BANNER_LEFT1));
		List<Blog> bannerLeft1s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerLeft1").toString(),
				null,
				0, 5);
		mv.addObject("bannerLeft1s", bannerLeft1s);
		mv.addObject("bannerLeft2", cmsService.getCmsContent(CmsConstant.BANNER_LEFT2));
		List<Blog> bannerLeft2s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerLeft2").toString(),
				null,
				0, 5);
		mv.addObject("bannerLeft2s", bannerLeft2s);
		mv.addObject("bannerLeft3", cmsService.getCmsContent(CmsConstant.BANNER_LEFT3));
		List<Blog> bannerLeft3s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerLeft3").toString(),
				null,
				0, 5);
		mv.addObject("bannerLeft3s", bannerLeft3s);
		mv.addObject("bannerLeft4", cmsService.getCmsContent(CmsConstant.BANNER_LEFT4));
		List<Blog> bannerLeft4s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerLeft4").toString(),
				null,
				0, 5);
		mv.addObject("bannerLeft4s", bannerLeft4s);
		mv.addObject("bannerLeft5", cmsService.getCmsContent(CmsConstant.BANNER_LEFT5));
		List<Blog> bannerLeft5s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerLeft5").toString(),
				null,
				0, 5);
		mv.addObject("bannerLeft5s", bannerLeft5s);
		mv.addObject("schoolTitle", cmsService.getCmsContent(CmsConstant.SCHOOL_TITLE));
		mv.addObject("footerContact", cmsService.getCmsContent(CmsConstant.FOOTER_CONTACT));
		
		//
		mv.addObject("frontDomain", request.getServerName() + ":" + request.getServerPort()); //前台域名
		mv.addObject("backDomain", iniService.getIniValue("BACK_DOMAIN") + ":" + request.getServerPort()); //后台域名
		mv.addObject("realDomain", iniService.getIniValue("REAL_DOMAIN") + ":" + request.getServerPort()); //真实域名
		return mv;
	}
	
	/**
	 * 英文版首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index_en.htm")
	public ModelAndView indexEn(HttpServletRequest request, HttpServletResponse response)
	{
		if (StringUtils.equals("logout", request.getParameter("action")))
		{
			request.getSession().invalidate();
		}
		if (StringUtils.equals("cn", CookieUtil.getCookieValue(request, "n18")))
		{
			return new ModelAndView("redirect:/web/spring/index.htm");
		}
		ModelAndView mv = new ModelAndView("/web/spring/index_en");
		initCommonBlock(mv);
		Menu cachedMenu = cachedMenuService.queryMenuByName(MenuModule.ENLANMU.getName());

		mv.addObject("cachedMenu", cachedMenu);
		initSideBar(mv);
		//公告栏
		mv.addObject("notice1", cmsService.getCmsContent(CmsConstant.NOTICE1));
		List<Blog> notice1s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("notice1").toString(), null, 0, 6);
		mv.addObject("notice1s", notice1s);

		//顶层栏目
		mv.addObject("bannerTop", cmsService.getCmsContent(CmsConstant.BANNER_TOP));
		List<Blog> bannerTops = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerTop").toString(), null, 0,
				7);
		mv.addObject("bannerTops", bannerTops);


		//最底部的栏目
		mv.addObject("ground01", cmsService.getCmsContent(CmsConstant.GROUND01));
		List<Blog> ground01s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("ground01").toString(), null, 0,
				5);
		mv.addObject("ground01s", ground01s);
		mv.addObject("ground02", cmsService.getCmsContent(CmsConstant.GROUND02));
		List<Blog> ground02s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("ground02").toString(), null, 0,
				5);
		mv.addObject("ground02s", ground02s);

		//图片栏目
		mv.addObject("bannerPic01", cmsService.getCmsContent(CmsConstant.BANNER_PIC01));
		List<Blog> bannerPic01s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerPic01").toString(),
				null,
				0, 10);
		mv.addObject("bannerPic01s", bannerPic01s);
		mv.addObject("bannerPic02", cmsService.getCmsContent(CmsConstant.BANNER_PIC02));
		List<Blog> bannerPic02s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerPic02").toString(),
				null,
				0, 10);
		mv.addObject("bannerPic02s", bannerPic02s);
		mv.addObject("bannerPic03", cmsService.getCmsContent(CmsConstant.BANNER_PIC03));
		List<Blog> bannerPic03s = blogBusiness.queryBlogsWithNoContent(mv.getModel().get("bannerPic03").toString(),
				null,
				0, 10);
		mv.addObject("bannerPic03s", bannerPic03s);
		String aString = cmsService.getCmsContent("contact01");

		mv.addObject("frontDomain", request.getServerName()); //前台域名
		mv.addObject("backDomain", iniService.getIniValue("BACK_DOMAIN")); //后台域名
		mv.addObject("realDomain", iniService.getIniValue("REAL_DOMAIN")); //真实域名
		return mv;
	}
	
}
