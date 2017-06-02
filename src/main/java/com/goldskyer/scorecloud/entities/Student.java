package com.goldskyer.scorecloud.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "student")
public class Student implements SchoolId
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String studentName;
	private String studentNo;//学号
	private Date birthDay;
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
	private String schId;
	private Date createDate;
	private Date updateDate;
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

	public Date getBirthDay()
	{
		return birthDay;
	}

	public void setBirthDay(Date birthDay)
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

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	public String getSchId()
	{
		return schId;
	}

	public void setSchId(String schId)
	{
		this.schId = schId;
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
