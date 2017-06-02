package com.goldskyer.gmxx.common.entities;

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
@Table(name = "message")
public class Message
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String sendId;
	private String recvId;
	private String textId;
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

	public String getSendId()
	{
		return sendId;
	}

	public void setSendId(String sendId)
	{
		this.sendId = sendId;
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

	public MessageStatus getStatus()
	{
		return status;
	}

	public void setStatus(MessageStatus status)
	{
		this.status = status;
	}


}
