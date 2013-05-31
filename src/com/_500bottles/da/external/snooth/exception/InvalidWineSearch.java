package com._500bottles.da.external.snooth.exception;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/22/13 Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
public class InvalidWineSearch extends Exception
{

	public InvalidWineSearch()
	{
		super();
	}

	public InvalidWineSearch(String msg)
	{
		super(msg);
	}

	public InvalidWineSearch(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
