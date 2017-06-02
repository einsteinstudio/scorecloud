package com.goldskyer.common.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

public class BeanUtil
{
	static
	{
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
		ConvertUtils.register(new DateConvert(), java.sql.Date.class);
		ConvertUtils.register(new DateConvert(), java.sql.Timestamp.class);
	}

	/**
	 * 浮点数拷贝，空会变成0
	 * @param target
	 * @param source
	 */
	public static void copyProperties(Object target, Object source)
	{
		try
		{
			BeanUtils.copyProperties(target, source);
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
