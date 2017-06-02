package com.goldskyer.gmxx.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.goldskyer.common.utils.HttpUtil;

public class PicDownload
{
	public static void main(String[] args) throws FileNotFoundException, IOException, Exception
	{
		for (int i = 0; i < 179; i++)
		{
			String index = String.valueOf(i);
			if (index.length() < 2)
			{
				index = "0" + index;
			}
			IOUtils.copy(HttpUtil.getInputStreamFromURL("http://sucai.heima.com/sucai/head/picqq/" + index + ".gif"),
					new FileOutputStream("/data/pics/" + index + ".gif"));
		}
	}
}
