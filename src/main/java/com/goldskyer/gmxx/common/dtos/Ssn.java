package com.goldskyer.gmxx.common.dtos;

import java.util.Date;

public class Ssn
{
	private String accountId;
	private Date loginDate;
	private String ip;
	private String domain;

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Date getLoginDate()
	{
		return loginDate;
	}

	public void setLoginDate(Date loginDate)
	{
		this.loginDate = loginDate;
	}


	public String getDomain()
	{
		return domain;
	}

	public void setDomain(String domain)
	{
		this.domain = domain;
	}

}
