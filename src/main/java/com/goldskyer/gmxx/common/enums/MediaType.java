package com.goldskyer.gmxx.common.enums;

public enum MediaType
{
	LIVE("直播"), VIDEO("视频"), DEFAULT("默认"), RICHTEXT("富文本");
	private String desc;

	MediaType(String desc)
	{
		this.desc = desc;
	}

	public String getDesc()
	{
		return this.desc;
	}

}
