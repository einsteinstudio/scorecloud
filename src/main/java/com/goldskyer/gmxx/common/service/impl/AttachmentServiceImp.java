package com.goldskyer.gmxx.common.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.common.utils.DateUtil;
import com.goldskyer.common.utils.FileUtil;
import com.goldskyer.common.utils.MD5Util;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.service.IniService;
import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.common.entities.Attachment;
import com.goldskyer.gmxx.common.service.AttachmentService;

@Service
public class AttachmentServiceImp extends CoreServiceImp implements AttachmentService
{
	@Autowired
	private IniService iniService;
	/**
	 * 已存在文件则直接跳过，返回地址
	 * @param attachment
	 * @param mf
	 * @return
	 */
	@Transactional
	public String addAttachment(Attachment attachment, MultipartFile mp)
	{
		try
		{
			attachment.setId(mp.getSize() + mp.getOriginalFilename());
			Object exist=baseDao.query(Attachment.class,attachment.getId());
			if(exist!=null)
			{
				log.warn("已经存在文件了。ID：" + attachment.getId());
				return attachment.getId();
			}
			attachment.setFileSize(mp.getSize());
			attachment.setCreateDate(new Date());
			attachment.setContentType(mp.getContentType());
			attachment.setDigest(MD5Util.getMD5(mp.getBytes()));
			attachment.setFileId(attachment.getId());
			attachment.setFilename(mp.getOriginalFilename());
			attachment.setFileType(
					com.goldskyer.gmxx.common.utils.FileUtil.getFileTypeByFileName(attachment.getFilename()));
			//上传文件路径的规划
			String relativePath = "/"
					+ EnvParameter.get().getDomain().substring(0, EnvParameter.get().getDomain().indexOf("."))
					+ "/" + DateUtil.date2String(new Date(), "yyyyMM");
			String storagePath = iniService.getIniValue("storagePath") + relativePath;
			if (EnvParameter.get().getEnv().equals("dev"))
			{
				storagePath = "/data/www/storage";
			}
			FileUtil.mkdir(storagePath);
			IOUtils.copy(mp.getInputStream(), new FileOutputStream(new File(storagePath + "/" + attachment.getId())));
			String storgaeDomain = iniService.getIniValue("storageDomain");
			attachment.setPath("//" + storgaeDomain + relativePath + "/" + attachment.getId());
		}
		catch (FileNotFoundException e)
		{
			log.fatal(e.getMessage(), e);
			throw new BusinessException("上传附件时候文件不存在");
		}
		catch (IOException e)
		{
			log.fatal(e.getMessage(), e);
			throw new BusinessException("上传附件异常");
		}
		String id = baseDao.add(attachment);
		return id;
	}

	public List<Attachment> queryImagesAttachmentsByPicUrl(String picUrl)
	{
		String[] picUrls = picUrl.split("\\|");
		Map paraMap = new HashMap();
		paraMap.put("ids", picUrls);
		return baseDao.query(
				"from Attachment where id in :ids and fileType in ('JPG','JPEG','PNG','BMP') order by createDate,id",
				paraMap);
	}

	public List<Attachment> queryOtherAttachmentsByPicUrl(String picUrl)
	{
		String[] picUrls = picUrl.split("\\|");
		Map paraMap = new HashMap();
		paraMap.put("ids", Arrays.asList(picUrls));
		return baseDao.query(
				"from Attachment where id in  (:ids) and fileType not in ('JPG','JPEG','PNG','BMP') order by createDate,id",
				paraMap);
	}
}
