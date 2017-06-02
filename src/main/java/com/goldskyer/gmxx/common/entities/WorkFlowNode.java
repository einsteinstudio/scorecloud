package com.goldskyer.gmxx.common.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.goldskyer.gmxx.common.enums.NodeType;

@Entity
@Table(name = "work_flow_node")
public class WorkFlowNode
{
	@Id
	@Column(name = "ID", length = 40) //使用自己生成ID
	private String id;
	private Integer nodeId;//节点ID，在一个模板中不重复,自动从1，2，3，4开始
	private String status;//节点状态名称
	private String role;
	private String accountId;
	@Enumerated(EnumType.STRING)
	private NodeType nodeType;//节点类型
	private String nextId;
	private String templateId;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getNextId()
	{
		return nextId;
	}

	public void setNextId(String nextId)
	{
		this.nextId = nextId;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public NodeType getNodeType()
	{
		return nodeType;
	}

	public void setNodeType(NodeType nodeType)
	{
		this.nodeType = nodeType;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	public Integer getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(Integer nodeId)
	{
		this.nodeId = nodeId;
	}


}
