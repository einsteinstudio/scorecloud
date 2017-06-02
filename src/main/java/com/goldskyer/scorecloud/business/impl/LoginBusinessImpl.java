package com.goldskyer.scorecloud.business.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.core.entities.Account;
import com.goldskyer.core.enums.AccountType;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.gmxx.acl.entities.Right;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.scorecloud.business.LoginBusiness;
import com.goldskyer.scorecloud.constant.RightConstant;
import com.goldskyer.scorecloud.constant.SessionConstant;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.ScoreCloudUserDto;

@Service
public class LoginBusinessImpl implements LoginBusiness
{
	@Autowired
	private AclService aclService;
	@Autowired
	private AccountService accountService;
	/**
	 * 执行登陆操作
	 * @param accountId
	 */
	public void doLogin(String accountId)
	{
		Account account = accountService.getAccountById(accountId);
		ScoreCloudUserDto userDto = new ScoreCloudUserDto();
		userDto.setAccountId(accountId);
		userDto.setNickname(account.getNickname());
		userDto.setUsername(account.getUsername());
		ScoreCloudEnv.get().setSessionAttr(SessionConstant.USER_DTO, userDto);
		//设置权限
		Map<String, Right> rightMap = aclService.queryRightMapByAccountId(accountId);
		ScoreCloudEnv.get().setSessionAttr(SessionConstant.RIGHT_MAP, rightMap);
		//判断账号类型
		if (account.getType() == AccountType.STUDENT)
		{
			rightMap.put(RightConstant.MYSCORE_VIEW, new Right());
		}
		else
		{
			rightMap.remove(RightConstant.MYSCORE_VIEW);
		}
	}
}
