package com.goldskyer.gmxx.weixin.dto;

import java.io.Serializable;
import java.util.List;

public class Button implements Serializable
{
	private String name;
	private String media_id;
	private String type;
	private String key;
	private String url;

	private List<Button> sub_button;

	public Button(String name, String type)
	{
		this.name = name;
		this.type = type;
	}

	public Button(String name, String type, String url)
	{
		this.name = name;
		this.type = type;
		this.url = url;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMedia_id()
	{
		return media_id;
	}

	public void setMedia_id(String media_id)
	{
		this.media_id = media_id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public List<Button> getSub_button()
	{
		return sub_button;
	}

	public void setSub_button(List<Button> sub_button)
	{
		this.sub_button = sub_button;
	}

}
