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
 * 角色-权限
 * 
 * @author zhang.li
 * @version 1.0
 * @since 2016-3-19
 */
@Entity
@Table(name = "role_right")
public class RoleRight implements Serializable {
	private static final long serialVersionUID = 1054708232560192147L;
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String roleId;
	private String rightId;
	private Date createDate;
	
	public RoleRight()
	{
		super();
	}
	public RoleRight(String roleId, String rightId)
	{
		super();
		this.roleId = roleId;
		this.rightId = rightId;
		this.createDate = new Date();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRightId() {
		return rightId;
	}
	public void setRightId(String rightId) {
		this.rightId = rightId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}