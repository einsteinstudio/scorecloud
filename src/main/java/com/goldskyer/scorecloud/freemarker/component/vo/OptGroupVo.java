package com.goldskyer.scorecloud.freemarker.component.vo;

import java.util.ArrayList;
import java.util.List;

import com.goldskyer.scorecloud.dto.OptionValueDto;

/**
 * 分组下拉菜单
 * @author jintianfan
 *
 */
public class OptGroupVo
{
	private String groupName;//分组名
	private List<OptionValueDto> optionValueDtos = new ArrayList<>();

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public List<OptionValueDto> getOptionValueDtos()
	{
		return optionValueDtos;
	}

	public void setOptionValueDtos(List<OptionValueDto> optionValueDtos)
	{
		this.optionValueDtos = optionValueDtos;
	}



}
