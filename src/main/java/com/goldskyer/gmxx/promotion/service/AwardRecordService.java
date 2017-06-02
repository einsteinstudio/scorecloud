package com.goldskyer.gmxx.promotion.service;

import com.goldskyer.gmxx.promotion.dtos.ApplyAwardDto;
import com.goldskyer.gmxx.promotion.entities.AwardRecord;


public interface AwardRecordService {
	public long getTotalAwardRecordCountByActivityId(String activityId);
	
	public boolean ifHasAwardRecord(ApplyAwardDto applyAwardDto);
	
	public void addAwardRecordLimitAccount(AwardRecord awardRecord);

}
