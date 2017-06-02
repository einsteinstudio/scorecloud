package com.goldskyer.scorecloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.scorecloud.dto.ClassDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.freemarker.component.SelectComponent;
import com.goldskyer.scorecloud.freemarker.component.vo.OperationResultVo;
import com.goldskyer.scorecloud.service.ClassService;

@Controller
@RequestMapping("/scorecloud/class")
public class ClassController extends BaseScoreCloudController
{
	@Autowired
	private ClassService classService;
	@Autowired
	private SelectComponent selectComponent;
	@RequestMapping(value = "/toList.htm")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/class/toList");
		List<ClassDto> classDtos = classService.queryAllClassesGroupByGrade(ScoreCloudEnv.get().getSchId());
		mv.addObject("classDtos", classDtos);
		return mv;
	}

	@RequestMapping(value = "/toAdd.htm")
	@RoleControl("CLASS_ADD")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/class/toAdd");
		mv.addObject("gradeIdOptionDto", selectComponent.buildGradeIdSelect());
		return mv;
	}

	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("CLASS_ADD")
	public JsonData add(ClassDto classDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		classService.addCladdDto(classDto);
		return jsonData;
	}

	@RequestMapping(value = "/addSuccess.htm")
	@RoleControl("CLASS_ADD")
	public ModelAndView addSuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listClass", "班级添加", "添加班级成功", "toAdd.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toModify.htm")
	@RoleControl("CLASS_MODIFY")
	public ModelAndView toModify(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/class/toModify");
		ClassDto classDto = classService.queryClassDtoById(id);
		mv.addObject("classDto", classDto);
		mv.addObject("gradeIdOptionDto", selectComponent.buildGradeIdSelect());
		return mv;
	}

	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("CLASS_MODIFY")
	public JsonData modify(ClassDto classDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		try
		{
			classService.modifyClassDto(classDto);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			jsonData = JsonData.failure();
		}
		return jsonData;
	}

	@RequestMapping(value = "/modifySuccess.htm")
	@RoleControl("CLASS_MODIFY")
	public ModelAndView modifySuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listClass", "班级修改", "修改班级成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toDelete.htm")
	@RoleControl("CLASS_DELETE")
	public ModelAndView toDelete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/class/toDelete");
		return mv;
	}

	@RequestMapping(value = "/delete.htm")
	@RoleControl("CLASS_DELETE")
	public ModelAndView delete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listClass", "班级删除", "班级删除成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		try
		{
			String[] ids = id.split("\\|");
			for (String i : ids)
			{
				if (StringUtils.isNotBlank(i))
				{
					classService.deleteClassDto(i);
				}
			}
		}
		catch (RespCodeException e)
		{
			log.error(e.getMessage(), e);
			operationResultVo.setOperationInfo(e.getRespCode().getDesc());
		}
		return mv;
	}

}
