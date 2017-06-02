package com.goldskyer.gmxx.front.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.entities.Blog;
import com.goldskyer.gmxx.common.vos.SelectVo;
import com.goldskyer.gmxx.front.business.dto.BlogListModel;

public interface BlogBusiness {
	/**
	 * 
	 * @param mv
	 * @param blogType
	 * @param pageNum 从1开始
	 * @param pageSize
	 * @return
	 */
	public BlogListModel buildCommonBlogListModel(ModelAndView mv, String blogType, Integer pageNum, Integer pageSize);

	public void formatBlogs(List<Blog> blogs);

	public List<Blog> queryBlogsWithNoContent(String type, String subType, Integer start, Integer limit);

	public List<Blog> queryBlogsWithNoContentByTypes(String type, Integer start, Integer limit);

	public String addBlog(Blog blog, String accountId);

	public List<Blog> queryRecentBlogs(Integer start, Integer limit);

	/**
	 * 查询最新的图片动态新闻
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Blog> queryRecentImgaeBlogs(Integer start, Integer limit);

	public List<SelectVo> queryMenuSelectVos(HttpServletRequest request);
}
