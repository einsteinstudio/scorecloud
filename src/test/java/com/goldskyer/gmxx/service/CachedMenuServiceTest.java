package com.goldskyer.gmxx.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.core.service.CachedMenuService;

public class CachedMenuServiceTest extends BaseTest

{
	@Autowired
	private CachedMenuService cachedMenuService;

	@Test
	public void testueryAllTypes()
	{
		System.out.println(cachedMenuService.queryAllTypes("校园新闻", "smart.goldskyer.com"));
	}
}
