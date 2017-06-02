package com.goldskyer.gmxx.common.helpers;

/**
 * vo转化器
 * @author jintianfan
 * @param <T>
 */
public interface VoConverter<T>
{
	public T convert(Object[] o);
}
