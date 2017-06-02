package com.goldskyer.gmxx.promotion.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 用户抽奖记录，存储用户有没有中奖
 * @author jintianfan
 *
 */
@Entity
@Table(name="award_record")
public class AwardRecord {
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String accountId; //手机号或者账号
	private String activityId;
	private String fp;//抽奖时浏览器指纹
	private String awardId; //奖品ID
	private boolean awarded=false;//是否中奖
	private String name;
	private String department;
	private String cardNo;
	
	private Date createDate;//
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
		
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getAwardId() {
		return awardId;
	}
	public void setAwardId(String awardId) {
		this.awardId = awardId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isAwarded() {
		return awarded;
	}
	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}
	public String getFp() {
		return fp;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}
	@Override
	public String toString() {
		return "AwardRecord [id=" + id + ", accountId=" + accountId + ", activityId=" + activityId + ", fp=" + fp
				+ ", awardId=" + awardId + ", awarded=" + awarded + ", createDate=" + createDate + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCardNo()
	{
		return cardNo;
	}

	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}
	
	
}
