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
import com.goldskyer.scorecloud.constant.InnerCodeConstant;
import com.goldskyer.scorecloud.dto.ExamDto;
import com.goldskyer.scorecloud.dto.OptionDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.SubjectDto;
import com.goldskyer.scorecloud.freemarker.component.vo.OperationResultVo;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.OptionService;
import com.goldskyer.scorecloud.service.SubjectService;

@Controller
@RequestMapping("/scorecloud/exam")
public class ExamController extends BaseScoreCloudController
{
	@Autowired
	private ExamService examService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private OptionService optionService;

	@RequestMapping(value = "/toList.htm")
	@RoleControl("EXAM_VIEW")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/exam/toList");
		List<ExamDto> examDtos = examService.queryExamDtos();
		mv.addObject("examDtos", examDtos);
		return mv;
	}

	@RequestMapping(value = "/toAdd.htm")
	@RoleControl("EXAM_ADD")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/exam/toAdd");
		return mv;
	}

	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("EXAM_ADD")
	public JsonData add(ExamDto examDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		examService.addExamDto(examDto);
		return jsonData;
	}

	@RequestMapping(value = "/addSuccess.htm")
	@RoleControl("EXAM_ADD")
	public ModelAndView addSuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listExam", "考试添加", "添加考试成功", "toAdd.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toModify.htm")
	@RoleControl("EXAM_MODIFY")
	public ModelAndView toModify(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/exam/toModify");
		ExamDto examDto = examService.queryExamDtoById(id);
		mv.addObject("examDto", examDto);
		OptionDto subjectOptionDto = optionService.queryOptionDtoByInnerCode(ScoreCloudEnv.get().getSchId(),
				InnerCodeConstant.SELECT_SUBJECT);
		mv.addObject("subjectOptionDto", subjectOptionDto);
		return mv;
	}

	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("EXAM_MODIFY")
	public JsonData modify(ExamDto examDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		try
		{
			examService.modifyExamDto(examDto);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			jsonData = JsonData.failure();
		}
		return jsonData;
	}

	@RequestMapping(value = "/modifySuccess.htm")
	@RoleControl("EXAM_MODIFY")
	public ModelAndView modifySuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listExam", "考试修改", "修改考试成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toDelete.htm")
	@RoleControl("EXAM_DELETE")
	public ModelAndView toDelete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/exam/toDelete");
		return mv;
	}

	@RequestMapping(value = "/delete.htm")
	@RoleControl("EXAM_DELETE")
	public ModelAndView delete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listExam", "考试删除", "考试删除成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		try
		{
			String[] ids = id.split("\\|");
			for (String i : ids)
			{
				if (StringUtils.isNotBlank(i))
				{
					examService.deleteExamDto(i);
				}
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			operationResultVo.setOperationInfo("考试删除失败");
		}
		return mv;
	}

	@RequestMapping(value = "/subject/addOrModify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("EXAM_MODIFY")
	public JsonData subjectAddorModify(SubjectDto subjectDto, HttpServletRequest request,
			HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		subjectService.addOrModifySUbjectDto(subjectDto);
		jsonData.data.put("rowId", subjectDto.getId());
		return jsonData;
	}

	@RequestMapping(value = "/subject/delete.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("EXAM_MODIFY")
	public JsonData subjectDelete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		try
		{
			subjectService.deleteSubjectById(id);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			jsonData = JsonData.failure();
		}
		return jsonData;
	}
}
