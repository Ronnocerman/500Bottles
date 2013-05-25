package com._500bottles.da.external.snooth.exception;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidSort extends Exception {

	public InvalidSort()
	{
		super();
	}

	public InvalidSort(String msg)
	{
		super(msg);
	}

	public InvalidSort(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
