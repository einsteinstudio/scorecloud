package com.goldskyer.scorecloud.dto;

public class OptionValueDto
{
	private String id;
	private String value;
	private int weight;
	private String optionId;
	private boolean checked = false;////是否默认选中
	private String descrp;
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	public int getWeight()
	{
		return weight;
	}
	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public String getOptionId()
	{
		return optionId;
	}

	public void setOptionId(String optionId)
	{
		this.optionId = optionId;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public String getDescrp()
	{
		return descrp;
	}

	public void setDescrp(String descrp)
	{
		this.descrp = descrp;
	}

}
