package com.goldskyer.gmxx.common.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 工作流任务
 * @author jintianfan
 *
 */
@Entity
@Table(name = "work_flow_task")
public class WorkFlowTask
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String taskName;
	private String objectId;
	private String objectType;
	private String subType;
	private String applyName;
	private String applyAccountId;
	private String nodeId; //任务中止，workFlowNodeNodeId为空
	private Date createDate;
	private Date updateDate;
	private String status; //当前状态（除了node中的状态，其他均为内置状态）
	private boolean finished; //流程是否结束


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

	public String getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
	}

	public boolean isFinished()
	{
		return finished;
	}

	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	@Override
	public String toString()
	{
		return "WorkFlowTask [id=" + id + ", objectId=" + objectId + ", objectType=" + objectType + ", nodeId=" + nodeId
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", finished=" + finished + "]";
	}

	public String getSubType()
	{
		return subType;
	}

	public void setSubType(String subType)
	{
		this.subType = subType;
	}

	public String getApplyName()
	{
		return applyName;
	}

	public void setApplyName(String applyName)
	{
		this.applyName = applyName;
	}

	public String getApplyAccountId()
	{
		return applyAccountId;
	}

	public void setApplyAccountId(String applyAccountId)
	{
		this.applyAccountId = applyAccountId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
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
