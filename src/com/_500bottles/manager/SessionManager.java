package com._500bottles.manager;

import java.util.Arrays;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.object.user.ApplicationUser;

public class SessionManager
{
	public static boolean login(String email, byte[] passwordHash)
			throws UserDoesNotExistException
	{
		try
		{
			ApplicationUser user = UserDAO.getUserByEmail(email);
			return Arrays.equals(user.getPasswordHash(), passwordHash);

		} catch (DAException e)
		{
			throw new UserDoesNotExistException(e);
		}
	}
}
