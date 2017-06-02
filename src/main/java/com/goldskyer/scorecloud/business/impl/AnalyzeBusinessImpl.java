package com.goldskyer.scorecloud.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.scorecloud.business.AnalyzeBusiness;
import com.goldskyer.scorecloud.service.TargetScoreService;

@Service
public class AnalyzeBusinessImpl implements AnalyzeBusiness
{
	@Autowired
	private TargetScoreService targetScoreService;
	

}
