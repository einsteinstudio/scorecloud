package com.goldskyer.scorecloud.freemarker.component.vo;

public class OperationResultVo
{
	private String m2="";
	private String pageTitle="";
	private String operationInfo = "";
	private String nextStepInfo = "";
	private String nextStepLink = "";

	public String getM2()
	{
		return m2;
	}

	public void setM2(String m2)
	{
		this.m2 = m2;
	}

	public String getPageTitle()
	{
		return pageTitle;
	}

	public void setPageTitle(String pageTitle)
	{
		this.pageTitle = pageTitle;
	}

	public String getOperationInfo()
	{
		return operationInfo;
	}

	public void setOperationInfo(String operationInfo)
	{
		this.operationInfo = operationInfo;
	}

	public String getNextStepInfo()
	{
		return nextStepInfo;
	}

	public void setNextStepInfo(String nextStepInfo)
	{
		this.nextStepInfo = nextStepInfo;
	}

	public String getNextStepLink()
	{
		return nextStepLink;
	}

	public void setNextStepLink(String nextStepLink)
	{
		this.nextStepLink = nextStepLink;
	}

}
