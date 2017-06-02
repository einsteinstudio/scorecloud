package com.goldskyer.gmxx.front.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.entities.Blog;
import com.goldskyer.gmxx.BaseController;
import com.goldskyer.gmxx.common.constants.GmxxConstant;
import com.goldskyer.gmxx.common.service.LiveVideoService;

@Controller
@RequestMapping("/front/tv")
public class TvController extends BaseController{
	@Autowired
	private LiveVideoService liveVideoService;
	
	@RequestMapping("/view.htm")
	public ModelAndView detail(@RequestParam String id, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/front/tv/view");
		Blog blog = blogService.queryBlog(id);
		//Menu menu = menuService.queryMenuByName(blog.getType());
		//mv.addObject("mainMenu", menuService.getMainMenu());
		//mv.addObject("menu", menu);
		mv.addObject("blog",blog);
		//其他视频资源
		blogBusiness.buildCommonBlogListModel(mv, blog.getType(), 0, Integer.valueOf(iniService.getIniValue(GmxxConstant.PAGE_SIZE)));		
		blogService.incViewCount(id);
		//直播
		if (blog.getNeedAuth() && request.getSession().getAttribute("blogId") == null)
		{
			return new ModelAndView("404");
		}
		return mv;
	}

}
