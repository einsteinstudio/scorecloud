package com.goldskyer.scorecloud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "subject")
public class Subject
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String subjectName; //科目名
	private float hegeScore;
	private float youliangScore;
	private float excellantScore;
	private String examId;//测试编号
	private int weight;

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

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

}
