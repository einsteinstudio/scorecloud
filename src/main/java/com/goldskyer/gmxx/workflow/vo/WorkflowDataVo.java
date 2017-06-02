package com.goldskyer.gmxx.workflow.vo;

import java.util.Date;
import java.util.List;

import com.goldskyer.gmxx.common.entities.Attachment;

public class WorkflowDataVo
{
	private String accountId;
	private String taskName;
	private String nickName;
	private String eventNote;
	private String note;
	private String eventType;
	private Date createDate;
	private List<Attachment> picAttachs;
	private List<Attachment> otherAttachs;

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getEventType()
	{
		return eventType;
	}

	public void setEventType(String eventType)
	{
		this.eventType = eventType;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public List<Attachment> getPicAttachs()
	{
		return picAttachs;
	}

	public void setPicAttachs(List<Attachment> picAttachs)
	{
		this.picAttachs = picAttachs;
	}

	public List<Attachment> getOtherAttachs()
	{
		return otherAttachs;
	}

	public void setOtherAttachs(List<Attachment> otherAttachs)
	{
		this.otherAttachs = otherAttachs;
	}

	public String getEventNote()
	{
		return eventNote;
	}

	public void setEventNote(String eventNote)
	{
		this.eventNote = eventNote;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

}
