package com.goldskyer.gmxx.common.interceptors;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.gmxx.common.aop.AvoidDuplicateSubmission;

public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter
{
	private static final Logger LOG = Logger.getLogger(AvoidDuplicateSubmissionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (!(handler instanceof HandlerMethod))
		{
			return true;
		}
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
			if (annotation != null)
			{
				boolean needSaveSession = annotation.needSaveToken();
				if (needSaveSession)
				{
					request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
				}

			//				boolean needRemoveSession = annotation.needRemoveToken();
			//				if (needRemoveSession)
			//				{
			//					if (isRepeatSubmit(request))
			//					{
			//					LOG.warn("please don't repeat submit,url:"
			//								+ request.getServletPath() + "]");
			//					throw new BusinessException("重复提交");
			//					}
			//					request.getSession(false).removeAttribute("token");
			//				}
			}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		if (!(handler instanceof HandlerMethod))
		{
			return;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
		if (annotation != null)
		{
			boolean needRemoveSession = annotation.needRemoveToken();
			if (needRemoveSession)
			{
				if (isRepeatSubmit(request))
				{
					LOG.warn("please don't repeat submit,url:" + request.getServletPath() + "]");
					throw new BusinessException("重复提交");
				}
				request.getSession(false).removeAttribute("token");
			}
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request)
	{
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (serverToken == null)
		{
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null)
		{
			return true;
		}
		if (!serverToken.equals(clinetToken))
		{
			return true;
		}
		return false;
	}

}