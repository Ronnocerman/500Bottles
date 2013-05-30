package com._500bottles.manager;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.user.UserAlreadyExistsException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.object.user.ApplicationUser;

public class UserManager
{

	public ApplicationUser getUser(long id) throws UserDoesNotExistException
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

	public void editUser(ApplicationUser u) throws UserDoesNotExistException
	{
		try
		{
			UserDAO.editUser(u);
		} catch (DAException e)
		{
			throw new UserDoesNotExistException(e);
		}

	}

	public void removeUser(long id) throws UserDoesNotExistException
	{
		ApplicationUser u = null;
		try
		{
			u = UserDAO.getUser(id);
			UserDAO.deleteUser(u);
		} catch (DAException e)
		{
			throw new UserDoesNotExistException(e);
		}
	}

	public void addUser(ApplicationUser u) throws UserAlreadyExistsException
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
