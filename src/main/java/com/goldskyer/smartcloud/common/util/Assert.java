package com.goldskyer.smartcloud.common.util;

import org.apache.commons.lang.StringUtils;

import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.exception.resp.RespCode;

public class Assert
{
	public static void notNull(Object object, RespCode respCode)
	{
		if(object==null)
			throw new RespCodeException(respCode);
	}

	public static void notEmpty(Object object, RespCode respCode)
	{
		if (object == null)
			throw new RespCodeException(respCode);
		if (object instanceof String)
		{
			if (StringUtils.isBlank((String) object))
			{
				throw new RespCodeException(respCode);
			}
		}
	}

	public static void meetCondition(boolean cond, RespCode respCode)
	{
		if (!cond)
			throw new RespCodeException(respCode);
	}
}
