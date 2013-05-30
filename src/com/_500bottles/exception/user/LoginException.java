package com._500bottles.exception.user;

@SuppressWarnings("serial")
public class LoginException extends UserException
{
	public LoginException()
	{

	}

	public LoginException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public LoginException(String msg)
	{
		super(msg);
	}

	public LoginException(Throwable cause)
	{
		super(cause);
	}
}