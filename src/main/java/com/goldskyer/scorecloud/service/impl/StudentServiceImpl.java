package com.goldskyer.scorecloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.util.BeanUtil;
import com.goldskyer.common.utils.DateUtil;
import com.goldskyer.common.utils.MD5Util;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Account;
import com.goldskyer.core.enums.AccountType;
import com.goldskyer.core.service.AccountService;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.StudentDto;
import com.goldskyer.scorecloud.entities.Student;
import com.goldskyer.scorecloud.exception.RespCodeException;
import com.goldskyer.scorecloud.exception.resp.RespCodeConstant;
import com.goldskyer.scorecloud.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private AccountService accountService;
	
	public StudentDto queryStudentDtoByAccountId(String id)
	{
		StudentDto studentDto = new StudentDto();
		Student student = (Student) baseDao.queryUnique("select t from Student t where t.accountId=?", id);
		BeanUtil.copyProperties(studentDto, student);
		if (student.getBirthDay() != null)
			studentDto.setBirthDay(DateUtil.date2String(student.getBirthDay(), "yyyy-MM-dd"));
		if (StringUtils.isNotBlank(student.getAccountId()))
		{
			Account account = accountService.getAccountById(student.getAccountId());
			studentDto.setUsername(account.getUsername());
			studentDto.setPassword(account.getPassword());
		}
		return studentDto;
	}

	public StudentDto queryStudentDtoById(String id)
	{
		StudentDto studentDto=new StudentDto();
		Student student=baseDao.query(Student.class,id);
		BeanUtil.copyProperties(studentDto, student);
		if (student.getBirthDay() != null)
			studentDto.setBirthDay(DateUtil.date2String(student.getBirthDay(), "yyyy-MM-dd"));
		if (StringUtils.isNotBlank(student.getAccountId()))
		{
		Account account = accountService.getAccountById(student.getAccountId());
		studentDto.setUsername(account.getUsername());
		studentDto.setPassword(account.getPassword());
		}
		return studentDto;
	}
	
	public List<StudentDto> queryActiveStudentDtosByGradeId(String gradeId)
	{
		List<StudentDto> studentDtos = new ArrayList<>();
		List<Object[]> oos = baseDao.query(
				"select stu,g.gradeName,c.className from Student stu,Grade g,Class c where stu.classId=c.id and c.gradeId=g.id and stu.status=1 and g.id=?",
				gradeId);
		for (Object[] oo : oos)
		{
			StudentDto studentDto = new StudentDto();
			BeanUtil.copyProperties(studentDto, (Student) oo[0]);
			studentDto.setGradeName(oo[1].toString());
			studentDto.setClassName(oo[2].toString());
			studentDtos.add(studentDto);
		}
		return studentDtos;
	}

	public List<StudentDto> queryAllActiveStudents(String schId)
	{
		List<StudentDto>  studentDtos=new ArrayList<>();
		List<Object[]> oos = baseDao.query(
				"select stu,g.gradeName,c.className from Student stu,Grade g,Class c where stu.classId=c.id and c.gradeId=g.id and stu.status=1 and schId=?",
				schId);
		for (Object[] oo : oos)
		{
			StudentDto studentDto=new StudentDto();
			BeanUtil.copyProperties(studentDto, (Student)oo[0]);
			studentDto.setGradeName(oo[1].toString());
			studentDto.setClassName(oo[2].toString());
			studentDtos.add(studentDto);
		}
		return studentDtos;
	}

	@Transactional
	public void addStudentDto(StudentDto studentDto)
	{
		checkStudentNoRepeat(studentDto.getStudentNo(), null);
		checkUsernameNoRepeat(studentDto.getUsername(), null);
		Student student = new Student();
		BeanUtil.copyProperties(student, studentDto);
		student.setCreateDate(new Date());
		student.setUpdateDate(new Date());
		student.setSchId(ScoreCloudEnv.get().getSchId());
		if (StringUtils.isBlank(studentDto.getAccountId()))
		{
			student.setAccountId(UUID.randomUUID().toString().replaceAll("-", ""));
			studentDto.setAccountId(student.getAccountId());
		}
		baseDao.add(student);
		createAccount(studentDto);
	}

	private void createAccount(StudentDto studentDto)
	{
		if (StringUtils.isBlank(studentDto.getUsername()))
			return;
		Account account=new  Account();
		account.setUsername(studentDto.getUsername());
		account.setPassword(MD5Util.getMD5(MD5Util.getMD5(studentDto.getPassword())));
		account.setType(AccountType.STUDENT);
		account.setCreateDate(new Date());
		account.setId(studentDto.getAccountId());
		account.setDomain(EnvParameter.get().getDomain());
		baseDao.add(account);
	}

	@Transactional
	public void modifyStudentDto(StudentDto studentDto)
	{
		checkStudentNoRepeat(studentDto.getStudentNo(), studentDto.getId());
		checkUsernameNoRepeat(studentDto.getUsername(), studentDto.getAccountId());
		Student oldStudent = baseDao.query(Student.class, studentDto.getId());
		BeanUtil.copyProperties(oldStudent, studentDto);

		if (StringUtils.isBlank(oldStudent.getAccountId()))
		{
			oldStudent.setAccountId(UUID.randomUUID().toString().replaceAll("-", ""));
			studentDto.setAccountId(oldStudent.getAccountId());
			createAccount(studentDto);
		}
		else
		{
			Account account = baseDao.query(Account.class, oldStudent.getAccountId());
			account.setUsername(studentDto.getUsername());
			account.setPassword(MD5Util.getMD5(MD5Util.getMD5(studentDto.getPassword())));
			baseDao.modify(account);
		}
		baseDao.modify(oldStudent);
	}
	

	@Transactional
	public void deleteStudentDtoById(String id)
	{
		Student student = baseDao.query(Student.class, id);
		if (student != null)
		{
			baseDao.delete(Student.class, id);
			if (StringUtils.isNotBlank(student.getAccountId()))
			{
				baseDao.delete(Account.class, student.getAccountId());
			}
		}
	}

	/**
	 * 检查学号的唯一性
	 * @param studentNo
	 * @param studentId
	 */
	private void checkStudentNoRepeat(String studentNo, String studentId)
	{
		List<Student> list = baseDao.query("from Student where studentNo=?", studentNo);
		if (list.size() > 0)
		{
			if (StringUtils.isBlank(studentId) || (!StringUtils.equals(list.get(0).getId(), studentId)))
				throw new RespCodeException(RespCodeConstant.STUDENT_NO_EXIST);
		}
	}

	private void checkUsernameNoRepeat(String username, String accountId)
	{
		if (StringUtils.isBlank(username))
			return;
		List<Account> accounts = baseDao.query("select a from Account a where a.username = ? and domain=?", username,
				EnvParameter.get().getDomain());
		if (accounts.size() > 0)
		{
			if (StringUtils.isBlank(accountId) || (!StringUtils.equals(accounts.get(0).getId(), accountId)))
				throw new RespCodeException(RespCodeConstant.ACCOUNT_EXIST);
		}

	}

}
