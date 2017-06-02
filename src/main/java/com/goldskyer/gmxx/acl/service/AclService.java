/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: gmxx
 * 
 * AclService.java File Created at 上午12:56:01
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
package com.goldskyer.gmxx.acl.service;

import java.util.List;
import java.util.Map;

import com.goldskyer.core.entities.Account;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.gmxx.acl.entities.Right;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.entities.RoleRight;
import com.goldskyer.gmxx.common.entities.Teacher;

/**
 * 权限服务
 * 
 * @author zhang.li
 * @version 1.0
 * @since 2016-3-20
 */
public interface AclService {
	public Role queryRole(String roleId);
	
	public String addRole(Role role, List<RoleRight> roleRights);
	
	public void modifyRole(Role role, List<RoleRight> roleRights);

	public void deleteRole(String roleId);

	//获取对应域下的角色列表
	public List<Role> queryRoles(Integer start, Integer limit);
	
	//查询角色权限列表
	public List<Right> queryRightsByRole(String roleId);
	
	public long countTotalRoles();
	
	//角色授权
	public void dispathRightsForRole(String roleId, List<String> rightIds);
	
	public Right queryRight(String rightId);
	
	public String addRight(Right right);
	
	public void deleteRight(String rightId);
	
	public void modifyRight(Right right);
	
	public List<Right> queryRights(Integer start, Integer limit);
	
	public long countTotalRights();
	
	//查询角色列表
	public List<Role> queryRolesByUser(String userId);
	
	//分配角色
	public void dispatchRolesForUser(String userId, List<String> roleIds);
	
	public Account queryAccount(String accountId);
	
	public String addAccount(Account account);

	public String addTeacher(Teacher teacher);
	
	public void deleteAccount(String accountId);
	
	//禁用账号
	public void disableAccount(String accountId);
	
	public void modifyAcount(Account account);
	
	public List<Account> queryAccounts(Integer start, Integer limit);
	
	public List<Teacher> queryTeachers(Integer start, Integer limit);
	
	public long countTotalAccounts();

	public long countTotalTeachers();
	
	public List<Right> queryAllRightsByAccountId(String accountId);

	/**
	 * 获取用户授权的所有栏目MAP
	 * @param accountId
	 * @return
	 */
	public Map<String, Menu> queryAuthMenuMapByAccountId(String accountId);

	/**
	 * 获取用户的权限MAP
	 * @param accountId
	 * @return
	 */
	public Map<String, Right> queryRightMapByAccountId(String accountId);

	void deleteTeacher(String teacherId);

	Teacher queryTeacher(String teacherId);

	void modifyTeacher(Teacher teacher);

	public boolean ifHasRole(String accountId, String roleId);

}