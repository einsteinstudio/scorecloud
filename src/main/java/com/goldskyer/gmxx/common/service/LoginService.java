package com.goldskyer.gmxx.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Account;

public interface LoginService
{
	public JsonData loginSessionSave(Account account, HttpServletRequest request, HttpServletResponse response);

	public void logout(HttpServletRequest request, HttpServletResponse response);
}
