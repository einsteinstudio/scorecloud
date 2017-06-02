package com.goldskyer.gmxx.common.aop;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.gmxx.acl.entities.Right;

@Component
@Aspect
public class RoleControlService
{
	private Log log = LogFactory.getLog(Log.class);
	
	@Pointcut("@annotation(com.goldskyer.gmxx.common.aop.RoleControl)")
	public void methodCachePointcut()
	{

	}

	@Around("methodCachePointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable
	{
		MethodSignature sign = (MethodSignature) pjp.getSignature();
		RoleControl roleControlAno = sign.getMethod().getAnnotation(RoleControl.class);
		log.info("haha:" + roleControlAno.value()[0]);
		//限权控制
		String[] verifyRights = roleControlAno.value();
		if (verifyRights == null || verifyRights.length == 0)
		{
			throw new BusinessException("权限控制出现异常");
		}
		Map<String, Right> rightMap = (Map<String, Right>) EnvParameter.get().getRequest().getSession()
				.getAttribute("rightMap");
		boolean checkPass = true;
		for (String verify : verifyRights)
		{
			if (!rightMap.containsKey(verify))
			{
				checkPass = false;
				break;
			}
		}
		if (checkPass)
		{
			return pjp.proceed();
		}
		else
		{
			throw new BusinessException("当前业务操作权限不足");
		}
	}

}
