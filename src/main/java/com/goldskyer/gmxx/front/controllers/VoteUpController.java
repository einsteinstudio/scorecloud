package com.goldskyer.gmxx.front.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.VoteUpLog;
import com.goldskyer.core.service.VoteUpLogService;
import com.goldskyer.gmxx.BaseController;

@Controller
@RequestMapping("/voteUp")
public class VoteUpController extends BaseController
{
	@Autowired
	private VoteUpLogService voteUpLogService;

	@RequestMapping(value = "/queryStatus.json", produces = "application/json")
	@ResponseBody
	public JsonData queryVoteStatus(VoteUpLog voteUp, HttpServletRequest request)
	{
		//参数校验
		voteUp.setId(null);
		voteUp.setIp(request.getRemoteAddr());
		if (StringUtils.isBlank(voteUp.getAccountId()) || StringUtils.isBlank(voteUp.getObjectId())
				|| StringUtils.isBlank(voteUp.getObjectType()))
		{
			return JsonData.failure("必填参数不能为空");
		}
		VoteUpLog voteUpLog = voteUpLogService.queryVoteUpLogbyId(voteUp.getAccountId(), voteUp.getObjectId(),
				voteUp.getObjectType());
		if (voteUpLog == null)
		{
			return JsonData.success();
		}
		else
		{
			return JsonData.failure("您已经点过赞了");
		}
	}
}
