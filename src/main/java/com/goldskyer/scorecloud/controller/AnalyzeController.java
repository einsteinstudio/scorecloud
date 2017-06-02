package com.goldskyer.scorecloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.scorecloud.business.MyScoreBusiness;
import com.goldskyer.scorecloud.dto.ExamDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.entities.Subject;
import com.goldskyer.scorecloud.form.TargetScoreForm;
import com.goldskyer.scorecloud.freemarker.component.SelectComponent;
import com.goldskyer.scorecloud.freemarker.component.vo.OptGroupVoWrapper;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.ScoreService;
import com.goldskyer.scorecloud.service.SubjectService;
import com.goldskyer.scorecloud.service.TargetScoreService;
import com.goldskyer.scorecloud.vo.TargetScoreVo;

/**
 * 成绩分析
 * @author jintianfan
 *
 */
@Controller
@RequestMapping("/scorecloud/analyze")
public class AnalyzeController extends BaseScoreCloudController
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
	private TargetScoreService targetScoreService;
	@Autowired
	private SubjectService subjectService;

	/**
	 * 目标分管理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/targetScore.htm")
	@RoleControl("ANALYZE_VIEW")
	public ModelAndView targetScore(TargetScoreForm targetScoreForm, HttpServletRequest request,
			HttpServletResponse response)
	{
		OptGroupVoWrapper wrapper = selectComponent.buildExamdSelect();
		if (org.apache.commons.lang.StringUtils.isBlank(targetScoreForm.getExamId()))
		{
			targetScoreForm.setExamId(wrapper.getFirstId());
			targetScoreForm.setSubjectId("总分");
		}
		ModelAndView mv = new ModelAndView("/scorecloud/analyze/targetScore");
		mv.addObject("examIdWrapper", wrapper);
		if (targetScoreForm.getSubjectId().equals("总分"))
		{
			TargetScoreVo targetScoreVo = targetScoreService.getTargetScoreVo(ScoreCloudEnv.get().getSchId(),
					targetScoreForm.getExamId());
			mv.addObject("targetScoreVo", targetScoreVo);
			mv.addObject("colTeacher", "班主任");
			mv.addObject("myTitle","下村小学成绩系统-目标分-总分");
		}else{
			TargetScoreVo targetScoreVo = targetScoreService.getTargetSubjectScoreVo(ScoreCloudEnv.get().getSchId(),
					targetScoreForm.getSubjectId());
			
			mv.addObject("targetScoreVo", targetScoreVo);
			mv.addObject("colTeacher", "任课老师");
			Subject subject=subjectService.querySubjectById(targetScoreForm.getSubjectId());
			mv.addObject("myTitle","下村小学成绩系统-目标分-"+subject.getSubjectName());
		}
		//回填数据
		ExamDto examDto = examService.queryExamDtoById(targetScoreForm.getExamId());//获取科目;
		mv.addObject("subjects", examDto.getSubjectDtos());
		mv.addObject("examId", targetScoreForm.getExamId());
		mv.addObject("subjectId", targetScoreForm.getSubjectId());
		return mv;
	}
}
