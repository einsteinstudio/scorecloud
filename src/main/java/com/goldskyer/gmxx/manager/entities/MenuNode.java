/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: gmxx
 * 
 * MenuNode.java File Created at 下午9:00:00
 * 
 * 
 * Copyright 2014 Taobao.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Taobao Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Taobao.com.
 */
package com.goldskyer.gmxx.manager.entities;

import java.util.List;

/**
 * 菜单树形节点
 * 
 * @author zhang.li
 * @version 1.0
 * @since 2016-4-14
 */
public class MenuNode {
	
	private String id;
	private String name;
	private boolean open;
	private double weight;
	private String pId;
	private List<MenuNode> children;
	//禁止子节点移走
	private boolean childOuter;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public List<MenuNode> getChildren() {
		return children;
	}
	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}
	public boolean isChildOuter() {
		return childOuter;
	}
	public void setChildOuter(boolean childOuter) {
		this.childOuter = childOuter;
	}
}