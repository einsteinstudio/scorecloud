package com.goldskyer.gmxx.acl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.acl.entities.RoleMenu;
import com.goldskyer.gmxx.acl.service.RoleMenuService;

@Service
public class RoleMenuServiceImpl extends CoreServiceImp implements RoleMenuService
{

	public void addRoleMenu(RoleMenu roleMenu)
	{
		baseDao.add(roleMenu);
	}

	public void deleteRoleMenu(RoleMenu roleMenu)
	{
		baseDao.delete(roleMenu);
	}

	public List<Menu> queryMenusByAccountId(String accountId)
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("accountId", accountId);
		return baseDao.query(
				"select distinct m from AccountRole ar, Role r ,RoleMenu rm ,Menu m where  ar.accountId=:accountId  and ar.roleId=r.id and r.id=rm.roleId and rm.menuId =m.id",
				paraMap);
	}

	public List<Menu> queryMenusByRoleId(String roleId)
	{
		return baseDao.query("select m from RoleMenu rm ,Menu m where rm.menuId=m.id and rm.roleId=?", roleId);
	}

	/**
	 * 获取角色授权的栏目MAP
	 */
	public Map<String, Menu> queryMenuMapByRoleId(String roleId)
	{
		Map<String, Menu> menuMap = new HashMap<>();
		List<Menu> menus = queryMenusByRoleId(roleId);
		for (Menu m : menus)
		{
			menuMap.put(m.getId(), m);
		}
		return menuMap;
	}

	@Transactional
	public void modifyRoleMenu(String roleId, String menuIds)
	{
		if (StringUtils.isBlank(menuIds))
		{
			return;
		}
		baseDao.execute("delete from RoleMenu where roleId=?", roleId);
		List<RoleMenu> roleMenus = new ArrayList<>();
		String [] ids=menuIds.split(",");
		for(int i=0;i<ids.length;i++)
		{
			RoleMenu rm = new RoleMenu();
			rm.setMenuId(ids[i]);
			rm.setRoleId(roleId);
			roleMenus.add(rm);
		}
		baseDao.add(roleMenus);
	}
}
