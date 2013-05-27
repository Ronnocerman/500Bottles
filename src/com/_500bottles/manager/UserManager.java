package com._500bottles.manager;

import java.sql.SQLException;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.user.User;

public class UserManager
{

	public User getUser(long id)
	{
		User u = null;
		try
		{
			u = UserDAO.getUser(id);
		} catch (DAException e)
		{
			// TODO: do a thing
		}
		return u;
	}

	public void editUser(long id)
	{
		User u = null;
		try
		{
			u = UserDAO.getUser(id);
			UserDAO.editUser(u);
		} catch (SQLException e)
		{
			// TODO: Stuff
		} catch (DAException e)
		{
			// TODO: Things
		}

	}

	public void removeUser(long id)
	{
		User u = null;
		try
		{
			u = UserDAO.getUser(id);
			UserDAO.deleteUser(u);
		} catch (DAException e)
		{
			// TODO
		} catch (SQLException e)
		{
			// TODO
		}
	}

	public void addUser(User u)
	{
		try
		{
			UserDAO.addUser(u);
		} catch (Exception e)
		{
			// TODO
		}
	}
}
