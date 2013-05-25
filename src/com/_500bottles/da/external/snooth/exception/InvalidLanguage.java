package com._500bottles.da.external.snooth.exception;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidLanguage extends Exception {

	public InvalidLanguage()
	{
		super();
	}

	public InvalidLanguage(String msg)
	{
		super(msg);
	}

	public InvalidLanguage(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
