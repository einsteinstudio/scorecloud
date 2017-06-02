package com.goldskyer.gmxx.manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.service.MenuService;
@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseManagerController
{
	
	private MenuService menuService;
	@RequestMapping("/index.htm")
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "index");
		return mv;
	}
	
	/**
	 * 这个作为后台的基础模板
	 * @return
	 */
	@RequestMapping("/template.htm")
	public ModelAndView template()
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		return mv;
	}
	
	@RequestMapping("/{path}.htm")
	public ModelAndView inner(@PathVariable String path)
	{
		
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", path);
		return mv;
	}
}
