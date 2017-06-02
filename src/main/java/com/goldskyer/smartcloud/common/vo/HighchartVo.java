package com.goldskyer.smartcloud.common.vo;

import java.util.List;

public class HighchartVo
{
	private String title;
	private String subTitle;
	private String xAxis;
	private List<Serie> series;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSubTitle()
	{
		return subTitle;
	}

	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}

	public String getxAxis()
	{
		return xAxis;
	}

	public void setxAxis(String xAxis)
	{
		this.xAxis = xAxis;
	}

	public List<Serie> getSeries()
	{
		return series;
	}

	public void setSeries(List<Serie> series)
	{
		this.series = series;
	}

}
