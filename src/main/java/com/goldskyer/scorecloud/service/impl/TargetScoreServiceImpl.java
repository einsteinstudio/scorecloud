package com.goldskyer.scorecloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.scorecloud.constant.ScoreConstant;
import com.goldskyer.scorecloud.dto.ClassDto;
import com.goldskyer.scorecloud.dto.GradeDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.SubjectDto;
import com.goldskyer.scorecloud.dto.TargetScoreItem;
import com.goldskyer.scorecloud.entities.Subject;
import com.goldskyer.scorecloud.helper.AvgValueHelper;
import com.goldskyer.scorecloud.service.ClassService;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.GradeService;
import com.goldskyer.scorecloud.service.SubjectService;
import com.goldskyer.scorecloud.service.TargetScoreService;
import com.goldskyer.scorecloud.vo.TargetScoreVo;

@Service
public class TargetScoreServiceImpl implements TargetScoreService
{
	@Autowired
	private ExamService examService;
	@Autowired
	private ClassService classService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private GradeService gradeService;

	/**
	 * 总分目标值计算
	 * @param examId
	 * @param classDto
	 * @return
	 */
	public TargetScoreItem calculateTargetScoreItem(String schId, String title, String examId, ClassDto classDto)
	{
		List<TargetScoreItem> sumList = new ArrayList<>();
		List<SubjectDto> subjects = subjectService.querySubjectsByExamId(examId);
		TargetScoreItem item = new TargetScoreItem();
		item.setTitle(title);
		item.setClassDto(classDto);
		item.setSubjectCnt(subjects.size());
		item.setStudentCnt(classService.queryStudentCntByClassId(classDto.getId()));
		if (subjects.size() == 0)
			return item;
		item.setJoinExamCnt(examService.queryJoinedExamCntByClassId(schId, classDto.getId(), examId));
		if (item.getJoinExamCnt() == 0)
		{
			return item;
		}
		item.setTotalScore(examService.queryJoinedExamTotalScoreCntByClassId(schId, classDto.getId(), examId));
		item.setAvgScore(item.getTotalScore() / item.getJoinExamCnt() / subjects.size());
		item.setHegeCnt(examService.queryJoinedAvgScoreReachedCntByClassId(schId, classDto.getId(), examId,
				Float.valueOf(ScoreConstant.HEGE_SCORE * subjects.size())));
		item.setHegeRate(item.getHegeCnt() * 100f / item.getJoinExamCnt());
		item.setYouliangCnt(examService.queryJoinedAvgScoreReachedCntByClassId(schId, classDto.getId(), examId,
				Float.valueOf(ScoreConstant.YOULIANG_SCORE * subjects.size())));
		item.setYouliangRate(item.getYouliangCnt() * 100f / item.getJoinExamCnt());
		//平均值计算
		item.setAvgValue(
				AvgValueHelper.calculateAvgValue(item.getAvgScore(), item.getHegeRate(), item.getYouliangRate()));
		return item;
	}

	public TargetScoreVo getTargetScoreVo(String schId, String examId)
	{
		List<TargetScoreItem> sumList = new ArrayList<>();
		TargetScoreVo targetScoreVo = new TargetScoreVo();
		List<GradeDto> gradeDtos = gradeService.queryAllGradeDtoWithClassDtos(ScoreCloudEnv.get().getSchId());
		for (GradeDto gradeDto : gradeDtos)
		{
			List<TargetScoreItem> tmp = new ArrayList<>();
			for (ClassDto classDto : gradeDto.getClassDtos())
			{
				tmp.add(calculateTargetScoreItem(schId, gradeDto.getGradeName() + classDto.getClassName(), examId,
						classDto));
			}
			if (tmp.size() > 0)//如果一个年级没有参加考试，则不会计入目标分管理
			{
				targetScoreVo.getItems().addAll(tmp);
				TargetScoreItem item = mergeTargetScoreItem("小计", tmp);
				targetScoreVo.getItems().add(item);
				sumList.add(item);
			}
		}
		targetScoreVo.getItems().add(mergeTargetScoreItem("总计", sumList));
		return targetScoreVo;
	}

