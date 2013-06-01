package com._500bottles.exception.cellar;

import com._500bottles.exception.NSFWException;

@SuppressWarnings("serial")
public class CellarException extends NSFWException
{
	public CellarException()
	{

	}

	public CellarException(String msg)
	{
		super(msg);
	}

	public CellarException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public CellarException(Throwable cause)
	{
		super(cause);
	}
}
