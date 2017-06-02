/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: gmxx
 * 
 * MenuUtil.java File Created at 下午9:18:02
 * 
 * 
 * Copyright 2014 Taobao.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Taobao Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Taobao.com.
 */
package com.goldskyer.gmxx.manager.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.goldskyer.core.entities.Menu;
import com.goldskyer.gmxx.manager.entities.MenuNode;

/**
 * 菜单工具
 * 
 * @author zhang.li
 * @version 1.0
 * @since 2016-4-14
 */
public class MenuUtils {
	
	public static MenuNode getRootMenuNode(List<Menu> menus) {
		MenuNode menuNode = null;
		for (Menu menu : menus) {
			if (null == menu.getParentId()) {
				menuNode = new MenuNode();
				menuNode.setId(menu.getId());
				menuNode.setName(menu.getName());
				menuNode.setOpen(true);
				if (null != menu.getWeight()) {
					menuNode.setWeight(menu.getWeight());
				}
				break;
			} 
		}
		return menuNode;
	}
	
	public static boolean isLeafNode(MenuNode menuNode, Map<String, Menu> menuMap) {
		Set<String> nonLeafIdSet = new HashSet<String>();
		for (Menu menu : menuMap.values()) {
			if (null != menu.getParentId()) {
				nonLeafIdSet.add(menu.getParentId());
			}
		}
		return !nonLeafIdSet.contains(menuNode.getId());
	}
	
	public static void generateMenuTree(MenuNode menuNode, Map<String, Menu> menuMap) {
		if (!isLeafNode(menuNode, menuMap)) {
			List<MenuNode> children = null;
			if (null == menuNode.getChildren()) {
				children = new ArrayList<MenuNode>();
				menuNode.setChildren(children);
			} else {
				children = menuNode.getChildren();
			}
			for (Map.Entry<String, Menu> entry : menuMap.entrySet()) {
				Menu menu = entry.getValue();
				if (null != menu.getParentId() && menu.getParentId().equals(menuNode.getId())) {
					MenuNode tmpNode = new MenuNode();
					tmpNode.setId(menu.getId());
					tmpNode.setName(menu.getName());
					tmpNode.setOpen(true);
					if (null != menu.getWeight()) {
						tmpNode.setWeight(menu.getWeight());
					}
					children.add(tmpNode);
					//对菜单项按权重排序
					Collections.sort(children, new Comparator<MenuNode>() {
						@Override
						public int compare(MenuNode o1, MenuNode o2) {
							if (o1.getWeight() > o2.getWeight()) {
								return 1;
							} else if (o1.getWeight() < o2.getWeight()) {
								return -1;
							}
							return 0;
						}
					});
					
					generateMenuTree(tmpNode, menuMap);
				}
			}
		}
	}
	
	//-------------------------------------------------------------------------------------
	/**
	 * 
	 * 遍历菜单项,刷新权重
	 * 
	 * @author zhang.li
	 * @version 1.0
	 * @since 2016-4-18 
	 *
	 * @param menuNode
	 * @param weight
	 */
	public static void dealMenuNode(MenuNode menuNode, int weight, Map<String, MenuNode> menuNodeMap) {
		menuNode.setWeight(weight);
		menuNodeMap.put(menuNode.getId(), menuNode);
		List<MenuNode> menuNodes = menuNode.getChildren();
		if (CollectionUtils.isNotEmpty(menuNodes)) {
			for (int i = 0; i < menuNodes.size(); i++) {
				dealMenuNode(menuNodes.get(i), i, menuNodeMap);
			}
		}
	}
}