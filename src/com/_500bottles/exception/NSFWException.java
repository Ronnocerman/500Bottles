package com._500bottles.exception;

@SuppressWarnings("serial")
public abstract class NSFWException extends Exception
{
	public NSFWException()
	{

	}

	public NSFWException(String msg)
	{
		super(msg);
	}

	public NSFWException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public NSFWException(Throwable cause)
	{
		super(cause);
	}
}
