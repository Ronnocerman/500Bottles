package com._500bottles.da.external.snooth.exception;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/22/13 Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
public class InvalidCountry extends Exception
{

	public InvalidCountry()
	{
		super();
	}

	public InvalidCountry(String msg)
	{
		super(msg);
	}

	public InvalidCountry(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
