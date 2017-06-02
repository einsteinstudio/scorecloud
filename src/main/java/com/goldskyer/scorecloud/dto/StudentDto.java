package com.goldskyer.scorecloud.dto;

import com.goldskyer.common.util.PingYinUtil;

public class StudentDto extends SchIdDto
{
	private String id;
	private String studentName;
	private String studentNo;//学号
	private String birthDay;
	private int enterYear;//入学年份
	private String fatherName;
	private String montherName;
	private String sex = "男";//默认1为男
	private String note;
	private String address;
	private String jiguan;//籍贯
	private String phone;//家庭电话
	private String national;//名族
	private String classId;//所在班级

	private int status;//0:未激活1:活跃，2：无效
	private String createDate;
	private String updateDate;
	private String gradeName;
	private String className;

	//账号信息
	private String username;
	private String password;
	private String accountId;
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public String getStudentNameSortKey()
	{
		return "<sortKey key='" + PingYinUtil.getFullSpell(getStudentName()).toUpperCase() + "'>" + getStudentName()
				+ "</sortKey>";
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public String getStudentNo()
	{
		return studentNo;
	}

	public void setStudentNo(String studentNo)
	{
		this.studentNo = studentNo;
	}

	public String getBirthDay()
	{
		return birthDay;
	}

	public void setBirthDay(String birthDay)
	{
		this.birthDay = birthDay;
	}

	public int getEnterYear()
	{
		return enterYear;
	}

	public void setEnterYear(int enterYear)
	{
		this.enterYear = enterYear;
	}

	public String getFatherName()
	{
		return fatherName;
	}

	public void setFatherName(String fatherName)
	{
		this.fatherName = fatherName;
	}

	public String getMontherName()
	{
		return montherName;
	}

	public void setMontherName(String montherName)
	{
		this.montherName = montherName;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getJiguan()
	{
		return jiguan;
	}

	public void setJiguan(String jiguan)
	{
		this.jiguan = jiguan;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getNational()
	{
		return national;
	}

	public void setNational(String national)
	{
		this.national = national;
	}

	public String getClassId()
	{
		return classId;
	}

	public void setClassId(String classId)
	{
		this.classId = classId;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}

	public String getGradeName()
	{
		return gradeName;
	}

	public void setGradeName(String gradeName)
	{
		this.gradeName = gradeName;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

}
