package com._500bottles.exception;

public class NSFWException extends Exception
{
	public NSFWException()
	{
		super();
	}

	public NSFWException(String msg)
	{
		super(msg);
	}

	public NSFWException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}
