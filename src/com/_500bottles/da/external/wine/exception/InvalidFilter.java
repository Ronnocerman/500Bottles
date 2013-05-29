package com._500bottles.da.external.wine.exception;

public class InvalidFilter extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFilter(String message)
	{
		super("Filter: " + message);
	}
}
