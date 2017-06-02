package com.goldskyer.gmxx.promotion.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.promotion.dtos.ApplyAwardDto;
import com.goldskyer.gmxx.promotion.entities.Activity;
import com.goldskyer.gmxx.promotion.entities.Award;
import com.goldskyer.gmxx.promotion.entities.AwardRecord;
import com.goldskyer.gmxx.promotion.service.ActivityService;
import com.goldskyer.gmxx.promotion.service.AwardRecordService;
import com.goldskyer.gmxx.promotion.service.AwardService;
@Service
public class AwardServiceImp implements AwardService{

	@Autowired
	private BaseDao  baseDao;
	@Autowired
	private AwardRecordService awardRecordService ;
	
	@Autowired
	private ActivityService activityService;
	/**
	 * 用户执行抽奖操作
	 */
	@Override
	public AwardRecord doAward(ApplyAwardDto applyAwardDto) {
		if(awardRecordService.ifHasAwardRecord(applyAwardDto))
		{
			throw new BusinessException("对不起，您只有一个一次抽奖机会");
		}
		
		AwardRecord awardRecord = new AwardRecord();
		awardRecord.setAccountId(applyAwardDto.getAccountId());
		awardRecord.setActivityId(applyAwardDto.getActivityId());
		awardRecord.setFp(applyAwardDto.getFp());
		
		List<Award> awards = getAllAwardsByActivityId(applyAwardDto.getActivityId());
		long joinedCount =awardRecordService.getTotalAwardRecordCountByActivityId(applyAwardDto.getActivityId()); //已参加的人数
		Activity activity = activityService.queryActivityById(applyAwardDto.getActivityId());
		
		long remainCount = activity.getTotalPerson()-joinedCount;
		while(remainCount<0)
		{
			remainCount+=100;
		}
		int sed = new Random().nextInt((int)remainCount);
		Integer awardIndex = 0 ;
		for(Award award:awards)
		{
			awardIndex +=award.getTotalNum()-award.getAwardedNum();
			if(sed<awardIndex) //有了，满足中奖条件了
			{
				//中奖了
				awardRecord.setAwarded(true);
				awardRecord.setAwardId(award.getId());
				try
				{
					awardRecordService.addAwardRecordLimitAccount(awardRecord);
				}
				catch (Exception e)
				{
					awardRecord.setAwarded(false);
					awardRecord.setAwardId(null);
					awardRecordService.addAwardRecordLimitAccount(awardRecord);
					return awardRecord;
				}
				return awardRecord;
			}
		}
		awardRecordService.addAwardRecordLimitAccount(awardRecord);
		return awardRecord;
	}

	
	public List<Award> getAllAwardsByActivityId(String activityId)
	{
		return baseDao.query("from Award where activityId=? order by activityId,id ",activityId);
	}
	

	
	public boolean hitAward(Award award)
	{
		int a=baseDao.execute("update Award set awardedNum=awardedNum+1 where id=? and awardedNum<totalNum",award.getId());
		if(a>0){
			return true;
		}
		return false;
	}
}
