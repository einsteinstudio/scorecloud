package com.goldskyer.gmxx.web.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.entities.Comment;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.entities.VoteUpLog;
import com.goldskyer.core.service.CommentService;
import com.goldskyer.core.service.VoteUpLogService;
import com.goldskyer.core.vo.Pagination;
import com.goldskyer.gmxx.common.constants.GmxxConstant;

@Controller("web.BlogController")
@RequestMapping("/web/{module}")
public class BlogController extends WebBaseController
{

	@Autowired
	private VoteUpLogService voteUpLogService;
	
	@Autowired
	private CommentService commentService;

	@RequestMapping("/list(view).htm")
	public ModelAndView list2(@PathVariable String view, @PathVariable String module, @RequestParam String blogType,
			@RequestParam(required = false) Integer pageNum)
	{
		if (pageNum == null)
		{
			pageNum = 1;
		}
		ModelAndView mv = new ModelAndView("/web/" + module + "/list" + view);
		//设置Size（优先级MOUDLE_PAGE_SZIE>PAGE_SIZE）
		Integer pageSize = 10;
		String typeIni = iniService.getIniValue(blogType + "_PAGE_SZIE");
		if (StringUtils.isNotBlank(typeIni))
		{
			pageSize = Integer.valueOf(typeIni);
		}
		else
		{
			pageSize = Integer.valueOf(iniService.getIniValue(GmxxConstant.PAGE_SIZE));
		}

		List<Blog> blogs = blogService.queryBlogsWithNoContent(blogType, null, (pageNum - 1) * pageSize, pageSize);
		long count = blogService.countTotalBlogs(blogType, null);

		Pagination pager = new Pagination(pageNum, count, pageSize,
				Integer.valueOf(iniService.getIniValue(GmxxConstant.PAGE_BTN_NUM)));
		mv.addObject("pager", pager);
		mv.addObject("blogs", blogs);
		mv.addObject("blogType", blogType);
		return mv;

	}

	@RequestMapping("/view.htm")
	public ModelAndView detail(@RequestParam String id,@PathVariable String module,@RequestParam(required=false)String accountId)
	{
		ModelAndView mv = new ModelAndView("/web/" + module + "/view");
		Menu cachedMenu = cachedMenuService.queryMenuByName("栏目管理");
		mv.addObject("cachedMenu", cachedMenu);
		initSideBar(mv);
		buildCommonBlogModel(mv,id);
		blogService.incViewCount(id);
		if(StringUtils.isNotBlank(accountId))
		{
			VoteUpLog voteUpLog = voteUpLogService.queryVoteUpLogbyId(accountId, id, "BLOG");
			if(voteUpLog!=null)
			{
				mv.addObject("voted", true);
			}
		}
		List<Comment> comments = commentService.queryCommentsByObject(id, "BLOG", 0, 100);
		mv.addObject("comments", comments);
		return mv;
	}
	
	@RequestMapping(value="/voteUp.json",produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData voteUp(VoteUpLog voteUpLog)
    {
		voteUpLog.setId(null);
		voteUpLog.setId(EnvParameter.get().getIp());
		voteUpLog.setObjectType("BLOG");
		if(!voteUpLogService.addVoteUpLog(voteUpLog))
		{
			return JsonData.failure("你已经点赞过了");

		}
		boolean result = blogService.incVoteUp(voteUpLog.getObjectId());
		if(result)
		{
			return JsonData.success();
		}
		else
		{
			return JsonData.failure("点赞失败,找不到要赞的博客");
		}
    }
	
	/**
	 * 所有的资源型内容列表通用这个接口
	 *  /web/tv/list.htm 对应 /web/tv/list.jsp
	 *  /web/tv/list_image.htm /web/tv/list_image.jsp
	 * @param view
	 * @param module
	 * @param blogType
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/list{view}.htm")
	public ModelAndView list(@PathVariable String view,@PathVariable String module,@RequestParam String blogType,@RequestParam(required=false) Integer pageNum)
	{
		ModelAndView mv = new ModelAndView("/web/" + module + "/list" + view);
		//设置Size（优先级MOUDLE_PAGE_SZIE>PAGE_SIZE）
		Integer pageSize = 10;
		String typeIni = iniService.getIniValue(blogType + "_PAGE_SZIE");
		if (StringUtils.isNotBlank(typeIni))
		{
			pageSize = Integer.valueOf(typeIni);
		}
		else
		{
			pageSize = Integer.valueOf(iniService.getIniValue(GmxxConstant.PAGE_SIZE));
		}
		blogBusiness.buildCommonBlogListModel(mv, blogType, pageNum, pageSize);
		return mv;
	}
	
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list.json",produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData mockJson(@RequestParam String type,@RequestParam(required=false) String subType,@RequestParam(required=false)Integer pageNo,@RequestParam(required=false)Integer pageSize){
		JsonData jsonData =JsonData.success();
		if(pageNo==null || pageNo<1)
		{
			pageNo=1;
		}
		if(pageSize==null || pageSize<1)
		{
			pageSize = Integer.valueOf(iniService.getIniValue(GmxxConstant.PAGE_SIZE));
		}
		Integer start =(pageNo-1) * pageSize;
		List<Blog> blogs = blogService.queryBlogsWithNoContent(type, subType, start, pageSize);
		long count = blogService.countTotalBlogs(type, subType);
		int maxPageNo=(int)count/pageSize +((count%pageSize==0)?0:1);
		int nextPageNo = Math.min(maxPageNo, pageNo+1);
		blogBusiness.formatBlogs(blogs);
		jsonData.data.put("blogs", blogs);
		jsonData.data.put("count", count);
		jsonData.data.put("pageSize", pageSize);
		jsonData.data.put("pageNo", pageNo);
		jsonData.data.put("nextPageNo", nextPageNo);
		jsonData.data.put("maxPageNo", maxPageNo);
		return jsonData;
    } 
	
	protected  void buildCommonBlogModel(ModelAndView mv,String blogId)
	{
		Blog blog = blogService.queryBlog(blogId);
		if(blog==null)
		{
			throw new BusinessException("当前请求记录不存在");
		}
		Menu menu = cachedMenuService.queryMenuByName(blog.getType());
		mv.addObject("mainMenu", cachedMenuService.getMainMenu());
		mv.addObject("menu", menu);
		mv.addObject("blog",blog);
	}
	
	
	
	
	
}
