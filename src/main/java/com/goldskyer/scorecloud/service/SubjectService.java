package com.goldskyer.scorecloud.service;

import java.util.List;

import com.goldskyer.scorecloud.dto.SubjectDto;
import com.goldskyer.scorecloud.entities.Subject;

public interface SubjectService
{
	public void addSubject(Subject subject);

	public List<Subject> querySubjectsByChoosedSujectIds(List<String> choosedSubjectIds);

	public Subject querySubjectById(String subjectId);

	public void addOrModifySUbjectDto(SubjectDto subjectDto);

	public void modifySubjectDto(SubjectDto subjectDto);

	public void addSubjectDto(SubjectDto subjectDto);

	public void deleteSubjectById(String id);

	public List<SubjectDto> querySubjectsByExamId(String examId);

	public List<String> querySubjectIdsByExamId(String examId);
}
