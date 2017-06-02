package com.goldskyer.scorecloud.business;

import com.goldskyer.scorecloud.vo.MyScoreVo;

public interface MyScoreBusiness
{
	public MyScoreVo queryScoreResultDtosByStudentId(String studentId);
}
