package com._500bottles.exception.user;

@SuppressWarnings("serial")
public class InvalidEmailException extends UserException
{
	public static void checkTheForDot(String email)
	{
		if (email.lastIndexOf('.') != -1)
		{
			int i = email.lastIndexOf('.');
			if (email.substring(i + 1) == "net")
			{

			} else if (email.substring(i + 1) == "com")
			{

			} else if (email.substring(i + 1) == "edu")
			{

			} else if (email.substring(i + 1) == "gov")
			{

			} else
			{
				// sends a invalid email message to user
			}

		} else
		{
			// sends a invalid email message to user
		}

	}
}
