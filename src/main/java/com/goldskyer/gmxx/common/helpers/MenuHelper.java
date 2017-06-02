package com.goldskyer.gmxx.common.helpers;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.core.SpringContextHolder;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.utils.BeanBuilderUtil;
import com.goldskyer.core.vo.MenuVo;

public class MenuHelper
{
	private static Log log = LogFactory.getLog(MenuHelper.class);
	//	public static JSONObject queryMenuJson(String menuName, Map<String, Menu> menuMap)
	//	{
	//		CachedMenuService cachedMenuService = SpringContextHolder.getBean("cachedMenuService");
	//		Menu menu = cachedMenuService.queryMenuByName("栏目管理");
	//
	//		JsonConfig jsonConfig = new JsonConfig();
	//		jsonConfig.setExcludes(new String[]
	//		{ "fullAlias", "crumbs", "parent", "siblings" });
	//		return JSONObject.fromObject(menu, jsonConfig);
	//	}

	/**
	 * 根据菜单名，获取菜单列表
	 * @param menuName
	 * @param authMenuMap
	 * @return
	 */
	public static MenuVo buildMenuVo(String menuName, Map<String, Menu> authMenuMap)
	{
		CachedMenuService cachedMenuService = SpringContextHolder.getBean("cachedMenuService");
		Menu menu = cachedMenuService.queryMenuByName(menuName);
		if(menu==null)
		{
			log.warn("未配置的菜单项menuName:" + menuName);
			return null;
		}
		MenuVo vo = BeanBuilderUtil.convert(menu);
		initChildMenuVo(vo, menu, authMenuMap);
		return vo;
	}
	
	public static MenuVo buildAuthMenuVo(String menuName, Map<String, Menu> menuMap)
	{
		CachedMenuService cachedMenuService = SpringContextHolder.getBean("cachedMenuService");
		Menu menu = cachedMenuService.queryMenuByName(menuName);
		MenuVo vo = BeanBuilderUtil.convert(menu);
		initChildMenuVo(vo, menu, menuMap);
		return vo;
	}

	private static void initChildMenuVo(MenuVo vo, Menu menu, Map<String, Menu> menuMap)
	{
		vo.setChecked(menuMap.containsKey(vo.getId()));
		for (Menu m : menu.getChildren())
		{
			MenuVo subVo = BeanBuilderUtil.convert(m);
			subVo.setChecked(menuMap.containsKey(vo.getId()));
			vo.getChildren().add(subVo);
			initChildMenuVo(subVo, m, menuMap);
		}
	}
}
