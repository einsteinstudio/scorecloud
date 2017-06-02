package com.goldskyer.gmxx.menu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.gmxx.manager.entities.MenuNode;
import com.goldskyer.gmxx.manager.utils.MenuUtils;
import com.goldskyer.gmxx.service.BaseTest;

import net.sf.json.JSONSerializer;
@Transactional
public class MenuServiceTest extends BaseTest {
	
	@Autowired
	@Qualifier("cachedMenuService")
	private CachedMenuService cachedMenuService;
	
	@SuppressWarnings("unused")
	@Autowired
	private BaseDao baseDao;
	
	

	@Test
	public void testQueryAllMenus() {
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
		
		System.out.println(JSONSerializer.toJSON(menuNode).toString());
		
	}
	
	
}
