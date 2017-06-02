package com.goldskyer.scorecloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.common.util.BeanUtil;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.scorecloud.constant.InnerCodeConstant;
import com.goldskyer.scorecloud.dto.ClassDto;
import com.goldskyer.scorecloud.dto.GradeDto;
import com.goldskyer.scorecloud.dto.OptionDto;
import com.goldskyer.scorecloud.dto.OptionValueDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.entities.Grade;
import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.exception.resp.RespCodeConstant;
import com.goldskyer.scorecloud.service.ClassService;
import com.goldskyer.scorecloud.service.GradeService;
import com.goldskyer.scorecloud.service.OptionService;

@Service
public class GradeServiceImpl implements GradeService
{
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private ClassService classService;
	@Autowired
	private OptionService optionService;

	public List<GradeDto> queryAllGradeDtoWithClassDtos(String schId)
	{
		List<GradeDto> gradeDtos = queryActivaeGradeDtos(schId);
		Map<String, GradeDto> map=new HashMap<>();
		for(GradeDto gradeDto:gradeDtos)
		{
			map.put(gradeDto.getId(), gradeDto);
		}
		List<ClassDto> classDtos = classService.queryAllClassesGroupByGrade(schId);
		for(ClassDto classDto:classDtos)
		{
			map.get(classDto.getGradeId()).getClassDtos().add(classDto);
		}
		return gradeDtos;
	}

	public GradeDto queryGradeDto(String id)
	{
		Grade grade = baseDao.query(Grade.class, id);
		GradeDto gradeDto = new GradeDto();
		gradeDto.setGradeName(grade.getGradeName());
		gradeDto.setEnterYear(grade.getEnterYear());
		gradeDto.setMajorTeacherName(grade.getMajorTeacherName());
		gradeDto.setStatus(grade.getStatus());
		gradeDto.setId(grade.getId());
		gradeDto.setWeight(grade.getWeight());
		return gradeDto;
	}

	public void addGrade(GradeDto gradeDto)
	{
		Grade grade=new Grade();
		grade.setGradeName(gradeDto.getGradeName());
		grade.setEnterYear(gradeDto.getEnterYear());
		grade.setWeight(gradeDto.getWeight());
		grade.setStatus(gradeDto.getStatus());
		grade.setMajorTeacherName(gradeDto.getMajorTeacherName());
		grade.setSchId(ScoreCloudEnv.get().getSchId());
		baseDao.add(grade);
	}

	public void deleteGrade(String id)
	{
		List list = classService.queryClassIdsByGradeId(id);
		if (list.size() > 0)
		{
			throw new RespCodeException(RespCodeConstant.DELETE_GRADE_EXIST_CLASS);
		}
		baseDao.delete(Grade.class, id);
	}

	/**
	 * 年级升级
	 * @param id
	 */
	public void upgradeGrade(String id)
	{
		Grade grade = baseDao.query(Grade.class, id);
		if (grade == null)
			throw new RespCodeException(RespCodeConstant.PARAM_ILLEGAL);
		OptionDto optionDto = optionService.queryOptionDtoByInnerCode(ScoreCloudEnv.get().getSchId(),
				InnerCodeConstant.SELECT_GRADE_NAME);
		String nextGrade = StringUtils.EMPTY;
		boolean ifFind = false;
		for (OptionValueDto v : optionDto.getOptionValueDtos())
		{
			if (ifFind)
			{
				nextGrade = v.getValue();
				break;
			}
			if (StringUtils.equals(v.getValue(), grade.getGradeName()))
			{
				ifFind = true;
			}
		}
		if (StringUtils.isNotBlank(nextGrade))
		{
			grade.setGradeName(nextGrade);
			baseDao.modify(grade);
		}
		else
		{
			grade.setStatus(2);//归档
			baseDao.modify(grade);
		}
	}

	public void modifyGrade(GradeDto gradeDto)
	{
		gradeDto.setSchId(ScoreCloudEnv.get().getSchId());
		Grade oldGrade = baseDao.query(Grade.class, gradeDto.getId());
		oldGrade.setId(gradeDto.getId());
		oldGrade.setGradeName(gradeDto.getGradeName());
		oldGrade.setEnterYear(gradeDto.getEnterYear());
		oldGrade.setMajorTeacherName(gradeDto.getMajorTeacherName());
		oldGrade.setStatus(gradeDto.getStatus());
		oldGrade.setWeight(gradeDto.getWeight());
		baseDao.modify(oldGrade);
	}

	/**
	 * 查询所有活跃的年级
	 * @return
	 */
	public List<Grade> queryActivaeGrades(String schId)
	{
		return baseDao.query("select g from Grade g where g.status=1 and schId=? order by weight ", schId);
	}

	public List<GradeDto> queryActivaeGradeDtos(String schId)
	{
		List<GradeDto> gradeDtos = new ArrayList<>();
		List<Grade> grades = queryActivaeGrades(schId);
		for (Grade grade : grades)
		{
			GradeDto gradeDto = new GradeDto();
			BeanUtil.copyProperties(gradeDto, grade);
			gradeDtos.add(gradeDto);
		}
		return gradeDtos;
	}

}
