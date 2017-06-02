package com.goldskyer.gmxx.service.manager;

import java.util.Date;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.goldskyer.gmxx.acl.entities.Right;
import com.goldskyer.gmxx.service.BaseTest;

public class CmsServiceTest extends BaseTest
{
	@Test
	@Rollback(false)
	public void testAddRights()
	{
		String domain = "smart.goldskyer.com";
		Right r1=new Right();
		r1.setCreateDate(new Date());
		r1.setDomain(domain);
		r1.setRightName("CMS添加");
		r1.setRightNo("CMS_ADD");
		r1.setType("CMS管理");
		baseDao.add(r1);

		Right r2 = new Right();
		r2.setCreateDate(new Date());
		r2.setDomain(domain);
		r2.setRightName("CMS修改");
		r2.setRightNo("CMS_EDIT");
		r2.setType("CMS管理");
		baseDao.add(r2);

		Right r3 = new Right();
		r3.setCreateDate(new Date());
		r3.setDomain(domain);
		r3.setRightName("CMS删除");
		r3.setRightNo("CMS_DELETE");
		r3.setType("CMS管理");
		baseDao.add(r3);

		Right r4 = new Right();
		r4.setCreateDate(new Date());
		r4.setDomain(domain);
		r4.setRightName("CMS查看");
		r4.setRightNo("CMS_VIEW");
		r4.setType("CMS管理");
		baseDao.add(r4);
	}
}
