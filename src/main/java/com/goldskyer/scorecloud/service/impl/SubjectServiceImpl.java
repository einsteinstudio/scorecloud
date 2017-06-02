package com.goldskyer.scorecloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.util.BeanUtil;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.scorecloud.dto.SubjectDto;
import com.goldskyer.scorecloud.entities.Subject;
import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.exception.resp.RespCodeConstant;
import com.goldskyer.scorecloud.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService
{
	@Autowired
	private BaseDao baseDao;

	public void addSubject(Subject subject)
	{
		baseDao.add(subject);
	}

	@Transactional
	public List<Subject> querySubjectsByChoosedSujectIds(List<String> choosedSubjectIds)
	{
		List<Subject> subjects = new ArrayList<>();
		for (String id : choosedSubjectIds)
		{
			Subject subject = querySubjectById(id);
			if (subject != null)
				subjects.add(subject);
		}
		return subjects;
	}

	public Subject querySubjectById(String subjectId)
	{
		if (StringUtils.isBlank(subjectId))
			return null;
		return baseDao.query(Subject.class, subjectId);
	}

	public void deleteSubjectById(String id)
	{
		baseDao.delete(Subject.class, id);
	}

	public void addSubjectDto(SubjectDto subjectDto)
	{
		Subject subject = new Subject();
		BeanUtil.copyProperties(subject, subjectDto);
		baseDao.add(subject);
	}

	private void checkSubjectNameRepeat(String subjectName, String examId, String subjectId)
	{
		List<Subject> list = baseDao.query("from Subject where examId=? and subjectName=?", examId, subjectName);
		if(list.size()>0)
		{
			if (StringUtils.isBlank(subjectId) || (!StringUtils.equals(list.get(0).getId(), subjectId)))
					throw new RespCodeException(RespCodeConstant.SUBJECT_EXIST);
		}
	}

	public void modifySubjectDto(SubjectDto subjectDto)
	{
		Subject oldSubject = baseDao.query(Subject.class, subjectDto.getId());
		BeanUtil.copyProperties(oldSubject, subjectDto);
		baseDao.modify(oldSubject);
	}

	@Transactional
	public void addOrModifySUbjectDto(SubjectDto subjectDto)
	{
		checkSubjectNameRepeat(subjectDto.getSubjectName(), subjectDto.getExamId(), subjectDto.getId());
		if (StringUtils.isBlank(subjectDto.getId()))
		{
			addSubjectDto(subjectDto);
		}
		else
		{
			modifySubjectDto(subjectDto);
		}
	}

	public List<SubjectDto> querySubjectsByExamId(String examId)
	{
		List<SubjectDto> subjectDtos=new ArrayList<>();
		List<Subject> subjects = baseDao.query("from Subject where examId=? order by weight", examId);
		for(Subject subject:subjects)
		{
			SubjectDto subjectDto = new SubjectDto();
			BeanUtil.copyProperties(subjectDto, subject);
			subjectDtos.add(subjectDto);
		}
		return subjectDtos;
	}

	public List<String> querySubjectIdsByExamId(String examId)
	{
		List<String> list = new ArrayList<>();
		List<SubjectDto> subjects = querySubjectsByExamId(examId);
		for (SubjectDto subject : subjects)
		{
			list.add(subject.getId());
		}
		return list;
	}
}
