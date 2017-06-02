package com.goldskyer.scorecloud.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生成绩一行
 * @author jintianfan
 *
 */
public class StudentScoreDto
{
	private String studentId;
	private String studentNo;
	private String studentName;
	private List<Float> subjectScores = new ArrayList<>();
	private Float totalScore; //总分
	private Integer classRank;
	private Integer gradeRank;
	private Float avgScore;//平均分
	private Float avgValue;//平均值

	/**
	 * 科目排名
	 */
	private List<Integer> subjectClassRanks = new ArrayList<>();

	private List<Integer> subjectGradeRanks = new ArrayList<>();

	public String getStudentNo()
	{
		return studentNo;
	}

	public void setStudentNo(String studentNo)
	{
		this.studentNo = studentNo;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public List<Float> getSubjectScores()
	{
		return subjectScores;
	}

	public void setSubjectScores(List<Float> subjectScores)
	{
		this.subjectScores = subjectScores;
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

	public Float getAvgValue()
	{
		return avgValue;
	}

	public void setAvgValue(Float avgValue)
	{
		this.avgValue = avgValue;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
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

	public List<Integer> getSubjectClassRanks()
	{
		return subjectClassRanks;
	}

	public void setSubjectClassRanks(List<Integer> subjectClassRanks)
	{
		this.subjectClassRanks = subjectClassRanks;
	}

	public List<Integer> getSubjectGradeRanks()
	{
		return subjectGradeRanks;
	}

	public void setSubjectGradeRanks(List<Integer> subjectGradeRanks)
	{
		this.subjectGradeRanks = subjectGradeRanks;
	}


}
