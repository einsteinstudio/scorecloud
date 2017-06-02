package com.goldskyer.scorecloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.common.util.BeanUtil;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.scorecloud.dto.ClassDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.entities.Class;
import com.goldskyer.scorecloud.entities.Grade;
import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.exception.resp.RespCodeConstant;
import com.goldskyer.scorecloud.service.ClassService;

@Service
@SuppressWarnings("unchecked")
public class ClassServiceImpl implements ClassService
{
	@Autowired
	private BaseDao baseDao;


	
	/**
	 * 根据年级ID查找对应班级
	 * @param gradeId
	 * @return
	 */
	public List<Class> queryClassesByGradeId(String gradeId)
	{
		return baseDao.query("select t from Class t where t.gradeId=? order by weight", gradeId);
	}

	public List<String> queryClassIdsByGradeId(String gradeId)
	{
		return baseDao.query("select t.id from Class t where t.gradeId=? order by weight", gradeId);
	}

	/**
	 * 用于列表展示
	 */
	public List<ClassDto> queryAllClassesGroupByGrade(String schId)
	{
		List<ClassDto> classDtos = new ArrayList<>();
		List<Object[]> classList = baseDao
				.query("select t,g from Class t,Grade g where t.gradeId=g.id and g.schId=? order by g.weight,t.weight",
						schId);
		for (Object[] oo : classList)
		{
			ClassDto classDto = new ClassDto();
			BeanUtil.copyProperties(classDto, (Class) oo[0]);
			classDto.setGradeName(((Grade) oo[1]).getGradeName());
			classDtos.add(classDto);
		}
		return classDtos;
	}


	public void addCladdDto(ClassDto classDto)
	{
		Class clazz = new Class();
		BeanUtil.copyProperties(clazz, classDto);
		clazz.setSchId(ScoreCloudEnv.get().getSchId());
		baseDao.add(clazz);
	}

	public void modifyClassDto(ClassDto classDto)
	{
		classDto.setSchId(ScoreCloudEnv.get().getSchId());
		Class oldClazz = baseDao.query(Class.class, classDto.getId());
		BeanUtil.copyProperties(oldClazz, classDto);
		baseDao.modify(oldClazz);
	}

	public void deleteClassDto(String id)
	{
		long cnt=baseDao.count("select count(1) from Student where classId=?",id);
		if(cnt>0)
		{
			throw new RespCodeException(RespCodeConstant.DELETE_CLASS_EXIST_STUDENT);
		}
		baseDao.delete(Class.class, id);
	}

	public ClassDto queryClassDtoById(String id)
	{
		ClassDto classDto=new ClassDto();
		Class clazz=baseDao.query(Class.class,id);
		BeanUtil.copyProperties(classDto, clazz);
		return classDto;
	}

	public ClassDto queryClassDtoByNameInfo(String schId, String gradeName, String className)
	{
		ClassDto classDto = new ClassDto();
		Class clazz = (Class) baseDao.queryUnique(
				"select c from Class c,Grade g where c.gradeId=g.id and g.gradeName=? and g.status=1 and c.className=? and c.schId=?",
				gradeName,
				className, schId);
		BeanUtil.copyProperties(classDto, clazz);
		return classDto;
	}

	public Integer queryStudentCntByClassId(String classId)
	{
		return new Long(baseDao.count("select count(1) from Student where classId=?", classId)).intValue();
	}
}
