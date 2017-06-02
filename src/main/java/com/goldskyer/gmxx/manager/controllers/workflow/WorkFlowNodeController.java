package com.goldskyer.gmxx.manager.controllers.workflow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.gmxx.manager.controllers.BaseManagerController;

@Controller("managerWorkFlowNodeController")
@RequestMapping("/manager/workflow/node/")
@SuppressWarnings(
{ "rawtypes", "unchecked", "deprecation" })
public class WorkFlowNodeController extends BaseManagerController
{
	@RequestMapping("/toModify.htm")
	public ModelAndView toModify(HttpServletRequest request, @RequestParam String templateId)
	{
		ModelAndView mv = new ModelAndView("/manager/template");
		mv.addObject("innerPage", "workflow/node_toModify");
		return mv;
	}
}
