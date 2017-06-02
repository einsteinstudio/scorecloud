package com.goldskyer.scorecloud.dto;

public class ScoreDto
{
	private String id;
	private String studentId;
	private String examId;
	private String subjectId;//科目
	private Float score;
	private String gradeName;//年级名称 //记录当前考试的年级，因为年级是可以变动的

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getExamId()
	{
		return examId;
	}

	public void setExamId(String examId)
	{
		this.examId = examId;
	}

	public String getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(String subjectId)
	{
		this.subjectId = subjectId;
	}

	public Float getScore()
	{
		return score;
	}

	public void setScore(Float score)
	{
		this.score = score;
	}

	public String getGradeName()
	{
		return gradeName;
	}

	public void setGradeName(String gradeName)
	{
		this.gradeName = gradeName;
	}

}
