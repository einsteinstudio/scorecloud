package com.goldskyer.scorecloud.dto;

import java.util.ArrayList;
import java.util.List;

public class ExamDto extends SchIdDto
{

	private String id;
	private String examName;
	private int year;//考试年份
	private String createDate; //yyyy-MM-dd
	private String updateDate; //yyyy-MM-dd
	private List<SubjectDto> subjectDtos = new ArrayList<>();
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getExamName()
	{
		return examName;
	}

	public void setExamName(String examName)
	{
		this.examName = examName;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}

	public List<SubjectDto> getSubjectDtos()
	{
		return subjectDtos;
	}

	public void setSubjectDtos(List<SubjectDto> subjectDtos)
	{
		this.subjectDtos = subjectDtos;
	}

}
