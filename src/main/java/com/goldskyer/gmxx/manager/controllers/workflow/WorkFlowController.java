package com.goldskyer.gmxx.manager.controllers.workflow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.CmsService;
import com.goldskyer.core.service.CommentService;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.entities.WorkFlowTask;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;
import com.goldskyer.gmxx.common.service.LiveVideoService;
import com.goldskyer.gmxx.common.service.WorkFlowService;
import com.goldskyer.gmxx.manager.controllers.BaseManagerController;
import com.goldskyer.gmxx.workflow.constants.WorkflowConstant;
import com.goldskyer.gmxx.workflow.vo.AuditVo;

/**
 * 流程模板管理
 * @author jintianfan
 *
 */
@Controller("managerWorkFlowController")
@RequestMapping("/manager/workflow")
@SuppressWarnings(
{ "rawtypes", "unchecked", "deprecation" })
public class WorkFlowController extends BaseManagerController
{
	@Autowired
	protected BaseDao baseDao;

	@Autowired
	protected CmsService cmsService;

	@Autowired
	@Qualifier("blogService")
	protected BlogService blogService;

	@Autowired
	protected CommentService commentService;
	@Autowired
	protected AccountService accountService;

	@Autowired
	protected WorkFlowService workFlowService;

	@Autowired
	@Qualifier("cachedMenuService")
	protected CachedMenuService cachedMenuService;

	@Autowired
	protected LiveVideoService liveVideoService;

	@RequestMapping("/apply_main.htm")
	@RoleControl("WORKFLOW_APPLY")
	public ModelAndView applyMain(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/apply_main");
		return mv;
	}


	/**
	 * 自己研发的流程列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.htm")
	@RoleControl("WORKFLOW_VIEW")
	public ModelAndView list(HttpServletRequest request)
	{
		String objectType = request.getParameter("objectType");
		if (!objectType.equals("内容审核"))
		{

		}
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/cms_list");
		return mv;
	}

	@RequestMapping(value = "/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("WORKFLOW_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql("select id,description,n18,content,gId from Cms  where domain=:domain");
		dataTableReqDto.setOrderBy("order by id ,n18");
		dataTableReqDto.setParam("domain", EnvParameter.get().getDomain());
		dataTableReqDto.setSearchField("description,n18,content");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/mytask/list.htm")
	@RoleControl("WORKFLOW_VIEW")
	public ModelAndView myTask(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/mytask");
		return mv;
	}

	@RequestMapping(value = "/mytask/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("WORKFLOW_VIEW")
	public Object mytaskListData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select t.applyName,t.objectType,t.status,t.createDate,t.id from WorkFlowTask t,WorkFlowNode n where t.objectType!='内容审核' and  t.nodeId=n.id and t.nodeId in ( :nodeIds ) and t.finished=0 ");
		dataTableReqDto.setOrderBy("order by t.createDate desc,t.id");
		dataTableReqDto.setSearchField("t.applyName,t.objectType,t.status");
		dataTableReqDto.setParam("nodeIds", workFlowService.getMyRangeNodeIds(getCurrentAccountId()));
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/myblog/list.htm")
	@RoleControl("BLOG_AUDIT")
	public ModelAndView myblog(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/my_blog_task");
		return mv;
	}

	@RequestMapping(value = "/myblog/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("BLOG_AUDIT")
	public Object myblogListdata(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select t.applyName,t.subType,t.status,t.createDate,t.id from WorkFlowTask t,WorkFlowNode n where t.objectType='内容审核' and  t.nodeId=n.id and t.nodeId in ( :nodeIds ) and t.finished=0 ");
		dataTableReqDto.setOrderBy("order by t.createDate desc,t.id");
		dataTableReqDto.setSearchField("t.applyName,t.subType,t.status");
		dataTableReqDto.setParam("nodeIds", workFlowService.getMyRangeNodeIds(getCurrentAccountId()));
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	/**
	 * 我的历史审核记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/myhistory/list.htm")
	@RoleControl("WORKFLOW_AUDIT")
	public ModelAndView myHistory(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/my_history");
		return mv;
	}

	@RequestMapping(value = "/myhistory/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("WORKFLOW_AUDIT")
	public Object myhistoryListData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select t.taskName,t.applyName,t.objectType,d.eventType,t.status,t.createDate,t.id from WorkFlowData d,WorkFlowTask t where d.objectId=t.objectId and d.accountId=:accountId and d.objectType!='内容审核' ");
		dataTableReqDto.setOrderBy("order by d.createDate desc,t.id");
		dataTableReqDto.setParam("accountId", getCurrentAccountId());
		dataTableReqDto.setSearchField("t.taskName,t.applyAccountId,t.applyName,t.objectType");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	/**
	 * 待我审批的流程详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/mytask/view.htm")
	public ModelAndView myTaskViewk(HttpServletRequest request, @RequestParam String id)
	{
		WorkFlowTask task = baseDao.query(WorkFlowTask.class, id);
		if (task == null)
			throw new BusinessException("找不到对应的流程详情");
		if (task.getObjectType().equals("内容审核"))
		{
			return new ModelAndView("redirect:/manager/workflow/blogflow/view.htm?id=" + task.getObjectId());
		}
		if (task.getObjectType().equals(WorkflowConstant.PURCHASE_FLOW))
		{
			return new ModelAndView("redirect:/manager/workflow/purchaseflow/view.htm?id=" + task.getObjectId());
		}
		else if (task.getObjectType().equals(WorkflowConstant.REPAIRE_FLOW))
		{
			return new ModelAndView("redirect:/manager/workflow/repaireflow/view.htm?id=" + task.getObjectId());
		}
		else if (task.getObjectType().equals(WorkflowConstant.USECAR_FLOW))
		{
			return new ModelAndView("redirect:/manager/workflow/usecarflow/view.htm?id=" + task.getObjectId());
		}
		else
		{
			throw new BusinessException("找不到该类型的流程详情页面。流程类型：" + task.getObjectType());
		}
	}


	/**
	 * 审批页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/audit/view.htm")
	public ModelAndView auditView(HttpServletRequest request, @RequestParam String id, @RequestParam String eventType)
	{
		ModelAndView mv = new ModelAndView("/manager/dialog_templ");
		mv.addObject("innerPage", "workflow/dialog/audit_view");
		WorkFlowTask task = workFlowService.queryTaskByObjectId(id);
		mv.addObject("task", task);
		mv.addObject("eventType", eventType);
		return mv;
	}

	@RequestMapping(value = "/audit/submit.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonData doAudit(HttpServletRequest request, AuditVo auditVo)
	{
		auditVo.setAccountId(getCurrentAccountId());
		workFlowService.signal(auditVo);
		return JsonData.success();
	}

}