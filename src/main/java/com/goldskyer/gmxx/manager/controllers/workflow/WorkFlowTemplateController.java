package com.goldskyer.gmxx.manager.controllers.workflow;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.CommentService;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.entities.WorkFlowNode;
import com.goldskyer.gmxx.common.entities.WorkFlowTemplate;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;
import com.goldskyer.gmxx.common.service.LiveVideoService;
import com.goldskyer.gmxx.common.service.WorkFlowNodeService;
import com.goldskyer.gmxx.common.service.WorkFlowService;
import com.goldskyer.gmxx.manager.controllers.BaseManagerController;
import com.goldskyer.gmxx.workflow.service.WorkflowTemplateService;

/**
 * 流程模板管理
 * @author jintianfan
 *
 */
@Controller
@RequestMapping("/manager/workflow/template")
@SuppressWarnings(
{ "rawtypes", "unchecked", "deprecation" })
public class WorkFlowTemplateController extends BaseManagerController
{
	@Autowired
	protected BaseDao baseDao;

	@Autowired
	protected WorkflowTemplateService workflowTemplateService;
	@Autowired
	protected WorkFlowNodeService workFlowNodeService;
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

	//@Autowired
	//@Qualifier("xdcmsBlogService")
	//protected BlogService xdBlogService;



	@RequestMapping("/list.htm")
	@RoleControl("WORKFLOW_TEMPLATE_VIEW")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/template_list");
		return mv;
	}


	@RequestMapping(value = "/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("WORKFLOW_TEMPLATE_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto
				.setSql("select a.name,b.nickname,a.createDate,a.updateDate,a.id from WorkFlowTemplate  a ,Account b where a.author=b.id and  a.domain=:domain");
		dataTableReqDto.setOrderBy("order by a.createDate desc,a.id");
		dataTableReqDto.setParam("domain", EnvParameter.get().getDomain());
		dataTableReqDto.setSearchField("a.name,a.author");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/toAdd.htm")
	@RoleControl("WORKFLOW_TEMPLATE_ADD")
	public ModelAndView toAdd(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/template_toAdd");
		mv.addObject("roles", aclService.queryRoles(0, 1000));
		return mv;
	}
	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("WORKFLOW_TEMPLATE_ADD")
	public JsonData addJson(HttpServletRequest request, WorkFlowTemplate template)
	{
		template.setAuthor(getCurrentAccountId());
		List<WorkFlowNode> nodes = new ArrayList<>();
		String[] statuses = request.getParameterValues("status[]");
		String[] accountIds = request.getParameterValues("accountId[]");
		String[] roles = request.getParameterValues("role[]");
		if (statuses != null & statuses.length > 0)
		{
			for (int i = 0; i < statuses.length; i++)
			{
				WorkFlowNode node = new WorkFlowNode();
				if (StringUtils.isBlank(statuses[i]) && StringUtils.isBlank(accountIds[i])
						&& StringUtils.isBlank(roles[i]))
				{
					continue;
				}
				node.setStatus(StringUtils.trimToEmpty(statuses[i]));
				node.setAccountId(null);
				node.setRole(StringUtils.trimToEmpty(roles[i]));
				nodes.add(node);
			}
		}
		workflowTemplateService.addTemplate(template, nodes);
		return JsonData.success();
	}
	
	@RequestMapping("/toModify.htm")
	@RoleControl("WORKFLOW_TEMPLATE_EDIT")
	public ModelAndView toModify(HttpServletRequest request, @RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/template_toModify");
		WorkFlowTemplate template = baseDao.query(WorkFlowTemplate.class, id);
		mv.addObject("template", template);
		List<WorkFlowNode> nodes = workFlowNodeService.queryWorkFlowNodesByTemplateId(id);
		mv.addObject("nodes", nodes);
		mv.addObject("roles", aclService.queryRoles(0, 1000));//角色列表
		return mv;
	}

	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("WORKFLOW_TEMPLATE_EDIT")
	public JsonData modify(HttpServletRequest request, WorkFlowTemplate template)
	{
		List<WorkFlowNode> nodes = new ArrayList<>();
		String[] nodeIds = request.getParameterValues("nodeId[]");
		String[] statuses = request.getParameterValues("status[]");
		String[] accountIds = request.getParameterValues("accountId[]");
		String[] roles = request.getParameterValues("role[]");
		if (statuses != null & statuses.length > 0)
		{
			for (int i = 0; i < statuses.length; i++)
			{
				WorkFlowNode node = new WorkFlowNode();
				if (StringUtils.isBlank(statuses[i]) && StringUtils.isBlank(accountIds[i])
						&& StringUtils.isBlank(roles[i]))
				{
					continue;
				}
				node.setId(nodeIds[i]);
				node.setStatus(StringUtils.trimToEmpty(statuses[i]));
				//node.setAccountId(StringUtils.trimToEmpty(accountIds[i]));
				node.setRole(StringUtils.trimToEmpty(roles[i]));
				nodes.add(node);
			}
		}
		workflowTemplateService.modifyTemplate(template, nodes);
		return JsonData.success();
	}

	@RequestMapping("/delete.htm")
	@RoleControl("WORKFLOW_TEMPLATE_DELETE")
	public ModelAndView delete(HttpServletRequest request)
	{
		String id = request.getParameter("id");
		workflowTemplateService.deleteTemplateById(id);
		return new ModelAndView("redirect:list.htm");
	}

}