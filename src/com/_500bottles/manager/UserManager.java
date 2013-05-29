package com._500bottles.manager;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.user.ApplicationUser;

public class UserManager
{

	public ApplicationUser getUser(long id)
	{
		ApplicationUser u = null;
		try
		{
			u = UserDAO.getUser(id);
		} catch (DAException e)
		{
			// TODO: do a thing
		}
		return u;
	}

	public void editUser(ApplicationUser u)
	{
		try
		{
			UserDAO.editUser(u);
		} catch (DAException e)
		{
			// TODO: Things
		}

	}

	public void removeUser(long id)
	{
		ApplicationUser u = null;
		try
		{
			u = UserDAO.getUser(id);
			UserDAO.deleteUser(u);
		} catch (DAException e)
		{
			// TODO
		}
	}

	public void addUser(ApplicationUser u)
	{
		try
		{
			UserDAO.addUser(u);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
