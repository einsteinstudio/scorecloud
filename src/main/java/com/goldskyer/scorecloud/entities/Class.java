package com.goldskyer.scorecloud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "class")
public class Class implements SchoolId
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String className;
	private int weight = 0;
	private String gradeId;//对应年级
	private String majorTeacherId;//班主任ID
	private String majorTracherName;
	private String note;//班级介绍

	private String schId;

	public String getSchId()
	{
		return schId;
	}

	public void setSchId(String schId)
	{
		this.schId = schId;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public String getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(String gradeId)
	{
		this.gradeId = gradeId;
	}

	public String getMajorTeacherId()
	{
		return majorTeacherId;
	}

	public void setMajorTeacherId(String majorTeacherId)
	{
		this.majorTeacherId = majorTeacherId;
	}

	public String getMajorTracherName()
	{
		return majorTracherName;
	}

	public void setMajorTracherName(String majorTracherName)
	{
		this.majorTracherName = majorTracherName;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

}
