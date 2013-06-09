package com._500bottles.da.external.wine.exception;

//check exception for invalid settings
public class InvalidSetting extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSetting(String err)
	{
		super(err);
	}

}
