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
import com.goldskyer.scorecloud.constant.GradeCosntant;
import com.goldskyer.scorecloud.constant.InnerCodeConstant;
import com.goldskyer.scorecloud.dto.GradeDto;
import com.goldskyer.scorecloud.dto.OptionDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.entities.Grade;
import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.freemarker.component.vo.OperationResultVo;
import com.goldskyer.scorecloud.service.GradeService;
import com.goldskyer.scorecloud.service.OptionService;

@Controller
@RequestMapping("/scorecloud/grade")
public class GradeController extends BaseScoreCloudController
{
	@Autowired
	private GradeService gradeService;
	@Autowired
	private OptionService optionService;

	@RequestMapping(value = "/toList.htm")
	@RoleControl("GRADE_VIEW")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/grade/toList");
		List<Grade> grades = gradeService.queryActivaeGrades(ScoreCloudEnv.get().getSchId());
		mv.addObject("grades", grades);
		return mv;
	}

	@RequestMapping(value = "/toAdd.htm")
	@RoleControl("GRADE_ADD")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/grade/toAdd");
		OptionDto gradeNameOptionDto = optionService.queryOptionDtoByInnerCode(ScoreCloudEnv.get().getSchId(),
				InnerCodeConstant.SELECT_GRADE_NAME);
		mv.addObject("gradeNameOptionDto", gradeNameOptionDto);
		return mv;
	}

	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("GRADE_ADD")
	public JsonData add(GradeDto gradeDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		gradeDto.setStatus(GradeCosntant.ACTIVE);
		gradeService.addGrade(gradeDto);
		return jsonData;
	}

	@RequestMapping(value = "/addSuccess.htm")
	@RoleControl("GRADE_ADD")
	public ModelAndView addSuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listGrade", "年级添加", "添加年级成功", "toAdd.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toModify.htm")
	@RoleControl("GRADE_MODIFY")
	public ModelAndView toModify(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/grade/toModify");
		GradeDto gradeDto = gradeService.queryGradeDto(id);
		mv.addObject("gradeDto", gradeDto);
		OptionDto gradeNameOptionDto = optionService.queryOptionDtoByInnerCode(ScoreCloudEnv.get().getSchId(),
				InnerCodeConstant.SELECT_GRADE_NAME);
		mv.addObject("gradeNameOptionDto", gradeNameOptionDto);
		return mv;
	}

	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("GRADE_MODIFY")
	public JsonData modify(GradeDto gradeDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		try
		{
			gradeService.modifyGrade(gradeDto);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			jsonData = JsonData.failure();
		}
		return jsonData;
	}

	@RequestMapping(value = "/modifySuccess.htm")
	@RoleControl("GRADE_MODIFY")
	public ModelAndView modifySuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listGrade", "年级修改", "修改年级成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toDelete.htm")
	@RoleControl("GRADE_DELETE")
	public ModelAndView toDelete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/grade/toDelete");
		return mv;
	}

	@RequestMapping(value = "/upgrade.htm")
	@RoleControl("GRADE_MODIFY")
	public ModelAndView upgrade(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listGrade", "年级升级", "年级升级成功", "toList.htm");

		mv.addObject("result", operationResultVo);
		try
		{
			String[] ids = id.split("\\|");
			for (String i : ids)
			{
				if (StringUtils.isNotBlank(i))
				{
					gradeService.upgradeGrade(i);
				}
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			operationResultVo.setOperationInfo("年级删除失败");
		}
		return mv;
	}

	@RequestMapping(value = "/delete.htm")
	@RoleControl("GRADE_DELETE")
	public ModelAndView delete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listGrade", "年级删除", "年级删除成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		try
		{
			String[] ids = id.split("\\|");
			for (String i : ids)
			{
				if (StringUtils.isNotBlank(i))
				{
					gradeService.deleteGrade(i);
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
