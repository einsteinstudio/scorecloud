package com.goldskyer.gmxx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.goldskyer.gmxx.common.entities.WorkFlowNode;
import com.goldskyer.gmxx.common.entities.WorkFlowTask;
import com.goldskyer.gmxx.common.entities.WorkFlowTemplate;
import com.goldskyer.gmxx.common.enums.NodeType;
import com.goldskyer.gmxx.common.service.WorkFlowNodeService;
import com.goldskyer.gmxx.common.service.WorkFlowService;

/**
 * 
 * @author jintianfan
 *
 */
public class WorkFlowServiceTest extends BaseTest
{
	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private WorkFlowNodeService workFlowNodeService;
	@Test
	@Rollback(false)
	public void testAddTemplate()
	{
		WorkFlowTemplate template = new WorkFlowTemplate();
		template.setAuthor("jintainfan");
		template.setCreateDate(new Date());
		template.setUpdateDate(new Date());
		template.setName("测试模板1");
		template.setDomain("test.goldskyer.com");
		baseDao.add(template);
	}

	@Test
	@Rollback(false)
	public void addNode()
	{
		WorkFlowNode node = new WorkFlowNode();
		node.setAccountId("master");
		node.setNextId(null);
		node.setNodeType(NodeType.END);
		node.setStatus("结束");
		node.setTemplateId("8a53b78255d943bb0155d943cae00000");
		baseDao.add(node);
	}

	/**
	 * 0000000054f880cb0154fcce30390098
	 * 
	 */
	@Test
	@Rollback(false)
	public void createTask()
	{
		String blogId = "0000000054f880cb0154fcce30390098";
		//workFlowService.createTask("jin", blogId);
	}

	//	@Test
	//	@Rollback(false)
	//	public void sinal()
	//	{
	//		workFlowService.signal("master", "8a72d8bd55da3f450155da3f54320000", true, "审核通过");
	//	}

	@Test
	@Rollback(false)
	public void queryTask()
	{
		List<WorkFlowTask> tasks = workFlowService.getMyTasks("master", "课堂直播");
		for (WorkFlowTask task : tasks)
		{
			System.out.println(task);
		}
	}

	@Test
	@Rollback(false)
	public void generateData()
	{
		for(int i=0;i<17;i++)
		{
			WorkFlowTemplate template=new WorkFlowTemplate();
			template.setAuthor("jin"+i);
			template.setName("name_"+i);
			template.setCreateDate(new Date());
			template.setObjectType("校内新闻");
			template.setUpdateDate(new Date());
			template.setDomain("smart.goldskyer.com");
			baseDao.add(template);
		}
	}

	@Test
	public void testQuery()
	{
		workFlowService.queryWorkFlowDataVo("40289f7856184da2015618bd53e8001f");
	}

	@Test
	@Rollback(false)
	public void addNodes()
	{
		List<WorkFlowNode> nodes = new ArrayList<>();
		WorkFlowNode n1 = new WorkFlowNode();
		n1.setStatus("创建");
		n1.setAccountId("x1@163.com");
		n1.setTemplateId("jin");
		WorkFlowNode n2 = new WorkFlowNode();
		n2.setStatus("校长审核");
		n2.setAccountId("x2@163.com");
		n2.setTemplateId("jin");
		nodes.add(n1);
		nodes.add(n2);
		workFlowNodeService.addOrModifyAllWorkFlowNode(nodes);
	}
}
