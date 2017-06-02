package com.goldskyer.gmxx.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.common.dtos.CreateTaskDto;
import com.goldskyer.gmxx.common.entities.MessageText;
import com.goldskyer.gmxx.common.entities.WorkFlowData;
import com.goldskyer.gmxx.common.entities.WorkFlowNode;
import com.goldskyer.gmxx.common.entities.WorkFlowTask;
import com.goldskyer.gmxx.common.entities.WorkFlowTemplate;
import com.goldskyer.gmxx.common.enums.MessageType;
import com.goldskyer.gmxx.common.enums.WorkFlowAuditEvent;
import com.goldskyer.gmxx.common.service.AttachmentService;
import com.goldskyer.gmxx.common.service.MessageService;
import com.goldskyer.gmxx.common.service.WorkFlowNodeService;
import com.goldskyer.gmxx.common.service.WorkFlowService;
import com.goldskyer.gmxx.workflow.vo.AuditVo;
import com.goldskyer.gmxx.workflow.vo.WorkflowDataVo;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })

public class WorkFlowServiceImpl implements WorkFlowService
{
	private static final Log log = LogFactory.getLog(WorkFlowServiceImpl.class);
	@Autowired
	private SimpleCacheManager cacheManager;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AclService aclService;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private WorkFlowNodeService workFlowNodeService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private MessageService messageService;

	public WorkFlowTask queryTaskByObjectId(String objectId)
	{
		return (WorkFlowTask) baseDao.queryUnique("from WorkFlowTask where objectId=?", objectId);
	}

	@Override
	public List<WorkFlowTask> getMyTasks(String accountId)
	{
		Map map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("roleIds", getMyRangeNodeIds(accountId));
		List<String> nodeIds = baseDao
				.query("select id from  WorkFlowNode  where accountId=:accountId or roleId in :roleIds and isEnd=false",
						map);
		Map paramMap = new HashMap<>();
		paramMap.put("nodeIds", nodeIds);
		return baseDao.query("from WorkFlowTask  where workFlowNodeNodeId in :nodeIds order by createDate desc",
				paramMap);
	}

	/**
	 * 我的任务
	 * 申请人，申请类型，子类型，申请时间，当前状态
	 * @param accountId
	 * @return
	 */
	public List<String> getMyRangeNodeIds(String accountId)
	{
		List<Role> roles = aclService.queryRolesByUser(accountId);
		List<String> roleIds = new ArrayList<String>();
		for (Role r : roles)
		{
			roleIds.add(r.getId());
		}
		roleIds.add("empty");
		Map map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("roleIds", roleIds);
		return baseDao
				.query("select id from  WorkFlowNode  where (accountId = :accountId or role in (:roleIds) )", map);
	}

	@Override
	public List<WorkFlowTask> getMyTasks(String accountId, String objectType)
	{
		Map map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("roleIds", getMyRangeNodeIds(accountId));
		List<String> nodeIds = baseDao.query(
				"select id from  WorkFlowNode  where (accountId = :accountId or role in :roleIds)",
				map);
		Map paramMap = new HashMap<>();
		nodeIds.add("empty");
		paramMap.put("nodeIds", nodeIds);
		paramMap.put("objectType", objectType);
		return baseDao.query(
				"from WorkFlowTask  where objectType=:objectType and nodeId in :nodeIds order by createDate desc",
				paramMap);
	}

