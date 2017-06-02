package com.goldskyer.scorecloud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "option_value")
public class OptionValue
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String value;
	private int weight;
	private String optionId;
	private boolean checked;////是否默认选中
	private String descrp;//选项中文描述

	public String getDescrp()
	{
		return descrp;
	}

	public void setDescrp(String descrp)
	{
		this.descrp = descrp;
	}

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

}
