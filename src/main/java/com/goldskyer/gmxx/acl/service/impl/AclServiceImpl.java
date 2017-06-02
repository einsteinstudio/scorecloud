/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: gmxx
 * 
 * AclServiceImpl.java File Created at 上午1:04:13
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
package com.goldskyer.gmxx.acl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.enums.AccountStatus;
import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.acl.entities.AccountRole;
import com.goldskyer.gmxx.acl.entities.Right;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.entities.RoleRight;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.acl.service.RoleMenuService;
import com.goldskyer.gmxx.common.entities.Teacher;

/**
 * 权限服务
 * 
 * @author zhang.li
 * @version 1.0
 * @since 2016-3-20
 */
@SuppressWarnings("unchecked")
@Service("aclService")
public class AclServiceImpl extends CoreServiceImp implements AclService {

	@Autowired
	protected RoleMenuService roleMenuService;

	@Override
	public Role queryRole(String roleId) {
		return baseDao.query(Role.class, roleId);
	}

	@Override
	@Transactional
	public String addRole(Role role, List<RoleRight> roleRights)
	{
		role.setCreateDate(new Date());
		role.setDomain(EnvParameter.get().getDomain());
		String roleId = baseDao.add(role);
		for (RoleRight rr : roleRights)
		{
			rr.setRoleId(roleId);
		}
		baseDao.add(roleRights);
		return roleId;
	}

	/**
	 * role，此处role为传递进来的表单
	 */
	@Transactional
	public void modifyRole(Role role, List<RoleRight> roleRights)
	{
		Role dbRole = baseDao.query(Role.class, role.getId());
		dbRole.setStatus(role.getStatus());
		dbRole.setRoleNo(role.getRoleNo());
		dbRole.setRoleName(role.getRoleName());
		baseDao.modify(dbRole);
		baseDao.execute("delete from RoleRight where roleId=?", role.getId());
		for (RoleRight rr : roleRights)
		{
			rr.setRoleId(role.getId());
		}
		baseDao.add(roleRights);
	}

