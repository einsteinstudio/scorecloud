package com.goldskyer.scorecloud.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.util.BeanUtil;
import com.goldskyer.common.utils.DateUtil;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.scorecloud.dto.ExamDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.SubjectDto;
import com.goldskyer.scorecloud.entities.Exam;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.SubjectService;

@Service
@SuppressWarnings("unchecked")
public class ExamServiceImpl implements ExamService
{
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private SubjectService subjectService;
	
	public ExamDto queryExamDtoById(String id)
	{
		ExamDto examDto = new ExamDto();
		Exam e = baseDao.query(Exam.class, id);
		examDto.setId(e.getId());
		examDto.setCreateDate(DateUtil.date2String(e.getCreateDate(), "yyyy-MM-dd"));
		examDto.setUpdateDate(DateUtil.date2String(e.getUpdateDate(), "yyyy-MM-dd"));
		examDto.setYear(e.getYear());
		examDto.setExamName(e.getExamName());
		//查询科目信息
		List<SubjectDto> subjectDtos = subjectService.querySubjectsByExamId(id);
		examDto.setSubjectDtos(subjectDtos);
		return examDto;
	}

	/**
	 * 查询按年份分组的考试列表
	 * @return
	 */
	public Map<String, List<ExamDto>> queryGroupedExamDtosByYear()
	{
		Map<String, List<ExamDto>> map = new LinkedHashMap<>();
		List<Exam> exams = baseDao.query("from Exam order by year desc, createDate desc ");
		for (Exam exam : exams)
		{
			ExamDto examDto = new ExamDto();
			BeanUtil.copyProperties(examDto, exam);
			if (!map.containsKey(String.valueOf(examDto.getYear())))
			{
				map.put(String.valueOf(examDto.getYear()), new ArrayList<ExamDto>());
			}
			map.get(String.valueOf(examDto.getYear())).add(examDto);
		}
		return map;
	}

	public void addExamDto(ExamDto examDto)
	{
		Exam exam = new Exam();
		exam.setCreateDate(new Date());
		exam.setUpdateDate(new Date());
		exam.setYear(examDto.getYear());
		exam.setExamName(examDto.getExamName());
		exam.setSchId(ScoreCloudEnv.get().getSchId());
		baseDao.add(exam);
	}

	public void modifyExamDto(ExamDto examDto)
	{
		examDto.setSchId(ScoreCloudEnv.get().getSchId());
		Exam oldExam = baseDao.query(Exam.class, examDto.getId());
		oldExam.setUpdateDate(new Date());
		oldExam.setExamName(examDto.getExamName());
		oldExam.setYear(examDto.getYear());
		baseDao.modify(oldExam);
	}

	@Transactional
	public void deleteExamDto(String id)
	{
		baseDao.delete(Exam.class, id);
		baseDao.execute("delete from Subject where examId=?", id);
	}

	public List<ExamDto> queryExamDtos()
	{
		List<Exam> exams = baseDao.query(" from Exam order by year desc,createDate desc");
		List<ExamDto> examDtos = new ArrayList<>();
		for (Exam e : exams)
		{
			ExamDto examDto = new ExamDto();
			examDto.setId(e.getId());
			examDto.setCreateDate(DateUtil.date2String(e.getCreateDate(), "yyyy-MM-dd"));
			examDto.setUpdateDate(DateUtil.date2String(e.getUpdateDate(), "yyyy-MM-dd"));
			examDto.setYear(e.getYear());
			examDto.setExamName(e.getExamName());
			examDtos.add(examDto);
		}
		return examDtos;
	}

	public List<ExamDto> queyJoinedExamDtosByStudentId(String schId, String studentId)
	{
		List<String> examIds = baseDao.querySql("select distinct examId from " + schId + "_score where studentId=? ",
				studentId);
		if (examIds == null || examIds.isEmpty())
		{
			return new ArrayList<>();
		}
		Map map = new HashMap<>();
		map.put("examIds", examIds);
		List<Exam> exams = baseDao.query(
				" from Exam where id in :examIds order by year desc,createDate desc",
				map);
		List<ExamDto> examDtos = new ArrayList<>();
		for (Exam e : exams)
		{
			ExamDto examDto = new ExamDto();
			examDto.setId(e.getId());
			examDto.setCreateDate(DateUtil.date2String(e.getCreateDate(), "yyyy-MM-dd"));
			examDto.setUpdateDate(DateUtil.date2String(e.getUpdateDate(), "yyyy-MM-dd"));
			examDto.setYear(e.getYear());
			examDto.setExamName(e.getExamName());
			examDtos.add(examDto);
		}
		return examDtos;
	}

	/**
	 * 查询某个班级的参考试人数
	 * @param examId
	 * @param classId
	 * @return
	 */
	public Integer queryJoinedExamCntByClassId(String schId, String classId, String examId)
	{
		return new Long((long) baseDao.countSql(
				"select count(distinct studentId) from " + schId
						+ "_score where examId=? and studentId in (select id from student where classId=?)",
				examId, classId)).intValue();
	}

	/**
	 * 查询某个班级的参加考试获得的总分
	 * @param examId
	 * @param classId
	 * @return
	 */
	public Float queryJoinedExamTotalScoreCntByClassId(String schId, String classId, String examId)
	{
		Object o = baseDao.querySqlUnique(
				"select sum(score) from " + schId
						+ "_score where examId=? and studentId in (select id from student where classId=?)",
				examId, classId);
		if (o == null)
			return 0f;
		return ((Double) o).floatValue();
	}

	/**
	 * 查询某个班级参加的考试分数满足某个条件的人数
	 * @param classId
	 * @param examId
	 * @param targetScore 为总分
	 * @return
	 */
	public Integer queryJoinedAvgScoreReachedCntByClassId(String schId, String classId, String examId,
			Float targetScore)
	{
		List list = baseDao.querySql(
				"select studentId,sum(score) from " + schId
						+ "_score where examId=? and studentId in (select id from student where classId=?) group by studentId having sum(score)>=?",
				examId, classId, targetScore.doubleValue());
		if(list==null)
			return 0;
		return list.size();
	}

	/**
	 * 查询某个班级的参考试人数
	 * @param examId
	 * @param classId
	 * @return
	 */
	public Integer queryJoinedSubjectCntByClassId(String schId, String classId, String subjectId)
	{
		return new Long((long) baseDao.countSql(
				"select count(distinct studentId) from " + schId
						+ "_score where subjectId=? and studentId in (select id from student where classId=?)",
				subjectId, classId)).intValue();
	}

	/**
	 * 查询某个班级的参加考试获得的总分
	 * @param examId
	 * @param classId
	 * @return
	 */
	public Float queryJoinedSubjectTotalScoreCntByClassId(String schId, String classId, String subjectId)
	{
		Object o = baseDao.querySqlUnique(
				"select sum(score) from " + schId
						+ "_score where subjectId=? and studentId in (select id from student where classId=?)",
				subjectId, classId);
		if (o == null)
			return 0f;
		return ((Double) o).floatValue();
	}

	/**
	 * 查询某个班级参加的考试分数满足某个条件的人数
	 * @param classId
	 * @param examId
	 * @param targetScore 为总分
	 * @return
	 */
	public Integer queryJoinedSubjectAvgScoreReachedCntByClassId(String schId, String classId, String subjectId,
			Float targetScore)
	{
		List list = baseDao.querySql(
				"select studentId,sum(score) from " + schId
						+ "_score where subjectId=? and studentId in (select id from student where classId=?) group by studentId having sum(score)>=?",
				subjectId, classId, targetScore.doubleValue());
		if (list == null)
			return 0;
		return list.size();
	}

	


}
