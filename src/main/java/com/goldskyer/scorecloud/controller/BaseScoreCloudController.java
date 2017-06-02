package com.goldskyer.scorecloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.utils.WebUtils;
import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.freemarker.component.vo.OperationResultVo;

public class BaseScoreCloudController
{

	protected Log log = LogFactory.getLog(BaseScoreCloudController.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception e)
	{
		//添加自己的异常处理逻辑，如日志记录
		String requestURI = request.getRequestURI();
		String msg = "网络繁忙，请稍后再试";
		if (e instanceof RespCodeException)
		{
			msg = ((RespCodeException) e).getRespCode().getDesc();
		}
		else
		{
			log.fatal(e.getMessage(), e);
		}
		boolean isAJax = requestURI.endsWith(".json");
		if (!isAJax)
		{
			ModelAndView mv = new ModelAndView("/scorecloud/templ/error/500");
			mv.addObject("errorMsg", msg);
			return mv;
		}
		JsonData jsonData = JsonData.error();
		jsonData.setMsg(msg);
		//jsonData.errorDetails.put("exception", e.getMessage());
		WebUtils.renderJson(request, response, jsonData);
		return null;
	}
	public OperationResultVo buildOperationResult(String m2, String pageTitle, String opeartionInfo, String nextLink)
	{
		OperationResultVo operationResultVo = new OperationResultVo();
		operationResultVo.setM2(m2);
		operationResultVo.setPageTitle(pageTitle);
		operationResultVo.setOperationInfo(opeartionInfo);
		operationResultVo.setNextStepInfo("继续操作");
		operationResultVo.setNextStepLink(nextLink);
		return operationResultVo;
	}
}
