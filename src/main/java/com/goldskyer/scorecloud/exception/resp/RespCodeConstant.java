package com.goldskyer.scorecloud.exception.resp;

public class RespCodeConstant
{
	public static final RespCode NOT_IMPLENENT = new RespCode("NOT_IMPLENENT", "功能暂时为实现");

	public static final RespCode ACCOUNT_EXIST = new RespCode("ACCOUNT_EXIST", "账号已经存在");
	public static final RespCode SUBJECT_EXIST = new RespCode("SUBJECT_EXIST", "该科目已经存在，请不要重复添加");
	public static final RespCode STUDENT_NO_EXIST = new RespCode("STUDENT_NO_EXIST", "该学号已经存在，请不要重复添加");
	public static final RespCode SCORE_ILLEGAL = new RespCode("SCORE_ILLEGAL", "分数不合法，不是一个数字");
	public static final RespCode PARAM_ILLEGAL = new RespCode("PARAM_ILLEGAL", "提交参数非法");

	public static final RespCode DELETE_GRADE_EXIST_CLASS = new RespCode("DELETE_GRADE_EXIST_CLASS", "请先删除班级，再删除年级");
	public static final RespCode DELETE_CLASS_EXIST_STUDENT = new RespCode("DELETE_CLASS_EXIST_STUDENT",
			"请先删除学生，再删除班级");

	public static final RespCode USER_NAME_IS_EMPTY = new RespCode("USER_NAME_IS_EMPTY", "用户名不能为空");
	public static final RespCode PWD_IS_EMPTY = new RespCode("PWD_IS_EMPTY", "密码不能为空");

}
