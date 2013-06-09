package com._500bottles.da.external.wine.exception;

//exception if category is not valid
public class InvalidCategory extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCategory(String message)
	{
		super("Category: " + message);
	}
}