	@Override
	@Transactional
	public void deleteRole(String roleId) {
		baseDao.delete(Role.class, roleId);
		baseDao.execute("delete from RoleRight where roleId=?", roleId);
	}

	
	@Override
	public List<Role> queryRoles(Integer start, Integer limit) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("domain", EnvParameter.get().getDomain());
		return (List<Role>) baseDao.query("select  r from Role r where r.domain=:domain order by createDate desc,id",
				paraMap, start, limit);
	}

	@Override
	public List<Right> queryRightsByRole(String roleId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("roleId", roleId);
		return (List<Right>)baseDao.query("select r from RoleRight rr, Right r where rr.roleId=:roleId and rr.rightId=r.id", paraMap);
	}
	
	@Override
	public long countTotalRoles() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("domain", EnvParameter.get().getDomain());
		return baseDao.count("select r from Role r where r.domain=:domain", paraMap);
	}

	@Override
	@Transactional
	public void dispathRightsForRole(String roleId, List<String> rightIds) {
		//1.先删除已存在的权限映射
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("roleId", roleId);
		List<RoleRight> roleRights = (List<RoleRight>)baseDao.query("select rr from RoleRight rr where rr.roleId=:roleId", paraMap);
		for (RoleRight roleRight : roleRights) {
			baseDao.delete(RoleRight.class, roleRight.getId());
		}
		//2.添加权限映射
		if (CollectionUtils.isNotEmpty(rightIds)) {
			for (String rightId : rightIds) {
				RoleRight roleRight = new RoleRight();
				roleRight.setRoleId(roleId);
				roleRight.setRightId(rightId);
				roleRight.setCreateDate(new Date());
				baseDao.add(roleRight);
			}
		}
	}
	
	@Override
	public Right queryRight(String rightId) {
		return baseDao.query(Right.class, rightId);
	}

	@Override
	public List<Right> queryRights(Integer start, Integer limit) {
		return (List<Right>)baseDao.query("select r from Right r order by createDate desc,id", null, start, limit);
	}
	
	public long countTotalRights() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("domain", EnvParameter.get().getDomain());
		return baseDao.count("select r from Right r", paraMap);
	}

	@Override
	public String addRight(Right right) {
		return baseDao.add(right);
	}

	@Override
	public void deleteRight(String rightId) {
		baseDao.delete(Right.class, rightId);
	}

	@Override
	public void modifyRight(Right right) {
		baseDao.modify(right);
	}
	
	@Override
	public List<Role> queryRolesByUser(String userId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		return (List<Role>)baseDao.query("select r from AccountRole ar, Role r where ar.accountId=:userId and ar.roleId=r.id", paraMap);
	}

	public boolean ifHasRole(String accountId, String roleId)
	{
		if (StringUtils.isBlank(accountId) || StringUtils.isBlank(roleId))
		{
			return false;
		}
		List aList = baseDao.query("from AccountRole where accountId=? and  roleId=?", accountId, roleId);
		if (aList != null && aList.size() > 0)
			return true;
		return false;
	}

	public List<Right> queryAllRightsByAccountId(String accountId)
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("accountId", accountId);
		return baseDao.query(
				"select distinct rt from AccountRole ar, Role r ,RoleRight rr ,Right rt where ar.accountId=:accountId and ar.roleId=r.id and r.id=rr.roleId and rr.rightId= rt.id",
				paraMap);
	}

	/**
	 * 获取用户的权限MAP
	 * @param accountId
	 * @return
	 */
	public Map<String, Right> queryRightMapByAccountId(String accountId)
	{
		List<Right> rights = queryAllRightsByAccountId(accountId);
		Map<String, Right> rightMap = new HashMap<>();
		for(Right right:rights)
		{
			rightMap.put(right.getRightNo(), right);
		}
		return rightMap;
	}

	@Override
	public void dispatchRolesForUser(String userId, List<String> roleIds) {
		//1.先删除已存在的权限映射
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		List<AccountRole> accountRoles = (List<AccountRole>)baseDao.query("select ar from AccountRole ar where ar.accountId=:userId", paraMap);
		for (AccountRole accountRole : accountRoles) {
			baseDao.delete(AccountRole.class, accountRole.getId());
		}
		//2.添加权限映射
		if (CollectionUtils.isNotEmpty(roleIds)) {
			for (String roleId : roleIds) {
				AccountRole accountRole = new AccountRole();
				accountRole.setAccountId(userId);
				accountRole.setRoleId(roleId);
				accountRole.setCreateDate(new Date());
				baseDao.add(accountRole);
			}
		}
	}

	@Override
	public Account queryAccount(String accountId) {
		return baseDao.query(Account.class, accountId);
	}

	@Override
	public Teacher queryTeacher(String teacherId) {
		return baseDao.query(Teacher.class, teacherId);
	}
	
	@Override
	public String addAccount(Account account) {
		account.setId(UUID.randomUUID().toString());
		if (EnvParameter.get() != null)
			account.setDomain(EnvParameter.get().getDomain());
		return baseDao.add(account);
	}

	@Override
	public String addTeacher(Teacher teacher) {
		if (EnvParameter.get() != null)
			teacher.setDomain(EnvParameter.get().getDomain());
		return baseDao.add(teacher);
	}
	
	@Override
	public void deleteAccount(String accountId) {
		baseDao.delete(Account.class, accountId);
	}

	@Override
	public void deleteTeacher(String teacherId) {
		baseDao.delete(Teacher.class, teacherId);
	}
	
	@Override
	public void disableAccount(String accountId) {
		Account account = this.queryAccount(accountId);
		account.setStatus(AccountStatus.NORMAL);
		this.modifyAcount(account);
	}

	@Override
	public void modifyAcount(Account account) {
		baseDao.modify(account);
	}

	@Override
	public void modifyTeacher(Teacher teacher) {
		baseDao.modify(teacher);
	}
	
	@Override
	public List<Account> queryAccounts(Integer start, Integer limit) {
		Map map = new HashMap<>();
		map.put("domain", EnvParameter.get().getDomain());
		return (List<Account>) baseDao.query(
				"select a from Account a  where a.domain =:domain order by createDate desc,id ",
				map, start, limit);
	}

	@Override
	public List<Teacher> queryTeachers(Integer start, Integer limit) {
		Map map = new HashMap<>();
		map.put("domain", EnvParameter.get().getDomain());
		return (List<Teacher>) baseDao.query(
				"select t from Teacher t  where t.domain =:domain order by createDate desc,id ",
				map, start, limit);
	}
	
	@Override
	public long countTotalAccounts() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("domain", null);
		return baseDao.count("select a from Account a", paraMap);
	}

	@Override
	public long countTotalTeachers() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("domain", null);
		return baseDao.count("select t from Teacher t", paraMap);
	}
	
	/**
	 * 获取用户授权的所有
	 * @param accountId
	 * @return
	 */
	public Map<String, Menu> queryAuthMenuMapByAccountId(String accountId)
	{
		List<Role> roles = queryRolesByUser(accountId);
		Map<String, Menu> authMenuMap = new HashMap<>();
		for (Role role : roles)
		{
			authMenuMap.putAll(roleMenuService.queryMenuMapByRoleId(role.getId()));
		}
		return authMenuMap;
	}

}