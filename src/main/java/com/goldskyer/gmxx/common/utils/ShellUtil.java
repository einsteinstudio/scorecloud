package com.goldskyer.gmxx.common.utils;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;


public class ShellUtil
{

	public static List runShell(String shStr) throws Exception
	{
		List<String> strList = new ArrayList();

		Process process;
		process = Runtime.getRuntime().exec(new String[]
		{ "/bin/sh", "-c", shStr }, null, null);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		process.waitFor();
		System.out.println(IOUtils.readLines(ir));
		while ((line = input.readLine()) != null)
		{
			strList.add(line);
		}

		return strList;
	}

	public static void main(String[] args)
	{
		try
		{
			List aa = runShell("/data/script/say.sh jin");
			System.out.println(aa);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
