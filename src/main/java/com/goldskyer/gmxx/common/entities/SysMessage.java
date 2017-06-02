package com.goldskyer.gmxx.common.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.goldskyer.gmxx.common.enums.MessageStatus;

@Entity
@Table(name = "sys_message")
public class SysMessage
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String recvId;
	private String textId;
	private Date createDate;
	@Enumerated(EnumType.STRING)
	private MessageStatus status; //已读、未读

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getRecvId()
	{
		return recvId;
	}

	public void setRecvId(String recvId)
	{
		this.recvId = recvId;
	}

	public String getTextId()
	{
		return textId;
	}

	public void setTextId(String textId)
	{
		this.textId = textId;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public MessageStatus getStatus()
	{
		return status;
	}

	public void setStatus(MessageStatus status)
	{
		this.status = status;
	}


}
