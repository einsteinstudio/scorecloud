package com.goldskyer.gmxx.common.service;

import java.util.List;

import com.goldskyer.gmxx.common.dtos.CreateTaskDto;
import com.goldskyer.gmxx.common.entities.WorkFlowData;
import com.goldskyer.gmxx.common.entities.WorkFlowTask;
import com.goldskyer.gmxx.workflow.vo.AuditVo;
import com.goldskyer.gmxx.workflow.vo.WorkflowDataVo;

public interface WorkFlowService
{

	public WorkFlowTask queryTaskByObjectId(String objectId);
	public List<String> getMyRangeNodeIds(String accountId);
	/**
	 * 获取我的任务列表
	 * @param accountId
	 * @return
	 */
	public List<WorkFlowTask> getMyTasks(String accountId);

	/**
	 * 获取当前我的列表
	 * @param accountId
	 * @param status
	 * @return
	 */
	public List<WorkFlowTask> getMyTasks(String accountId, String objectType);//状态

	/**
	 * 执行审核操作
	 */
	public void signal(AuditVo auditVo);

	/**
	 * 获取对象的审核记录
	 * @param objectId
	 * @param objectType
	 * @return
	 */
	public List<WorkFlowData> queryWorkFlowDataHis(String objectId, String objectType);

	/**
	 * 审核资源
	 * @param accountId
	 * @param blogId
	 */
	public void createTask(CreateTaskDto createTaskDto);

	/**
	 * 判断是否有该流程的操作权限
	 * @param task
	 * @param accountId
	 * @return
	 */
	public boolean ifCanOperationTask(WorkFlowTask task, String accountId);

	public List<WorkflowDataVo> queryWorkFlowDataVo(String objectId);
}
