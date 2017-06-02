package com.goldskyer.gmxx.mock.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.BaseController;
import com.goldskyer.gmxx.mock.entities.MockUrl;
import com.goldskyer.gmxx.mock.service.MockUrlService;

import net.sf.json.JSONObject;

//@Controller
//@RequestMapping("/")
public class MockController extends BaseController{
	
	@Autowired
	private MockUrlService mockUrlService;
	
	@RequestMapping(value="**" ,params="mock=html")
    public ModelAndView mockHtml(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/mock/mock_html");
		String url= request.getRequestURI();
		MockUrl mockUrl = mockUrlService.queryMockUrlByUrl(url);
		mv.addObject("mockUrl", mockUrl);
		return mv;
    } 
	
	@RequestMapping(value="**", params="mock=json",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object mockJson(HttpServletRequest request){
		String url= request.getRequestURI();
		MockUrl mockUrl = mockUrlService.queryMockUrlByUrl(url);
		JSONObject jsonObject = JSONObject.fromObject(mockUrl.getContent());
		return jsonObject;
    } 
	

	@RequestMapping(value="mock/fabu_static.htm",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JsonData staticResource(HttpServletRequest request)
	{
		JsonData jsonData =JsonData.success();
		jsonData.data.put("cmdResult", mockUrlService.executeShell("sh /data/script/fabu_static.sh"));
		return jsonData;
	}
	
	@RequestMapping(value="mock/fabu_back.htm",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JsonData back(HttpServletRequest request)
	{
		JsonData jsonData =JsonData.success();
		jsonData.data.put("cmdResult", mockUrlService.executeShell("sh /data/script/fabu_g.sh"));
		return jsonData;
	}
	
	
	
	@RequestMapping("mock/list.htm")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/mock/list");
		List list =mockUrlService.queryAllMockUrls();
		mv.addObject("mockUrls", list);
		return mv;
	}
	
	@RequestMapping("mock/json_detail.htm")
	public ModelAndView jsonDetail(@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/mock/json_detail");
		MockUrl mockUrl=mockUrlService.queryMockUrlById(id);
		mv.addObject("mockUrl",mockUrl );
		return mv;
	}
	
	@RequestMapping("mock/html_detail.htm")
	public ModelAndView htmlDetail(@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/mock/html_detail");
		MockUrl mockUrl=mockUrlService.queryMockUrlById(id);
		mv.addObject("mockUrl",mockUrl );
		return mv;
	}
	
	@RequestMapping("mock/to_add.htm")
	public ModelAndView toAdd()
	{
		return new ModelAndView("/mock/to_add");
	}
	
	@RequestMapping("mock/add.htm")
	public ModelAndView add( MockUrl mockUrl)
	{
		String id=mockUrlService.addMockUrl(mockUrl);
		return new ModelAndView("redirect:/mock/list.htm");
	}
	
	@RequestMapping("mock/delete")
    @ResponseBody
	public Object delete(@RequestParam String id)
	{
		mockUrlService.deleteMockUrl(id);
		return JsonData.success();
	}
	
	@RequestMapping("mock/to_modify")
    @ResponseBody
	public ModelAndView toModify(@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("/mock/to_modify");
		MockUrl mockurUrl = mockUrlService.queryMockUrlById(id);
		mv.addObject("mockUrl", mockurUrl);
		return mv;
	}
	
	@RequestMapping("mock/modify")
    @ResponseBody
	public ModelAndView modify(MockUrl mockUrl)
	{
		ModelAndView mv = new ModelAndView("/mock/to_modify");
		mockUrlService.modify(mockUrl);
		return new ModelAndView("redirect:/mock/list.htm");
	}

//	@InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(MockType.class, new MockTypeEditor());
//    }
	
	
}

