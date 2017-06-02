package com.goldskyer.gmxx.manager.controllers;


import javax.servlet.http.HttpServletRequest;

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
import com.goldskyer.core.entities.Cms;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.CmsService;
import com.goldskyer.core.service.CommentService;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;
import com.goldskyer.gmxx.common.service.LiveVideoService;
import com.goldskyer.gmxx.common.service.WorkFlowService;

/**
 * 流程模板管理
 * @author jintianfan
 *
 */
@Controller("managerCmsCOntroller")
@RequestMapping("/manager/cms")
@SuppressWarnings(
{ "rawtypes", "unchecked", "deprecation" })
public class CmsController extends BaseManagerController
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




	@RequestMapping("/list.htm")
	@RoleControl("CMS_VIEW")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "cms/cms_list");
		return mv;
	}


	@RequestMapping(value = "/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("CMS_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto
				.setSql("select id,description,n18,content,gId from Cms  where domain=:domain");
		dataTableReqDto.setOrderBy("order by id ,n18");
		dataTableReqDto.setParam("domain", EnvParameter.get().getDomain());
		dataTableReqDto.setSearchField("description,n18,content");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/delete.htm")
	@RoleControl("CMS_DELETE")
	public ModelAndView delete(HttpServletRequest request)
	{
		String id = request.getParameter("id");
		baseDao.delete(Cms.class, id);
		return new ModelAndView("redirect:list.htm");
	}

	@RequestMapping("/toAdd.htm")
	@RoleControl("CMS_ADD")
	public ModelAndView toAdd(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "cms/cms_toAdd");
		return mv;
	}

	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("CMS_ADD")
	public JsonData addJson(HttpServletRequest request, Cms cms)
	{
		cms.setDomain(EnvParameter.get().getDomain());
		baseDao.add(cms);
		cmsService.reloadCms();
		return JsonData.success();
	}

	@RequestMapping("/toModify.htm")
	@RoleControl("CMS_EDIT")
	public ModelAndView toModify(HttpServletRequest request, @RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "cms/cms_toModify");
		Cms cms = baseDao.query(Cms.class, id);
		mv.addObject("cs", cms);
		return mv;
	}

	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("CMS_EDIT")
	public JsonData modify(HttpServletRequest request, Cms cms)
	{
		Cms dbCms = baseDao.query(Cms.class, cms.getgId());
		dbCms.setId(cms.getId());
		dbCms.setDescription(cms.getDescription());
		dbCms.setN18(cms.getN18());
		dbCms.setContent(cms.getContent());
		baseDao.modify(dbCms);
		cmsService.reloadCms();
		return JsonData.success();
	}

}