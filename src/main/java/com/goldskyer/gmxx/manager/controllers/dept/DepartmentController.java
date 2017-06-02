package com.goldskyer.gmxx.manager.controllers.dept;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Department;
import com.goldskyer.core.service.DepartmentService;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.business.DepartmentBusiness;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;
import com.goldskyer.gmxx.manager.controllers.BaseManagerController;

import net.sf.json.JSONObject;

@Controller("managerDepartmentController")
@RequestMapping("/manager/dept/")
public class DepartmentController extends BaseManagerController
{
	@Autowired
	protected DepartmentBusiness departmentBusiness;
	@Autowired
	protected DepartmentService departmentService;

	@RequestMapping("/toAdd.htm")
	@RoleControl("DEPT_ADD")
	public ModelAndView toAdd(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "dept/department_toAdd");
		mv.addObject("pId", pId);
		mv.addObject("selectVos", departmentBusiness.queryDepartmentSelectVos());
		return mv;
	}


	/**
	 * 同时具有修改和删除的功能
	 * @param request
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("DEPT_ADD")
	public JsonData addJson(HttpServletRequest request, Department department)
	{
		JsonData jsonData=JsonData.success();
		department.setDomain(EnvParameter.get().getDomain());
		if (StringUtils.isBlank(department.getParentId()))
		{
			department.setParentId("root");
		}
		if (department.getWeight() == null)
			department.setWeight(10);
		String id = department.getId();
		if (StringUtils.isBlank(department.getId()))
		{
			id = departmentService.addDepartment(department);
		}
		else
		{
			departmentService.modifyDepartment(department);
		}
		jsonData.data.put("department", baseDao.query(Department.class, id));
		return jsonData;
	}

	@RequestMapping("/toModify.htm")
	@RoleControl("DEPT_EDIT")
	public ModelAndView toModify(HttpServletRequest request, @RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "dept/department_toModify");
		Department dept = baseDao.query(Department.class, id);
		mv.addObject("dept", dept);
		mv.addObject("selectVos", departmentBusiness.queryDepartmentSelectVos());
		return mv;
	}

	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("DEPT_EDIT")
	public JsonData modify(HttpServletRequest request, Department department)
	{
		department.setDomain(EnvParameter.get().getDomain());
		departmentService.modifyDepartment(department);
		return JsonData.success();
	}

	@RequestMapping(value = "/item.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("DEPT_EDIT")
	public JsonData item(HttpServletRequest request, @RequestParam String id)
	{
		Department department = baseDao.query(Department.class, id);
		JsonData jsonData = JsonData.success();
		jsonData.data.put("department", department);
		return jsonData;
	}

	@RequestMapping(value = "/view.htm")
	@RoleControl("DEPT_VIEW")
	public ModelAndView view(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "dept/department_view");
		Department department = departmentService.getDepartmentTree(EnvParameter.get().getDomain());
		mv.addObject("treeNode", JSONObject.fromObject(department).toString());
		return mv;
	}

	@RequestMapping(value = "/list.htm")
	@RoleControl("DEPT_VIEW")
	public ModelAndView list(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		if (StringUtils.isBlank(pId))
		{
			pId = "root";
		}
		mv.addObject("innerPage", "dept/department_list");
		mv.addObject("pId", pId);
		return mv;
	}

	@RequestMapping(value = "/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("DEPT_VIEW")
	public Object listJson(DataTableReqDto dataTableReqDto, HttpServletRequest request,
			@RequestParam(required = false) String pId)
	{
		if (StringUtils.isBlank(pId))
		{
			pId = "root";
		}
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select name ,phone,weight,id from Department  where domain=:domain and parentId=:parentId");
		dataTableReqDto.setOrderBy("order by weight,id");
		dataTableReqDto.setParam("domain", EnvParameter.get().getDomain());
		dataTableReqDto.setParam("parentId", pId);
		dataTableReqDto.setSearchField("name,phone,duty");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/delete.htm")
	@RoleControl("DEPT_DELETE")
	public ModelAndView delete(HttpServletRequest request)
	{
		String id = request.getParameter("id");
		departmentService.deleteDepartment(id);
		return new ModelAndView("redirect:list.htm?pId=" + request.getParameter("pId"));
	}
}
