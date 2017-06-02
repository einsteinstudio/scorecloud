package com.goldskyer.gmxx.manager.controllers.workflow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.entities.Blog;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.entities.WorkFlowTask;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;
import com.goldskyer.gmxx.manager.controllers.BaseManagerController;
import com.goldskyer.gmxx.workflow.vo.WorkflowDataVo;

/**
 * 流程模板管理
 * @author jintianfan
 *
 */
@Controller("managerBlogFlowController")
@RequestMapping("/manager/workflow/")
@SuppressWarnings(
{ "rawtypes", "unchecked", "deprecation" })
public class BlogFlowController extends BaseManagerController
{


	@RequestMapping("/blogflow/view.htm")
	public ModelAndView view(HttpServletRequest request,@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		Blog flow = baseDao.query(Blog.class, id);
		mv.addObject("flow", flow);
		mv.addObject("innerPage", "workflow/blogflow_view");
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
	@RequestMapping("/blogflow/list.htm")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/blogflow_list");
		return mv;
	}


	/**
	 * 我的申请列表
	 * @param dataTableReqDto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/blogflow/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto
				.setSql("select a.title,a.author,a.type,t.status,a.createDate,a.id from Blog a,WorkFlowTask t  where a.id=t.objectId  and a.accountId=:accountId ");
		dataTableReqDto.setOrderBy("order by a.createDate desc ,a.id");
		dataTableReqDto.setParam("accountId", getCurrentAccountId());
		dataTableReqDto.setSearchField("a.title,a.type,t.status");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/blogflow/history/list.htm")
	@RoleControl("BLOG_AUDIT")
	public ModelAndView myHistory(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/blogflow/his_list");
		return mv;
	}

	@RequestMapping(value = "/blogflow/history/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("BLOG_AUDIT")
	public Object myhistoryListData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select t.taskName,t.applyName,t.subType,d.eventType,t.status,t.createDate,t.id from WorkFlowData d,WorkFlowTask t where d.objectId=t.objectId and d.accountId=:accountId and d.objectType='内容审核' ");
		dataTableReqDto.setOrderBy("order by d.createDate desc,t.id");
		dataTableReqDto.setParam("accountId", getCurrentAccountId());
		dataTableReqDto.setSearchField("t.taskName,t.applyName,t.subType");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}


}