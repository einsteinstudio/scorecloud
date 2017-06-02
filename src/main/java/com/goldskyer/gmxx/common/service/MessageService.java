package com.goldskyer.gmxx.common.service;

import java.util.List;

import com.goldskyer.gmxx.common.entities.MessageText;

public interface MessageService
{
	public boolean sendMessage(MessageText text, String fromAccountId, String toAccountId);

	public boolean sendMessage(MessageText text, String fromAccountId, List<String> toAccountIds);

	public void readMessage(String mId, String accountId);

	public long queryUnReadMessageCount(String accountId);

	public void readMessageByBlogId(String blogId, String accountId);
}
