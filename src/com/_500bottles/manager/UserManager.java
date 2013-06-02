package com._500bottles.manager;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.user.UserAlreadyExistsException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.object.user.ApplicationUser;

public class UserManager
{

	/**
	 * Gets specified ApplicationUser object
	 * 
	 * @param id
	 *            ID of the user of which the ApplicationUser will be returned
	 * @return ApplicationUser object of specified user null if user does not
	 *         exist
	 * @throws UserDoesNotExistException
	 *             If user does not exist
	 * 
	 */
	public static ApplicationUser getUser(long id)
			throws UserDoesNotExistException
	{
		ApplicationUser u = null;
		try
		{
			u = UserDAO.getUser(id);
		} catch (DAException e)
		{
			throw new UserDoesNotExistException(e);
		}
		return u;
	}

	/**
	 * Edits the user of the specified ApplicationUser object
	 * 
	 * @param u
	 *            The ApplicationUser object of the user to be edited
	 * @throws UserDoesNotExistException
	 *             If user does not exist
	 */
	public static void editUser(ApplicationUser u)
			throws UserDoesNotExistException
	{
		try
		{
			UserDAO.editUser(u);
		} catch (DAException e)
		{
			throw new UserDoesNotExistException(e);
		}

	}

	/**
	 * Remove user with specified id
	 * 
	 * @param id
	 *            ID of the user to be removed
	 * @return True if user was removed successfully
	 */
	public static boolean removeUser(long id)
	{
		return UserDAO.deleteUser(id);
	}

	/**
	 * Adds user to database
	 * 
	 * @param u
	 *            AppicationUser object of user to be added to database
	 * @throws UserAlreadyExistsException
	 *             If user already exists in the database
	 */
	public static void addUser(ApplicationUser u)
			throws UserAlreadyExistsException
	{
		try
		{
			UserDAO.addUser(u);
		} catch (DAException e)
		{
			throw new UserAlreadyExistsException(e);
		}
	}
}
