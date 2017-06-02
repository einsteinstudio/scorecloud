package com.goldskyer.gmxx.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.enums.MenuModule;
import com.goldskyer.core.service.CmsService;
import com.goldskyer.gmxx.BaseController;

public class WebBaseController extends BaseController
{
	@Autowired
	protected CmsService cmsService;

	protected String getCurrentAccountId()
	{
		return EnvParameter.get().getAccountId();
	}

	protected Account getCurrentAccount()
	{
		return EnvParameter.get().getAccount();
	}

	protected void initCommonBlock(ModelAndView mv)
	{
		Menu friendLink = cachedMenuService.queryMenuByName("友情链接");
		mv.addObject("friendLink", friendLink);
		Menu innerLink = cachedMenuService.queryMenuByName("校内资源");
		mv.addObject("innerLink", innerLink);
		//栏目加载
		String lanmu = EnvParameter.get().getN18().equals("cn") ? MenuModule.LANMU.getName()
				: MenuModule.ENLANMU.getName();
		Menu cachedMenu = cachedMenuService.queryMenuByName(lanmu);
		mv.addObject("cachedMenu", cachedMenu);
	}

	protected void initSideBar(ModelAndView mv)
	{
		/*List<Blog> notices = blogService.queryBlogsWithNoContent("对外通知", null, 0, 5);
		mv.addObject("notices", notices);
		List<Blog> anquans = blogService.queryBlogsWithNoContent("安全管理", null, 0, 5);
		mv.addObject("anquans", anquans);
		
		List<Blog> suibis = blogService.queryBlogsWithNoContent("教师随笔", null, 0, 5);
		mv.addObject("suibis", suibis);
		
		List<Blog> dangquns = blogService.queryBlogsWithNoContent("党群路线", null, 0, 5);
		mv.addObject("dangquns", dangquns);*/
	}
	
	protected List<String> getCrumbs(String menuName)
	{
		List<String> crumbs = new ArrayList<>();
		crumbs.add(menuName);
		Menu domainRoot = cachedMenuService.queryDomainRootMenu();
		Menu menu = cachedMenuService.queryMenuByName(menuName);
		if (menu.getParent() != null && menu.getParent().getParent() != domainRoot)
		{
			crumbs.add(0, menu.getParent().getName());
		}
		else
		{
			return crumbs;
		}
		if (menu.getParent() != null && menu.getParent().getParent() != null
				&& menu.getParent().getParent().getParent() != null
				&& menu.getParent().getParent().getParent() != domainRoot)
		{
			crumbs.add(menu.getParent().getParent().getName());
		}
		else
		{
			return crumbs;
		}
		return crumbs;
	}

	protected Integer getMenuDeep(String menuName)
	{
		Integer deep = 0;
		Menu menu = cachedMenuService.queryMenuByName(menuName);
		if (menu == null)
			throw new BusinessException("栏目不存在");
		while (menu.getParent() != null)
		{
			deep++;
			menu = menu.getParent();
		}
		return deep;
	}


}
