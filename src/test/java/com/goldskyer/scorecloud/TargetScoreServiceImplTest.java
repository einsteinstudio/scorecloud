package com.goldskyer.scorecloud;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.gmxx.service.BaseTest;
import com.goldskyer.scorecloud.service.ClassService;
import com.goldskyer.scorecloud.service.TargetScoreService;

public class TargetScoreServiceImplTest extends BaseTest
{
	@Autowired
	private TargetScoreService targetScoreService;
	@Autowired
	private ClassService classService;

}
