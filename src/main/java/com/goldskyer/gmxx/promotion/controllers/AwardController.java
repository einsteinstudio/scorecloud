package com.goldskyer.gmxx.promotion.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldskyer.core.controllers.CoreBaseController;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.promotion.dtos.ApplyAwardDto;
import com.goldskyer.gmxx.promotion.entities.AwardRecord;
import com.goldskyer.gmxx.promotion.service.AwardService;

/**
 * 抽奖类控制器
 * @author jintianfan
 *
 */
@Controller
@RequestMapping("/award")
public class AwardController extends CoreBaseController {
	
	@Autowired
	private AwardService awardService;
	
	@Autowired
	private BaseDao baseDao;
	@RequestMapping(value="/doAward.json", produces="application/json")
	@ResponseBody
	public Object doAward(ApplyAwardDto applyAwardDto, HttpServletRequest request)
	{
		JsonData jsonData = JsonData.success();
		//判断有没有做题了
		applyAwardDto.setFp(applyAwardDto.getFp() + StringUtils.trimToEmpty(applyAwardDto.getRandom()));
		long a1 = baseDao.count("select count(1) from AccountAnswer where accountId=? and paperId='paper001'",applyAwardDto.getFp());
		long a2 = baseDao.count("select count(1) from AccountAnswer where accountId=? and paperId='paper002'",applyAwardDto.getFp());
		if(a1<=0 ||a2<=0)
		{
			return JsonData.failure("请先完成压力测试和抑郁测试");
		}
		jsonData.data.put("awardRecord", awardService.doAward(applyAwardDto));
		return jsonData;
	}
	
	@RequestMapping(value="/completeInfo.json", produces="application/json")
	@ResponseBody
    public Object completeInfo(ApplyAwardDto applyAwardDto) {
		JsonData jsonData = JsonData.success();
		applyAwardDto.setFp(applyAwardDto.getFp() + StringUtils.trimToEmpty(applyAwardDto.getRandom()));

		AwardRecord awardRecord = (AwardRecord) baseDao.queryUnique("select a from AwardRecord  a where a.fp=?",applyAwardDto.getFp());
		if(awardRecord==null)
		{
			return JsonData.failure("抱歉，找不到对应的抽奖纪录");
		}
		if(StringUtils.isBlank(awardRecord.getAwardId()))
		{
			return JsonData.failure("抱歉，您已抽奖，但未中奖");
		}
		if(StringUtils.isNotBlank(awardRecord.getAccountId()) &&!StringUtils.equals(applyAwardDto.getAccountId(), awardRecord.getAccountId()))
		{
			return JsonData.failure("抱歉，该中奖纪录已被其他人认领");
		}
		awardRecord.setAccountId(applyAwardDto.getAccountId());
		awardRecord.setName(applyAwardDto.getName());
		awardRecord.setDepartment(applyAwardDto.getDepartment());
		awardRecord.setCardNo(applyAwardDto.getCardNo());
		baseDao.modify(awardRecord);
		return jsonData;
	}
	
	
	@RequestMapping(value="/finishPaper.json", produces="application/json")
	@ResponseBody
    public Object finishPaper(ApplyAwardDto applyAwardDto,@RequestParam String paperId) {
		applyAwardDto.setFp(applyAwardDto.getFp() + StringUtils.trim(applyAwardDto.getRandom()));
		long a1 = baseDao.count("select count(1) from AccountAnswer where accountId=? and paperId='paper001'",applyAwardDto.getFp());
		if(a1==0)
		{
			return JsonData.failure();
		}
		return JsonData.success();
	}
	
}
