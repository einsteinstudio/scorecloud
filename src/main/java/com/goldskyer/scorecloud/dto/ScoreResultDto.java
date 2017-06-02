package com.goldskyer.scorecloud.dto;

import java.util.ArrayList;
import java.util.List;

import com.goldskyer.scorecloud.entities.Subject;

/**
 * 学生成绩集合
 * @author jintianfan
 *
 */
public class ScoreResultDto
{
	private List<StudentScoreDto> studentScoreDtos = new ArrayList<>(); //学生的各个列的成绩
	private List<Subject> subjects;
	private String examId;
	private GradeDto gradeDto; //考试年级信息
	private ClassDto classDto;//考试班级信息
	private ExamDto examDto;//考试明细
	private String title = "成绩详情";

	public List<StudentScoreDto> getStudentScoreDtos()
	{
		return studentScoreDtos;
	}

	public void setStudentScoreDtos(List<StudentScoreDto> studentScoreDtos)
	{
		this.studentScoreDtos = studentScoreDtos;
	}

	public List<Subject> getSubjects()
	{
		return subjects;
	}

	public void setSubjects(List<Subject> subjects)
	{
		this.subjects = subjects;
	}

	public String getExamId()
	{
		return examId;
	}

	public void setExamId(String examId)
	{
		this.examId = examId;
	}

	public GradeDto getGradeDto()
	{
		return gradeDto;
	}

	public void setGradeDto(GradeDto gradeDto)
	{
		this.gradeDto = gradeDto;
	}

	public ClassDto getClassDto()
	{
		return classDto;
	}

	public void setClassDto(ClassDto classDto)
	{
		this.classDto = classDto;
	}

	public ExamDto getExamDto()
	{
		return examDto;
	}

	public void setExamDto(ExamDto examDto)
	{
		this.examDto = examDto;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

}
