package com.goldskyer.gmxx.common.business.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.utils.MD5Util;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.common.business.UserBusiness;
import com.goldskyer.gmxx.common.dtos.UserDto;
import com.goldskyer.gmxx.common.entities.Teacher;

@Service
public class UserBusinessImpl implements UserBusiness
{
	@Autowired
	private AccountService accountService;
	@Autowired
	private AclService aclService;

	@Autowired
	private BaseDao baseDao;
	@Transactional
	public void addUser(UserDto userDto)
	{
		userDto.getAccount().setNickname(userDto.getTeacher().getName());
		userDto.getAccount().setPassword(MD5Util.getMD5(MD5Util.getMD5(userDto.getAccount().getPassword())));
		if (StringUtils.isBlank(userDto.getAccount().getPhoto()))
		{
			int index=new Random().nextInt(179);
			String photoPath = "/manager/assets/photos_v1/" + index + ".gif";
			userDto.getAccount().setPhoto(photoPath);
		}
		String accountId = aclService.addAccount(userDto.getAccount());
		userDto.getTeacher().setAccountId(accountId);
		aclService.dispatchRolesForUser(accountId, userDto.getRoleIds());
		userDto.getTeacher().setDomain(EnvParameter.get().getDomain());
		userDto.getTeacher().setCreateDate(new Date());
		baseDao.add(userDto.getTeacher());
	}

	@Transactional
	public void modifyUser(UserDto userDto)
	{
		Account dbAccount = aclService.queryAccount(userDto.getAccount().getId());
		dbAccount.setUsername(userDto.getAccount().getUsername());
		if (!StringUtils.equals(dbAccount.getPassword(), userDto.getAccount().getPassword()))
		{
			dbAccount.setPassword(MD5Util.getMD5(MD5Util.getMD5(userDto.getAccount().getPassword())));
		}
		dbAccount.setNickname(userDto.getTeacher().getName());
		dbAccount.setPhoto(userDto.getAccount().getPhoto());
		if (StringUtils.isBlank(userDto.getAccount().getPhoto()))
		{
			int index = new Random().nextInt(179);
			String photoPath = "/manager/assets/photos_v1/" + index + ".gif";
			dbAccount.setPhoto(photoPath);
		}
		baseDao.modify(dbAccount);
		//修改教师信息
		userDto.getTeacher().setAccountId(dbAccount.getId());
		userDto.getTeacher().setDomain(EnvParameter.get().getDomain());
		baseDao.modify(userDto.getTeacher());

	}

	@Transactional
	public void modifyUserWithRole(UserDto userDto)
	{
		modifyUser(userDto);
		//分配权限
		aclService.dispatchRolesForUser(userDto.getAccount().getId(), userDto.getRoleIds());
	}

	public UserDto queryUserDtoById(String accountId)
	{
		UserDto userDto = new UserDto();
		userDto.setAccount(baseDao.query(Account.class,accountId));
		userDto.setTeacher((Teacher) baseDao.queryUnique("from Teacher where accountId=?", accountId));
		List<Role> curRoles = aclService.queryRolesByUser(accountId);
		if (CollectionUtils.isNotEmpty(curRoles))
		{
			for (Role role : curRoles)
			{
				userDto.getRoleIds().add(role.getId());
			}
		}
		return userDto;
	}

	@Transactional
	public void deleteUser(String accountId)
	{
		baseDao.execute("delete from Teacher where accountId=?", accountId);
		aclService.deleteAccount(accountId);
		baseDao.execute("delete from AccountRole where accountId=?", accountId);
	}
}
