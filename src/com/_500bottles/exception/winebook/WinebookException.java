package com._500bottles.exception.winebook;

import com._500bottles.exception.NSFWException;

@SuppressWarnings("serial")
public class WinebookException extends NSFWException
{
	public WinebookException()
	{

	}

	public WinebookException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public WinebookException(String msg)
	{
		super(msg);
	}

	public WinebookException(Throwable cause)
	{
		super(cause);
	}
}
