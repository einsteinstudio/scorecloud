package com.goldskyer.gmxx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.controllers.CoreBaseController;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.IniService;
import com.goldskyer.core.utils.WebUtils;
import com.goldskyer.gmxx.common.service.MessageService;
import com.goldskyer.gmxx.front.business.BlogBusiness;


public class BaseController  extends CoreBaseController{
	protected static Log log = LogFactory.getLog(BaseController.class);
	@Autowired
	protected IniService iniService;
	@Autowired
	protected BlogService blogService;
	@Autowired
	@Qualifier("cachedMenuService")
	protected CachedMenuService cachedMenuService;
	
	@Autowired
	protected MessageService messageService;

	@Autowired
	protected BlogBusiness blogBusiness;
	
	public static void main(String[] args) {
		System.out.println("xx");
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception e)
	{
		//添加自己的异常处理逻辑，如日志记录
		log.fatal(e.getMessage(), e);
		String requestURI = request.getRequestURI();
		boolean isAJax = requestURI.endsWith(".json");
		log.debug("当前接口为url:" + requestURI + ",isAjax:" + isAJax);
		if (!isAJax)
		{
			ModelAndView mv = new ModelAndView("/manager/template");
			mv.addObject("innerPage", "error/common_error");
			mv.addObject("errorMsg", "网络繁忙，请稍后再试");
			return mv;
		}
		JsonData jsonData = JsonData.error();
		jsonData.setMsg(e.getMessage());
		jsonData.errorDetails.put("exception", e.getMessage());
		WebUtils.renderJson(request, response, jsonData);
		return null;
	}

}
