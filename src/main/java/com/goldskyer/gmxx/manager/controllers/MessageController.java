package com.goldskyer.gmxx.manager.controllers;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.entities.MessageText;
import com.goldskyer.gmxx.common.enums.MessageType;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;

@Controller("managerMessageController")
@RequestMapping("/manager/message")
public class MessageController extends BaseManagerController
{
	@RequestMapping("/list.htm")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "message/message_list");
		return mv;
	}

	@RequestMapping(value = "/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select concat(c.username,c.nickname),b.title,b.type,a.status,b.createDate,a.id,b.text from Message a,MessageText b,Account c where a.textId=b.id and a.sendId=c.id and a.recvId=:accountId");
		dataTableReqDto.setOrderBy("order by b.createDate desc,b.id ");
		dataTableReqDto.setSearchField("c.nickname,c.username,b.title,b.type,a.status");
		dataTableReqDto.setParam("accountId", getCurrentAccountId());
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping(value = "/list_send_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object list_send_data(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select concat(c.username,c.nickname),b.title,b.type,a.status,b.createDate,a.id,b.text from Message a,MessageText b,Account c where a.textId=b.id and a.recvId=c.id and a.sendId=:accountId");
		dataTableReqDto.setOrderBy("order by b.createDate desc,b.id ");
		dataTableReqDto.setSearchField("c.nickname,c.username,b.title,b.type,a.status");
		dataTableReqDto.setParam("accountId", getCurrentAccountId());
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping(value = "/read.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonData read(HttpServletRequest request, @RequestParam String messageId)
	{
		messageService.readMessage(messageId, getCurrentAccountId());
		return JsonData.success();
	}

	@RequestMapping("/toAdd.htm")
	@RoleControl("MSG_ADD")
	public ModelAndView toAdd(HttpServletRequest request, @RequestParam(required = false) String pId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "message/message_toAdd");
		return mv;
	}

	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("MSG_ADD")
	public JsonData addJson(HttpServletRequest request, MessageText text)
	{
		String recvIds = request.getParameter("receivers");
		String[] reIds = recvIds.split(",");
		text.setType(MessageType.用户消息);
		messageService.sendMessage(text, getCurrentAccountId(), Arrays.asList(reIds));
		return JsonData.success();
	}
}