	/**
	 * 判断是否有该流程的操作权限
	 * @param task
	 * @param accountId
	 * @return
	 */
	public boolean ifCanOperationTask(WorkFlowTask task, String accountId)
	{
		if (task.isFinished())
		{
			return false; //流程结束，操作关闭
		}
		WorkFlowNode node = baseDao.query(WorkFlowNode.class, task.getNodeId());
		if (node != null && (accountId.equals(node.getAccountId()) || aclService.ifHasRole(accountId, node.getRole())))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 执行审核操作
	 * 如果发表评论的话，fromStatus和toStatus和审核通过一样，只是task中的状态无任何变化
	 */
	@Transactional
	public void signal(AuditVo auditVo)
	{
		Account account = accountService.getAccountById(auditVo.getAccountId());
		// TODO Auto-generated method stub
		WorkFlowTask task = baseDao.query(WorkFlowTask.class, auditVo.getTaskId());
		WorkFlowNode node = baseDao.query(WorkFlowNode.class, task.getNodeId());
		WorkFlowNode nextNode = workFlowNodeService.queryNextNode(node);
		if (ifCanOperationTask(task, auditVo.getAccountId()) || auditVo.getEventType() == WorkFlowAuditEvent.REVOKE
				|| auditVo.getEventType() == WorkFlowAuditEvent.COMMENT)
		{

			//判断能否回收，已结束的流程不能回收
			if (auditVo.getEventType() == WorkFlowAuditEvent.REVOKE && task.isFinished())
			{
				throw new BusinessException("已经结束的流程无法回收");
			}
			if (auditVo.getEventType() == WorkFlowAuditEvent.APPROVE
					|| auditVo.getEventType() == WorkFlowAuditEvent.CREATE)//节点审核通过
			{
				task.setNodeId(node.getNextId());
				task.setUpdateDate(new Date());
				task.setFinished(nextNode == null);
				if (task.isFinished())
				{
					task.setStatus("审核通过");
					if (task.getObjectType().equals("内容审核"))
					{
						final Blog blog = baseDao.query(Blog.class, task.getObjectId());
						blog.setAuditStatus("审核通过");
						blog.setNeedLogin(false);//SPE:审核后的内容都是对外开放的。 
						baseDao.modify(blog);
						cacheManager.getCache("blog").clear();//刷新blog的缓存
						new Thread(new Runnable() {
							
							@Override
							public void run()
							{
								try{
								MessageText messageText = new MessageText();
								messageText.setType(MessageType.系统消息);
								messageText.setText("用户 " + blog.getAuthor() + "发布 " + blog.getTitle()
										+ ",<a target='_blank' href='/web/spring/blog/view.htm?id=" + blog.getId() + "' >点此查看</a>");
								messageText.setTitle("栏目 " + blog.getType()
										+ "新发布 <a target='_blank' href='/web/spring/blog/view.htm?id=" + blog.getId()
										+ "' >" + blog.getTitle() + "</a>");
									List<String> toAccountIds = accountService
											.queryAllValidAccountIds(blog.getDomain());
								messageText.setBlogId(blog.getId());
								messageService.sendMessage(messageText, blog.getAccountId(), toAccountIds);
								}
								catch (Exception e)
								{
									log.fatal("发送消息失败" + e.getMessage(), e);
								}
							}
						}).start();
						
					}
				}
				else
				{
					if (task.getObjectType().equals("内容审核"))
					{
						Blog blog = baseDao.query(Blog.class, task.getObjectId());
						blog.setAuditStatus(nextNode.getStatus());
						baseDao.modify(blog);
					}
					task.setStatus(nextNode.getStatus());
				}
				baseDao.modify(task);
			}
			else if (auditVo.getEventType() == WorkFlowAuditEvent.REJECT
					|| auditVo.getEventType() == WorkFlowAuditEvent.REVOKE)
			{
				task.setFinished(true);
				task.setNodeId(null);
				if (auditVo.getEventType() == WorkFlowAuditEvent.REJECT)
				{
					if (task.getObjectType().equals("内容审核"))
					{
						Blog blog = baseDao.query(Blog.class, task.getObjectId());
						blog.setAuditStatus("已拒绝");
						baseDao.modify(blog);
					}
					task.setStatus("已拒绝");
				}
				else if (auditVo.getEventType() == WorkFlowAuditEvent.REVOKE)
				{
					if (task.getObjectType().equals("内容审核"))
					{
						Blog blog = baseDao.query(Blog.class, task.getObjectId());
						blog.setAuditStatus("已回收");
						baseDao.modify(blog);
					}
					task.setStatus("已回收");
				}
				task.setUpdateDate(new Date());
				baseDao.modify(task);
			}
			//审核数据
			WorkFlowData data = new WorkFlowData();
			data.setEventType(auditVo.getEventType());
			if (auditVo.getEventType() == WorkFlowAuditEvent.APPROVE)
			{
				data.setEventNote(account.getNickname() + "同意了审核");
			}
			else if (auditVo.getEventType() == WorkFlowAuditEvent.REJECT)
			{
				data.setEventNote(account.getNickname() + "拒绝了审核");
			}
			else if (auditVo.getEventType() == WorkFlowAuditEvent.CREATE)
			{
				data.setEventNote(account.getNickname() + "新建了流程");
			}
			else if (auditVo.getEventType() == WorkFlowAuditEvent.REVOKE)
			{
				data.setEventNote(account.getNickname() + "回收了流程");
			}
			else if (auditVo.getEventType() == WorkFlowAuditEvent.COMMENT)
			{
				data.setEventNote(account.getNickname() + "发布了一条评论");
			}
			data.setNote(auditVo.getNote());
			data.setComment(auditVo.getComment());
			data.setPicUrl(auditVo.getPicUrl());
			data.setAccountId(auditVo.getAccountId());
			data.setFromStatus(node.getStatus());
			if (nextNode != null)
			{
				data.setToStatus(nextNode.getStatus());
			}
			data.setObjectId(task.getObjectId());
			data.setObjectType(task.getObjectType());
			baseDao.add(data);//添加审核数据
			if (!StringUtils.equalsIgnoreCase(auditVo.getAccountId(), task.getApplyAccountId()))//非自己账号则发送站内信
			{
				MessageText text = new MessageText();
				text.setTitle(data.getEventNote());
				text.setText("流程事件：" + data.getEventNote() + ";评论或意见：" + data.getNote());
				text.setType(MessageType.流程消息);
				messageService.sendMessage(text, auditVo.getAccountId(), task.getApplyAccountId());
			}
		}
		else
		{
			throw new BusinessException("当前用户没权限处理该任务");
		}
	}

	public WorkFlowTemplate queryTemplateByObjectType(String objecType, String subType, String domain)
	{
		if (StringUtils.isBlank(subType))
		{
			return (WorkFlowTemplate) baseDao.queryUnique(
					"from WorkFlowTemplate where objectType=? and domain=?", objecType, domain);
		}
		else
		{
			return (WorkFlowTemplate) baseDao.queryUnique(
					"from WorkFlowTemplate where objectType=? and subType=? and domain=?", objecType, subType, domain);
		}

	}


	/**
	 * 
	 */
	@Transactional
	public void createTask(CreateTaskDto createTaskDto)
	{
		Account account = accountService.getAccountById(createTaskDto.getAccountId());
		WorkFlowTemplate template = queryTemplateByObjectType(createTaskDto.getObjectType(), createTaskDto.getSubType(),
				EnvParameter.get().getDomain());
		if (template == null)
			throw new BusinessException("不存在流程对应的模板：templateName" + createTaskDto.getObjectType());
		WorkFlowNode startNode = workFlowNodeService.queryStartNode(template.getId());
		if (startNode == null)
			throw new BusinessException("流程模板未配置起始节点");
		//		WorkFlowNode nextNode=workFlowNodeService.queryNextNode(workFlowNode);
		//		//
		//		WorkFlowData data = new WorkFlowData();
		//		data.setAccountId(accountId);
		//		data.setFromStatus(workFlowNode.getStatus());
		//		data.setToStatus(nextNode.getStatus());
		//		data.setObjectId(blogId);
		//		data.setObjectType(objectType);
		//		baseDao.add(data);
		//创建任务
		WorkFlowTask task = new WorkFlowTask();
		task.setTaskName(createTaskDto.getTaskName());
		task.setApplyAccountId(account.getId());
		task.setApplyName(account.getNickname());
		task.setObjectId(createTaskDto.getObjectId());
		task.setObjectType(createTaskDto.getObjectType());
		task.setSubType(createTaskDto.getSubType());
		task.setFinished(false);
		task.setCreateDate(new Date());
		task.setNodeId(startNode.getId());
		baseDao.add(task);
		//构建audit
		AuditVo auditVo = new AuditVo();
		auditVo.setAccountId(createTaskDto.getAccountId());
		auditVo.setTaskId(task.getId());
		auditVo.setEventType(WorkFlowAuditEvent.CREATE);
		signal(auditVo);
		
	}

	@Override
	public List<WorkFlowData> queryWorkFlowDataHis(String objectId, String objectType)
	{
		List<WorkFlowData> datas = baseDao.query("from WorkFlowData where objectId=? order by createDate,id", objectId);
		return datas;
	}

	public List<WorkflowDataVo> queryWorkFlowDataVo(String objectId)
	{
		List<WorkflowDataVo> vos = new ArrayList<>();
		List<Object[]> datas = baseDao.query(
				"select a.accountId,a.createDate ,a.eventType ,a.note,b.nickname,a.picUrl,a.eventNote,t.taskName from WorkFlowData a,Account b,WorkFlowTask t where a.accountId=b.id and t.objectId=a.objectId and a.objectId=? order by a.createDate,a.id",
				objectId);
		for (Object[] data : datas)
		{
			WorkflowDataVo vo = new WorkflowDataVo();
			vo.setAccountId(String.valueOf(data[0]));
			vo.setCreateDate((Date) data[1]);
			if (data[2] != null)
				vo.setEventType(data[2].toString());
			if (data[3] != null)
				vo.setNote(data[3].toString());
			if (data[4] != null)
				vo.setNickName(data[4].toString());
			if (data[5] != null)
			{
				vo.setPicAttachs(attachmentService.queryImagesAttachmentsByPicUrl(data[5].toString()));
				vo.setOtherAttachs(attachmentService.queryOtherAttachmentsByPicUrl(data[5].toString()));
			}
			if (data[6] != null)
				vo.setEventNote(data[6].toString());
			if (data[7] != null)
				vo.setTaskName(data[7].toString());
			vos.add(vo);
		}
		return vos;
	}




}
