package com.goldskyer.scorecloud.exception;

import com.goldskyer.scorecloud.exception.resp.RespCode;

public class RespCodeException extends AppException
{

	private RespCode respCode;

	public RespCodeException(RespCode respCode)
	{
		super();
		this.respCode = respCode;
	}

	public RespCodeException(Exception exception)
	{
		super(exception);
	}

	public RespCodeException(String msg)
	{
		super(msg);
	}

	public RespCodeException(String msg, Exception e)
	{
		super(msg, e);
	}

	public RespCode getRespCode()
	{
		return respCode;
	}

	public void setRespCode(RespCode respCode)
	{
		this.respCode = respCode;
	}

}
