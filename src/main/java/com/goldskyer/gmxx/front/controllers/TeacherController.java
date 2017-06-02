package com.goldskyer.gmxx.front.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.gmxx.common.constants.GmxxConstant;

//@Controller
//@RequestMapping("/front/teacher")
public class TeacherController extends BlogController{

	
	@RequestMapping("/view.htm")
	public ModelAndView detail(@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/front/teacher/view");
		Blog blog = blogService.queryBlog(id);
		Menu menu = cachedMenuService.queryMenuByName(blog.getType());
		mv.addObject("mainMenu", cachedMenuService.getMainMenu());
		mv.addObject("menu", menu);
		mv.addObject("blog",blog);
		return mv;
	}
	
	
	@RequestMapping("/list.htm")
	public ModelAndView list(@RequestParam String blogType,@RequestParam(required=false) Integer pageNum)
	{
		ModelAndView mv = new ModelAndView("/front/teacher/list");
		blogBusiness.buildCommonBlogListModel(mv, blogType, pageNum, Integer.valueOf(iniService.getIniValue(GmxxConstant.PAGE_SIZE)));
		return mv;
	}
}
