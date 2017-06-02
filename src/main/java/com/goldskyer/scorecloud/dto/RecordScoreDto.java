package com.goldskyer.scorecloud.dto;

/**
 * 分数录入
 * @author jintianfan
 *
 */
public class RecordScoreDto
{
	private String studentId;
	private String examId;
	private String classId;
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


	public String getClassId()
	{
		return classId;
	}

	public void setClassId(String classId)
	{
		this.classId = classId;
	}



}
