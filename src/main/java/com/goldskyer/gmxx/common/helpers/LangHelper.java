package com.goldskyer.gmxx.common.helpers;

import java.util.HashMap;
import java.util.Map;

import com.goldskyer.gmxx.common.vos.Lang;

/**
 * 本地化类
 * @author jintianfan
 *
 */
public class LangHelper
{
	public static Map<String, Lang> buildLangMap()
	{
		Map<String, Lang> map = new HashMap<>();
		map.put("read", new Lang("阅读", "read"));
		map.put("author", new Lang("作者", "author"));
		map.put("source", new Lang("来源", "source"));
		map.put("clickCount", new Lang("点击数", "click Count"));
		map.put("releaseTime", new Lang("发布时间", "release Time"));
		map.put("print", new Lang("打印文章", "Print"));
		map.put("postComment", new Lang("发表评论", "Post Comment"));
		map.put("preBlog", new Lang("上一篇文章", "Pre Blog"));
		map.put("nextBlog", new Lang("下一篇文章", "Next Blog"));
		map.put("emptyResult", new Lang("没有了", "Not Exist"));
		map.put("wantSaySonthing", new Lang("我想要说些什么..", "Say sonthing.."));
		map.put("submit", new Lang("提交", "submit"));
		map.put("pleaseLoginFirst", new Lang("请您回到首页进行登录之后再评论!", "Please Login First!"));
		map.put("setHomePage", new Lang("设为首页", "Set Home Page"));
		map.put("addToFavorite", new Lang("加入收藏", "Add to Favorites"));
		map.put("contactManager", new Lang("联系站长", "Concat Manager"));
		map.put("backendLogin", new Lang("管理登录", "Manager Login"));
		map.put("dog", new Lang("用户名", "Username"));
		map.put("cat", new Lang("密码", "Password"));
		/**
		 * 注册框
		 */
		map.put("userLogin", new Lang("用户登录", "User Login"));
		map.put("username", new Lang("用户名", "Username"));
		map.put("password", new Lang("密码", "Password"));
		map.put("authCode", new Lang("验证码", "AuthCode"));
		map.put("login", new Lang("登录", "Login"));
		map.put("regist", new Lang("新用户注册", "Regist"));
		map.put("forget", new Lang("忘记密码", "Forget"));

		map.put("pleaseInputUsername", new Lang("请输入用户名", "Please input username"));
		map.put("pleaseInputPassword", new Lang("请输入密码", "Please input password"));
		map.put("pleaseInputAuthCode", new Lang("请输入验证码", "Please input Auth Code"));

		/**
		 * 首页
		 */
		map.put("week1", new Lang("星期一", "Monday"));
		map.put("week2", new Lang("星期二", "Tuesday"));
		map.put("week3", new Lang("星期三", "Wednesday"));
		map.put("week4", new Lang("星期四", "Thursday"));
		map.put("week5", new Lang("星期五", "Friday"));
		map.put("week6", new Lang("星期六", "Saturday"));
		map.put("week7", new Lang("星期日", "Sunday"));

		map.put("more", new Lang("更多", "More"));
		map.put("currentLocation", new Lang("您现在的位置", "Current Location"));
		map.put("nextAuthCode", new Lang("看不清楚，换个图片", "Next Auth Code"));
		map.put("more", new Lang("更多", "More"));
		map.put("notOpen", new Lang("功能未开放", "This is not Not Open Yet!"));
		map.put("normalArticle", new Lang("普通文章", "Normal Article"));
		map.put("latestAnnouncement", new Lang("最新公告", "Latest"));
		map.put("index", new Lang("网站首页", "Index"));

		/**
		 * 列表页面
		 */
		map.put("total", new Lang("共", "total"));
		map.put("articles", new Lang("篇文章", "Articles"));
		map.put("index", new Lang("网站首页", "Index"));
		map.put("pre", new Lang("上一页", "Pre"));
		map.put("next", new Lang("下一页", "Next"));
		map.put("first", new Lang("首页", "First"));
		map.put("last", new Lang("尾页", "Last"));

		return map;
	}
}
