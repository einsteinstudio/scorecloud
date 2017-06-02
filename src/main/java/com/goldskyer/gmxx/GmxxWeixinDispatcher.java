package com.goldskyer.gmxx;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldskyer.core.dao.WeixinDispatcher;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.entities.WeixinMessage;
import com.goldskyer.core.service.BlogService;
import com.goldskyer.core.weixin.response.ArticleDto;
import com.goldskyer.core.weixin.response.NewsWeixinResponseDto;
import com.goldskyer.core.weixin.response.TextWeixinResponseDto;
import com.goldskyer.core.weixin.response.WeixinResponseDto;
import com.goldskyer.gmxx.common.constants.WeixinConstant;

@Component
public class GmxxWeixinDispatcher implements WeixinDispatcher{
	@Autowired
	private BlogService blogService;

	@Override
	public WeixinResponseDto dispatch(WeixinMessage weixinMessage) {

		if (weixinMessage.msgType.equals(WeixinConstant.MSG_TYPE_TEXT))
		{
			return buildTextInfo(weixinMessage, "欢迎关注光明小学微信公众号，有意见可直接回复内容。");
		} 
		else if (weixinMessage.msgType.equals(WeixinConstant.MSG_TYPE_EVENT)) {
			if (weixinMessage.event.equals(WeixinConstant.EVENT_TYPE_SUBSCRIBE)) {
				return buildSUbscribeInfo(weixinMessage);
			} else if (weixinMessage.event
					.equals(WeixinConstant.EVENT_TYPE_SUBSCRIBE)) {
				return buildTextInfo(weixinMessage, "欢迎关注光明小学微信公众号，有意见可直接回复内容。");
			}
			else if (weixinMessage.eventKey.equals("fankui"))
			{
				return buildTextInfo(weixinMessage, "欢迎您提出宝贵意见，直接回复意见给本公众号即可。");
			}
		}
		return buildTextInfo(weixinMessage, "欢迎您提出宝贵意见，直接回复意见给本公众号即可。");
	}
	
	
	public TextWeixinResponseDto buildTextInfo(WeixinMessage weixinMessage, String content)
	{
		TextWeixinResponseDto textResponseDto = new TextWeixinResponseDto();
		textResponseDto.setFromUserName(weixinMessage.toUserName);
		textResponseDto.setToUserName(weixinMessage.fromUserName);
		textResponseDto.setCreateDate(weixinMessage.createDate);
		textResponseDto.content = content;
		return textResponseDto;
	}

	public NewsWeixinResponseDto buildSUbscribeInfo(WeixinMessage weixinMessage)
	{
		Blog blog = blogService.queryBlog("welcome");
		NewsWeixinResponseDto dto = new NewsWeixinResponseDto();
		dto.setFromUserName(weixinMessage.toUserName);
		dto.setToUserName(weixinMessage.fromUserName);
		dto.setCreateDate(weixinMessage.createDate);
		dto.setDescription(StringUtils.trim(blog.getSubject()));
		dto.setPicUrl(StringUtils.trim(blog.getCoverImage()));
		dto.setTitle(blog.getTitle());
		dto.setPicUrl("http://gmxx.goldskyer.com/front/index.htm");
		dto.setArticleCount(1);
		ArticleDto articleDto = new ArticleDto();
		articleDto.setDescription(StringUtils.trim(blog.getSubject()));
		articleDto.setTitle(blog.getTitle());
		articleDto.setUrl("http://gmxx.goldskyer.com/front/index.htm");
		articleDto.setPicUrl(blog.getCoverImage());
		dto.getArticleDtos().add(articleDto);
		return dto;
	}

}
