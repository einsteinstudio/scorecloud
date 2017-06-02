package com.goldskyer.gmxx.common.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.common.entities.WorkFlowNode;
import com.goldskyer.gmxx.common.enums.NodeType;
import com.goldskyer.gmxx.common.service.WorkFlowNodeService;

@Service
public class WorkFlowNodeServiceImpl implements WorkFlowNodeService
{
	@Autowired
	private BaseDao baseDao;

	public WorkFlowNode queryStartNode(String  templateId)
	{
		return (WorkFlowNode) baseDao.queryUnique("from WorkFlowNode where templateId=? and nodeType='START'",
				templateId);
	}

	public WorkFlowNode queryNextNode(WorkFlowNode curNode)
	{
		if (StringUtils.isBlank(curNode.getNextId()))
		{
			return null;
		}
		return baseDao.query(WorkFlowNode.class, curNode.getNextId());
	}

	public List<WorkFlowNode> queryWorkFlowNodesByTemplateId(String templateId)
	{
		return baseDao.query("from WorkFlowNode where templateId=? order by nodeId asc,id", templateId);
	}

	/**
	 * 传递过来的WorkFlowNode只有基础数据
	 * @param workFlowNodes
	 */
	@Transactional
	public void addOrModifyAllWorkFlowNode(List<WorkFlowNode> workFlowNodes)
	{
		for (int i = 0; i < workFlowNodes.size(); i++)
		{
			WorkFlowNode node = workFlowNodes.get(i);
			if (StringUtils.isBlank(node.getId()))
			{
				node.setId(UUID.randomUUID().toString());
			}
			node.setNodeId(i + 1);
			if (i == 0)
			{
				node.setNodeType(NodeType.START);
			}
			else if (i == workFlowNodes.size() - 1)
			{
				node.setNodeType(NodeType.END);
			}
			else
			{
				node.setNodeType(NodeType.IO);
			}
		}
		//链表生成
		for (int i = 0; i < workFlowNodes.size(); i++)
		{
			WorkFlowNode node = workFlowNodes.get(i);
			if (i < workFlowNodes.size() - 1)
				node.setNextId(workFlowNodes.get(i + 1).getId());
		}
		baseDao.addOrModify(workFlowNodes);
	}

	public void deleteAllWorkFlowNodeByTemplateId(String templateId)
	{
		baseDao.execute("delete from WorkFlowNode where templateId=?", templateId);
	}
}
