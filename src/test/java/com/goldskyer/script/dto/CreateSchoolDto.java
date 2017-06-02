package com.goldskyer.script.dto;

public class CreateSchoolDto
{
	private String domain = "smart.goldskyer.com";
	private String schoolName = "智慧小学";
	private String menuRootId = "smart";
	private String username = "smart";
	private String title = "智慧小学"; //学校标题

	public String getDomain()
	{
		return domain;
	}

	public void setDomain(String domain)
	{
		this.domain = domain;
	}

	public String getSchoolName()
	{
		return schoolName;
	}

	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}

	public String getMenuRootId()
	{
		return menuRootId;
	}

	public void setMenuRootId(String menuRootId)
	{
		this.menuRootId = menuRootId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

}
