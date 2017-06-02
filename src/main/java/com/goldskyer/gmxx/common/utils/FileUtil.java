package com.goldskyer.gmxx.common.utils;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class FileUtil
{
	/**
	 * 根据文件名获取文件后缀
	 * @param fileName
	 * @return
	 */
	public static String getFileTypeByFileName(String fileName)
	{
		if (StringUtils.isBlank(fileName))
			return StringUtils.EMPTY;
		if (fileName.indexOf(".") > 0)
			return StringUtils.upperCase(fileName.substring(fileName.lastIndexOf(".") + 1));
		return StringUtils.EMPTY;
	}

	public static String bytes2kb(long bytes)
	{
		BigDecimal filesize = new BigDecimal(bytes);
		BigDecimal megabyte = new BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
		if (returnValue > 1)
			return (returnValue + "MB");
		BigDecimal kilobyte = new BigDecimal(1024);
		returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
		return (returnValue + "KB");
	}

	public static void main(String[] args)
	{
		System.out.println(getFileTypeByFileName("adc.csad.pdf"));
		System.out.println(bytes2kb(2000000000000l));
	}
}
