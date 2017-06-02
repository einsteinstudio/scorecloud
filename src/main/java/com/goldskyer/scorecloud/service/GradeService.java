package com.goldskyer.scorecloud.service;

import java.util.List;

import com.goldskyer.scorecloud.dto.GradeDto;
import com.goldskyer.scorecloud.entities.Grade;

public interface GradeService
{
	public List<GradeDto> queryAllGradeDtoWithClassDtos(String schId);

	public GradeDto queryGradeDto(String id);

	public void addGrade(GradeDto gradeDto);
	/**
	 * 查询所有活跃的年级
	 * @return
	 */
	public List<Grade> queryActivaeGrades(String schId);

	public void modifyGrade(GradeDto gradeDto);

	public void deleteGrade(String id);

	public List<GradeDto> queryActivaeGradeDtos(String schId);

	public void upgradeGrade(String id);
}
