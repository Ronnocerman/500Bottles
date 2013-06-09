package com._500bottles.da.external.wine.exception;

//exception for any other parameters, if they are invalid
public class InvalidOtherParameters extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidOtherParameters(String errormsg)
	{
		super(errormsg);
	}
}
