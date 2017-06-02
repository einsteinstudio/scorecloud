package com.goldskyer.gmxx.acl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.enums.AccountStatus;
import com.goldskyer.core.enums.AccountType;
import com.goldskyer.gmxx.acl.entities.Right;
import com.goldskyer.gmxx.acl.entities.Role;
import com.goldskyer.gmxx.acl.enums.ResTypeEnum;
import com.goldskyer.gmxx.acl.service.AclService;
import com.goldskyer.gmxx.service.BaseTest;
public class AclServiceTest extends BaseTest {
	
	@Autowired
	@Qualifier("aclService")
	private AclService aclService;
	
	@SuppressWarnings("unused")
	@Autowired
	private BaseDao baseDao;
	
	@Test
	@Rollback(false)
	@Transactional
	public void completeAccountPhoto()
	{
		List<Account> accounts = baseDao.query("from Account where domain='xcxx.goldskyer.com'");
		for (Account account : accounts)
		{
			if (StringUtils.isBlank(account.getPhoto()))
			{
				int index = new Random().nextInt(179);
				String photoPath = "/manager/assets/photos_v1/" + index + ".gif";
				account.setPhoto(photoPath);
				baseDao.modify(account);
			}
		}
	}

	@Test
	public void testAddAccount()
	{
		Account account = new Account();
		account.setUsername("sadssadadd");
		account.setId(account.getUsername());
		account.setType(AccountType.ADMIN);
		account.setStatus(AccountStatus.NORMAL);
		aclService.addAccount(account);
	}

	@Test
	public void test_right() {
		//1.添加
		Right right = new Right();
		right.setResId("0102");
		right.setResType(ResTypeEnum.MENU.getType());
		right.setRecordStatus(0);
		right.setRightName("新闻添加");
		right.setRightNo("010201");
		right.setCreateDate(new Date());
		
		String id = aclService.addRight(right);
		
		System.out.println(id);
		
		Role role = aclService.queryRoles(0, 1).get(0);
		System.out.println("roleId: " + role.getId());
		
		List<String> rightIds = new ArrayList<String>();
		rightIds.add(id);
		aclService.dispathRightsForRole(role.getId(), rightIds);
		
		List<Right> rights = aclService.queryRightsByRole(role.getId());
//		Right dbright = aclService.queryRight(id);
		
		System.out.println(JSON.toJSONString(rights, true));
		
		//--------------------------------------------------
	}
	
	@Test
	public void test_account() {
		long total = aclService.countTotalAccounts();
		List<Account> accounts = aclService.queryAccounts(0, 10);
		System.out.println(total);
		System.out.println(JSON.toJSONString(accounts, true));
	}

	@Test
	public void testQueryAllRightsByAccountId()
	{
		List<Right> rights = aclService.queryAllRightsByAccountId("admin");
		System.out.println(rights);
	}

}