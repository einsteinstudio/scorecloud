package com.goldskyer.scorecloud.dto;

import java.util.ArrayList;
import java.util.List;

public class ScoreQueryDto
{
	private String examId;//测试名称,必须传递，分组的时候按照
	private String studentNo;//学号  
	private String studentName;//学生姓名 
	private String classId;//所在班级 
	private String gradeId;//所在年级
	private String nameno;
	private String schId;

	private String orderType;//STUDENT_NO,STUDENT_NAME,BY_SCORE,BY_AVG

	private List<String> choosedSubjectIds = new ArrayList<>();//选择的科目

	public String getExamId()
	{
		return examId;
	}

	public void setExamId(String examId)
	{
		this.examId = examId;
	}

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

	public String getClassId()
	{
		return classId;
	}

	public void setClassId(String classId)
	{
		this.classId = classId;
	}

	public List<String> getChoosedSubjectIds()
	{
		return choosedSubjectIds;
	}

	public void setChoosedSubjectIds(List<String> choosedSubjectIds)
	{
		this.choosedSubjectIds = choosedSubjectIds;
	}

	public String getOrderType()
	{
		return orderType;
	}

	public void setOrderType(String orderType)
	{
		this.orderType = orderType;
	}

	public String getNameno()
	{
		return nameno;
	}

	public void setNameno(String nameno)
	{
		this.nameno = nameno;
	}

	public String getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(String gradeId)
	{
		this.gradeId = gradeId;
	}

	public String getSchId()
	{
		return schId;
	}

	public void setSchId(String schId)
	{
		this.schId = schId;
	}

}
