package com.goldskyer.scorecloud.dto;

/**
 * 目标分分析中的行
 * @author jintianfan
 *
 */
public class TargetScoreItem
{
	private String title; //行首名称
	private Integer subjectCnt;
	private ClassDto classDto;
	private Integer studentCnt;
	private Integer joinExamCnt = 0;
	private Float totalScore = 0f; //班级总分
	private Float avgScore = 0f; //只计算参加考试的。如果有人只参加了一门考试，则也算如参加考试总人数
	private Integer hegeCnt = 0;
	private Float hegeRate = 0f;//1-100
	private Integer youliangCnt = 0;
	private Float youliangRate = 0f;
	private Float avgValue = 0f;

	public ClassDto getClassDto()
	{
		return classDto;
	}

	public void setClassDto(ClassDto classDto)
	{
		this.classDto = classDto;
	}


	public Integer getStudentCnt()
	{
		return studentCnt;
	}

	public void setStudentCnt(Integer studentCnt)
	{
		this.studentCnt = studentCnt;
	}

	public Integer getJoinExamCnt()
	{
		return joinExamCnt;
	}

	public void setJoinExamCnt(Integer joinExamCnt)
	{
		this.joinExamCnt = joinExamCnt;
	}

	public Float getTotalScore()
	{
		return totalScore;
	}

	public void setTotalScore(Float totalScore)
	{
		this.totalScore = totalScore;
	}

	public Float getAvgScore()
	{
		return avgScore;
	}

	public void setAvgScore(Float avgScore)
	{
		this.avgScore = avgScore;
	}

	public Integer getHegeCnt()
	{
		return hegeCnt;
	}

	public void setHegeCnt(Integer hegeCnt)
	{
		this.hegeCnt = hegeCnt;
	}

	public Float getHegeRate()
	{
		return hegeRate;
	}

	public void setHegeRate(Float hegeRate)
	{
		this.hegeRate = hegeRate;
	}

	public Integer getYouliangCnt()
	{
		return youliangCnt;
	}

	public void setYouliangCnt(Integer youliangCnt)
	{
		this.youliangCnt = youliangCnt;
	}

	public Float getYouliangRate()
	{
		return youliangRate;
	}

	public void setYouliangRate(Float youliangRate)
	{
		this.youliangRate = youliangRate;
	}

	public Float getAvgValue()
	{
		return avgValue;
	}

	public void setAvgValue(Float avgValue)
	{
		this.avgValue = avgValue;
	}

	public Integer getSubjectCnt()
	{
		return subjectCnt;
	}

	public void setSubjectCnt(Integer subjectCnt)
	{
		this.subjectCnt = subjectCnt;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Override
	public String toString()
	{
		return "TargetScoreItem [classDto=" + classDto + ", studentCnt=" + studentCnt + ", joinExamCnt=" + joinExamCnt
				+ ", totalScore=" + totalScore + ", avgScore=" + avgScore + ", hegeCnt=" + hegeCnt + ", hegeRate="
				+ hegeRate + ", youliangCnt=" + youliangCnt + ", youliangRate=" + youliangRate + ", avgValue="
				+ avgValue + "]";
	}

}
