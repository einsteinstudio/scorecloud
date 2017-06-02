package com.goldskyer.scorecloud.form;

public class ScoreSearchForm
{
	private String classId;
	private String examId;
	private String studentName; //模糊
	private String stduentNo; //模糊
	private String gradeId;

	private String nameno;
	public String getClassId()
	{
		return classId;
	}

	public void setClassId(String classId)
	{
		this.classId = classId;
	}

	public String getExamId()
	{
		return examId;
	}

	public void setExamId(String examId)
	{
		this.examId = examId;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public String getStduentNo()
	{
		return stduentNo;
	}

	public void setStduentNo(String stduentNo)
	{
		this.stduentNo = stduentNo;
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

}
