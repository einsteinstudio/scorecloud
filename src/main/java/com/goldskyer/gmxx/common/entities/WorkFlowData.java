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

import com.goldskyer.gmxx.common.enums.WorkFlowAuditEvent;

/**
 *TODO 如何实现转交
 * @author jintianfan
 *
 */
@Entity
@Table(name = "work_flow_data")
public class WorkFlowData
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String objectId;
	private String objectType;
	private String accountId;
	private String nickName;
	private String fromStatus;
	private String toStatus;
	private String comment;
	private boolean approved; //审核状态

	private String note; //审核意见，评论页放在note里面，简化处理流程
	@Enumerated(EnumType.STRING)
	private WorkFlowAuditEvent eventType;//审核，评论
	private String eventNote; //系统生成的事件记录
	@Column(updatable = false)
	private Date createDate;

	private String picUrl;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getObjectId()
	{
		return objectId;
	}

	public void setObjectId(String objectId)
	{
		this.objectId = objectId;
	}

	public String getObjectType()
	{
		return objectType;
	}

	public void setObjectType(String objectType)
	{
		this.objectType = objectType;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getFromStatus()
	{
		return fromStatus;
	}

	public void setFromStatus(String fromStatus)
	{
		this.fromStatus = fromStatus;
	}

	public String getToStatus()
	{
		return toStatus;
	}

	public void setToStatus(String toStatus)
	{
		this.toStatus = toStatus;
	}

	public boolean isApproved()
	{
		return approved;
	}

	public void setApproved(boolean approved)
	{
		this.approved = approved;
	}



	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public WorkFlowAuditEvent getEventType()
	{
		return eventType;
	}

	public void setEventType(WorkFlowAuditEvent eventType)
	{
		this.eventType = eventType;
	}

	public String getEventNote()
	{
		return eventNote;
	}

	public void setEventNote(String eventNote)
	{
		this.eventNote = eventNote;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

}
