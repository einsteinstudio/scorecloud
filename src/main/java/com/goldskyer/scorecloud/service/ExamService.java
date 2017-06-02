package com.goldskyer.scorecloud.service;

import java.util.List;
import java.util.Map;

import com.goldskyer.scorecloud.dto.ExamDto;

public interface ExamService
{
	public ExamDto queryExamDtoById(String id);

	public void addExamDto(ExamDto examDto);

	public void modifyExamDto(ExamDto examDto);

	public void deleteExamDto(String id);

	public List<ExamDto> queryExamDtos();

	public Map<String, List<ExamDto>> queryGroupedExamDtosByYear();
	
	public List<ExamDto> queyJoinedExamDtosByStudentId(String schId, String studentId);

	/**
	 * 查询某个班级的参考试人数
	 * @param examId
	 * @param classId
	 * @return
	 */
	public Integer queryJoinedExamCntByClassId(String schId, String classId, String examId);

	/**
	 * 查询某个班级的参加考试获得的总分
	 * @param examId
	 * @param classId
	 * @return
	 */
	public Float queryJoinedExamTotalScoreCntByClassId(String schId, String classId, String examId);

	/**
	 * 查询某个班级参加的考试分数满足某个条件的人数
	 * @param classId
	 * @param examId
	 * @param targetScore 为总分
	 * @return
	 */
	public Integer queryJoinedAvgScoreReachedCntByClassId(String schId, String classId, String examId,
			Float targetScore);

	/**
	 * 查询某个班级的参考试人数
	 * @param examId
	 * @param classId
	 * @return
	 */
	public Integer queryJoinedSubjectCntByClassId(String schId, String classId, String subjectId);

	/**
	 * 查询某个班级的参加考试获得的总分
	 * @param examId
	 * @param classId
	 * @return
	 */
	public Float queryJoinedSubjectTotalScoreCntByClassId(String schId, String classId, String subjectId);

	/**
	 * 查询某个班级参加的考试分数满足某个条件的人数
	 * @param classId
	 * @param examId
	 * @param targetScore 为总分
	 * @return
	 */
	public Integer queryJoinedSubjectAvgScoreReachedCntByClassId(String schId, String classId, String subjectId,
			Float targetScore);

}
