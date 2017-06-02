package com.goldskyer.scorecloud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 年级
 * @author jintianfan
 *
 */
@Entity
@Table(name = "grade")
public class Grade implements SchoolId
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String gradeName;
	private int enterYear;//入学年
	private int weight = 0;//越小，显示靠前
	private String majorTeacherName; //年级主任名字
	private String majorTeacherId;//年级主任ID

	private int status;//1有效，2：归档

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

	public String getMajorTeacherName()
	{
		return majorTeacherName;
	}

	public void setMajorTeacherName(String majorTeacherName)
	{
		this.majorTeacherName = majorTeacherName;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public String getMajorTeacherId()
	{
		return majorTeacherId;
	}

	public void setMajorTeacherId(String majorTeacherId)
	{
		this.majorTeacherId = majorTeacherId;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}


}
