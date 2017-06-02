package com.goldskyer.gmxx.manager.controllers.workflow;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.common.aop.AvoidDuplicateSubmission;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.dtos.CreateTaskDto;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.entities.WorkFlowTask;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;
import com.goldskyer.gmxx.manager.controllers.BaseManagerController;
import com.goldskyer.gmxx.workflow.entities.PurchaseDetail;
import com.goldskyer.gmxx.workflow.entities.PurchaseFlow;
import com.goldskyer.gmxx.workflow.vo.WorkflowDataVo;

/**
 * 流程模板管理
 * @author jintianfan
 *
 */
@Controller("managerPurchaseFlowController")
@RequestMapping("/manager/workflow/")
@SuppressWarnings(
{ "rawtypes", "unchecked", "deprecation" })
public class PurchaseFlowController extends BaseManagerController
{
	@RequestMapping("/purchaseflow/toApply.htm")
	@AvoidDuplicateSubmission(needSaveToken = true)
	@RoleControl("WORKFLOW_ADD")
	public ModelAndView toAdd(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/purchaseflow_toApply");
		return mv;
	}

	@RequestMapping(value = "/purchaseflow/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RoleControl("WORKFLOW_ADD")
	public JsonData addJson(HttpServletRequest request, PurchaseFlow flow)
	{
		String accountId = getCurrentAccountId();
		flow.setAccountId(accountId);
		flow.setCreateDate(new Date());
		flow.setUpdateDate(new Date());
		//封装对象
		String[] names = request.getParameterValues("name[]");
		String[] buyCounts = request.getParameterValues("buyCount[]");
		String[] norms = request.getParameterValues("norm[]");
		String[] prices = request.getParameterValues("price[]");
		if (names != null & names.length > 0)
		{
			for (int i = 0; i < names.length; i++)
			{
				PurchaseDetail detail = new PurchaseDetail();
				if (StringUtils.isBlank(names[i]) && StringUtils.isBlank(buyCounts[i]) && StringUtils.isBlank(norms[i])
						&& StringUtils.isBlank(prices[i]))
				{
					continue;
				}
				detail.setName(StringUtils.trimToEmpty(names[i]));
				detail.setNorm(StringUtils.trimToEmpty(norms[i]));
				detail.setPrice(StringUtils.trimToEmpty(prices[i]));
				detail.setPurchaseFlow(flow);
				if (StringUtils.isNotBlank(StringUtils.trimToEmpty(buyCounts[i])))
					detail.setBuyCount(Integer.valueOf(StringUtils.trimToEmpty(buyCounts[i])));
				flow.getDetails().add(detail);
			}
		}
		String flowId = baseDao.add(flow);
		CreateTaskDto createTaskDto = new CreateTaskDto();
		createTaskDto.setAccountId(getCurrentAccountId());
		createTaskDto.setObjectId(flowId);
		createTaskDto.setObjectType("采购流程");
		createTaskDto.setTaskName(getCurrentAccount().getNickname() + "提出采购申请");
		workFlowService.createTask(createTaskDto);
		return JsonData.success();
	}

	@RequestMapping("/purchaseflow/view.htm")
	@RoleControl("WORKFLOW_VIEW")
	public ModelAndView view(HttpServletRequest request,@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		PurchaseFlow flow = baseDao.query(PurchaseFlow.class, id);
		mv.addObject("flow", flow);
		mv.addObject("innerPage", "workflow/purchaseflow_view");
		//显示流程功能按钮
		WorkFlowTask task=workFlowService.queryTaskByObjectId(flow.getId());
		mv.addObject("task", task);
		mv.addObject("canOp", workFlowService.ifCanOperationTask(task, getCurrentAccountId()));
		//显示流程操作数据
		List<WorkflowDataVo> flowDatas = workFlowService.queryWorkFlowDataVo(flow.getId());
		mv.addObject("flowDatas", flowDatas);
		return mv;
	}

	/**
	 * 我的申请列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/purchaseflow/list.htm")
	@RoleControl("WORKFLOW_VIEW")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/purchaseflow_list");
		return mv;
	}


	/**
	 * 我的申请列表
	 * @param dataTableReqDto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/purchaseflow/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("WORKFLOW_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto
				.setSql("select a.department,a.deliverDate,a.type,t.status,a.createDate,a.id from PurchaseFlow a,WorkFlowTask t  where a.id=t.objectId  and a.accountId=:accountId ");
		dataTableReqDto.setOrderBy("order by a.createDate desc ,a.id");
		dataTableReqDto.setParam("accountId", getCurrentAccountId());
		dataTableReqDto.setSearchField("a.department,a.note,a.type,a.payWay,t.status");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/purchaseflow/revoke.htm")
	@RoleControl("WORKFLOW_ADD")
	public ModelAndView revoke(HttpServletRequest request)
	{
		String id = request.getParameter("id");
		PurchaseFlow flow=baseDao.query(PurchaseFlow.class,id);
		return new ModelAndView("redirect:list.htm");
	}

}