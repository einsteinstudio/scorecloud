package com.goldskyer.scorecloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.scorecloud.business.MyScoreBusiness;
import com.goldskyer.scorecloud.constant.SessionConstant;
import com.goldskyer.scorecloud.dto.ScoreCloudUserDto;
import com.goldskyer.scorecloud.dto.StudentDto;
import com.goldskyer.scorecloud.freemarker.component.SelectComponent;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.ScoreService;
import com.goldskyer.scorecloud.service.StudentService;
import com.goldskyer.scorecloud.vo.MyScoreVo;

@Controller
@RequestMapping("/scorecloud/myscore")
public class MyScoreController extends BaseScoreCloudController
{

	@Autowired
	private ScoreService scoreService;
	@Autowired
	private SelectComponent selectComponent;
	@Autowired
	private ExamService examService;
	@Autowired
	private MyScoreBusiness myScoreBusiness;
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/toList.htm")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
	{
		String studentId = StringUtils.trimToEmpty(request.getParameter("studentId"));
		if (StringUtils.isBlank(studentId))
		{
			ScoreCloudUserDto userDto = (ScoreCloudUserDto) request.getSession().getAttribute(SessionConstant.USER_DTO);
			StudentDto studentDto = studentService.queryStudentDtoByAccountId(userDto.getAccountId());
			studentId = studentDto.getId();
		}
		ModelAndView mv = new ModelAndView("/scorecloud/myscore/toList");
		MyScoreVo myScoreVo = myScoreBusiness.queryScoreResultDtosByStudentId(studentId);
		mv.addObject("myScoreVo", myScoreVo);
		mv.addObject("studentDto", studentService.queryStudentDtoById(studentId));
		return mv;
	}
}
