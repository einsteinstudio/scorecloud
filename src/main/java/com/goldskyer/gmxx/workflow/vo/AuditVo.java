package com.goldskyer.gmxx.workflow.vo;

import com.goldskyer.gmxx.common.enums.WorkFlowAuditEvent;

public class AuditVo
{
	private WorkFlowAuditEvent eventType;
	private String note;
	private String comment;//不用
	private String picUrl;//附件
	private String accountId;
	private String taskId;

	public WorkFlowAuditEvent getEventType()
	{
		return eventType;
	}

	public void setEventType(WorkFlowAuditEvent eventType)
	{
		this.eventType = eventType;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getTaskId()
	{
		return taskId;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}



}
