package com.goldskyer.gmxx.manager.controllers;


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
import com.goldskyer.core.entities.Ini;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.CommentService;
import com.goldskyer.core.service.IniService;
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
@Controller("managerIniCOntroller")
@RequestMapping("/manager/ini")
@SuppressWarnings(
{ "rawtypes", "unchecked", "deprecation" })
public class IniController extends BaseManagerController
{
	@Autowired
	protected BaseDao baseDao;
	@Autowired
	protected IniService iniService;

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
	@RoleControl("INI_VIEW")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "ini/ini_list");
		return mv;
	}


	@RequestMapping(value = "/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("INI_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto
				.setSql("select name,nameCn,description,value,changeable,id from Ini  where domain=:domain");
		dataTableReqDto.setOrderBy("order by name ,id");
		dataTableReqDto.setParam("domain", EnvParameter.get().getDomain());
		dataTableReqDto.setSearchField("name,nameCn,description,value");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/delete.htm")
	@RoleControl("INI_DELETE")
	public ModelAndView delete(HttpServletRequest request)
	{
		String id = request.getParameter("id");
		baseDao.delete(Ini.class, id);
		return new ModelAndView("redirect:list.htm");
	}

	@RequestMapping("/toAdd.htm")
	@RoleControl("INI_ADD")
	public ModelAndView toAdd(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "ini/ini_toAdd");
		return mv;
	}

	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("INI_ADD")
	public JsonData addJson(HttpServletRequest request, Ini ini)
	{
		ini.setChangeable(StringUtils.equals(request.getParameter("changeable"), "on"));
		ini.setDomain(EnvParameter.get().getDomain());
		baseDao.add(ini);
		iniService.reload();
		return JsonData.success();
	}

	@RequestMapping("/toModify.htm")
	@RoleControl("INI_EDIT")
	public ModelAndView toModify(HttpServletRequest request, @RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "ini/ini_toModify");
		Ini ini = baseDao.query(Ini.class, id);
		mv.addObject("ini", ini);
		return mv;
	}

	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("INI_EDIT")
	public JsonData modify(HttpServletRequest request, Ini ini)
	{
		ini.setChangeable(StringUtils.equals(request.getParameter("changeable"), "on"));
		Ini dbIni = baseDao.query(Ini.class, ini.getId());
		dbIni.setChangeable(ini.isChangeable());
		dbIni.setName(ini.getName());
		dbIni.setDescription(ini.getDescription());
		dbIni.setNameCn(ini.getNameCn());
		dbIni.setValue(ini.getValue());
		baseDao.modify(dbIni);
		iniService.reload();
		return JsonData.success();
	}

}