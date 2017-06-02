package com.goldskyer.gmxx.common.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataTableReqDto implements Serializable{

	private Map<String, Object> paraMap = new HashMap();
	private String sql;
	private String searchField;// 模糊查询的字段，多个用逗号隔开
	private String orderBy;
	//private String domain;
	private String searchKey;
	private Integer start;
	private Integer length;
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSearchKey()
	{
		return searchKey;
	}

	public void setSearchKey(String searchKey)
	{
		this.searchKey = searchKey;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	//	public String getDomain()
	//	{
	//		return domain;
	//	}
	//
	//	public void setDomain(String domain)
	//	{
	//		this.domain = domain;
	//	}

	public String getSearchField()
	{
		return searchField;
	}

	public void setSearchField(String searchField)
	{
		this.searchField = searchField;
	}

	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}



	public Map<String, Object> getParaMap()
	{
		return paraMap;
	}
	public void setParam(String key, Object val)
	{
		paraMap.put(key, val);
	}
	
}
