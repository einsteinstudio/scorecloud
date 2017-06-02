package com.goldskyer.smartcloud.common.vo;

import java.util.List;

public class Serie<T>
{
	private String name;
	private List<T> datas;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<T> getDatas()
	{
		return datas;
	}

	public void setDatas(List<T> datas)
	{
		this.datas = datas;
	}

}
