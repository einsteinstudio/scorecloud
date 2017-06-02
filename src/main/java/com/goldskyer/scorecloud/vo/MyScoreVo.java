package com.goldskyer.scorecloud.vo;

import java.util.ArrayList;
import java.util.List;

import com.goldskyer.scorecloud.dto.StudentDto;

public class MyScoreVo
{
	private StudentDto studentDto;
	private List<MyExamVo> myExamVos = new ArrayList<>();

	public StudentDto getStudentDto()
	{
		return studentDto;
	}

	public void setStudentDto(StudentDto studentDto)
	{
		this.studentDto = studentDto;
	}

	public List<MyExamVo> getMyExamVos()
	{
		return myExamVos;
	}

	public void setMyExamVos(List<MyExamVo> myExamVos)
	{
		this.myExamVos = myExamVos;
	}

}
