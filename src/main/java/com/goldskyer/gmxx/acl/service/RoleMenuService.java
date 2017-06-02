package com.goldskyer.gmxx.acl.service;

import java.util.List;
import java.util.Map;

import com.goldskyer.core.entities.Menu;
import com.goldskyer.gmxx.acl.entities.RoleMenu;

public interface RoleMenuService
{
	public void addRoleMenu(RoleMenu roleMenu);

	public void deleteRoleMenu(RoleMenu roleMenu);

	public List<Menu> queryMenusByAccountId(String accountId);

	public void modifyRoleMenu(String roleId, String menuIds);

	public List<Menu> queryMenusByRoleId(String roleId);

	/**
	 * 获取角色授权的栏目MAP
	 */
	public Map<String, Menu> queryMenuMapByRoleId(String roleId);
}
