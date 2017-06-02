package com.goldskyer.gmxx.manager.forms;

public class BlogMediaForm
{
	private String liveIp;
	private boolean isOpen;
	private boolean needSync;

	public String getLiveIp()
	{
		return liveIp;
	}

	public void setLiveIp(String liveIp)
	{
		this.liveIp = liveIp;
	}

	public boolean isOpen()
	{
		return isOpen;
	}

	public void setOpen(boolean isOpen)
	{
		this.isOpen = isOpen;
	}

	public boolean isNeedSync()
	{
		return needSync;
	}

	public void setNeedSync(boolean needSync)
	{
		this.needSync = needSync;
	}

}
