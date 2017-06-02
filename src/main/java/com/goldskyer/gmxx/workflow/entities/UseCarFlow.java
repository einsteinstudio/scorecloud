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

@Entity
@Table(name = "use_car_flow")
public class UseCarFlow
{
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String department;
	private String accountId;
	private String useReason;
	private String fromLocation;
	private String toLocation;
	private String returnLocation;
	private boolean returnBack; //是否返程
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date fromDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date toDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date returnDate;
	private String note;
	private String picUrl;
	private Date createDate;
	private Date updateDate;

	@OneToMany(cascade =
	{ CascadeType.ALL }, mappedBy = "useCarFlow", fetch = FetchType.EAGER)
	private Set<UseCarDetail> userCarDetail = new HashSet<UseCarDetail>();

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUseReason()
	{
		return useReason;
	}

	public void setUseReason(String useReason)
	{
		this.useReason = useReason;
	}

	public String getFromLocation()
	{
		return fromLocation;
	}

	public void setFromLocation(String fromLocation)
	{
		this.fromLocation = fromLocation;
	}

	public String getReturnLocation()
	{
		return returnLocation;
	}

	public void setReturnLocation(String returnLocation)
	{
		this.returnLocation = returnLocation;
	}

	public Date getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(Date fromDate)
	{
		this.fromDate = fromDate;
	}

	public Date getReturnDate()
	{
		return returnDate;
	}

	public void setReturnDate(Date returnDate)
	{
		this.returnDate = returnDate;
	}

	public Set<UseCarDetail> getUserCarDetail()
	{
		return userCarDetail;
	}

	public void setUserCarDetail(Set<UseCarDetail> userCarDetail)
	{
		this.userCarDetail = userCarDetail;
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

	public String getToLocation()
	{
		return toLocation;
	}

	public void setToLocation(String toLocation)
	{
		this.toLocation = toLocation;
	}

	public boolean isReturnBack()
	{
		return returnBack;
	}

	public void setReturnBack(boolean returnBack)
	{
		this.returnBack = returnBack;
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

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public Date getToDate()
	{
		return toDate;
	}

	public void setToDate(Date toDate)
	{
		this.toDate = toDate;
	}

}
