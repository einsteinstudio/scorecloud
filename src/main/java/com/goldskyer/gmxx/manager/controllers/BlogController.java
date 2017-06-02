package com.goldskyer.gmxx.manager.controllers;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.entities.Comment;
import com.goldskyer.core.entities.Menu;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.service.CachedMenuService;
import com.goldskyer.core.service.CommentService;
import com.goldskyer.gmxx.common.aop.RoleControl;
import com.goldskyer.gmxx.common.constants.GmxxConstant;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;
import com.goldskyer.gmxx.common.entities.LiveVideo;
import com.goldskyer.gmxx.common.entities.WorkFlowTask;
import com.goldskyer.gmxx.common.enums.LiveStatus;
import com.goldskyer.gmxx.common.enums.MediaType;
import com.goldskyer.gmxx.common.helpers.DataTableHelper;
import com.goldskyer.gmxx.common.service.LiveVideoService;
import com.goldskyer.gmxx.common.vos.SelectVo;
import com.goldskyer.gmxx.front.business.BlogBusiness;
import com.goldskyer.gmxx.manager.forms.BlogMediaForm;
import com.goldskyer.gmxx.workflow.service.WorkflowTemplateService;
import com.goldskyer.gmxx.workflow.vo.AuditVo;

@Controller("mamangerBlogController")
@RequestMapping("/manager")
public class BlogController extends BaseManagerController
{
	@Autowired
	private WorkflowTemplateService workflowTemplateService;
	@Autowired
	@Qualifier("blogService")
	protected BlogService blogService;
	@Autowired
	protected BlogBusiness blogBusiness;

	@Autowired
	protected CommentService commentService;
	@Autowired
	protected AccountService accountService;

	@Autowired
	@Qualifier("cachedMenuService")
	protected CachedMenuService cachedMenuService;

	@Autowired
	protected LiveVideoService liveVideoService;

	//	@Autowired
	//	@Qualifier("xdcmsBlogService")
	//protected BlogService xdBlogService;

