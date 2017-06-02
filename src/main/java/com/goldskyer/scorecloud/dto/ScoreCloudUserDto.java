package com.goldskyer.scorecloud.dto;

/**
 * 用户对象
 * @author jintianfan
 *
 */
public class ScoreCloudUserDto
{
	private String accountId;
	private String username;
	private String nickname;//名字

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

}
