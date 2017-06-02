package com.goldskyer.gmxx.common.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.utils.WebUtils;
import com.goldskyer.gmxx.BaseController;
import com.goldskyer.gmxx.common.entities.LiveVideo;
import com.goldskyer.gmxx.common.service.LiveVideoService;

@Controller
@RequestMapping("/liveVideo")
public class LiveVideoController extends BaseController
{
	@Autowired
	private LiveVideoService liveVideoService;

	@RequestMapping("/list.htm")
	public Object getLiveVideos(HttpServletRequest request, HttpServletResponse response)
	{
		List<LiveVideo> liveVideos = liveVideoService.queryLiveVideos();
		StringBuilder sb = new StringBuilder();
		for (LiveVideo lv : liveVideos)
		{
			sb.append(lv.getIp());
		}
		WebUtils.renderText(request, response, sb.toString(), null);
		return null;
	}

	@RequestMapping(value = "/checkAuthCode.json", produces = "application/json")
	@ResponseBody
	public JsonData checkAuthCode(@RequestParam String blogId, @RequestParam String authCode,
			HttpServletRequest request)
	{
		if (liveVideoService.checkAuthCode(blogId, authCode))
		{
			request.getSession().setAttribute("blogId", blogId);
			return JsonData.success();
		}
		else
		{
			return JsonData.failure();
		}
	}
}
