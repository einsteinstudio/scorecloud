package com.goldskyer.gmxx.promotion;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.gmxx.promotion.dtos.ApplyAwardDto;
import com.goldskyer.gmxx.promotion.service.AwardService;
import com.goldskyer.gmxx.service.BaseTest;

public class AwardServiceTest extends BaseTest{
	
	@Autowired
	private AwardService awardService;
	@Test
	public void testAward()
	{
		for(int i=0;i<100;i++)
		{
			try
			{
				ApplyAwardDto applyAwardDto = new ApplyAwardDto();
				applyAwardDto.setAccountId(""+i);
				applyAwardDto.setActivityId("001");
				applyAwardDto.setFp("fp"+i);
				awardService.doAward(applyAwardDto);
			}catch(BusinessException e)
			{
				log.fatal(e.getMessage(),e);
				continue;
			}
		}
	}
}
