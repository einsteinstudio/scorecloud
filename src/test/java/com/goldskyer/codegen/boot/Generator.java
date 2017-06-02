package com.goldskyer.codegen.boot;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.goldskyer.common.utils.FileUtil;

public class Generator
{
	public static final String BATH_PATH = "/Users/jintianfan/Documents/MyWorkspace/gmxx/src/main/java/";
	public static final String TEST_PATH = "/Users/jintianfan/Documents/MyWorkspace/gmxx/src/test/java/";

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		//basePath
		GenInfo genInfo = new GenInfo();
		//genInfo.entityPath = BATH_PATH + "/com/goldskyer/scorecloud/entities/Student.java";
		genInfo.target = "student";
		genInfo.Target = "Student";
		genInfo.cnName="学生";
		//genService(genInfo);
		genControoler(genInfo);
		System.out.println("success");
	}

	public static void genService(GenInfo genInfo)
	{
		String demoService = FileUtil.readFileContent(TEST_PATH + "/com/goldskyer/codegen/sources/service.txt")
				.toString();
		demoService = demoService.replaceAll("\\{Grade\\}", genInfo.Target);
		demoService = demoService.replaceAll("\\{grade\\}", genInfo.target);
		FileUtil.writeFileContent(BATH_PATH + "/com/goldskyer/scorecloud/service/" + genInfo.Target + "Service.java",
				demoService);
		String demoServiceImpl = FileUtil.readFileContent(TEST_PATH + "/com/goldskyer/codegen/sources/serviceimpl.txt")
				.toString();
		demoServiceImpl = demoServiceImpl.replaceAll("\\{Grade\\}", genInfo.Target);
		demoServiceImpl = demoServiceImpl.replaceAll("\\{grade\\}", genInfo.target);
		FileUtil.writeFileContent(
				BATH_PATH + "/com/goldskyer/scorecloud/service/impl/" + genInfo.Target + "ServiceImpl.java",
				demoServiceImpl);

	}

	public static void genControoler(GenInfo genInfo)
	{
		String demoService = FileUtil.readFileContent(TEST_PATH + "/com/goldskyer/codegen/sources/controller.txt")
				.toString();
		demoService = demoService.replaceAll("\\{Grade\\}", genInfo.Target);
		demoService = demoService.replaceAll("\\{grade\\}", genInfo.target);
		demoService = demoService.replaceAll("\\{cnName\\}", genInfo.cnName);
		FileUtil.writeFileContent(
				BATH_PATH + "/com/goldskyer/scorecloud/controller/" + genInfo.Target + "Controller.java",
				demoService);
	}


}
