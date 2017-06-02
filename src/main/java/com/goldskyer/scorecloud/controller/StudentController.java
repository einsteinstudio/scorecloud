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
import com.goldskyer.scorecloud.dto.OptionDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.StudentDto;
import com.goldskyer.scorecloud.freemarker.component.SelectComponent;
import com.goldskyer.scorecloud.freemarker.component.vo.OperationResultVo;
import com.goldskyer.scorecloud.freemarker.component.vo.OptGroupVoWrapper;
import com.goldskyer.scorecloud.service.StudentService;

@Controller
@RequestMapping("/scorecloud/student")
public class StudentController extends BaseScoreCloudController
{
	@Autowired
	private StudentService studentService;
	@Autowired
	private SelectComponent selectComponent;
	
	@RequestMapping(value = "/toList.htm")
	@RoleControl("STUDENT_VIEW")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/student/toList");
		String gradeId = StringUtils.trimToEmpty(request.getParameter("gradeId"));
		OptionDto gradeIdOptionDto = selectComponent.buildGradeIdSelect();
		if (StringUtils.isBlank(gradeId))
		{
			gradeId = gradeIdOptionDto.getOptionValueDtos().get(0).getValue();
		}
		List<StudentDto> studentDtos = studentService.queryActiveStudentDtosByGradeId(gradeId);
		mv.addObject("studentDtos", studentDtos);
		mv.addObject("gradeIdOptionDto", gradeIdOptionDto);
		mv.addObject("gradeId", gradeId);
		return mv;
	}

	@RequestMapping(value = "/toAdd.htm")
	@RoleControl("STUDENT_ADD")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/student/toAdd");
		OptGroupVoWrapper classIdWrapper = selectComponent.buildCLassIdSelect();
		mv.addObject("classIdWrapper", classIdWrapper);
		return mv;
	}

	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("STUDENT_ADD")
	public JsonData add(StudentDto studentDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		studentDto.setStatus(1);
		studentDto.setSchId(ScoreCloudEnv.get().getSchId());
		studentService.addStudentDto(studentDto);
		return jsonData;
	}

	@RequestMapping(value = "/addSuccess.htm")
	@RoleControl("STUDENT_ADD")
	public ModelAndView addSuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listStudent", "学生添加", "添加学生成功", "toAdd.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toModify.htm")
	@RoleControl("STUDENT_MODIFY")
	public ModelAndView toModify(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/student/toModify");
		StudentDto studentDto = studentService.queryStudentDtoById(id);
		mv.addObject("dto", studentDto);
		OptGroupVoWrapper classIdWrapper = selectComponent.buildCLassIdSelect();
		mv.addObject("classIdWrapper", classIdWrapper);
		return mv;
	}

	/**
	 * 密码修改规则，发现传过来的密码和已有的密码不一致，则说明密码有改动，需要重新进行MD5加密(md5(md5(orign)))都是小写
	 * @param studentDto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("STUDENT_MODIFY")
	public JsonData modify(StudentDto studentDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		studentDto.setStatus(1);
		studentDto.setSchId(ScoreCloudEnv.get().getSchId());
		studentService.modifyStudentDto(studentDto);
		return jsonData;
	}

	@RequestMapping(value = "/modifySuccess.htm")
	@RoleControl("STUDENT_MODIFY")
	public ModelAndView modifySuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listStudent", "学生修改", "修改学生成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toDelete.htm")
	@RoleControl("STUDENT_DELETE")
	public ModelAndView toDelete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/student/toDelete");
		return mv;
	}

	@RequestMapping(value = "/delete.htm")
	@RoleControl("STUDENT_DELETE")
	public ModelAndView delete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listStudent", "学生删除", "学生删除成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		try
		{
			String[] ids = id.split("\\|");
			for (String i : ids)
			{
				if (StringUtils.isNotBlank(i))
				{
					studentService.deleteStudentDtoById(i);
				}
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			operationResultVo.setOperationInfo("学生删除失败");
		}
		return mv;
	}

}
