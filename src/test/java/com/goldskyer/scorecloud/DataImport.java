package com.goldskyer.scorecloud;

import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.gmxx.service.BaseTest;
import com.goldskyer.scorecloud.dto.ClassDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.StudentDto;
import com.goldskyer.scorecloud.service.ClassService;
import com.goldskyer.scorecloud.service.GradeService;
import com.goldskyer.scorecloud.service.StudentService;

public class DataImport extends BaseTest
{
	@Autowired
	private StudentService studentService;
	@Autowired
	private GradeService gradeService;

	@Autowired
	private ClassService classService;

	@Test
	public void queryClassInfo()
	{
		ClassDto classDto = classService.queryClassDtoByNameInfo("xcxx", "一年级", "1班");
		System.out.println(classDto);
	}

	@Test
	@Rollback(false)
	@Transactional
	public void test() throws Exception
	{
		EnvParameter.put(new EnvParameter());
		EnvParameter.get().setDomain("xcxx.goldskyer.com");
		ScoreCloudEnv.put(new ScoreCloudEnv());
		ScoreCloudEnv.get().setSchId("xcxx");
		String excel = "/data/xcxx/深圳市光明新区下村小学.csv";
		List<String> lines = IOUtils.readLines(new FileInputStream(excel), "GBK");
		for (int i = 1000; i < lines.size(); i++)
		{
			System.out.println(lines.get(i));
			String[] item = lines.get(i).split(";");
			StudentDto studentDto=new StudentDto();
			studentDto.setStudentNo(StringUtils.trimToEmpty(item[0]));
			studentDto.setStudentName(StringUtils.trimToEmpty(item[1]));
			studentDto.setSex(StringUtils.trimToEmpty(item[2]));
			studentDto.setBirthDay(StringUtils.trimToEmpty(item[7]));
			studentDto.setStatus(1);
			studentDto.setEnterYear(Integer.valueOf(StringUtils.trimToEmpty(item[4]).substring(0, 4)));
			studentDto.setNational(StringUtils.trimToEmpty(item[8]));
			studentDto.setAddress(StringUtils.trimToEmpty(item[9]));
			studentDto.setUsername(studentDto.getStudentNo());
			studentDto.setPassword("123456");
			ClassDto classDto = classService.queryClassDtoByNameInfo("xcxx", StringUtils.trimToEmpty(item[6]),
					StringUtils.trimToEmpty(item[3]));
			if (classDto == null)
				log.fatal("找不到班级：" + lines.get(i));
			studentDto.setClassId(classDto.getId());
			studentService.addStudentDto(studentDto);
			//studentDto.setClassId(classId);
		}
	}

	public static void main(String[] args) throws Exception
	{

	}
}
