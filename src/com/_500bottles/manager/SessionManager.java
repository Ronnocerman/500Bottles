package com._500bottles.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.object.user.ApplicationUser;

public class SessionManager
{
	private static SessionManager sessionManager = null;

	private HttpSession session = null;
	private long logged_in_user_id = -1;

	private SessionManager(HttpServletRequest request)
	{
		session = request.getSession();
		checkForLoggedInUser();
	}

	public ApplicationUser getLoggedInUser()
	{
		ApplicationUser user = null;

		long user_id = -1;

		try {
			Object n = session.getAttribute("userId");

			if (n == null)
				return null;

			user_id = (Long) n;

		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		try {
			user = UserDAO.getUser(user_id);

		} catch (DAException e) {
			// TODO: error handling...
		}

		return user;
	}

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
	public boolean login(String email, String passwordHash)
			throws UserDoesNotExistException
	{
		try
		{
			ApplicationUser user = UserDAO.getUserByEmail(email);

			if (user == null)
				return false;

			// Set the userId session variable to indicate that
			// the user is logged in.
			try {
				if (passwordHash.equals(user.getPasswordHash()))
					session.setAttribute("userId", user.getUserId());

			} catch (IllegalStateException e) {
				e.printStackTrace();
			}

			return passwordHash.equals(user.getPasswordHash());

		} catch (DAException e)
		{
			throw new UserDoesNotExistException(e);
		}
	}

	/**
	 * Set userId to null to logout
	 */
	public void logout()
	{

		session.setAttribute("userId", null);

	}

	/**
	 * Checks if a user is logged in, and if-so, sets the appropriate private
	 * variable to the user object.
	 */
	private void checkForLoggedInUser()
	{
		try {
			Object userId = session.getAttribute("userId");

			if (userId == null) {
				logged_in_user_id = -1;
				return;
			}

			logged_in_user_id = Long.parseLong(userId.toString());

		} catch (IllegalStateException e) {
			logged_in_user_id = -1;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static SessionManager getSessionManager()
	{
		// TODO: error handling in-case the session manager wasn't
		// initialized.

		return sessionManager;
	}

	private void setSession(HttpSession session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static SessionManager initiateSessionManager(
			HttpServletRequest request)
	{
		if (sessionManager == null)
			sessionManager = new SessionManager(request);

		sessionManager.setSession(request.getSession());
		sessionManager.checkForLoggedInUser();

		return sessionManager;
	}

}
