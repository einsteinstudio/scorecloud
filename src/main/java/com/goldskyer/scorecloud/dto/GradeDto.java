package com.goldskyer.scorecloud.dto;

import java.util.ArrayList;
import java.util.List;

public class GradeDto extends SchIdDto
{
	private String id;
	private String gradeName;
	private int enterYear;//入学年
	private int weight = 0;//越小，显示靠前
	private String majorTeacherName; //年级主任名字
	private int status;
	private List<ClassDto> classDtos = new ArrayList<>();

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getGradeName()
	{
		return gradeName;
	}

	public void setGradeName(String gradeName)
	{
		this.gradeName = gradeName;
	}

	public int getEnterYear()
	{
		return enterYear;
	}

	public void setEnterYear(int enterYear)
	{
		this.enterYear = enterYear;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public String getMajorTeacherName()
	{
		return majorTeacherName;
	}

	public void setMajorTeacherName(String majorTeacherName)
	{
		this.majorTeacherName = majorTeacherName;
	}

	public List<ClassDto> getClassDtos()
	{
		return classDtos;
	}

	public void setClassDtos(List<ClassDto> classDtos)
	{
		this.classDtos = classDtos;
	}

}
