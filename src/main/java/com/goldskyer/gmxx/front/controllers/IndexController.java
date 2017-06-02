package com.goldskyer.gmxx.front.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.SpringContextHolder;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.gmxx.BaseController;
import com.goldskyer.gmxx.common.constants.GmxxConstant;

@Controller
@RequestMapping("/front")
public class IndexController extends BaseController
{
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/index.htm")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/front/index");
		return mv;
	}
	
	@RequestMapping(value="/overview.htm")
	public ModelAndView overview(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/front/overview");
		List<Blog> blogs =blogService.queryBlogsWithNoContent(GmxxConstant.OVERVEIW,null, 0, 20);
		mv.addObject("blogs", blogs);
		return mv;
	}
	
	@RequestMapping(value="/scenery.htm")
	public ModelAndView scenery(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/front/scenery");
		List<Blog> blogs =blogService.queryBlogsWithNoContent(GmxxConstant.SCENERY,null, 0, 20);
		mv.addObject("blogs", blogs);
		return mv;
	}
	
	@RequestMapping(value="/contact.htm")
	public ModelAndView contact(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/front/contact");
		//mv.addObject("domain", "schoolAddress");
		SpringContextHolder.sc.setAttribute("domain", "schoolAddress");
		return mv;
	}
	
	@RequestMapping(value = "/test.json", produces = "application/json")
	@ResponseBody
	public JsonData refresh()
	{

		return JsonData.success();
	}
	
	@RequestMapping(value = "/test2.json", produces = "application/json")
	@ResponseBody
	public JSONPObject refresh2()
	{

		return new JSONPObject("xx", JsonData.success());
	}

}
