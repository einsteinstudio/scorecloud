package com.goldskyer.gmxx.common.dtos;

import java.util.ArrayList;
import java.util.List;

import com.goldskyer.core.entities.Account;
import com.goldskyer.gmxx.common.entities.Teacher;

public class UserDto
{
	private Account account;
	private Teacher teacher;

	private List<String> roleIds = new ArrayList<String>();

	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

	public Teacher getTeacher()
	{
		return teacher;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	public List<String> getRoleIds()
	{
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds)
	{
		this.roleIds = roleIds;
	}

}
