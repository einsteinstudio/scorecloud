package com.goldskyer.scorecloud.exception;

public class AppException extends RuntimeException
{

	public AppException()
	{
		super();
	}

	public AppException(Exception exception)
	{
		super(exception);
	}

	public AppException(String msg)
	{
		super(msg);
	}

	public AppException(String msg, Exception e)
	{
		super(msg, e);
	}
}