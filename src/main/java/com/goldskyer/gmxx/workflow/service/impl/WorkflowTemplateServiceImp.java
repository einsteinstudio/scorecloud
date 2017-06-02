package com.goldskyer.gmxx.workflow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.common.entities.WorkFlowNode;
import com.goldskyer.gmxx.common.entities.WorkFlowTemplate;
import com.goldskyer.gmxx.common.service.WorkFlowNodeService;
import com.goldskyer.gmxx.workflow.service.WorkflowTemplateService;

@Service
public class WorkflowTemplateServiceImp extends CoreServiceImp implements WorkflowTemplateService
{
	@Autowired
	private WorkFlowNodeService workFlowNodeService;

	@Transactional
	public void addTemplate(WorkFlowTemplate template, List<WorkFlowNode> nodes)
	{
		template.setCreateDate(new Date());
		template.setUpdateDate(new Date());
		template.setDomain(EnvParameter.get().getDomain());
		String templateId = baseDao.add(template);
		for (WorkFlowNode node : nodes)
		{
			node.setTemplateId(templateId);
		}
		workFlowNodeService.addOrModifyAllWorkFlowNode(nodes);
	}

	@Transactional
	public void modifyTemplate(WorkFlowTemplate template, List<WorkFlowNode> nodes)
	{
		WorkFlowTemplate dbTemplate = baseDao.query(WorkFlowTemplate.class, template.getId());
		dbTemplate.setObjectType(template.getObjectType());
		dbTemplate.setSubType(template.getSubType());
		dbTemplate.setName(template.getName());
		dbTemplate.setUpdateDate(new Date());
		baseDao.modify(dbTemplate);
		for (WorkFlowNode node : nodes)
		{
			node.setTemplateId(dbTemplate.getId());
		}
		//当删除节点时候，需要把无用的节点删除
		List<String> useIds = new ArrayList<>();
		useIds.add("empty");
		for (WorkFlowNode n : nodes)
		{
			if (StringUtils.isNotBlank(n.getId()))
				useIds.add(n.getId());
		}
		Map map=new HashMap<>();
		map.put("useIds", useIds);
		map.put("templateId", dbTemplate.getId());
		baseDao.execute("delete from WorkFlowNode where id not in :useIds and templateId=:templateId", map);
		workFlowNodeService.addOrModifyAllWorkFlowNode(nodes);
	}

	@Transactional
	public void deleteTemplateById(String templateId)
	{
		workFlowNodeService.deleteAllWorkFlowNodeByTemplateId(templateId);
		baseDao.delete(WorkFlowTemplate.class, templateId);
	}

	public WorkFlowTemplate queryTemplateByType(String objectType,String subType)
	{
		return (WorkFlowTemplate) baseDao
				.queryUnique("from WorkFlowTemplate t where t.objectType=? and t.subType=? and t.domain=?", objectType,
						subType, EnvParameter.get().getDomain());
	}
}
