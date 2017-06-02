package com.goldskyer.scorecloud.service;

import java.util.List;

import com.goldskyer.scorecloud.dto.ClassDto;
import com.goldskyer.scorecloud.entities.Class;

public interface ClassService
{
	public List<String> queryClassIdsByGradeId(String gradeId);
	/**
	 * 根据年级ID查找对应班级
	 * @param gradeId
	 * @return
	 */
	public List<Class> queryClassesByGradeId(String gradeId);

	public List<ClassDto> queryAllClassesGroupByGrade(String schId);

	public void addCladdDto(ClassDto classDto);

	public void deleteClassDto(String id);

	public void modifyClassDto(ClassDto classDto);

	public ClassDto queryClassDtoById(String id);

	public Integer queryStudentCntByClassId(String classId);

	public ClassDto queryClassDtoByNameInfo(String schId, String gradeName, String className);

}
