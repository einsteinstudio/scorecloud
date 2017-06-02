package com.goldskyer.gmxx.front.business;

import com.goldskyer.gmxx.common.entities.MessageText;

public interface MessageBusiness
{
	public void sendMessageToAll(final MessageText text, final String fromAccountId);
}
