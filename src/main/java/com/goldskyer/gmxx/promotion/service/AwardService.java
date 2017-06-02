package com.goldskyer.gmxx.promotion.service;

import com.goldskyer.gmxx.promotion.dtos.ApplyAwardDto;
import com.goldskyer.gmxx.promotion.entities.AwardRecord;

public interface AwardService {
	public AwardRecord doAward(ApplyAwardDto applyAwardDto) ;	
}
