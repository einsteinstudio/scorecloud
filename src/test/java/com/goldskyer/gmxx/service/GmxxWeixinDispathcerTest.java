package com.goldskyer.gmxx.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.goldskyer.common.exceptions.NetException;
import com.goldskyer.common.utils.HttpUtil;
import com.goldskyer.gmxx.weixin.dto.Button;

import net.sf.json.JSONObject;

public class GmxxWeixinDispathcerTest
{
	@Test
	public void test()
	{
		String content = HttpUtil.openUrlReturnMoreMessage(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd78675ef4a833a68&secret=a2c77f37a4d879ab9847999c2cd2d571");
		System.out.println(content);
	}



	@Test
	public void setMenu() throws UnsupportedEncodingException
	{
		String host = "http://gmxxwgw.szgm.edu.cn";
		String content = HttpUtil.openUrlReturnMoreMessage(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd78675ef4a833a68&secret=a2c77f37a4d879ab9847999c2cd2d571");
		JSONObject tokenJson = JSONObject.fromObject(content);
		System.out.println("token:" + tokenJson.getString("access_token"));
		
		
		Map body = new HashMap<>();
		Button b1 = new Button("走进光小", "");
		List<Button> b1Subs = new ArrayList<>();
		b1Subs.add(new Button("光小微网", "view", host + "/front/index.htm"));
		b1Subs.add(new Button("光小名片", "view", "http://v6.rabbitpre.com/m/riqQFrJ"));
		b1Subs.add(new Button("联系我们", "view", host + "/html/lianxi.html"));

		b1.setSub_button(b1Subs);
		Button b2 = new Button("魅力光小", "");
		List<Button> b2Subs = new ArrayList<>();
		b2Subs.add(new Button("特色课程", "view",
				host + "/front/blog/list_image.htm?blogType=%e7%89%b9%e8%89%b2%e8%af%be%e7%a8%8b"));
		b2Subs.add(new Button("学生社团", "view", host + "/front/team/list.htm?blogType=学生社团"));
		b2Subs.add(new Button("名师风采", "view", "http://v7.rabbitpre.com/m/uEfqyzVRx"));
		b2.setSub_button(b2Subs);
		Button b3 = new Button("智慧光小", "");
		List<Button> b3Subs = new ArrayList<>();
		b3Subs.add(new Button("光小电视台", "view",
				host + "/front/tv/list.htm?blogType=每周视讯"));
		b3Subs.add(new Button("直播光小", "view", host + "/front/tv/list.htm?blogType=课堂直播"));
		b3Subs.add(
				new Button("微课中心", "view", host + "/front/tv/list.htm?blogType=语文"));
		b3Subs.add(new Button("学习中心", "view",
				host + "/front/learnCenter/list.htm?blogType=学生"));
		b3Subs.add(new Button("来宾Wi-Fi", "view",
				"http://2.2.2.1/wx.html?href=6e3d6e69636b6e616d6526753d6f70656e696426743d31343132303735303033266c3d3332&id=gh_f4a51c3db9e6"));
		
		Button fankuiBtn = new Button("意见反馈", "click",
				"http://www.baidu.com");
		fankuiBtn.setKey("fankui");
		b1Subs.add(fankuiBtn);
		b3.setSub_button(b3Subs);

		List<Button> btns = new ArrayList<>();
		btns.add(b1);
		btns.add(b2);
		btns.add(b3);

		body.put("button", btns);
		System.out.println("body:" + JSONObject.fromObject(body));

		String result = postToUrlReturnMoreMessage(
						" https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
								+ tokenJson.getString("access_token"),
				JSONObject.fromObject(body).toString(),
				"UTF-8", 20000,
				20000);
		System.out.println("result:" + result);
	}

	/**
	 * 关闭校园公众号的栏目
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void dropMenu() throws UnsupportedEncodingException
	{
		String content = HttpUtil.openUrlReturnMoreMessage(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd78675ef4a833a68&secret=a2c77f37a4d879ab9847999c2cd2d571");
		JSONObject tokenJson = JSONObject.fromObject(content);
		System.out.println("token:" + tokenJson.getString("access_token"));
		String result = postToUrlReturnMoreMessage(
				"https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="
						+ tokenJson.getString("access_token"),
				null, "UTF-8", 2000, 2000);
		System.out.println("result:" + result);

	}

	public static String postToUrlReturnMoreMessage(String strUrl, String postData, String charSet,
			int connectTimeout, int readTimeout) throws UnsupportedEncodingException
	{
		InputStream is = null;
		OutputStream os = null;
		StringBuilder postParams = new StringBuilder();
		postParams.append(postData);
		try
		{
			URL webURL = new URL(strUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) webURL.openConnection();
			httpURLConnection.setDoOutput(true);// 打开写入属性  
			httpURLConnection.setDoInput(true);// 打开读取属性  
			httpURLConnection.setRequestMethod("POST");// 设置提交方法  
			httpURLConnection.setConnectTimeout(connectTimeout);// 连接超时时间  
			httpURLConnection.setReadTimeout(readTimeout);
			httpURLConnection.connect();
			os = httpURLConnection.getOutputStream();
			os.write(postParams.toString().getBytes());

			os.flush();

			if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
				throw new NetException("Connect to url[" + strUrl + "] error, response code is "
						+ httpURLConnection.getResponseCode() + ",params:" + postData);
			}

			is = httpURLConnection.getInputStream();
			return HttpUtil.getReturnValueFromInputStream(is, charSet);
		}
		catch (Exception e)
		{
			throw new NetException("Connect to url[" + strUrl + "] error,params:" + postData);
		}
		finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				}
				catch (IOException e1)
				{
				}
			}
			if (os != null)
			{
				try
				{
					os.close();
				}
				catch (IOException e1)
				{

				}
			}
		}
	}

}
