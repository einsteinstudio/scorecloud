package com.goldskyer.gmxx.manager.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.enums.MenuModule;
import com.goldskyer.core.enums.MenuType;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.MenuService;
import com.goldskyer.core.utils.EnumUtil;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.enums.MediaType;
import com.goldskyer.gmxx.manager.entities.MenuNode;
import com.goldskyer.gmxx.manager.utils.MenuUtils;

@Controller("managerMenuController")
@RequestMapping("/manager")
public class MenuController extends BaseManagerController
{
	
	@Autowired
	@Qualifier("cachedMenuService")
	private CachedMenuService cachedMenuService;
	
	@Autowired
	private MenuService menuService;

	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;
	
	@RequestMapping("/menu/toAdd.htm")
	@RoleControl("LANMU_ADD")
	public ModelAndView toAdd(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		if (StringUtils.isBlank(pId))
		{
			String menuModule = request.getParameter("menuModule");
			Menu menu = menuService.queryMenuByModule(MenuModule.valueOf(menuModule), EnvParameter.get().getDomain());
			pId = menu.getId();
		}
		Menu pMenu = menuService.queryMenuById(pId);
		mv.addObject("innerPage", "menu/toAdd");
		mv.addObject("pMenu", pMenu);
		return mv;
	}

	@RequestMapping(value = "/menu/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("LANMU_ADD")
	public JsonData addJson(HttpServletRequest request, Menu menu, @RequestParam(required = false) String show)
	{
		menu.setHide(!StringUtils.equalsIgnoreCase("on", request.getParameter("show")));
		menu.setCanComment(StringUtils.equalsIgnoreCase("on", request.getParameter("canComment")));
		menu.setNeedLogin(StringUtils.equalsIgnoreCase("on", request.getParameter("needLogin")));
		menu.setDomain(EnvParameter.get().getDomain());
		baseDao.add(menu);
		cachedMenuService.reload();
		return JsonData.success();
	}

	@RequestMapping("/menu/list.htm")
	@RoleControl("LANMU_VIEW")
	public ModelAndView list(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		if (StringUtils.isBlank(pId))
		{
			String menuModule = request.getParameter("menuModule");
			Menu menu = menuService.queryMenuByModule(MenuModule.valueOf(menuModule), EnvParameter.get().getDomain());
			pId = menu.getId();
		}
		mv.addObject("innerPage", "menu/list");
		mv.addObject("pId", pId);
		Menu parentMenu = cachedMenuService.queryCachedMenuById(pId);
		if (!parentMenu.getParent().getName().equals(MenuModule.MASTER.getName()))
		{
			mv.addObject("ppId", parentMenu.getParentId());
		}
		return mv;
	}

	@RequestMapping("/menu/toModify.htm")
	@RoleControl("LANMU_EDIT")
	public ModelAndView toMdify(HttpServletRequest request, @RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		Menu menu = menuService.queryMenuById(id);
		Menu pMenu = menuService.queryMenuById(menu.getParentId());
		mv.addObject("innerPage", "menu/toModify");
		mv.addObject("menu", menu);
		mv.addObject("pMenu", pMenu);
		return mv;
	}

	@RequestMapping(value = "/menu/list.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("LANMU_VIEW")
	public Object listJson(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		if (StringUtils.isBlank(pId))
		{
			String menuModule = request.getParameter("menuModule");
			Menu menu = menuService.queryMenuByModule(MenuModule.valueOf(menuModule), EnvParameter.get().getDomain());
			pId = menu.getId();
		}
		JsonData jsonData = JsonData.success();
		List<Menu> menus = menuService.queryMenusByPId(pId);
		long total = menuService.queryMenuCountByPId(pId);
		List<Object[]> list = new ArrayList<Object[]>();
		for (Menu m : menus)
		{
			Object[] ss = new Object[6];
			ss[0] = m.getName();
			ss[1] = EnumUtil.valueOf(MenuType.class, m.getType()).getDesc();
			ss[2] = EnumUtil.valueOf(MediaType.class, m.getMediaType()).getDesc();
			ss[3] = m.getWeight();
			ss[4] = m.getHide();
			ss[5] = m.getId();
			list.add(ss);
		}
		Map map = new HashMap();
		map.put("data", list);
		map.put("recordsTotal", total);
		map.put("recordsFiltered", total);
		map.put("draw",
				Integer.parseInt(request.getParameter("draw") == null ? "0" : request.getParameter("draw")) + 1);
		return map;
	}

	@RequestMapping(value = "/menu/delete.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("LANMU_DELETE")
	public JsonData delete(@RequestParam String id)
	{
		menuService.deleteMenu(id);
		cachedMenuService.reload();
		return JsonData.success();
	}
	@RequestMapping("/menu/menu.htm")
	@RoleControl("LANMU_VIEW")
	public ModelAndView listInfo(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "menu/menu");
		System.out.println(request.getRequestURI()+","+request.getRequestURL().toString());
		return mv;
	}
	
	@RequestMapping(value = "/menu/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("LANMU_EDIT")
	public JsonData modify(Menu menu, HttpServletRequest request)
	{
		Menu oldMenu = baseDao.query(Menu.class, menu.getId());
		//判断是否重名
		if(!StringUtils.equals(oldMenu.getName(), menu.getName()))
		{
			boolean check = menuService.ifExistMenuName(menu.getName(), EnvParameter.get().getDomain());
			if (!check)
			{
				return JsonData.failure("菜单不能重名");
			}
		}
		menu.setHide(!StringUtils.equalsIgnoreCase("on", request.getParameter("show")));
		menu.setCanComment(StringUtils.equalsIgnoreCase("on", request.getParameter("canComment")));
		menu.setNeedLogin(StringUtils.equalsIgnoreCase("on", request.getParameter("needLogin")));
		//设置修改的参数
		oldMenu.setName(menu.getName());
		oldMenu.setMediaType(menu.getMediaType());
		oldMenu.setType(menu.getType());
		oldMenu.setLink(menu.getLink());
		oldMenu.setWeight(menu.getWeight());
		oldMenu.setHide(menu.getHide());
		oldMenu.setCanComment(menu.getCanComment());
		oldMenu.setNeedLogin(menu.getNeedLogin());
		if (StringUtils.equals(menu.getType(), MenuType.LINK.name()))
		{
			oldMenu.setLink(StringUtils.trimToEmpty(menu.getLink()));
		}
		baseDao.modify(oldMenu);
		cachedMenuService.reload();
		return JsonData.success();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value="/menu/save.htm",produces="application/json;charset=UTF-8")
    @ResponseBody
	@RoleControl("LANMU_EDIT")
	public Object save(HttpServletRequest request)
	{
		String menuData = request.getParameter("menuData");
		System.out.println(menuData);
		MenuNode menuNode = (MenuNode)JSON.parseObject(menuData, MenuNode.class);
		Map<String, MenuNode> menuNodeMap = new HashMap<String, MenuNode>();
		MenuUtils.dealMenuNode(menuNode, 0, menuNodeMap);
		System.out.println(JSON.toJSONString(menuNode, true));
		
		/**
		 * 更新菜单项
		 * 1.添加集
		 * 2.更新集
		 * 3.删除集
		 */
		List<Menu> menus = cachedMenuService.queryAllMenus();
		Map<String, Menu> oldMenuMap = new HashMap<String, Menu>();
		Set<String> menuIdSet = new HashSet<String>();
		for (Menu menu : menus) {
			menuIdSet.add(menu.getId());
		}
		for (Menu menu : menus) {
			if (null == menu.getParentId() || menuIdSet.contains(menu.getParentId())) {
				oldMenuMap.put(menu.getId(), menu);
			} 
		}
		
		List<Menu> addMenuNodes = new ArrayList<Menu>();
		List<Menu> updateMenuNodes = new ArrayList<Menu>();
		//1.获取添加集
		//2.获取更新集
		for (Map.Entry<String, MenuNode> entry : menuNodeMap.entrySet()) {
			MenuNode itemMenuNode = entry.getValue();
			if (!oldMenuMap.containsKey(entry.getKey())) {
				Menu menu = new Menu();
				menu.setId(itemMenuNode.getId());
				menu.setName(itemMenuNode.getName());
				menu.setParentId(itemMenuNode.getpId());
				menu.setWeight(itemMenuNode.getWeight());
				menu.setType(itemMenuNode.getName());
				addMenuNodes.add(menu);
			} else if (oldMenuMap.containsKey(entry.getKey())) {
				// 目前暂支持修改菜单项名、权重
				Menu oldMenu = oldMenuMap.get(entry.getKey());
				boolean isUpdateName = !StringUtils.equals(itemMenuNode.getName(), oldMenu.getName());
				boolean isUpdateWeight = (null == oldMenu.getWeight()) 
						|| (itemMenuNode.getWeight() * 1.0 != oldMenu.getWeight().doubleValue());
				if (isUpdateName || isUpdateWeight) {
					oldMenu.setName(itemMenuNode.getName());
					oldMenu.setWeight(itemMenuNode.getWeight());
					if (StringUtils.isEmpty(oldMenu.getMediaType())) {
						oldMenu.setType(itemMenuNode.getName());
					}
					updateMenuNodes.add(oldMenu);
				}
			}
		}
		//3.获取删除集
		List<Menu> deleteMenuNodes = new ArrayList<Menu>();
		for (Map.Entry<String, Menu> entry : oldMenuMap.entrySet()) {
			Menu oldMenu = entry.getValue();
			if (!menuNodeMap.containsKey(oldMenu.getId())) {
				deleteMenuNodes.add(oldMenu);
			}
		}
		
		//4.执行更新操作
		for (Menu menu : addMenuNodes) {
			baseDao.addOrModify(menu);
			System.out.println("--->menu@add: " + menu.getId() + ":" + menu.getName() + ":" + menu.getWeight());
		}
		for (Menu menu : updateMenuNodes) {
			baseDao.addOrModify(menu);
			System.out.println("--->menu@update: " + menu.getId() + ":" + menu.getName() + ":" + menu.getWeight());
		}
		for (Menu menu : deleteMenuNodes) {
			baseDao.delete(menu);
			System.out.println("--->menu@delete: " + menu.getId() + ":" + menu.getName() + ":" + menu.getWeight());
		}
		
		JsonData jsonData = JsonData.success();
		jsonData.data.put("success", true);
		return jsonData;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/menu/init.json",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object init(HttpServletRequest request){
		JsonData jsonData = JsonData.success();
		/**
		 * 初始化菜单树: 只展现父节点存在的节点
		 */
		List<Menu> menus = cachedMenuService.queryAllMenus();
		Map<String, Menu> menuMap = new HashMap<String, Menu>();
		Set<String> menuIdSet = new HashSet<String>();
		for (Menu menu : menus) {
			menuIdSet.add(menu.getId());
		}
		for (Menu menu : menus) {
			if (null == menu.getParentId() || menuIdSet.contains(menu.getParentId())) {
				menuMap.put(menu.getId(), menu);
			} 
		}
		MenuNode menuNode = MenuUtils.getRootMenuNode(menus);
		MenuUtils.generateMenuTree(menuNode, menuMap);
		
		jsonData.data.put("menuData", JSON.toJSONString(menuNode));
		return jsonData;
    } 
}