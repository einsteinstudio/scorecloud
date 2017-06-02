package com.goldskyer.gmxx.templ.service;

import java.util.List;

/**
 * 模板业务类
 * @author jintianfan
 *
 */
public interface TempService<T>
{
	public void delete(String id);
	
	public void modify(T t);
	
	public void add(T t);

	public T queryById(String id);

	public List list();
}
