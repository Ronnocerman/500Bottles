package com._500bottles.manager;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.object.user.ApplicationUser;

public class SessionManager
{
	/**
	 * Attempts to login into the specified account using and email and a
	 * password hash
	 * 
	 * @param email
	 *            Email address to be logged in with
	 * @param passwordHash
	 *            PasswordHash to be logged in with
	 * @return True if the password hash specified and the hash and in the
	 *         database are equal False if the specified user does not exist or
	 *         if the password hashes do not match
	 * @throws UserDoesNotExistException
	 *             If fails to find the specified user
	 */
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
