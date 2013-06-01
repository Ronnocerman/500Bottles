package com._500bottles.exception.da;


@SuppressWarnings("serial")
public class ConnectionError extends Error
{

	public ConnectionError(String msg)
	{
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public ConnectionError(String msg, Throwable cause)
	{
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

	public ConnectionError(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
