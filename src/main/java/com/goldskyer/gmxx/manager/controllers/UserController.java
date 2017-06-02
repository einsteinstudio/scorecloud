package com.goldskyer.gmxx.manager.controllers;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.entities.Department;
import com.goldskyer.core.enums.AccountStatus;
import com.goldskyer.core.enums.AccountType;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.business.UserBusiness;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.dtos.UserDto;
import com.goldskyer.gmxx.common.entities.Teacher;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/manager")
public class UserController extends BaseManagerController
{
	
	@Autowired
	@Qualifier("aclService")
	protected AclService aclService;

	@Autowired
	protected UserBusiness userBusiness;


	@RequestMapping("/user/list.htm")
	@RoleControl("USER_VIEW")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "user/list");
		return mv;
	}



	@RequestMapping(value = "/user/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("USER_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String dept = request.getParameter("dept");
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto
				.setSql("select username,nickname,createDate,lastLoginDate,id from Account  where domain=:domain");
		if (StringUtils.isNotBlank(dept))
		{
			dataTableReqDto.setSql(dataTableReqDto.getSql() + " ");
		}
		dataTableReqDto.setOrderBy("order by username ,id");
		dataTableReqDto.setParam("domain", EnvParameter.get().getDomain());
		dataTableReqDto.setSearchField("nickname,username");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}
	
	@RequestMapping(value = "/user/dialog/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("USER_VIEW")
	public Object listDialogData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String dept = request.getParameter("dept");
		if(StringUtils.equalsIgnoreCase("root", dept))
		{
			dept = "";
		}
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto
				.setSql("select a.id,a.username,t.name from Teacher t ,Account a  where t.accountId=a.id and  t.domain=:domain");
		if (StringUtils.isNotBlank(dept))
		{
			dataTableReqDto.setSql(dataTableReqDto.getSql() + " and departmentId=:dept");
		}
		dataTableReqDto.setOrderBy("order by t.name ,t.id");
		dataTableReqDto.setParam("domain", EnvParameter.get().getDomain());
		dataTableReqDto.setParam("dept", dept);
		dataTableReqDto.setSearchField("a.username,t.name");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/user/dialog/list.htm")
	@RoleControl("USER_VIEW")
	public ModelAndView listDialog(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/dialog_templ");
		Department department = departmentBusiness.getDepartmentTree(EnvParameter.get().getDomain());
		mv.addObject("treeNode", JSONObject.fromObject(department).toString());
		mv.addObject("innerPage", "user/dialog_list");
		return mv;
	}

	@RequestMapping("/user/dialog/list_data.htm")
	@RoleControl("USER_VIEW")
	public ModelAndView listDataDialog(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");

		mv.addObject("innerPage", "user/dialog_list");
		return mv;
	}

	@RequestMapping("/user/toAdd.htm")
	@RoleControl("USER_ADD")
	public ModelAndView toAdd(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "user/add");
		mv.addObject("selectVos", departmentBusiness.queryDepartmentSelectVos());
		List<Role> roles = aclService.queryRoles(0, 1000);
		mv.addObject("roles", roles);
		return mv;
	}
	
	@RequestMapping(value = "/user/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("USER_ADD")
	public JsonData add(HttpServletRequest request, @ModelAttribute("account") Account account,
			@ModelAttribute("teacher") Teacher teacher)
	{
		UserDto userDto = new UserDto();
		account.setCreateDate(new Date());
		account.setStatus(AccountStatus.NORMAL);
		account.setType(AccountType.ADMIN);
		userDto.setAccount(account);
		String[] roleIds = request.getParameterValues("roleId");
		if (null != roleIds && roleIds.length > 0) {
			userDto.setRoleIds(Arrays.asList(roleIds));
		}
		userDto.setTeacher(teacher);
		userBusiness.addUser(userDto);
		return JsonData.success();
	}
	
	@RequestMapping("/user/toModify.htm")
	@RoleControl("USER_EDIT")
	public ModelAndView toModify(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "user/modify");
		String id = request.getParameter("id");
		List<Role> roles = aclService.queryRoles(0, 1000);
		mv.addObject("roles", roles);
		UserDto userDto = userBusiness.queryUserDtoById(id);
		mv.addObject("userDto", userDto);
		mv.addObject("selectVos", departmentBusiness.queryDepartmentSelectVos());
		return mv;
	}
	
	/**
	 * 修改我的资料
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/modifyMine.htm")
	public ModelAndView modifyMine(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "user/modifyMine");
		UserDto userDto = userBusiness.queryUserDtoById(getCurrentAccountId());
		mv.addObject("userDto", userDto);
		mv.addObject("selectVos", departmentBusiness.queryDepartmentSelectVos());
		return mv;
	}

	@RequestMapping(value = "/user/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("USER_EDIT")
	public JsonData modify(HttpServletRequest request, @ModelAttribute("account") Account account,
			@ModelAttribute("teacher") Teacher teacher)
	{
		UserDto userDto = new UserDto();
		userDto.setAccount(account);
		userDto.setTeacher(teacher);
		String[] roleIds = request.getParameterValues("roleId");
		if (null != roleIds && roleIds.length > 0) {
			//为用户分配角色
			userDto.setRoleIds(Arrays.asList(roleIds));
		}
		userBusiness.modifyUserWithRole(userDto);
		return JsonData.success();
	}
	
	@RequestMapping(value = "/user/modifyMine.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("USER_EDIT")
	public JsonData modifyMineJson(HttpServletRequest request, @ModelAttribute("account") Account account,
			@ModelAttribute("teacher") Teacher teacher)
	{
		UserDto userDto = new UserDto();
		userDto.setAccount(account);
		userDto.setTeacher(teacher);
		String[] roleIds = request.getParameterValues("roleId");
		if (null != roleIds && roleIds.length > 0)
		{
			//为用户分配角色
			userDto.setRoleIds(Arrays.asList(roleIds));
		}
		System.out.println(request.getParameter("photo"));
		if (StringUtils.isNotBlank(request.getParameter("photo")))
		{
			Pattern p = Pattern.compile("<img.*src=\"([^\\s]*)\"");
			Matcher m = p.matcher(request.getParameter("photo"));
			String imgRelPath = null;
			while (m.find())
			{
				imgRelPath = m.group(1);
				break;
			}
			userDto.getAccount().setPhoto(imgRelPath);
		}
		userBusiness.modifyUser(userDto);
		request.getSession().setAttribute("account", userDto.getAccount());
		return JsonData.success();
	}


	@RequestMapping("/user/view.htm")
	@RoleControl("USER_VIEW")
	public ModelAndView view(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "user/view");
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
	
	@RequestMapping("/user/delete.htm")
	@RoleControl("USER_DELETE")
	public ModelAndView delete(HttpServletRequest request)
	{
		String accountId = request.getParameter("id");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		userBusiness.deleteUser(accountId);
		return new ModelAndView("redirect:/manager/user/list.htm");
	}

	@RequestMapping(value = "/user/check_username.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonData checkUsername(@RequestParam String username)
	{
		boolean check = accountService.checkUserName(username);
		return !check ? JsonData.success() : JsonData.failure();
	}

	@InitBinder("account")
	public void initAccountBinder(WebDataBinder binder)
	{
		binder.setFieldDefaultPrefix("account.");
	}

	@InitBinder("teacher")
	public void initTeacherBinder(WebDataBinder binder)
	{
		binder.setFieldDefaultPrefix("teacher.");
	}
}