package com.goldskyer.gmxx.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.goldskyer.gmxx.common.entities.Attachment;

public interface AttachmentService
{
	public String addAttachment(Attachment attachment, MultipartFile mp);

	public List<Attachment> queryImagesAttachmentsByPicUrl(String picUrl);

	public List<Attachment> queryOtherAttachmentsByPicUrl(String picUrl);
}
