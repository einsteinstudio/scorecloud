package com.goldskyer.gmxx.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.goldskyer.gmxx.common.entities.Attachment;
import com.goldskyer.gmxx.common.service.AttachmentService;

public class AttachmentServiceTest extends BaseTest
{
	@Autowired
	private AttachmentService attachmentService;

	@Test
	@Rollback(false)
	public void testA()
	{
		Attachment attachment=new Attachment();
		attachment.setId("asda");
		baseDao.add(attachment);
	}
}
