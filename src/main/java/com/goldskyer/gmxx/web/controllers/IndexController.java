package com.goldskyer.gmxx.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;

@Controller("web.IndexController")
@RequestMapping("/web")
public class IndexController extends WebBaseController
{
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CachedMenuService cachedMenuService;

	@RequestMapping(value="/index.htm")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/web/index");
		Menu cachedMenu = cachedMenuService.queryMenuByName("栏目管理");

		mv.addObject("cachedMenu", cachedMenu);
		initSideBar(mv);
		List<Blog> xinwens = blogService.queryBlogsWithNoContent("教育新闻", null, 0, 5);
		mv.addObject("xinwens", xinwens);

		//中间栏目
		List<Blog> fuwus = blogService.queryBlogsWithNoContent("服务窗口", null, 0, 5);
		mv.addObject("fuwus", fuwus);

		List<Blog> jiaxiaos = blogService.queryBlogsWithNoContent("家长必读", null, 0, 5);
		mv.addObject("jiaxiaos", jiaxiaos);

		List<Blog> xiaowus = blogService.queryBlogsWithNoContent("校务公开", null, 0, 5);
		mv.addObject("xiaowus", xiaowus);

		List<Blog> baixiaos = blogService.queryBlogsWithNoContent("百校帮扶", null, 0, 5);
		mv.addObject("baixiaos", baixiaos);

		List<Blog> fengjings = blogService.queryBlogsWithNoContent("校园图片", null, 0, 5);
		mv.addObject("fengjings", fengjings);


		//我去
		return mv;
	}
	
	
	
}
