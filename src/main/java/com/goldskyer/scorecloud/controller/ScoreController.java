package com.goldskyer.scorecloud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.scorecloud.dto.ExamDto;
import com.goldskyer.scorecloud.dto.RecordScoreDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.ScoreDto;
import com.goldskyer.scorecloud.dto.ScoreQueryDto;
import com.goldskyer.scorecloud.dto.ScoreResultDto;
import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.exception.resp.RespCodeConstant;
import com.goldskyer.scorecloud.form.ScoreSearchForm;
import com.goldskyer.scorecloud.freemarker.component.SelectComponent;
import com.goldskyer.scorecloud.freemarker.component.vo.OperationResultVo;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.ScoreService;

@Controller
@RequestMapping("/scorecloud/score")
public class ScoreController extends BaseScoreCloudController
{
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private SelectComponent selectComponent;
	@Autowired
	private ExamService examService;

	@RequestMapping(value = "/toSearch.htm")
	@RoleControl("SCORE_VIEW")
	public ModelAndView toSearch(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/toSearch");
		mv.addObject("examIdWrapper", selectComponent.buildExamdSelect());
		mv.addObject("classIdWrapper", selectComponent.buildCLassIdSelect());
		mv.addObject("gradeIdOptionDto", selectComponent.buildGradeIdSelect());
		return mv;
	}

	@RequestMapping(value = "/searchByGrade.htm")
	@RoleControl("SCORE_VIEW")
	public ModelAndView searchByGrade(ScoreSearchForm scoreSearchForm, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/searchResult");
		ScoreQueryDto scoreQueryDto = new ScoreQueryDto();
		scoreQueryDto.setExamId(scoreSearchForm.getExamId());
		scoreQueryDto.setGradeId(scoreSearchForm.getGradeId());
		scoreQueryDto.setSchId(ScoreCloudEnv.get().getSchId());
		ScoreResultDto scoreResultDto = scoreService.queryScoresByScoreQueryDto(scoreQueryDto);
		mv.addObject("scoreResultDto", scoreResultDto);
		return mv;
	}

	@RequestMapping(value = "/searchByExam.htm")
	@RoleControl("SCORE_VIEW")
	public ModelAndView searchByExam(ScoreSearchForm scoreSearchForm, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/searchResult");
		ScoreQueryDto scoreQueryDto = new ScoreQueryDto();
		scoreQueryDto.setExamId(scoreSearchForm.getExamId());
		scoreQueryDto.setClassId(scoreSearchForm.getClassId());
		scoreQueryDto.setSchId(ScoreCloudEnv.get().getSchId());
		ScoreResultDto scoreResultDto = scoreService.queryScoresByScoreQueryDto(scoreQueryDto);
		mv.addObject("scoreResultDto", scoreResultDto);
		return mv;
	}

	@RequestMapping(value = "/searchByStudent.htm")
	@RoleControl("SCORE_VIEW")
	public ModelAndView searchByStudent(ScoreSearchForm scoreSearchForm, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/searchResult");
		ScoreQueryDto scoreQueryDto = new ScoreQueryDto();
		scoreQueryDto.setExamId(scoreSearchForm.getExamId());
		scoreQueryDto.setNameno(scoreSearchForm.getNameno());
		scoreQueryDto.setSchId(ScoreCloudEnv.get().getSchId());
		ScoreResultDto scoreResultDto = scoreService.queryScoresByScoreQueryDto(scoreQueryDto);
		mv.addObject("scoreResultDto", scoreResultDto);
		return mv;
	}

	@RequestMapping(value = "/searchByExamForModify.htm")
	@RoleControl("SCORE_MODIFY")
	public ModelAndView searchByExamForModify(ScoreSearchForm scoreSearchForm, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/searchResultForModify");
		ScoreQueryDto scoreQueryDto = new ScoreQueryDto();
		scoreQueryDto.setExamId(scoreSearchForm.getExamId());
		scoreQueryDto.setClassId(scoreSearchForm.getClassId());
		scoreQueryDto.setSchId(ScoreCloudEnv.get().getSchId());
		ScoreResultDto scoreResultDto = scoreService.queryScoresByScoreQueryDto(scoreQueryDto);
		mv.addObject("scoreResultDto", scoreResultDto);
		//设置列
		return mv;
	}

	@RequestMapping(value = "/searchByStudentForModify.htm")
	@RoleControl("SCORE_MODIFY")
	public ModelAndView searchByStudentForModify(ScoreSearchForm scoreSearchForm, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/searchResultForModify");
		ScoreQueryDto scoreQueryDto = new ScoreQueryDto();
		scoreQueryDto.setExamId(scoreSearchForm.getExamId());
		scoreQueryDto.setNameno(scoreSearchForm.getNameno());
		scoreQueryDto.setSchId(ScoreCloudEnv.get().getSchId());
		ScoreResultDto scoreResultDto = scoreService.queryScoresByScoreQueryDto(scoreQueryDto);
		mv.addObject("scoreResultDto", scoreResultDto);
		return mv;
	}

	@RequestMapping(value = "/toRecordStep1.htm")
	@RoleControl("SCORE_RECORD")
	public ModelAndView toRecordStep1(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/toRecordStep1");
		mv.addObject("examIdWrapper", selectComponent.buildExamdSelect());
		mv.addObject("classIdWrapper", selectComponent.buildCLassIdSelect());
		return mv;
	}

