package com.goldskyer.gmxx.promotion.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.promotion.dtos.ApplyAwardDto;
import com.goldskyer.gmxx.promotion.entities.AwardRecord;
import com.goldskyer.gmxx.promotion.service.AwardRecordService;
@Service
public class AwardRecordServiceImp  implements AwardRecordService{
	
	@Autowired
	private BaseDao  baseDao;
	
	public long getTotalAwardRecordCountByActivityId(String activityId)
	{
		return  baseDao.count("select count(1) from AwardRecord where activityId=? ",activityId);
	}
	
	public boolean ifHasAwardRecord(ApplyAwardDto applyAwardDto)
	{
		List list = baseDao.query(" from AwardRecord where  fp=?  and activityId=? ", applyAwardDto.getFp(),
				applyAwardDto.getActivityId());
		if(list.size()>0)
		{
			return true;
		}
		return false;
	}
	
	@Transactional
	public void addAwardRecordLimitAccount(AwardRecord awardRecord)
	{
		List list = baseDao.query("from AwardRecord where   fp=? and activityId=?", 
				awardRecord.getFp(), awardRecord.getActivityId());
		if(list.size()>0)
			throw new BusinessException("对不起，您只有一个一次抽奖机会");
		awardRecord.setCreateDate(new Date());
		baseDao.add(awardRecord);
		if (StringUtils.isNotBlank(awardRecord.getAwardId()))
		{
			int a = baseDao.execute("update Award set awardedNum=awardedNum+1 where id=? and awardedNum<totalNum",
					awardRecord.getAwardId());
			if (a == 0)
			{
				throw new BusinessException("抽奖程序出错");
			}
		}

	}
}
