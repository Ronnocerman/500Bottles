package com._500bottles.exception.user;

import com._500bottles.exception.NSFWException;

@SuppressWarnings("serial")
public class UserException extends NSFWException
{
	public UserException()
	{

	}

	public UserException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public UserException(String msg)
	{
		super(msg);
	}

	public UserException(Throwable cause)
	{
		super(cause);
	}
}