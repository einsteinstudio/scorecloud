package com.goldskyer.gmxx.acl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.acl.entities.Right;
import com.goldskyer.gmxx.acl.service.RightService;

@Service("aclRightService")
public class RightSericeImpl extends CoreServiceImp implements RightService
{

	@Override
	public Map<String, List<Right>> queryGroupedRights()
	{
		Map<String, List<Right>> map = new HashMap<>();
		List<Right> rights = baseDao.query("select r from Right r where r.domain=?", EnvParameter.get().getDomain());
		for (Right right : rights)
		{
			if (map.containsKey(right.getType()))
			{
				map.get(right.getType()).add(right);
			}
			else
			{
				map.put(right.getType(), new ArrayList<Right>());
				map.get(right.getType()).add(right);
			}
		}
		return map;
	}

	public List<Right> queryRightsByRoleId(String roleId)
	{
		return baseDao.query("select r from RoleRight rr ,Right r where rr.roleId=? and rr.rightId=r.id", roleId);
	}

	public Map<String, Right> queryRightMapByRoleId(String roleId)
	{
		Map<String, Right> rightMap = new HashMap<>();
		List<Right> rights = queryRightsByRoleId(roleId);
		for(Right r:rights )
		{
			rightMap.put(r.getId(), r);
		}
		return rightMap;
	}

}