	@RequestMapping(value = "/toRecordStep2.htm")
	@RoleControl("SCORE_RECORD")
	public ModelAndView toRecordStep2(HttpServletRequest request, HttpServletResponse response)
	{
		String examId = StringUtils.trimToEmpty(request.getParameter("examId"));
		String classId = StringUtils.trimToEmpty(request.getParameter("classId"));
		ModelAndView mv = new ModelAndView("/scorecloud/score/toRecordStep2");
		ExamDto examDto=examService.queryExamDtoById(examId);
		mv.addObject("subjects", examDto.getSubjectDtos());
		mv.addObject("examId", examId);
		mv.addObject("classId", classId);
		return mv;
	}

	/**
	 *展示分数录入页面
	 * @param recordScoreDto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toRecordStep3.htm")
	@RoleControl("SCORE_RECORD")
	public ModelAndView toRecordStep3(RecordScoreDto recordScoreDto, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/toRecordStep3");
		String[] subjectIds = request.getParameterValues("subjectIds[]");
		ScoreQueryDto scoreQueryDto=new ScoreQueryDto();
		scoreQueryDto.setSchId(ScoreCloudEnv.get().getSchId());
		scoreQueryDto.setExamId(recordScoreDto.getExamId());
		scoreQueryDto.setClassId(recordScoreDto.getClassId());
		for (int i = 0; i < subjectIds.length; i++)
		{
			scoreQueryDto.getChoosedSubjectIds().add(subjectIds[i]);
		}
		ScoreResultDto scoreResultDto = scoreService.queryScoresByScoreQueryDto(scoreQueryDto);
		mv.addObject("scoreResultDto", scoreResultDto);
		mv.addObject("subjectIds", subjectIds);
		mv.addObject("examId", recordScoreDto.getExamId());
		mv.addObject("classId", recordScoreDto.getClassId());
		return mv;
	}

	/**
	 * 多维数组无法通过request的方法接收，只能把多维数组转换为以为数组，才可以进行操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/recordScore.htm")
	@RoleControl("SCORE_RECORD")
	public ModelAndView recordScore(RecordScoreDto recordScoreDto, HttpServletRequest request,
			HttpServletResponse response)
	{
		//数据导入操作
		List<ScoreDto> scoreDtos = new ArrayList<>();
		String[] subjectIds=request.getParameterValues("subjectIds[]");
		String[] studentIds = request.getParameterValues("studentIds[]");
		for(int i=0;i<studentIds.length;i++)
		{
			String[] scoreCol = request.getParameterValues("inputScores_" + i + "[]");
			for (int k = 0; k < subjectIds.length; k++)
			{
				if (StringUtils.isNotBlank(scoreCol[k]) && !NumberUtils.isNumber(scoreCol[k]))
					throw new RespCodeException(RespCodeConstant.SCORE_ILLEGAL);
				ScoreDto scoreDto = new ScoreDto();
				scoreDto.setExamId(recordScoreDto.getExamId());
				scoreDto.setStudentId(studentIds[i]);
				scoreDto.setSubjectId(subjectIds[k]);
				if (StringUtils.isNotBlank(scoreCol[k]))
					scoreDto.setScore(Float.valueOf(scoreCol[k]));
				//scoreDto.setGradeName(gradeName); //占时不管这个，
				scoreDtos.add(scoreDto);
			}
		}
		scoreService.batchAddScoreDtos(ScoreCloudEnv.get().getSchId(), scoreDtos);
		//封装DTO
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("recordScore", "成绩导入", "成绩导入成功",
				"toRecordStep1.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toList.htm")
	@RoleControl("SCORE_VIEW")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/toList");
		return mv;
	}


	@RequestMapping(value = "/toModify.htm")
	@RoleControl("SCORE_MODIFY")
	public ModelAndView toModify(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/toModify");
		mv.addObject("examIdWrapper", selectComponent.buildExamdSelect());
		mv.addObject("classIdWrapper", selectComponent.buildCLassIdSelect());
		return mv;
	}

	@RequestMapping(value = "/modifyList.htm")
	@RoleControl("SCORE_MODIFY")
	public ModelAndView modifyList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/score/toModify");
		return mv;
	}



	@RequestMapping(value = "/addOrModifyScore.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("SCORE_MODIFY")
	public JsonData addOrModifyScore(ScoreDto scoreDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		String[] scoreVals = request.getParameterValues("scores[]");
		ExamDto examDto = examService.queryExamDtoById(scoreDto.getExamId());
		if (examDto.getSubjectDtos().size() != scoreVals.length)
			throw new RespCodeException(RespCodeConstant.PARAM_ILLEGAL);
		List<ScoreDto> scoreDtos = new ArrayList<>();
		for (int i = 0; i < scoreVals.length; i++)
		{
			if (StringUtils.isNotBlank(scoreVals[i]) && !NumberUtils.isNumber(scoreVals[i]))
				throw new RespCodeException(RespCodeConstant.SCORE_ILLEGAL);
			ScoreDto dto = new ScoreDto();
			dto.setExamId(scoreDto.getExamId());
			dto.setStudentId(scoreDto.getStudentId());
			dto.setSubjectId(examDto.getSubjectDtos().get(i).getId());
			if(StringUtils.isNotBlank(scoreVals[i]))
				dto.setScore(Float.valueOf(scoreVals[i]));
			scoreDtos.add(dto);
		}
		scoreService.batchAddScoreDtos(ScoreCloudEnv.get().getSchId(), scoreDtos);
		return jsonData;
	}
}
