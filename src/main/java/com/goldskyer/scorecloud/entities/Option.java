package com.goldskyer.scorecloud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_option")
public class Option implements SchoolId
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String optionName;
	private int weight;
	private boolean showTip;//显示请选择
	private String tip;
	private String innerCode;

	private String schId;

	public String getSchId()
	{
		return schId;
	}

	public void setSchId(String schId)
	{
		this.schId = schId;
	}

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

	public String getInnerCode()
	{
		return innerCode;
	}

	public void setInnerCode(String innerCode)
	{
		this.innerCode = innerCode;
	}

}
