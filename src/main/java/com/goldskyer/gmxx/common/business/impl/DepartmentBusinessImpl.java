package com.goldskyer.gmxx.common.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.goldskyer.core.SpringContextHolder;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Department;
import com.goldskyer.core.service.DepartmentService;
import com.goldskyer.gmxx.common.business.DepartmentBusiness;
import com.goldskyer.gmxx.common.vos.SelectVo;

@Service("departmentBusiness")
public class DepartmentBusinessImpl implements DepartmentBusiness
{
	@Autowired
	protected CacheManager cacheManager;
	@Autowired
	private DepartmentService departmentService;
	
	@Cacheable(value =
	{ "dept" }, key = "#domain.concat('departmentTree')")
	public Department getDepartmentTree(String domain)
	{
		return departmentService.getDepartmentTree(domain);
	}

	public List<SelectVo> queryDepartmentSelectVos()
	{
		DepartmentBusiness departmentBusiness = SpringContextHolder.getBean("departmentBusiness");
		Department root = departmentBusiness.getDepartmentTree(EnvParameter.get().getDomain());
		List<SelectVo> vos = new ArrayList<SelectVo>();
		deepSearch(vos, root, 0);
		return vos;
	}


	private void deepSearch(List<SelectVo> vos, Department department, final int deep)
	{
		String prefix = "";
		int i = 0;
		while (i < deep)
		{
			prefix += "-";
			i++;
		}
		if (department == null)
			return;
		for (Department d : department.getChildren())
		{
			SelectVo vo = new SelectVo();
			vo.setName(prefix + d.getName());
			vo.setValue(d.getId());
			vos.add(vo);
			deepSearch(vos, d, (deep + 1));
		}
	}
}
