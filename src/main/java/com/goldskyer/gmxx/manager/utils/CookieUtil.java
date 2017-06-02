package com.goldskyer.gmxx.manager.utils;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.gmxx.common.dtos.Ssn;
import com.goldskyer.gmxx.common.utils.AESUtil;

public class CookieUtil
{
	private static Log log = LogFactory.getLog(CookieUtil.class);

	public static String getCookieValue(HttpServletRequest request, String name)
	{
		try
		{
			String ntesCookieValue = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
			{
				for (Cookie cookie : cookies)
				{
					if ((cookie.getName()).equals(name))
					{
						ntesCookieValue = cookie.getValue();
						break;
					}
				}
			}
			cookies = null;
			return ntesCookieValue;
		}
		catch (Exception e)
		{
			log.error("", e);
			return null;
		}
	}

	public static void setCookie(String cookieName, String cookieValue, String domain, HttpServletResponse response)
	{
		try
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cookieName);
			stringBuffer.append("=");
			stringBuffer.append(cookieValue);
			stringBuffer.append("; path=/; domain=");
			stringBuffer.append(domain);
			stringBuffer.append("; HttpOnly");
			log.info("即将要设置的cookie的值为:" + stringBuffer.toString());
			response.addHeader("Set-Cookie", stringBuffer.toString());
		}
		catch (Exception e)
		{
			log.fatal("设置cookie出错" + e.getMessage(), e);
		}
	}

	public static Ssn getSsnFromCookie(HttpServletRequest request)
	{
		try
		{
			String val = CookieUtil.getCookieValue(request, "SSN_TOKEN");
			if (StringUtils.isBlank(val))
				return new Ssn();
			String source = AESUtil.Decrypt(val, AESUtil.KEY);
			String[] items = source.split("\\|");
			Ssn ssn = new Ssn();
			ssn.setAccountId(items[0]);
			ssn.setDomain(items[1]);
			ssn.setIp(items[2]);
			ssn.setLoginDate(new Date(Long.valueOf(items[3])));
			return ssn;
		}
		catch (Exception e)
		{
			return new Ssn();
		}

	}
}
