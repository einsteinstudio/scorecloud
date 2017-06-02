package com.goldskyer.gmxx.common.business;

import com.goldskyer.gmxx.common.dtos.UserDto;

public interface UserBusiness
{
	public void addUser(UserDto userDto);

	public void deleteUser(String accountId);

	public UserDto queryUserDtoById(String accountId);

	public void modifyUser(UserDto userDto);

	public void modifyUserWithRole(UserDto userDto);
}
