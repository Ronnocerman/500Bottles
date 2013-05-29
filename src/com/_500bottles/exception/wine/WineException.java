package com._500bottles.exception.wine;

import com._500bottles.exception.NSFWException;

public class WineException extends NSFWException
{
	public WineException()
	{
		super();
	}

	public WineException(String msg)
	{
		super(msg);
	}

	public WineException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}
