package com.goldskyer.scorecloud.service;

import com.goldskyer.scorecloud.dto.ClassDto;
import com.goldskyer.scorecloud.dto.TargetScoreItem;
import com.goldskyer.scorecloud.vo.TargetScoreVo;

public interface TargetScoreService
{
	/**
	 * 目标值计算
	 * @param examId
	 * @param classDto
	 * @return
	 */
	public TargetScoreItem calculateTargetScoreItem(String schId, String title, String examId, ClassDto classDto);

	public TargetScoreVo getTargetScoreVo(String schId, String examId);

	public TargetScoreVo getTargetSubjectScoreVo(String schId, String subjectId);
}
