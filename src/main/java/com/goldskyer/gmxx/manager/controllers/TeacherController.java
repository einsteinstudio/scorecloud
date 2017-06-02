package com.goldskyer.gmxx.manager.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.entities.Teacher;

@Controller
@RequestMapping("/manager")
public class TeacherController extends BaseManagerController
{
	
	@Autowired
	@Qualifier("aclService")
	protected AclService aclService;

	@RequestMapping("/teacher/list.htm")
	@RoleControl("TEACHER_VIEW")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "teacher/list");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());
		return mv;
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked", "deprecation"})
	@RequestMapping(value = "/teacher/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("TEACHER_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		JsonData jsonData = JsonData.success();
		long total = aclService.countTotalTeachers();//EnvParameter.get().getDomain()
		List<Teacher> teachers = aclService.queryTeachers(dataTableReqDto.getStart(), dataTableReqDto.getLength());

		List<Object[]> list = new ArrayList<Object[]>();
		for (Teacher teacher : teachers)
		{
			Object[] ss = new Object[6];
			ss[0] = teacher.getName();
			ss[1] = teacher.getGender();
			ss[2] = teacher.getMobile();
			ss[3] = teacher.getDiscipline();
			ss[4] = teacher.getId();
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
	
	@RequestMapping("/teacher/toAdd.htm")
	@RoleControl("TEACHER_ADD")
	public ModelAndView toAdd(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "teacher/add");
		return mv;
	}
	
	@RequestMapping("/teacher/add.htm")
	@RoleControl("TEACHER_ADD")
	public ModelAndView add(HttpServletRequest request, @ModelAttribute("teacher") Teacher teacher)
	{
		teacher.setCreateDate(new Date());
		String teacherId = aclService.addTeacher(teacher);
		return new ModelAndView("redirect:/manager/teacher/list.htm");
	}
	
	@RequestMapping("/teacher/toModify.htm")
	@RoleControl("TEACHER_EDIT")
	public ModelAndView toModify(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "teacher/modify");
		
		String id = request.getParameter("id");
		Teacher teacher = aclService.queryTeacher(id);
		
		mv.addObject("teacher", teacher);
		return mv;
	}
	
	@RequestMapping("/teacher/modify.htm")
	@RoleControl("TEACHER_EDIT")
	public ModelAndView modify(HttpServletRequest request, @ModelAttribute("teacher") Teacher teacher)
	{
		Teacher dbTeacher = aclService.queryTeacher(teacher.getId());
		dbTeacher.setName(teacher.getName());
		dbTeacher.setGender(teacher.getGender());
		dbTeacher.setAddress(teacher.getAddress());
		dbTeacher.setQq(teacher.getQq());
		dbTeacher.setEmail(teacher.getEmail());
		dbTeacher.setIdcardno(teacher.getIdcardno());
		dbTeacher.setJoblevel(teacher.getJoblevel());
		dbTeacher.setJobnum(teacher.getJobnum());
		dbTeacher.setDiscipline(teacher.getDiscipline());
		dbTeacher.setMobile(teacher.getMobile());
		dbTeacher.setOfficetel(teacher.getOfficetel());
		aclService.modifyTeacher(dbTeacher);
		return new ModelAndView("redirect:/manager/teacher/list.htm");
	}
	
	@RequestMapping("/teacher/view.htm")
	@RoleControl("TEACHER_VIEW")
	public ModelAndView view(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "teacher/view");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		String teacherId = request.getParameter("id");
		Teacher teacher = aclService.queryTeacher(teacherId);
		if (teacher == null)
		{
			throw new BusinessException("当前请求记录不存在");
		}
		
		mv.addObject("teacher", teacher);
		return mv;
	}
	
	@RequestMapping("/teacher/delete.htm")
	@RoleControl("TEACHER_DELETE")
	public ModelAndView delete(HttpServletRequest request)
	{
		String teacherID = request.getParameter("id");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		aclService.deleteTeacher(teacherID);
		return new ModelAndView("redirect:/manager/teacher/list.htm");
	}
}