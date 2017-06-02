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
@Table(name = "purchase_detail")
public class PurchaseDetail
{
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String name;
	private String norm;//规格
	private Integer buyCount; //数量
	private String price;//价格
	private String unit; //单位 (不需要)

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "purchaseFlowId")
	private PurchaseFlow purchaseFlow;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNorm()
	{
		return norm;
	}

	public void setNorm(String norm)
	{
		this.norm = norm;
	}

	public Integer getBuyCount()
	{
		return buyCount;
	}

	public void setBuyCount(Integer buyCount)
	{
		this.buyCount = buyCount;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public PurchaseFlow getPurchaseFlow()
	{
		return purchaseFlow;
	}

	public void setPurchaseFlow(PurchaseFlow purchaseFlow)
	{
		this.purchaseFlow = purchaseFlow;
	}


}
