package com.goldskyer.gmxx.common.dtos;

import java.io.Serializable;

public class DataTablesRespDto implements Serializable
{
	private Object data;
	private long recordsTotal;
	private long recordsFiltered;

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public long getRecordsTotal()
	{
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal)
	{
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered()
	{
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered)
	{
		this.recordsFiltered = recordsFiltered;
	}
}
