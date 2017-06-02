package com.goldskyer.gmxx.manager.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.controllers.CoreBaseController;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.CmsService;
import com.goldskyer.core.service.CommentService;
import com.goldskyer.core.service.DepartmentService;
import com.goldskyer.core.service.MemoryService;
import com.goldskyer.core.utils.WebUtils;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.common.business.DepartmentBusiness;
import com.goldskyer.gmxx.common.service.AttachmentService;
import com.goldskyer.gmxx.common.service.MessageService;
import com.goldskyer.gmxx.common.service.WorkFlowService;

public class BaseManagerController extends CoreBaseController
{


	@Autowired
	protected MessageService messageService;
	@Autowired
	protected BaseDao baseDao;
	@Autowired
	protected DepartmentService departmentService;
	@Autowired
	protected DepartmentBusiness departmentBusiness;
	@Autowired
	protected SimpleCacheManager cacheManager;
	@Autowired
	protected CmsService cmsService;
	@Autowired
	protected AttachmentService attachmentService;

	@Autowired
	protected AclService aclService;

	@Autowired
	@Qualifier("blogService")
	protected BlogService blogService;

	@Autowired
	protected CommentService commentService;
	@Autowired
	protected AccountService accountService;

	@Autowired
	protected WorkFlowService workFlowService;
	@Autowired
	protected MemoryService memoryService;

	@Autowired
	@Qualifier("cachedMenuService")
	protected CachedMenuService cachedMenuService;

	protected String getCurrentAccountId()
	{
		return EnvParameter.get().getAccountId();
	}

	protected Account getCurrentAccount()
	{
		return EnvParameter.get().getAccount();
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
			mv.addObject("errorMsg", e.getMessage());
			return mv;
		}
		JsonData jsonData = JsonData.error();
		jsonData.setMsg(e.getMessage());
		jsonData.errorDetails.put("exception", e.getMessage());
		WebUtils.renderJson(request, response, jsonData);
		return null;
	}

}
