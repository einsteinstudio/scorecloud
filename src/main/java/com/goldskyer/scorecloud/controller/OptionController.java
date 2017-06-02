package com.goldskyer.scorecloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.scorecloud.dto.OptionDto;
import com.goldskyer.scorecloud.dto.OptionValueDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.freemarker.component.vo.OperationResultVo;
import com.goldskyer.scorecloud.service.OptionService;
import com.goldskyer.scorecloud.service.OptionValueService;

@Controller
@RequestMapping("/scorecloud/option")
public class OptionController extends BaseScoreCloudController
{
	@Autowired
	private OptionService optionService;

	@Autowired
	private OptionValueService optionValueService;

	@RequestMapping(value = "/toList.htm")
	@RoleControl("OPTION_VIEW")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/option/toList");
		List<OptionDto> optionDtos = optionService.queryAllOptionDtos(ScoreCloudEnv.get().getSchId());
		mv.addObject("optionDtos", optionDtos);
		return mv;
	}

	@RequestMapping(value = "/toAdd.htm")
	@RoleControl("OPTION_VIEW")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/option/toAdd");
		return mv;
	}

	@RequestMapping(value = "/add.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("OPTION_VIEW")
	public JsonData add(OptionDto optionDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		optionService.addOptionDto(optionDto);
		return jsonData;
	}

	@RequestMapping(value = "/addSuccess.htm")
	@RoleControl("OPTION_VIEW")
	public ModelAndView addSuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listOption", "选项添加", "添加选项成功", "toAdd.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toModify.htm")
	@RoleControl("OPTION_VIEW")
	public ModelAndView toModify(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/option/toModify");
		OptionDto optionDto = optionService.queryOptionDtoById(id);
		mv.addObject("optionDto", optionDto);
		return mv;
	}

	@RequestMapping(value = "/modify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("OPTION_VIEW")
	public JsonData modify(OptionDto optionDto, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		try
		{
			optionService.modifyOptionDto(optionDto);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			jsonData = JsonData.failure();
		}
		return jsonData;
	}

	@RequestMapping(value = "/modifySuccess.htm")
	@RoleControl("OPTION_VIEW")
	public ModelAndView modifySuccess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listOption", "选项修改", "修改选项成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		return mv;
	}

	@RequestMapping(value = "/toDelete.htm")
	@RoleControl("OPTION_VIEW")
	public ModelAndView toDelete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/option/toDelete");
		return mv;
	}

	@RequestMapping(value = "/delete.htm")
	@RoleControl("OPTION_VIEW")
	public ModelAndView delete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/common/operation_result");
		OperationResultVo operationResultVo = buildOperationResult("listOption", "选项删除", "选项删除成功", "toList.htm");
		mv.addObject("result", operationResultVo);
		try
		{
			String[] ids = id.split("\\|");
			for (String i : ids)
			{
				if (StringUtils.isNotBlank(i))
				{
					optionService.deleteOption(i);
				}
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			operationResultVo.setOperationInfo("选项删除失败");
		}
		return mv;
	}

	@RequestMapping(value = "/optionValue/addOrModify.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("OPTION_VIEW")
	public JsonData optionValueAddOrModify(OptionValueDto optionValueDto, HttpServletRequest request,
			HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		try
		{
			optionValueService.addOrModifyOptionValueDto(optionValueDto);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			jsonData = JsonData.failure();
		}
		jsonData.data.put("rowId", optionValueDto.getId());
		return jsonData;
	}

	@RequestMapping(value = "/optionValue/delete.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("OPTION_VIEW")
	public JsonData optionValueDelete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
	{
		JsonData jsonData = JsonData.success();
		try
		{
			optionValueService.deleteOptionValue(id);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			jsonData = JsonData.failure();
		}
		return jsonData;
	}
}
