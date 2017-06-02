package com.goldskyer.gmxx.xdcms.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.gmxx.service.BaseTest;
@Transactional
public class XdcmsBlogServiceImpTest extends BaseTest {
	
	@Autowired
	@Qualifier("xdcmsBlogService")
	private BlogService blogService;
	
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	@Qualifier("blogService")
	private BlogService blogServiceV2;
	
	@Test
	public void testSqlCount()
	{
		long count = blogService.countTotalBlogs("校园新闻", null);
		System.out.println("当前数量："+count);
	}
	
	@Test
	public void queryBlog()
	{
		Blog blog = blogService.queryBlog("1511281506146416815");
		System.out.println(blog.getContent());
	}
	
	@Test
	public void queryBlogList()
	{
		List<Blog> blogs =blogService.queryBlogsWithNoContent("校园新闻", null, 0, 5);
		for(Blog blog:blogs)
		{
			System.out.println(blog.getId()+","+blog.getTitle());
		}
	}
	@Test
	@NotTransactional
	public void importBlogs()
	{
		List<Blog> blogs = blogService.queryBlogsWithNoContent("教研动态", null, 0, 80);
		//baseDao.add(blogs);
		for (Blog b : blogs)
		{
			try
			{
			b.setDomain("gmxx.goldskyer.com");
			baseDao.add(b);
			}
			catch (Exception e)
			{
				System.out.println(b.getContent().trim().length());
				System.out.println(b.getTitle());
				throw new BusinessException();
			}
		}
		System.out.println(blogs.size());
		//baseDao.add(blogs);
	}

	@Test
	@NotTransactional
	public void testImportBlog()
	{
		Blog blog = blogService.queryBlog("1506081714359221709");
		blog.setType("学校简介");
		baseDao.add(blog);
	}

	@Test
	public void addXd()
	{
		Blog blog = blogServiceV2.queryBlog("00000000543c95b401543dc5fbb20f4e");
		blog.setType("校园新闻");
		blogService.addBlog(blog);
	}
}
