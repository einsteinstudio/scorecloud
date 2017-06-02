package com.goldskyer.gmxx.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.core.entities.WeixinMessage;
import com.goldskyer.core.weixin.response.NewsWeixinResponseDto;
import com.goldskyer.gmxx.GmxxWeixinDispatcher;

public class GmxxWeixinDispatcherTest extends BaseTest
{
	@Autowired
	private GmxxWeixinDispatcher dispatcher;

	@Test
	public void testWelcome()
	{
		WeixinMessage weixinMessage = new WeixinMessage();
		weixinMessage.fromUserName = "jin";
		weixinMessage.toUserName = "tain";
		weixinMessage.createDate = new Date();
		NewsWeixinResponseDto dto = dispatcher.buildSUbscribeInfo(weixinMessage);
		System.out.println(dto.toXML());
	}
}
