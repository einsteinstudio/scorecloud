package com.goldskyer.gmxx.front.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dto.BlogQuery;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.vo.MenuVo;
import com.goldskyer.gmxx.common.dtos.CreateTaskDto;
import com.goldskyer.gmxx.common.entities.MessageText;
import com.goldskyer.gmxx.common.entities.WorkFlowTask;
import com.goldskyer.gmxx.common.entities.WorkFlowTemplate;
import com.goldskyer.gmxx.common.enums.MessageType;
import com.goldskyer.gmxx.common.service.WorkFlowService;
import com.goldskyer.gmxx.common.vos.SelectVo;
import com.goldskyer.gmxx.front.business.BlogBusiness;
import com.goldskyer.gmxx.front.business.MessageBusiness;
import com.goldskyer.gmxx.front.business.dto.BlogListModel;
import com.goldskyer.gmxx.workflow.service.WorkflowTemplateService;

@Service("blogBusiness")
public class BlogBusinessImpl implements BlogBusiness{

	@Autowired
	private MessageBusiness messageBusiness;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private WorkflowTemplateService workflowTemplateService;
	@Autowired
	private BlogService blogService;
	@Autowired
	@Qualifier("cachedMenuService")
	private CachedMenuService cachedMenuService;
	

	public BlogListModel buildCommonBlogListModel(ModelAndView mv, String blogType, Integer pageNum, Integer pageSize)
	{
		BlogListModel blogListModel = new BlogListModel();
		if(mv==null || StringUtils.isBlank(blogType) )
		{
			throw new BusinessException("构建Blog公共数据模型异常");
		}
		if(pageNum==null)
		{
			pageNum=1;
		}
		Menu menu = cachedMenuService.queryMenuByName(blogType);
		mv.addObject("mainMenu", cachedMenuService.getMainMenu());
		mv.addObject("menu", menu);
		//BlogBusiness blogBusiness = SpringContextHolder.getBean("blogBusiness");
		List<Blog> blogs = queryBlogsWithNoContent(blogType, null,
				(pageNum - 1) * pageSize, pageSize);
		formatBlogs(blogs);
		long count = blogService.countTotalBlogs(blogType, null);
		blogListModel.setCount(count);
		mv.addObject("blogType", blogType);
		if(count>0)
		{
			mv.addObject("blogs", blogs);
		}
		mv.addObject("blogListModel", blogListModel);
		return blogListModel;
	}

	@Cacheable(value =
	{ "blog" }, key = "T(com.goldskyer.core.dto.EnvParameter).get().getAccountId().concat(#type).concat(#start).concat(#limit)")
	public List<Blog> queryBlogsWithNoContent(String type, String subType, Integer start, Integer limit)
	{
		return blogService.queryBlogsWithNoContent(type, subType, start, limit);
	}

	@Cacheable(value =
	{ "blog" }, key = "T(com.goldskyer.core.dto.EnvParameter).get().getAccountId().concat(#type).concat(#start).concat(#limit).concat('types')")
	public List<Blog> queryBlogsWithNoContentByTypes(String type, Integer start, Integer limit)
	{
		List<String> types = cachedMenuService.queryAllTypes(type, EnvParameter.get().getDomain());
		return blogService.queryBlogsWithNoContent(types, start, limit);
	}

	/**
	 * 查询最新的图片动态新闻
	 * @param start
	 * @param limit
	 * @return
	 */
	@Cacheable(value =
	{ "blog" }, key = "T(com.goldskyer.core.dto.EnvParameter).get().getAccountId().concat(#start).concat(#limit)")
	public List<Blog> queryRecentImgaeBlogs(Integer start, Integer limit)
	{
		List<String> types = cachedMenuService.queryAllTypes("栏目管理", EnvParameter.get().getDomain());
		BlogQuery query=new BlogQuery();
		query.setTypes(types);
		query.setExistImage(true);
		query.setLimit(5);
		return blogService.queryBlogsWithNoContent(query);
	}


	@Cacheable(value =
	{ "blog" }, key = "T(com.goldskyer.core.dto.EnvParameter).get().getAccountId().concat(#start).concat(#limit).concat('types')")
	public List<Blog> queryRecentBlogs(Integer start, Integer limit)
	{
		return blogService.queryBlogsWithNoContent(null, start, limit);
	}
	public void formatBlogs(List<Blog> blogs)
	{

	}

	@Transactional
	public String addBlog(final Blog blog, String accountId)
	{
		blog.setAccountId(accountId);
		blog.setAuditStatus("审核通过");
		String blogId = blogService.addBlog(blog);

		//如果对内，则不需要审核
		WorkFlowTemplate template = workflowTemplateService.queryTemplateByType("内容审核", blog.getType());
		if (template != null)
		{
			CreateTaskDto createTaskDto = new CreateTaskDto();
			createTaskDto.setAccountId(accountId);
			createTaskDto.setObjectId(blogId);
			createTaskDto.setObjectType("内容审核");
			createTaskDto.setSubType(blog.getType());
			createTaskDto.setTaskName(blog.getTitle());
			workFlowService.createTask(createTaskDto);
			WorkFlowTask task = workFlowService.queryTaskByObjectId(blogId);
			Blog blog2 = blogService.queryBlog(blogId);
			blog2.setAuditStatus(task.getStatus());
			blogService.modifyBlog(blog2);
		}
		else //正常发布
		{
			if (!blog.getNeedLogin())
			{
				throw new BusinessException("对外发布内容需要配置审核模板");
			}
			MessageText messageText = new MessageText();
			messageText.setType(MessageType.系统消息);
			messageText.setText("用户 " + blog.getAuthor() + "发布 " + blog.getTitle()
					+ ",<a target='_blank' href='/web/spring/blog/view.htm?id=" + blog.getId() + "' >点此查看</a>");
			messageText.setTitle("栏目 " + blog.getType() + "新发布 <a target='_blank' href='/web/spring/blog/view.htm?id="
					+ blog.getId() + "' >" + blog.getTitle() + "</a>");
			messageText.setBlogId(blog.getId());
			messageBusiness.sendMessageToAll(messageText, blog.getAccountId());
		}
		return blogId;
	}

	/**
	 * 查询栏目分类下拉选择器
	 * @return
	 */
	public List<SelectVo> queryMenuSelectVos(HttpServletRequest request)
	{
		MenuVo roledMenu = (MenuVo) request.getSession().getAttribute("roledMenu");
		List<SelectVo> vos = new ArrayList<SelectVo>();
		deepSearch(vos, roledMenu, 0);
		return vos;
	}

	private void deepSearch(List<SelectVo> vos, MenuVo department, final int deep)
	{
		String prefix = "";
		int i = 0;
		while (i < deep)
		{
			prefix += "-";
			i++;
		}
		if (department == null)
			return;
		for (MenuVo d : department.getChildren())
		{
			if (d.isChecked())
			{
				SelectVo vo = new SelectVo();
				vo.setName(prefix + d.getName());
				vo.setValue(d.getName());
				vos.add(vo);
				deepSearch(vos, d, (deep + 1));
			}
		}
	}


}
