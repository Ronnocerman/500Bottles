package com._500bottles.exception.user;

@SuppressWarnings("serial")
public class UserDoesNotExistException extends UserException
{
	public UserDoesNotExistException(Throwable cause)
	{
		super(cause);
	}
}
