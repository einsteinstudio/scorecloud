package com.goldskyer.scorecloud.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.scorecloud.dto.ExamDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.ScoreQueryDto;
import com.goldskyer.scorecloud.dto.ScoreResultDto;
import com.goldskyer.scorecloud.dto.StudentDto;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.ScoreService;
import com.goldskyer.scorecloud.service.StudentService;
import com.goldskyer.scorecloud.vo.MyExamVo;
import com.goldskyer.scorecloud.vo.MyScoreVo;
import com.goldskyer.scorecloud.vo.SubjectScoreItem;

@Service
public class MyScoreBusinessImpl implements MyScoreBusiness
{
	@Autowired
	private StudentService studentService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ExamService examService;
	 
	public MyScoreVo queryScoreResultDtosByStudentId(String studentId)
	{
		MyScoreVo myScoreVo = new MyScoreVo();
		StudentDto studentDto=studentService.queryStudentDtoById(studentId);
		myScoreVo.setStudentDto(studentDto);
		List<ScoreResultDto> results = new ArrayList<>();
		List<ExamDto> examDtos = examService.queyJoinedExamDtosByStudentId(studentDto.getSchId(), studentId);
		for (ExamDto examDto : examDtos)
		{
			MyExamVo myExamVo=new MyExamVo();
			myExamVo.setExamName(examDto.getExamName());
			myExamVo.setYear(examDto.getYear());
			ScoreQueryDto scoreQueryDto = new ScoreQueryDto();
			scoreQueryDto.setStudentNo(studentDto.getStudentNo());
			scoreQueryDto.setClassId(studentDto.getClassId());
			scoreQueryDto.setExamId(examDto.getId());
			scoreQueryDto.setSchId(ScoreCloudEnv.get().getSchId());
			ScoreResultDto scoreResultDto = scoreService.queryScoresByScoreQueryDto(scoreQueryDto);
			//遍历科目
			for (int i = 0; i < scoreResultDto.getSubjects().size(); i++)
			{
				SubjectScoreItem item = new SubjectScoreItem();
				item.setSubjectName(scoreResultDto.getSubjects().get(i).getSubjectName());
				item.setClassRank(scoreResultDto.getStudentScoreDtos().get(0).getSubjectClassRanks().get(i));
				item.setGradeRank(scoreResultDto.getStudentScoreDtos().get(0).getSubjectGradeRanks().get(i));
				item.setSubjectScore(scoreResultDto.getStudentScoreDtos().get(0).getSubjectScores().get(i));
				myExamVo.getSubjectScoreItems().add(item);
			}
			//遍历总分
			SubjectScoreItem item = new SubjectScoreItem();
			item.setSubjectName("总分");
			item.setClassRank(scoreResultDto.getStudentScoreDtos().get(0).getClassRank());
			item.setGradeRank(scoreResultDto.getStudentScoreDtos().get(0).getGradeRank());
			item.setSubjectScore(scoreResultDto.getStudentScoreDtos().get(0).getTotalScore());
			myExamVo.getSubjectScoreItems().add(item);
			myScoreVo.getMyExamVos().add(myExamVo);
		}
		return myScoreVo;
	}
}
