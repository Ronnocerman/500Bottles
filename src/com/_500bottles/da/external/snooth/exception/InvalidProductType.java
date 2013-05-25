package com._500bottles.da.external.snooth.exception;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidProductType extends Exception {

	public InvalidProductType()
	{
		super();
	}

	public InvalidProductType(String msg)
	{
		super(msg);
	}

	public InvalidProductType(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
