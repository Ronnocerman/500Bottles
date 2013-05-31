package com._500bottles.da.external.snooth.exception;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/22/13 Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
public class InvalidColor extends Exception
{

	public InvalidColor()
	{
		super();
	}

	public InvalidColor(String msg)
	{
		super(msg);
	}

	public InvalidColor(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
