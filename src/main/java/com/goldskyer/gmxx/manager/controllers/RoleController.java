package com.goldskyer.gmxx.manager.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.enums.MenuModule;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.vo.MenuVo;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.entities.RoleRight;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.acl.service.RightService;
import com.goldskyer.gmxx.acl.service.RoleMenuService;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.helpers.MenuHelper;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/manager")
public class RoleController extends BaseManagerController
{
	@Autowired
	private CachedMenuService cachedMenuService;
	@Autowired
	@Qualifier("aclService")
	protected AclService aclService;

	@Autowired
	protected RoleMenuService roleMenuService;

	@Autowired
	protected RightService rightService;
	@Autowired
	protected BaseDao baseDao;

	@RoleControl("ROLE_VIEW")
	@RequestMapping("/role/list.htm")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "role/list");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());
		return mv;
	}

	//	@SuppressWarnings("unchecked")
	//	@RequestMapping(value = "/role/menu/init.json", produces = "application/json;charset=UTF-8")
	//	@ResponseBody
	//	public Object init(HttpServletRequest request)
	//	{
	//		return MenuHelper.queryMenuJson("栏目管理");
	//	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked", "deprecation"})
	@RequestMapping(value = "/role/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("ROLE_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		JsonData jsonData = JsonData.success();
		long total = aclService.countTotalRoles();//EnvParameter.get().getDomain()
		List<Role> roles = aclService.queryRoles(dataTableReqDto.getStart(), dataTableReqDto.getLength());

		List<Object[]> list = new ArrayList<Object[]>();
		for (Role role : roles)
		{
			Object[] ss = new Object[6];
			ss[0] = role.getRoleNo();
			ss[1] = role.getRoleName();
			ss[2] = role.getStatus();
			ss[3] = role.getCreateDate().toGMTString();
			ss[4] = role.getId();
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
	
	@RequestMapping(value = "/role/toAdd.htm")
	@RoleControl("ROLE_ADD")
	public ModelAndView toAdd(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "role/toAdd");
		
		Map groupedRights = rightService.queryGroupedRights();
		mv.addObject("groupedRights", groupedRights);
		return mv;
	}
	
	@RequestMapping(value = "/role/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("ROLE_ADD")
	public JsonData add(HttpServletRequest request, Role role, @RequestParam(required = false) String useStatus)
	{
		if (StringUtils.equalsIgnoreCase(useStatus, "on"))
		{
			role.setStatus(1);
		}
		List<RoleRight> roleRights = new ArrayList<>();
		for (Object key : request.getParameterMap().keySet())
		{
			String param = key.toString();
			if (param.startsWith("right_"))
			{
				RoleRight roleRight = new RoleRight();
				roleRight.setCreateDate(new Date());
				roleRight.setRightId(param.replace("right_", ""));
				roleRights.add(roleRight);
			}
		}
		String roleId = aclService.addRole(role, roleRights);
		return JsonData.success();
	}
	
	@RequestMapping("/role/toModify.htm")
	@RoleControl("ROLE_EDIT")
	public ModelAndView toModify(HttpServletRequest request, @RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "role/toModify");
		Role role = aclService.queryRole(id);
		Map rightMap = rightService.queryRightMapByRoleId(role.getId());
		mv.addObject("rightMap", rightMap);
		mv.addObject("role", role);
		Map groupedRights = rightService.queryGroupedRights();
		mv.addObject("groupedRights", groupedRights);
		return mv;
	}
	
	@RequestMapping(value = "/role/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("ROLE_EDIT")
	public JsonData modify(HttpServletRequest request, Role role, @RequestParam(required = false) String useStatus)
	{
		role.setStatus(StringUtils.equalsIgnoreCase(useStatus, "on") ? 1 : 0);
		List<RoleRight> roleRights = new ArrayList<>();
		for (Object key : request.getParameterMap().keySet())
		{
			String param = key.toString();
			if (param.startsWith("right_"))
			{
				RoleRight roleRight = new RoleRight();
				roleRight.setCreateDate(new Date());
				roleRight.setRightId(param.replace("right_", ""));
				roleRights.add(roleRight);
			}
		}
		aclService.modifyRole(role, roleRights);
		return JsonData.success();
	}
	
	@RequestMapping(value = "/role/toAuthMenu.htm")
	@RoleControl("ROLE_AUTH_MENU")
	public ModelAndView toAuthMenu(@RequestParam String roleId,HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "role/toAuthMenu");
		mv.addObject("roleId", roleId);
		Map<String,Menu> menuMap = roleMenuService.queryMenuMapByRoleId(roleId);
		//栏目数据初始化 
		MenuVo vo = MenuHelper.buildMenuVo(MenuModule.MASTER.getName(), menuMap);
		mv.addObject("menuData", JSONObject.fromObject(vo).toString());
		return mv;
	}
	
	@RequestMapping(value = "/role/authMenu.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("ROLE_AUTH_MENU")
	public JsonData authMenu(@RequestParam String roleId, String menuIds)
	{
		if (StringUtils.isBlank(menuIds))
		{
			return JsonData.success();
		}
		roleMenuService.modifyRoleMenu(roleId, menuIds);
		return JsonData.success();
		
	}

	@RequestMapping("/role/delete.htm")
	@RoleControl("ROLE_DELETE")
	public ModelAndView delete(HttpServletRequest request)
	{
		String roleId = request.getParameter("id");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		aclService.deleteRole(roleId);
		return new ModelAndView("redirect:/manager/role/list.htm");
	}
}