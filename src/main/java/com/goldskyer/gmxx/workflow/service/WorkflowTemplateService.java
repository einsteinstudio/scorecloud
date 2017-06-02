package com.goldskyer.gmxx.workflow.service;

import java.util.List;

import com.goldskyer.gmxx.common.entities.WorkFlowNode;
import com.goldskyer.gmxx.common.entities.WorkFlowTemplate;

public interface WorkflowTemplateService
{
	public void addTemplate(WorkFlowTemplate template, List<WorkFlowNode> nodes);

	public void modifyTemplate(WorkFlowTemplate template, List<WorkFlowNode> nodes);

	public void deleteTemplateById(String templateId);

	public WorkFlowTemplate queryTemplateByType(String objectType, String subType);
}
