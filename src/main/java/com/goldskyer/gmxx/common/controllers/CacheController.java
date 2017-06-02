package com.goldskyer.gmxx.common.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldskyer.core.dto.JsonData;

@Controller
@RequestMapping("/cache")
public class CacheController
{
	@Autowired
	private SimpleCacheManager cacheManager;

	@RequestMapping(value = "/refresh.json", produces = "application/json")
	@ResponseBody
	public JsonData refresh()
	{
		cacheManager.getCache("default").clear();
		cacheManager.getCache("blog").clear();
		cacheManager.getCache("dept").clear();
		return JsonData.success();
	}
}
