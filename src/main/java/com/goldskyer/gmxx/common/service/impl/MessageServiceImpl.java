package com.goldskyer.gmxx.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.common.entities.Message;
import com.goldskyer.gmxx.common.entities.MessageText;
import com.goldskyer.gmxx.common.enums.MessageStatus;
import com.goldskyer.gmxx.common.service.MessageService;

@Service
public class MessageServiceImpl extends CoreServiceImp implements MessageService
{
	/**
	 * 发送站内信
	 * @param message
	 * @return
	 */
	@Transactional
	public boolean sendMessage(MessageText text, String fromAccountId, String toAccountId)
	{
		List<String> toAccountIds=new ArrayList<>();
		toAccountIds.add(toAccountId);
		sendMessage(text, fromAccountId, toAccountIds);
		return true;
	}

	@Transactional
	public boolean sendMessage(MessageText text, String fromAccountId, List<String> toAccountIds)
	{
		String mId = baseDao.add(text);
		text.setId(mId);
		for(String aid:toAccountIds)
		{
			if (!StringUtils.equals(aid, fromAccountId))
			{
				Message message = new Message();
				message.setTextId(text.getId());
				message.setRecvId(aid);
				message.setSendId(fromAccountId);
				message.setStatus(MessageStatus.未读);
				baseDao.add(message);
			}
		}
		return true; 
	}

	/**
	 * 读取一条消息
	 * @param mId
	 * @param accountId
	 */
	@Transactional
	public void readMessage(String mId, String accountId)
	{
		Message message = baseDao.query(Message.class, mId);
		if (message == null)
		{
			throw new BusinessException("消息不存在");
		}
		if (!StringUtils.equals(message.getRecvId(), accountId))
		{
			throw new BusinessException("无法对他人消息设置已读");
		}
		int i = baseDao.execute("update Message set status=? where id=?", MessageStatus.已读,
				mId);
	}

	public void readMessageByBlogId(String blogId, String accountId)
	{
		List<Message> messages = baseDao.query(
				"select m from MessageText t,Message m where t.blogId=? and m.textId=t.id and m.status=? and m.recvId=?",
				blogId, MessageStatus.未读, accountId);
		if (messages.size() > 0)
		{
			for (Message m : messages)
				readMessage(m.getId(), accountId);
		}
	}

	public long queryUnReadMessageCount(String accountId)
	{
		return baseDao.count("select count(1) from Message m where m.recvId=? and m.status=?", accountId,
				MessageStatus.未读);
	}
	
}
