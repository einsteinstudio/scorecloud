package com.goldskyer.gmxx.common.service;

import java.util.List;

import com.goldskyer.gmxx.common.entities.WorkFlowNode;

public interface WorkFlowNodeService
{
	public WorkFlowNode queryStartNode(String templateId);

	public WorkFlowNode queryNextNode(WorkFlowNode curNode);

	public List<WorkFlowNode> queryWorkFlowNodesByTemplateId(String templateId);

	public void deleteAllWorkFlowNodeByTemplateId(String templateId);

	public void addOrModifyAllWorkFlowNode(List<WorkFlowNode> workFlowNodes);
}
