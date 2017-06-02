package com.goldskyer.gmxx.front.business.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.gmxx.common.entities.MessageText;
import com.goldskyer.gmxx.common.service.MessageService;
import com.goldskyer.gmxx.front.business.MessageBusiness;

@Service
public class MessageBusinessImpl implements MessageBusiness
{
	@Autowired
	private AccountService accountService;
	@Autowired
	private static Log log = LogFactory.getLog(MessageBusinessImpl.class);

	@Autowired
	private MessageService messageService;

	public void sendMessageToAll(final MessageText text, final String fromAccountId)
	{
		final String domain = EnvParameter.get().getDomain();
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try{
					List<String> accountIds = accountService.queryAllValidAccountIds(domain);
				messageService.sendMessage(text, fromAccountId, accountIds);
				}catch(Exception e)
				{
					log.fatal(e.getMessage(), e);
				}
			}
		}).start();

	}
}
