package com.goldskyer.gmxx.acl.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 权限，为了简单，目前使用单节点
 * 
 * @author zhang.li
 * @version 1.0
 * @since 2016-3-19
 */
@Entity
@Table(name = "rights")
public class Right implements Serializable {
	private static final long serialVersionUID = 1054708232560192147L;
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String rightNo;
	private String rightName;
	private String resId = ""; //资源id: 可关联菜单资源等
	private String resType = "";//1-菜单资源 @see com.goldskyer.gmxx.acl.enums.ResTypeEnum
	private Integer recordStatus = 0;
	private Date createDate;
	private String domain;

	private String type;//权限分类，防止权限都列在一行
	private double weight = 1000;//权重

	public Right()
	{
		super();
	}
	public Right(String rightNo, String rightName, String type, String domain)
	{
		super();
		this.rightNo = rightNo;
		this.rightName = rightName;
		this.domain = domain;
		this.type = type;
		this.createDate = new Date();
		this.recordStatus = 1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRightNo() {
		return rightNo;
	}
	public void setRightNo(String rightNo) {
		this.rightNo = rightNo;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getDomain()
	{
		return domain;
	}

	public void setDomain(String domain)
	{
		this.domain = domain;
	}

	@Override
	public String toString()
	{
		return "Right [id=" + id + ", rightNo=" + rightNo + ", rightName=" + rightName + ", resId=" + resId
				+ ", resType=" + resType + ", recordStatus=" + recordStatus + ", createDate=" + createDate + ", domain="
				+ domain + ", type=" + type + ", weight=" + weight + "]";
	}

}