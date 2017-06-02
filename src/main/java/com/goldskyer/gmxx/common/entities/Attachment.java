package com.goldskyer.gmxx.common.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "attachment")
public class Attachment
{
	@Id
	private String id;
	private String fileId; //fileSize+fileName;
	private String filename;
	private String fileType;//文件类型，大写（TXT，JPEG，DOC）
	private long fileSize;
	private String accountId;
	private Date createDate;
	private String description;
	private Integer downloadCount = 0;
	private String digest; //文件MD5签名
	private String contentType;//文件类型
	private String objectType;
	private String objectId;
	private String path;



	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public long getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(long fileSize)
	{
		this.fileSize = fileSize;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Integer getDownloadCount()
	{
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount)
	{
		this.downloadCount = downloadCount;
	}

	public String getDigest()
	{
		return digest;
	}

	public void setDigest(String digest)
	{
		this.digest = digest;
	}

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	public String getObjectType()
	{
		return objectType;
	}

	public void setObjectType(String objectType)
	{
		this.objectType = objectType;
	}

	public String getObjectId()
	{
		return objectId;
	}

	public void setObjectId(String objectId)
	{
		this.objectId = objectId;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getFileSizeStr()
	{
		return com.goldskyer.gmxx.common.utils.FileUtil.bytes2kb(fileSize);
	}
	public String getThumbImage()
	{
		if (StringUtils.isNotBlank(getPath()))
		{
			return getPath().replace("storage.goldskyer.com", "storage.goldskyer.com/thumb");
		}
		return StringUtils.EMPTY;
	}
}
