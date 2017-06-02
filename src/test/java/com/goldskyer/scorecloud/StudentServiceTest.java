package com.goldskyer.scorecloud;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.goldskyer.gmxx.service.BaseTest;
import com.goldskyer.scorecloud.entities.Student;
import com.goldskyer.scorecloud.service.StudentService;

public class StudentServiceTest extends BaseTest
{
	@Autowired
	private StudentService studentService;
	@Test
	@Rollback(false)
	public void testAddStudents()
	{
		for (int i = 1; i < 100; i++)
		{
			Student student = new Student();
			student.setStudentNo("GEN" + i);
			student.setStudentName("王" + i);
			student.setClassId("8a72a3f558f312d20158f33f128a0009"); //班级都是1班
			student.setStatus(1);
			baseDao.add(student);
		}
	}
}
