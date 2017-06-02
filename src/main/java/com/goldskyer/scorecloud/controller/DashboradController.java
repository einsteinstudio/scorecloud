package com.goldskyer.scorecloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.scorecloud.form.TargetScoreForm;

@Controller
@RequestMapping("/scorecloud/dashboard")
public class DashboradController extends BaseScoreCloudController
{
	@RequestMapping(value = "/welcome.htm")
	public ModelAndView welcome(TargetScoreForm targetScoreForm, HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/scorecloud/dashboard/welcome");
		return mv;
	}
}
