package com.goldskyer.gmxx.xdcms.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.controllers.CoreBaseController;

@Controller
public class XdcmsController extends CoreBaseController{

	@RequestMapping(value="/UploadFile/**")
	public ModelAndView contact(HttpServletRequest request,HttpServletResponse response)
	{
		String url= request.getRequestURI();
		return new ModelAndView("redirect:"+"http://gmxx.szgm.edu.cn"+url);
	}
}
