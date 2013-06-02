package com._500bottles.action;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com._500bottles.exception.user.LoginException;
import com._500bottles.exception.user.UserAlreadyExistsException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.manager.SessionManager;
import com._500bottles.manager.UserManager;
import com._500bottles.object.user.ApplicationUser;
import com._500bottles.object.user.Sex;
import com._500bottles.util.Utilities;

public class UserAction
{
	public static boolean login(String email, String passwordHash)
			throws UserDoesNotExistException
	{
		return SessionManager.login(email, passwordHash);
	}

	public static void logout(String email)
	{

	}

	public static void createAccount(ApplicationUser u)
			throws UserAlreadyExistsException
	{
		UserManager.addUser(u);
	}

	public static void resetPassword(ApplicationUser u, String newPassword)
			throws UserDoesNotExistException
	{
		try
		{
			u.setPassword(Utilities.hashPassword(newPassword));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		UserManager.editUser(u);
	}

	public static void deleteAccount(long userID) throws UserDoesNotExistException
	{
		UserManager.removeUser(userID);
	}

	public static void editWeight(long userID, double weight)
			throws UserDoesNotExistException
	{
		UserManager.editUser(UserManager.getUser(userID).setWeight(weight));
	}

	public static void editHeight(long userID, double height)
			throws UserDoesNotExistException
	{
		UserManager.editUser(UserManager.getUser(userID).setHeight(height));
	}

	public static void editSex(long userID, Sex sex) throws UserDoesNotExistException
	{
		UserManager.editUser(UserManager.getUser(userID).setSex(sex));
	}

	public static void editEmail(long userID, String emailAddress)
			throws UserDoesNotExistException
	{
		UserManager
				.editUser(UserManager.getUser(userID).setEmail(emailAddress));
	}

	public static void editDateOfBirth(long userID, Date dateOfBirth)
			throws UserDoesNotExistException
	{
		UserManager.editUser(UserManager.getUser(userID).setDOB(dateOfBirth));
	}
}
