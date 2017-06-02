package com.goldskyer.gmxx.acl.service;

import java.util.List;
import java.util.Map;

import com.goldskyer.gmxx.acl.entities.Right;

public interface RightService
{
	public Map<String, List<Right>> queryGroupedRights();

	public List<Right> queryRightsByRoleId(String roleId);

	public Map<String, Right> queryRightMapByRoleId(String roleId);
}
