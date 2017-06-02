package com.goldskyer.script;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.entities.Account;
import com.goldskyer.core.entities.Cms;
import com.goldskyer.core.entities.Ini;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.enums.AccountStatus;
import com.goldskyer.core.enums.AccountType;
import com.goldskyer.core.enums.MenuModule;
import com.goldskyer.core.enums.MenuType;
import com.goldskyer.core.service.MenuService;
import com.goldskyer.gmxx.acl.entities.AccountRole;
import com.goldskyer.gmxx.acl.entities.Right;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.entities.RoleRight;
import com.goldskyer.gmxx.common.enums.MediaType;
import com.goldskyer.gmxx.service.BaseTest;
import com.goldskyer.script.dto.CreateSchoolDto;

public class CreateSchoolScript extends BaseTest
{
	@Autowired
	private MenuService menuService;
	/**
	 * smart.goldskyer.com
	 * 创建一个校园
	 */
	@Test
	@Rollback(false)
	@Transactional
	public void testCreateSchool()
	{
		CreateSchoolDto dto = new CreateSchoolDto();
		//创建menu
		//创建right
		//创建超级管理员角色
		//创建管理员账号

		Menu m1  = new Menu();
		m1.setType(MenuType.MULTI.name());
		m1.setDomain(dto.getDomain());
		m1.setName(MenuModule.MASTER.getName());
		m1.setParentId("ROOT");
		m1.setMediaType(MediaType.RICHTEXT.name());
		baseDao.add(m1);

		Menu m11 = new Menu();
		m11.setType(MenuType.MULTI.name());
		m11.setDomain(dto.getDomain());
		m11.setName(MenuModule.LANMU.getName());
		m11.setParentId(m1.getParentId());
		m11.setMediaType(MediaType.RICHTEXT.name());
		baseDao.add(m11);

		Menu sub = new Menu();
		sub.setType(MenuType.MULTI.name());
		sub.setDomain(dto.getDomain());
		sub.setName("校园新闻");
		sub.setParentId(m11.getId());
		sub.setMediaType(MediaType.RICHTEXT.name());
		baseDao.add(sub);

		Menu sub1 = new Menu();
		sub1.setType(MenuType.MULTI.name());
		sub1.setDomain(dto.getDomain());
		sub1.setName("校外新闻");
		sub1.setParentId(sub.getId());
		sub1.setMediaType(MediaType.RICHTEXT.name());
		baseDao.add(sub);

		Menu sub2 = new Menu();
		sub2.setType(MenuType.MULTI.name());
		sub2.setDomain(dto.getDomain());
		sub2.setName("校内新闻");
		sub2.setParentId(sub.getId());
		sub2.setMediaType(MediaType.RICHTEXT.name());
		baseDao.add(sub2);

		//创建超级管理员角色
		Role role = new Role();
		role.setDomain(dto.getDomain());
		role.setRoleName("超级管理员");
		role.setRoleNo("1");
		role.setStatus(1);
		role.setCreateDate(new Date());
		String roleId = baseDao.add(role);
		List<Right> rights = baseDao.query("from Right where domain=?", dto.getDomain());
		for(Right right:rights)
		{
			RoleRight rr = new RoleRight();
			rr.setRightId(right.getId());
			rr.setRoleId(roleId);
			rr.setCreateDate(new Date());
			baseDao.add(rr);
		}
		Account account = new Account();
		account.setId(dto.getUsername());
		account.setUsername(dto.getUsername());
		account.setNickname("admin");
		account.setType(AccountType.ADMIN);
		account.setStatus(AccountStatus.NORMAL);
		account.setPassword("14e1b600b1fd579f47433b88e8d85291");
		account.setCreateDate(new Date());
		account.setDomain(dto.getDomain());
		String accountId = baseDao.add(account);
		
		AccountRole accountRole =new AccountRole();
		accountRole.setAccountId(accountId);
		accountRole.setRoleId(roleId);
		accountRole.setCreateDate(new Date());
		baseDao.add(accountRole);

		Cms cms = new Cms();
		cms.setId("title");
		cms.setDescription("网站标题");
		cms.setDomain(dto.getDomain());
		cms.setContent(dto.getTitle());
		cms.setN18("cn");
		baseDao.add(cms);
	}