	public TargetScoreItem calculateTargetSubjectScoreItem(String schId, String title, String subjectId,
			ClassDto classDto)
	{
		Subject subject = subjectService.querySubjectById(subjectId);
		TargetScoreItem item = new TargetScoreItem();
		item.setTitle(title);
		item.setClassDto(classDto);
		item.setSubjectCnt(1);
		item.setStudentCnt(classService.queryStudentCntByClassId(classDto.getId()));
		item.setJoinExamCnt(examService.queryJoinedSubjectCntByClassId(schId, classDto.getId(), subjectId));
		if (item.getJoinExamCnt() == 0)
		{
			return item;
		}
		item.setTotalScore(examService.queryJoinedSubjectTotalScoreCntByClassId(schId, classDto.getId(), subjectId));
		item.setAvgScore(item.getTotalScore() / item.getJoinExamCnt());
		item.setHegeCnt(examService.queryJoinedSubjectAvgScoreReachedCntByClassId(schId, classDto.getId(), subjectId,
				Float.valueOf(subject.getHegeScore())));
		item.setHegeRate(item.getHegeCnt() * 100f / item.getJoinExamCnt());
		item.setYouliangCnt(examService.queryJoinedSubjectAvgScoreReachedCntByClassId(schId, classDto.getId(),
				subjectId,
				Float.valueOf(subject.getYouliangScore())));
		item.setYouliangRate(item.getYouliangCnt() * 100f / item.getJoinExamCnt());
		//平均值计算
		item.setAvgValue(
				AvgValueHelper.calculateAvgValue(item.getAvgScore(), item.getHegeRate(), item.getYouliangRate()));
		return item;
	}

	public TargetScoreVo getTargetSubjectScoreVo(String schId, String subjectId)
	{
		List<TargetScoreItem> sumList = new ArrayList<>();
		TargetScoreVo targetScoreVo = new TargetScoreVo();
		List<GradeDto> gradeDtos = gradeService.queryAllGradeDtoWithClassDtos(ScoreCloudEnv.get().getSchId());
		for (GradeDto gradeDto : gradeDtos)
		{
			List<TargetScoreItem> tmp = new ArrayList<>();
			for (ClassDto classDto : gradeDto.getClassDtos())
			{
				tmp.add(calculateTargetSubjectScoreItem(schId, gradeDto.getGradeName() + classDto.getClassName(),
						subjectId,
						classDto));
			}
			targetScoreVo.getItems().addAll(tmp);
			TargetScoreItem item=mergeTargetScoreItem("小计", tmp);
			targetScoreVo.getItems().add(item);
			sumList.add(item);
		}
		targetScoreVo.getItems().add(mergeTargetScoreItem("总计", sumList));
		return targetScoreVo;
	}

	/**
	 * 合并统计量，获取汇总信息
	 * @param title
	 * @param items
	 * @return
	 */
	private TargetScoreItem mergeTargetScoreItem(String title, List<TargetScoreItem> items)
	{
		TargetScoreItem result = new TargetScoreItem();
		result.setStudentCnt(0);//统计量初始学生数为0
		result.setTitle(title);
		for (TargetScoreItem item : items)
		{
			result.setSubjectCnt(item.getSubjectCnt());
			result.setStudentCnt(result.getStudentCnt() + item.getStudentCnt());
			result.setJoinExamCnt(result.getJoinExamCnt() + item.getJoinExamCnt());
			result.setTotalScore(result.getTotalScore() + item.getTotalScore());
			result.setHegeCnt(result.getHegeCnt() + item.getHegeCnt());
			result.setYouliangCnt(result.getYouliangCnt() + item.getYouliangCnt());
		}
		if (result.getJoinExamCnt() == 0)
		{
			return result;
		}
		result.setAvgScore(result.getTotalScore() / result.getJoinExamCnt() / result.getSubjectCnt());
		result.setHegeRate(result.getHegeCnt() * 100f / result.getJoinExamCnt());
		result.setYouliangRate(result.getYouliangCnt() * 100f / result.getJoinExamCnt());
		result.setAvgValue(
				AvgValueHelper.calculateAvgValue(result.getAvgScore(), result.getHegeRate(), result.getYouliangRate()));
		return result;
	}
}
