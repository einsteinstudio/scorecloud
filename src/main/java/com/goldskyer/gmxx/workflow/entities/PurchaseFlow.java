package com.goldskyer.gmxx.workflow.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 流程只可以回收，不可以修改
 * 流程是不需要带申请人的
 * @author jintianfan
 *
 */
@Entity
@Table(name = "purchase_flow")
public class PurchaseFlow
{
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String accountId;
	private String department;
	private String type;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date deliverDate; //交付日
	@OneToMany(cascade =
	{ CascadeType.ALL }, mappedBy = "purchaseFlow", fetch = FetchType.EAGER)
	private Set<PurchaseDetail> details = new HashSet<>();
	private String payWay; //支付方式
	private String note; //备注
	private String picUrl; //图片
	private Date createDate;
	private Date updateDate;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public Date getDeliverDate()
	{
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate)
	{
		this.deliverDate = deliverDate;
	}

	public Set<PurchaseDetail> getDetails()
	{
		return details;
	}

	public void setDetails(Set<PurchaseDetail> details)
	{
		this.details = details;
	}

	public String getPayWay()
	{
		return payWay;
	}

	public void setPayWay(String payWay)
	{
		this.payWay = payWay;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

}
