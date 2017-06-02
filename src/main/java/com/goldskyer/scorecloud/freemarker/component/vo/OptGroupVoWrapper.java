package com.goldskyer.scorecloud.freemarker.component.vo;

import java.util.ArrayList;
import java.util.List;

public class OptGroupVoWrapper
{
	private String name;//对应field的name
	private List<OptGroupVo> optGroupVos = new ArrayList<>();
	private String firstId;
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<OptGroupVo> getOptGroupVos()
	{
		return optGroupVos;
	}

	public void setOptGroupVos(List<OptGroupVo> optGroupVos)
	{
		this.optGroupVos = optGroupVos;
	}

	public String getFirstId()
	{
		return firstId;
	}

	public void setFirstId(String firstId)
	{
		this.firstId = firstId;
	}

}