	@RequestMapping("/blog/{path}.htm")
	public ModelAndView inner(@PathVariable String path)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "blog/" + path);
		return mv;
	}

	@RequestMapping("/blog/list.htm")
	@RoleControl("BLOG_VIEW")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "blog/list");
		mv.addObject("type", request.getParameter("type"));
		return mv;
	}

	public static void main(String[] args)
	{

	}
	@SuppressWarnings(
	{ "rawtypes", "unchecked", "deprecation"})
	@RequestMapping(value = "/blog/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@RoleControl("BLOG_VIEW")
	public Object listData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String type = request.getParameter("type");
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select title,author,auditStatus,updateDate,id from Blog  where deleted=0 and domain=:domain and type=:type");
		dataTableReqDto.setOrderBy("order by createDate desc ,id");
		dataTableReqDto.setParam("domain", EnvParameter.get().getDomain());
		dataTableReqDto.setParam("type", type);
		dataTableReqDto.setParam("types", cachedMenuService.queryAllTypes(type, EnvParameter.get().getDomain()));
		dataTableReqDto.setSearchField("title,author");
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	@RequestMapping("/blog/view.htm")
	@RoleControl("BLOG_VIEW")
	public ModelAndView view(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		String blogId = request.getParameter("id");
		Blog blog = blogService.queryBlog(blogId);
		if (blog == null)
		{
			throw new BusinessException("当前请求记录不存在");
		}
		
		if (isVideoBlogType(blog.getType())) {
			mv.addObject("innerPage", "blog/view_video");
		} else {
			mv.addObject("innerPage", "blog/view");
		}
		
		mv.addObject("blog", blog);
		//是否获取显示评论
		if (blog.getCanComment())
		{
			List<Comment> comments = commentService.queryCommentsByObject(blog.getId(), blog.getType(), 0, 100);
			mv.addObject("comments", comments);
		}
		return mv;
	}
	
	@RequestMapping("/blog/view_video.htm")
	@RoleControl("BLOG_VIEW")
	public ModelAndView viewVideo(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "blog/view_video");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		String blogId = request.getParameter("id");
		Blog blog = blogService.queryBlog(blogId);
		if (blog == null)
		{
			throw new BusinessException("当前请求记录不存在");
		}
		mv.addObject("blog", blog);
		return mv;
	}

	/**
	 * 进入添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/blog/toAdd.htm")
	@RoleControl("BLOG_ADD")
	public ModelAndView toAdd(HttpServletRequest request, HttpSession session)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		String type = request.getParameter("type");
		Menu menu = cachedMenuService.queryMenuByName(type);
		if (StringUtils.isNotBlank(menu.getMediaType()))
		{
			mv.addObject("innerPage", "blog/add/" + menu.getMediaType());
		}
		else
		{
			mv.addObject("innerPage", "blog/add/default");
		}
		Blog blog = new Blog();
		blog.setType(type);

		String accountId = (String) session.getAttribute("accountId");
		Account account = accountService.getAccountById(accountId);
		blog.setAuthor(account.getNickname());
		mv.addObject("blog", blog);
		List<SelectVo> selectVos = blogBusiness.queryMenuSelectVos(request);
		mv.addObject("selectVos", selectVos);
		return mv;
	}
	

	//保存新增
	@RequestMapping("/blog/add.htm")
	@RoleControl("BLOG_ADD")
	public ModelAndView add(HttpServletRequest request, @ModelAttribute("blog") Blog blog,
			BlogMediaForm blogMediaForm)
	{
		Menu menu = cachedMenuService.queryMenuByName(blog.getType());
		blog.setMediaType(menu.getMediaType());


		if (StringUtils.isNotBlank(blog.getCoverImage()))
		{
			Pattern p = Pattern.compile("<img.*src=\"([^\\s]*)\"");
			Matcher m = p.matcher(blog.getCoverImage());
			String imgRelPath = null;
			while (m.find())
			{
				imgRelPath = m.group(1);
				break;
			}
			blog.setCoverImage(imgRelPath);
		}
		
		//根据mediaType对类型进行处理
		if (StringUtils.endsWithIgnoreCase(MediaType.LIVE.name(), blog.getMediaType()))
		{
			String liveSrc = GmxxConstant.STORAGE_VIDEO_URL + blogMediaForm.getLiveIp() + ".m3u8";
			blog.setContent(liveSrc);
			String coverImage = liveSrc.replace("/gmxx/", "/videocut/gmxx/") + ".jpg";
			blog.setCoverImage(coverImage);
		}
		if (StringUtils.endsWithIgnoreCase(MediaType.VIDEO.name(), blog.getMediaType()))
		{
			String videoSrc = parseVideoSrc(blog.getContent());
			blog.setContent(videoSrc);
			String coverImage = videoSrc.replace("/gmxx/", "/videocut/gmxx/") + ".jpg";
			blog.setCoverImage(coverImage);
		}
		else if (StringUtils.isNotBlank(blog.getContent()))
		{
			String newContent = fillVideoPosterImg(blog.getContent());
			blog.setContent(newContent);
		}

		String blogId = blogBusiness.addBlog(blog, getCurrentAccountId());
		//特殊处理直播资源
		if (StringUtils.equalsIgnoreCase(MediaType.LIVE.name(), menu.getMediaType()))
		{
			LiveVideo liveVideo = new LiveVideo();
			liveVideo.setBlogId(blogId);
			liveVideo.setIp(blogMediaForm.getLiveIp());
			liveVideo.setName(blog.getTitle());
			if (blogMediaForm.isOpen())
			{
				liveVideo.setStatus(LiveStatus.ON);
				liveVideoService.startLiveVideo(liveVideo);
			}
			else
			{
				liveVideo.setStatus(LiveStatus.OFF);
			}
			liveVideoService.addLiveVideo(liveVideo);
		}
		return new ModelAndView("redirect:/manager/blog/list.htm?type=" + URLEncoder.encode(blog.getType()));
	}
	

	@RequestMapping("/blog/delete_record")
	@RoleControl("BLOG_DELETE")
	public ModelAndView delete(HttpServletRequest request)
	{
		String type = request.getParameter("type");
		String blogId = request.getParameter("id");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		blogService.deleteBlog(blogId);
		return new ModelAndView("redirect:/manager/blog/list.htm?type=" + URLEncoder.encode(type));
	}

	@RequestMapping("/blog/toModify.htm")
	@RoleControl("BLOG_EDIT")
	public ModelAndView toModify(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		System.out.println(request.getRequestURI() + "," + request.getRequestURL().toString());

		String blogId = request.getParameter("id");
		Blog blog = blogService.queryBlog(blogId);
		if (blog == null)
		{
			throw new BusinessException("当前请求记录不存在");
		}
		Menu menu = cachedMenuService.queryMenuByName(blog.getType());
		
		if (StringUtils.isNotBlank(menu.getMediaType()))
		{
			mv.addObject("innerPage", "blog/edit/" + menu.getMediaType());
		}
		else
		{
			mv.addObject("innerPage", "blog/edit/default");
		}
		mv.addObject("blog", blog);
		//live video
		if (StringUtils.equalsIgnoreCase(menu.getMediaType(), MediaType.LIVE.name()))
		{
			LiveVideo liveVideo = liveVideoService.queryLiveVideoByBlogId(blogId);
			mv.addObject("liveVideo", liveVideo);
			mv.addObject("isOpen", liveVideo.getStatus() == LiveStatus.ON);
		}
		List<SelectVo> selectVos = blogBusiness.queryMenuSelectVos(request);
		mv.addObject("selectVos", selectVos);
		return mv;
	}
	


	//保存修改
	@RequestMapping("/blog/modify.htm")
	@RoleControl("BLOG_EDIT")
	public ModelAndView modify(HttpServletRequest request, @ModelAttribute("blog") Blog blog,
			BlogMediaForm blogMediaForm)
	{
		Blog dbBlog = blogService.queryBlog(blog.getId());
		if (dbBlog == null)
		{
			throw new BusinessException("当前请求记录不存在");
		}
		dbBlog.setNeedLogin(blog.getNeedLogin());
		dbBlog.setCanComment(blog.getCanComment());
		dbBlog.setTitle(blog.getTitle());
		dbBlog.setSubject(blog.getSubject());
		dbBlog.setType(blog.getType());
		dbBlog.setWeight(blog.getWeight());
		if (null != blog.getCoverImage())
		{
			Pattern p = Pattern.compile("<img.*src=\"([^\\s]*)\"");
			Matcher m = p.matcher(blog.getCoverImage());
			String imgRelPath = null;
			while (m.find())
			{
				imgRelPath = m.group(1);
				break;
			}
			dbBlog.setCoverImage(imgRelPath);
		}

		if (StringUtils.endsWithIgnoreCase(MediaType.LIVE.name(), dbBlog.getMediaType()))
		{
			String liveSrc = GmxxConstant.STORAGE_VIDEO_URL + blogMediaForm.getLiveIp() + ".m3u8";
			blog.setContent(liveSrc);
			String coverImage = liveSrc.replace("/gmxx/", "/videocut/gmxx/") + ".jpg";
			blog.setCoverImage(coverImage);
		}
		else if (StringUtils.equalsIgnoreCase(MediaType.VIDEO.name(), dbBlog.getMediaType()))
		{
			String videoSrc = parseVideoSrc(blog.getContent());
			dbBlog.setContent(videoSrc);

			String coverImage = videoSrc.replace("/gmxx/", "/videocut/gmxx/") + ".jpg";
			dbBlog.setCoverImage(coverImage);
		}
		else if (StringUtils.isNotBlank(blog.getContent())) 
		{
			String newContent = fillVideoPosterImg(blog.getContent());
			dbBlog.setContent(newContent);
		}
		//dbBlog.setAuthor(blog.getAuthor());
		dbBlog.setUpdateDate(new Date());
		blogService.modifyBlog(dbBlog);
		Menu menu = cachedMenuService.queryMenuByName(dbBlog.getType());
		if (StringUtils.equalsIgnoreCase(MediaType.LIVE.name(), menu.getMediaType()))
		{
			LiveVideo liveVideo = liveVideoService.queryLiveVideoByBlogId(blog.getId());
			liveVideo.setIp(blogMediaForm.getLiveIp());
			liveVideo.setName(blog.getTitle());
			if (blogMediaForm.isOpen())
			{
				if (liveVideo.getStatus() == LiveStatus.OFF) //原来是关闭状态，调用脚本启动
				{
					liveVideoService.startLiveVideo(liveVideo);
				}
				liveVideo.setStatus(LiveStatus.ON);
			}
			else
			{
				if (liveVideo.getStatus() == LiveStatus.ON) //原来开启状态，现在关闭
				{
					liveVideoService.stopLiveVideo(liveVideo);
				}
				liveVideo.setStatus(LiveStatus.OFF);
			}
			liveVideoService.updateLiveVideo(liveVideo);
		}
		return new ModelAndView("redirect:/manager/blog/list.htm?type=" + URLEncoder.encode(blog.getType()));
	}
	
	
	static String fillVideoPosterImg(String content) {
		Pattern p = Pattern.compile("(<video.*\")(><source src)");
		Matcher m = p.matcher(content);
		while (m.find())
		{
			String ss = m.group(1);
			//1.获取视频SRC
			Pattern p1 = Pattern.compile("<video.*src=\"([^\\s]*)\"");
			Matcher m1 = p1.matcher(ss);

			String imgSrc = "";
			while (m1.find()) {
				imgSrc = m1.group(1);
			}
			
			imgSrc = imgSrc.replace("/gmxx/", "/videocut/gmxx/") + ".jpg";
			
			//说明没有更新
			if (ss.contains("poster=")) {
				continue;
			} else {//新增
				String newVideo = ss + " poster=\"" + imgSrc + "\"";
				content = content.replace(ss, newVideo);
			}
		}
		return content;
	}
	
	static String parseVideoSrc(String content) {
		Pattern p = Pattern.compile("<video.*<source src=\"(.*)\"\\s.*</video>");
		Matcher m = p.matcher(content);
		String videoSrc = "";
		while (m.find())
		{
			videoSrc = m.group(1);
			break;
		}
		return videoSrc;
	}
	
	//TODO：调整为配置
	static boolean isVideoBlogType(String type)
	{
		List<String> videoBlogTypes = new ArrayList<String>();
		videoBlogTypes.add("语文");
		videoBlogTypes.add("英语");
		videoBlogTypes.add("数学");
		videoBlogTypes.add("综合");
		videoBlogTypes.add("每周视讯");
		videoBlogTypes.add("校园人物");
		videoBlogTypes.add("校宣传片");
		return null != type && videoBlogTypes.contains(type);
	}

	@RequestMapping("/blog/mytask/list.htm")
	public ModelAndView myTask(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "blog/mytask");
		return mv;
	}

	@RequestMapping(value = "/blog/mytask/list_data.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object mytaskListData(DataTableReqDto dataTableReqDto, HttpServletRequest request)
	{
		String search = request.getParameter("search[value]");
		dataTableReqDto.setSearchKey(search);
		dataTableReqDto.setSql(
				"select t.applyName,t.subType,t.status,t.createDate,t.id from WorkFlowTask t,WorkFlowNode n where t.objectType='内容审核' and t.nodeId=n.id and t.nodeId in ( :nodeIds ) and t.finished=0 ");
		dataTableReqDto.setOrderBy("order by t.createDate desc,t.id");
		dataTableReqDto.setSearchField("t.applyName,t.subType,t.status");
		dataTableReqDto.setParam("nodeIds", workFlowService.getMyRangeNodeIds(getCurrentAccountId()));
		DataTablesRespDto respDto = DataTableHelper.execute(dataTableReqDto, baseDao);
		return respDto;
	}

	/**
	 * 审批页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/blog/audit/view.htm")
	public ModelAndView auditView(HttpServletRequest request, @RequestParam String id, @RequestParam String eventType)
	{
		ModelAndView mv = new ModelAndView("/manager/dialog_templ");
		mv.addObject("innerPage", "workflow/dialog/audit_view");
		WorkFlowTask task = workFlowService.queryTaskByObjectId(id);
		mv.addObject("task", task);
		mv.addObject("eventType", eventType);
		return mv;
	}

	@RequestMapping(value = "/blog/audit/submit.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonData doAudit(HttpServletRequest request, AuditVo auditVo)
	{
		auditVo.setAccountId(getCurrentAccountId());
		workFlowService.signal(auditVo);
		return JsonData.success();
	}


}