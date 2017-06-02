package com.goldskyer.gmxx.promotion.dtos;

public class ApplyAwardDto {
	private String accountId;
	private String activityId;
	private String fp;
	private String name;
	private String department;
	private String cardNo;
	private String random;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getFp() {
		return fp;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCardNo()
	{
		return cardNo;
	}

	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}

	public String getRandom()
	{
		return random;
	}

	public void setRandom(String random)
	{
		this.random = random;
	}

}
