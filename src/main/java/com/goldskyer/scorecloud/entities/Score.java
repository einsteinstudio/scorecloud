package com.goldskyer.scorecloud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 保证exam为准就可以了
 * @author jintianfan
 *
 */
@Entity
@Table(name = "score")
public class Score
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String studentId;
	private String examId;
	private String subjectId;//科目
	private Float score; //必须使用double，因为sum计算之后为float
	private String gradeName;//年级名称 //记录当前考试的年级，因为年级是可以变动的
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getExamId()
	{
		return examId;
	}

	public void setExamId(String examId)
	{
		this.examId = examId;
	}

	public String getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(String subjectId)
	{
		this.subjectId = subjectId;
	}

	public Float getScore()
	{
		return score;
	}

	public void setScore(Float score)
	{
		this.score = score;
	}

}
