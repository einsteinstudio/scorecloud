package com.goldskyer.scorecloud.service;

import java.util.List;

import com.goldskyer.scorecloud.dto.ScoreDto;
import com.goldskyer.scorecloud.dto.ScoreQueryDto;
import com.goldskyer.scorecloud.dto.ScoreResultDto;
import com.goldskyer.scorecloud.entities.Score;

public interface ScoreService
{
	public void modifyScore(String schId, Score score);

	public String addScore(String schId, Score score);
	/**
	 *  考试成绩查询
	 *  聚合计算放在内存中进行.
	 *  
	 * @param scoreQueryDto
	 * @return  ss:11;bb:22;
	 */
	public ScoreResultDto queryScoresByScoreQueryDto(ScoreQueryDto scoreQueryDto);

	public void batchAddScoreDtos(String schId, List<ScoreDto> scoreDtos);

	public void addOrUpdateScoreDto(String schId, ScoreDto scoreDto);

	public Score queryUniqueScore(String schId, String examId, String subjectId, String studentId);

}
