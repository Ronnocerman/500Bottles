package com._500bottles.exception.user;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends UserException
{

	public UserAlreadyExistsException(Throwable cause)
	{
		super(cause);
	}
}
