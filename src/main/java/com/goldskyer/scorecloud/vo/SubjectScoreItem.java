package com.goldskyer.scorecloud.vo;

public class SubjectScoreItem
{
	private String subjectName;
	private Float subjectScore;
	private Integer classRank;
	private Integer gradeRank;

	public String getSubjectName()
	{
		return subjectName;
	}

	public void setSubjectName(String subjectName)
	{
		this.subjectName = subjectName;
	}

	public Float getSubjectScore()
	{
		return subjectScore;
	}

	public void setSubjectScore(Float subjectScore)
	{
		this.subjectScore = subjectScore;
	}

	public Integer getClassRank()
	{
		return classRank;
	}

	public void setClassRank(Integer classRank)
	{
		this.classRank = classRank;
	}

	public Integer getGradeRank()
	{
		return gradeRank;
	}

	public void setGradeRank(Integer gradeRank)
	{
		this.gradeRank = gradeRank;
	}

}
