package com.goldskyer.gmxx.schedules;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldskyer.core.controllers.CoreBaseController;
import com.goldskyer.core.dto.JsonData;
@Controller
@RequestMapping("/schedule")
public class SyncBlogController extends CoreBaseController {
	
	@RequestMapping(value="/syncBlog.json",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object syncBlog(HttpServletRequest request){
		return JsonData.success();
    } 
}
