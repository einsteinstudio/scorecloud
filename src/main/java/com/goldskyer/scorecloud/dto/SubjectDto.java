package com.goldskyer.scorecloud.dto;

public class SubjectDto
{
	private String id;
	private String subjectName; //科目名
	private float hegeScore;
	private float youliangScore;
	private float excellantScore;
	private String examId;//测试编号
	private String weight;

	public String getWeight()
	{
		return weight;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSubjectName()
	{
		return subjectName;
	}

	public void setSubjectName(String subjectName)
	{
		this.subjectName = subjectName;
	}

	public float getHegeScore()
	{
		return hegeScore;
	}

	public void setHegeScore(float hegeScore)
	{
		this.hegeScore = hegeScore;
	}

	public float getYouliangScore()
	{
		return youliangScore;
	}

	public void setYouliangScore(float youliangScore)
	{
		this.youliangScore = youliangScore;
	}

	public float getExcellantScore()
	{
		return excellantScore;
	}

	public void setExcellantScore(float excellantScore)
	{
		this.excellantScore = excellantScore;
	}

	public String getExamId()
	{
		return examId;
	}

	public void setExamId(String examId)
	{
		this.examId = examId;
	}

}
