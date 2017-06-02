package com.goldskyer.gmxx.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.common.entities.MessageText;
import com.goldskyer.gmxx.workflow.entities.PurchaseDetail;
import com.goldskyer.gmxx.workflow.entities.PurchaseFlow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BaseTest {
	protected Log log = LogFactory.getLog(BaseTest.class);
	@Autowired
	protected BaseDao baseDao;

	@Test
	public void testAdd()
	{
		MessageText text = new MessageText();
		text.setText("123");
		text.setText("我的");
		baseDao.add(text);
	}

	@Test
	@Rollback(false)
	public void query()
	{
		PurchaseFlow purchaseFlow=new PurchaseFlow();
		purchaseFlow.setNote("ss");
		PurchaseDetail detail = new PurchaseDetail();
		detail.setBuyCount(4);
		detail.setPurchaseFlow(purchaseFlow);
		purchaseFlow.getDetails().add(detail);
		baseDao.add(purchaseFlow);
	}

	@Test
	@Rollback(false)
	public void testAA()
	{
		baseDao.delete(PurchaseFlow.class, "8a53b78255ef9f490155ef9f58d20000");
	}
}
