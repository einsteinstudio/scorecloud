package com.goldskyer.gmxx.workflow.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "use_car_detail")
public class UseCarDetail
{
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String carType;
	private Integer carCount;
	private String other; //其他要求

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "useCarFlowId")
	private UseCarFlow useCarFlow;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCarType()
	{
		return carType;
	}

	public void setCarType(String carType)
	{
		this.carType = carType;
	}

	public Integer getCarCount()
	{
		return carCount;
	}

	public void setCarCount(Integer carCount)
	{
		this.carCount = carCount;
	}

	public String getOther()
	{
		return other;
	}

	public void setOther(String other)
	{
		this.other = other;
	}

	public UseCarFlow getUseCarFlow()
	{
		return useCarFlow;
	}

	public void setUseCarFlow(UseCarFlow useCarFlow)
	{
		this.useCarFlow = useCarFlow;
	}


}