	@Test
	@Rollback(false)
	public void test()
	{
		Cms cms = new Cms();
		cms.setId("title");
		cms.setDescription("网站标题");
		cms.setContent("智慧小学");
		cms.setDomain("smart.goldskyer.com");
		cms.setN18("cn");
		baseDao.add(cms);
	}

	@Test
	@Rollback(false)
	public void syncRights()
	{
		List<Right> rights = baseDao.query("from Right where domain='gmxx.goldskyer.com' and type='资源管理'");
		for (Right right : rights)
		{
			right.setId(null);
			right.setDomain("smart.goldskyer.com");
			baseDao.add(right);
		}
	}

	@Test
	@Rollback(false)
	public void syncRightsFrom()
	{
		String fromDomain = "";

		List<Right> rights = baseDao.query("from Right where domain='gmxx.goldskyer.com' and type='资源管理'");
		for (Right right : rights)
		{
			right.setId(null);
			right.setDomain("smart.goldskyer.com");
			baseDao.add(right);
		}
	}

	@Test
	@Rollback(false)
	public void syncINI()
	{
		List<Ini> rights = baseDao.query("from Ini where domain='xcxx.goldskyer.com'");
		for (Ini right : rights)
		{
			right.setId(null);
			right.setDomain("smart.goldskyer.com");
			baseDao.add(right);
		}
	}

	@Test
	@Rollback(false)
	public void deleteSchool()
	{
		String domain = "smart.goldskyer.com";
		baseDao.execute("delete from AccountRole  where accountId in (select id from Account where domain=?)", domain);
		baseDao.execute("delete from Account where domain=?", domain);
		baseDao.execute("delete from Menu where domain=?", domain);
		baseDao.execute("delete from RoleRight where rightId in (select id from Right where domain=?) ", domain);
		baseDao.execute("delete from Right where domain=?",domain);
		baseDao.execute("delete from Role where domain=?", domain);
		baseDao.execute("delete from Blog where domain=?", domain);
		baseDao.execute("delete from Cms where domain=?", domain);

	}

	/**
	 * 
	 * CMS权限添加
	 */
	@Test
	@Rollback(false)
	public void addCmsRight()
	{
		String domain = "xcxx.goldskyer.com";
		Right right = new Right("CMS_ADD", "CMS添加", "CMS管理", domain);
		baseDao.add(right);
		Right right2 = new Right("CMS_EDIT", "CMS修改", "CMS管理", domain);
		baseDao.add(right2);
		Right right3 = new Right("CMS_LIST", "CMS查看", "CMS管理", domain);
		baseDao.add(right3);
		Right right4 = new Right("CMS_DELETE", "CMS删除", "CMS管理", domain);
		baseDao.add(right4);
	}

	/**
	 * 
	 * CMS权限添加
	 */
	@Test
	@Rollback(false)
	public void addDepartmentRight()
	{
		String domain = "xcxx.goldskyer.com";
		Right right = new Right("DEPT_ADD", "部门添加", "部门管理", domain);
		baseDao.add(right);
		Right right2 = new Right("DEPT_EDIT", "部门修改", "部门管理", domain);
		baseDao.add(right2);
		Right right3 = new Right("DEPT_LIST", "部门查看", "部门管理", domain);
		baseDao.add(right3);
		Right right4 = new Right("DEPT_DELETE", "部门删除", "部门管理", domain);
		baseDao.add(right4);
	}

	@Test
	@Rollback(false)
	public void testAddRole()
	{
		String roleId = "000000005776c332015776d526730000";
		List<Account> accounts = baseDao.query("select a from Account a where a.domain='xcxx.goldskyer.com'");
		for(Account account:accounts)
		{
			AccountRole aRole = (AccountRole) baseDao
					.queryUnique("select a from AccountRole a where a.accountId=? and a.roleId=?", account.getId(),
							roleId);
			if (aRole == null)
			{
			AccountRole ar=new AccountRole();
			ar.setAccountId(account.getId());
			ar.setCreateDate(new Date());
			ar.setRoleId(roleId);
			baseDao.add(ar);
			}
		}
	}


}
