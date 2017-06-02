package com.goldskyer.scorecloud.entities;

import java.util.Date;

public class OpLog
{
	private String id;
	private Date operationDate;
	private String operationName;
	private String note;
	private String accountId;
	private String bizType;//业务类型

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Date getOperationDate()
	{
		return operationDate;
	}

	public void setOperationDate(Date operationDate)
	{
		this.operationDate = operationDate;
	}

	public String getOperationName()
	{
		return operationName;
	}

	public void setOperationName(String operationName)
	{
		this.operationName = operationName;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getBizType()
	{
		return bizType;
	}

	public void setBizType(String bizType)
	{
		this.bizType = bizType;
	}
	
}
