package com.goldskyer.gmxx.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.entities.Blog;

public class CopyData extends BaseTest
{
	@Autowired
	private BaseDao baseDao;

	@Test
	@Rollback(false)
	public void testCopyBlog()
	{
		List<Blog> blogs = baseDao.query("select b from Blog b where b.type='校园风景' ");
		for (Blog b : blogs)
		{
			b.setType("Beautiful Campus");
			b.setId(null);
			b.setAuthor("Information Center");
			b.setTitle("Beautiful Campus");
			baseDao.add(b);
		}
	}
}
