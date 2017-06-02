package com.goldskyer.scorecloud.service;

import java.util.List;

import com.goldskyer.scorecloud.dto.StudentDto;

public interface StudentService
{
	public List<StudentDto> queryActiveStudentDtosByGradeId(String gradeId);

	public List<StudentDto> queryAllActiveStudents(String schId);

	public StudentDto queryStudentDtoById(String id);

	public StudentDto queryStudentDtoByAccountId(String id);

	public void addStudentDto(StudentDto studentDto);

	public void modifyStudentDto(StudentDto studentDto);

	public void deleteStudentDtoById(String id);
}
