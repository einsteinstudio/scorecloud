package com.goldskyer.scorecloud;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.goldskyer.gmxx.service.BaseTest;
import com.goldskyer.scorecloud.entities.Subject;
import com.goldskyer.scorecloud.service.SubjectService;

public class SubjectServiceTest extends BaseTest
{
	@Autowired
	private SubjectService subjectService;

	@Test
	@Rollback(false)
	public void addSubject()
	{
		{
		Subject subject = new Subject();
		subject.setExamId("1");
		subject.setSubjectName("语文");
		subject.setId("1");
		subjectService.addSubject(subject);
		}
		{
			Subject subject = new Subject();
			subject.setExamId("1");
			subject.setSubjectName("数学");
			subject.setId("2");
			subjectService.addSubject(subject);
		}
	}
}
