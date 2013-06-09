package com._500bottles.exception.winebook;

@SuppressWarnings("serial")
public class PhotoDoesNotExist extends WinebookException
{
	public PhotoDoesNotExist()
	{

	}

	public PhotoDoesNotExist(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public PhotoDoesNotExist(String msg)
	{
		super(msg);
	}

	public PhotoDoesNotExist(Throwable cause)
	{
		super(cause);
	}
}
