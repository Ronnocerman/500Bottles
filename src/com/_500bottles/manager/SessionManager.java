package com._500bottles.manager;

import java.util.Arrays;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.object.user.ApplicationUser;

public class SessionManager
{
	public static boolean login(String email, String passwordHash)
			throws UserDoesNotExistException
	{
		try
		{
			ApplicationUser user = UserDAO.getUserByEmail(email);

			if (user == null)
				return false;

			return passwordHash.equals(user.getPasswordHash());

		} catch (DAException e)
		{
			throw new UserDoesNotExistException(e);
		}
	}
}
