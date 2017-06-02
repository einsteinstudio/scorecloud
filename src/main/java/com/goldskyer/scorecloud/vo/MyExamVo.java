package com.goldskyer.scorecloud.vo;

import java.util.ArrayList;
import java.util.List;

public class MyExamVo
{
	private String examName;
	private Integer year;
	private List<SubjectScoreItem> subjectScoreItems = new ArrayList<>();

	public String getExamName()
	{
		return examName;
	}

	public void setExamName(String examName)
	{
		this.examName = examName;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public List<SubjectScoreItem> getSubjectScoreItems()
	{
		return subjectScoreItems;
	}

	public void setSubjectScoreItems(List<SubjectScoreItem> subjectScoreItems)
	{
		this.subjectScoreItems = subjectScoreItems;
	}

}
