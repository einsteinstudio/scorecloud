package com.goldskyer.gmxx.manager.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.common.entities.Attachment;

/**
 * 通用文件上传插件
 * @author jintianfan
 *
 */
@Controller("managerUploadController")
@RequestMapping("/manager/upload")
@SuppressWarnings(
{ "rawtypes", "unchecked", "deprecation" })
public class UploadController extends BaseManagerController
{
	@RequestMapping(value = "/addImage.htm", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonData handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		JsonData jsonData = JsonData.success();
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = mr.getFiles("fileList");
		String picUrl="";
		for (MultipartFile mp : fileList)
		{
			Attachment attachment = new Attachment();
			attachment.setAccountId(getCurrentAccountId());
			String id = attachmentService.addAttachment(attachment, mp);
			picUrl += id + "|";
		}
		jsonData.getData().put("picUrl", picUrl);
		return jsonData;
	}
}