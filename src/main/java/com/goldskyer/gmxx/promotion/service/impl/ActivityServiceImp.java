package com.goldskyer.gmxx.promotion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.promotion.entities.Activity;
import com.goldskyer.gmxx.promotion.service.ActivityService;
@Service
public class ActivityServiceImp implements ActivityService{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public Activity queryActivityById(String activityId) {
		// TODO Auto-generated method stub
		return baseDao.query(Activity.class,activityId);
	}

}
