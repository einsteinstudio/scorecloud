package com.goldskyer.gmxx.common.business;

import java.util.List;

import com.goldskyer.core.entities.Department;
import com.goldskyer.gmxx.common.vos.SelectVo;

public interface DepartmentBusiness
{
	public Department getDepartmentTree(String domain);

	public List<SelectVo> queryDepartmentSelectVos();
}
