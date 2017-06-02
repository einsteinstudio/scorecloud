package com.goldskyer.scorecloud.dto;

import java.util.ArrayList;
import java.util.List;

public class OptionDto extends SchIdDto
{
	private String id;
	private String optionName;
	private String innerCode;//内部编码，程序使用
	private int weight;
	private boolean showTip;//显示请选择
	private String tip;
	private List<OptionValueDto> optionValueDtos = new ArrayList<OptionValueDto>();

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getOptionName()
	{
		return optionName;
	}

	public void setOptionName(String optionName)
	{
		this.optionName = optionName;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public boolean isShowTip()
	{
		return showTip;
	}

	public boolean getShowTip()
	{
		return showTip;
	}

	public void setShowTip(boolean showTip)
	{
		this.showTip = showTip;
	}

	public String getTip()
	{
		return tip;
	}

	public void setTip(String tip)
	{
		this.tip = tip;
	}

	public List<OptionValueDto> getOptionValueDtos()
	{
		return optionValueDtos;
	}

	public void setOptionValueDtos(List<OptionValueDto> optionValueDtos)
	{
		this.optionValueDtos = optionValueDtos;
	}

	public String getInnerCode()
	{
		return innerCode;
	}

	public void setInnerCode(String innerCode)
	{
		this.innerCode = innerCode;
	}

}
