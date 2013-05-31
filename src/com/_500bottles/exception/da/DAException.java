package com._500bottles.exception.da;

import com._500bottles.exception.NSFWException;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/25/13 Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
public class DAException extends NSFWException
{
	public DAException()
	{
		super();
	}

	public DAException(String msg)
	{
		super(msg);
	}

	public DAException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}
