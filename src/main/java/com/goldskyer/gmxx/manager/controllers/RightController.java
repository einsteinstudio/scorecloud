package com.goldskyer.gmxx.manager.controllers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.enums.AccountStatus;
import com.goldskyer.gmxx.acl.entities.Right;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;

@Controller
@RequestMapping("/manager")
public class RightController extends BaseManagerController
{
	
	@Autowired
	@Qualifier("aclService")
	protected AclService aclService;

	@RequestMapping("/right/list.htm")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "right/list");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());
		return mv;
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked", "deprecation"})
	@RequestMapping(value = "/right/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		JsonData jsonData = JsonData.success();
		long total = aclService.countTotalRights();//EnvParameter.get().getDomain()
		List<Right> rights = aclService.queryRights(dataTableReqDto.getStart(), dataTableReqDto.getLength());

		List<Object[]> list = new ArrayList<Object[]>();
		for (Right right : rights)
		{
			Object[] ss = new Object[6];
			ss[0] = right.getRightNo();
			ss[1] = right.getRightName();
			ss[2] = right.getRecordStatus();
			ss[3] = right.getCreateDate().toGMTString();
			ss[4] = right.getId();
			list.add(ss);
		}

		jsonData.data.put("list", list);
		Map map = new HashMap();
		map.put("data", list);
		map.put("recordsTotal", total);
		map.put("recordsFiltered", total);
		map.put("draw", Integer.parseInt(request.getParameter("draw") == null ? "0" : request.getParameter("draw")) + 1);
		return map;
	}
	
	@RequestMapping("/right/toAdd.htm")
	public ModelAndView toAdd(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "right/add");
		
		List<Role> roles = aclService.queryRoles(0, 1000);
		mv.addObject("roles", roles);
		return mv;
	}
	
	@RequestMapping("/right/add.htm")
	public ModelAndView add(HttpServletRequest request, @ModelAttribute("account") Account account)
	{
		account.setCreateDate(new Date());
		account.setStatus(AccountStatus.NORMAL);
		String accountId = aclService.addAccount(account);
		
		String[] roleIds = request.getParameterValues("roleId");
		if (null != roleIds && roleIds.length > 0) {
			//为用户分配角色
			aclService.dispatchRolesForUser(accountId, Arrays.asList(roleIds));
		}
		return new ModelAndView("redirect:/manager/right/list.htm");
	}
	
	@RequestMapping("/right/toModify.htm")
	public ModelAndView toModify(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "right/modify");
		
		String id = request.getParameter("id");
		Account account = aclService.queryAccount(id);
		
		//获取账号角色
		List<Role> curRoles = aclService.queryRolesByUser(id);
		Set<String> curRoleIds = new HashSet<String>();
		if (CollectionUtils.isNotEmpty(curRoles)) {
			for (Role role : curRoles) {
				curRoleIds.add(role.getId());
			}
		}
		mv.addObject("curRoleIds", curRoleIds);
		
		List<Role> roles = aclService.queryRoles(0, 1000);
		mv.addObject("roles", roles);
		
		mv.addObject("account", account);
		return mv;
	}
	
	@RequestMapping("/right/modify.htm")
	public ModelAndView modify(HttpServletRequest request, @ModelAttribute("account") Account account)
	{
		
		Account dbAccount = aclService.queryAccount(account.getId());
		dbAccount.setUsername(account.getUsername());
		dbAccount.setPassword(account.getPassword());
		dbAccount.setNickname(account.getNickname());
		
		String[] roleIds = request.getParameterValues("roleId");
		if (null != roleIds && roleIds.length > 0) {
			//为用户分配角色
			aclService.dispatchRolesForUser(account.getId(), Arrays.asList(roleIds));
		}
		return new ModelAndView("redirect:/manager/right/list.htm");
	}
	
	@RequestMapping("/right/view.htm")
	public ModelAndView view(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "right/view");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		String accountId = request.getParameter("id");
		Account account = aclService.queryAccount(accountId);
		if (account == null)
		{
			throw new BusinessException("当前请求记录不存在");
		}
		
		//获取账号角色
		List<Role> roles = aclService.queryRolesByUser(accountId);
		
		mv.addObject("account", account);
		mv.addObject("roles", roles);
		return mv;
	}
	
	@RequestMapping("/right/delete.htm")
	public ModelAndView delete(HttpServletRequest request)
	{
		String accountId = request.getParameter("id");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		aclService.deleteAccount(accountId);
		return new ModelAndView("redirect:/manager/right/list.htm");
	}
}