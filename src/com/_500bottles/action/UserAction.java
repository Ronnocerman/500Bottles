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

public class UserAction
{
	public static boolean login(String email, char[] password)
			throws UserDoesNotExistException, LoginException
	{
		try
		{
			return SessionManager.login(email, hash(password));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
		{
			throw new LoginException(e);
		}
	}

	private static byte[] hash(char[] in) throws NoSuchAlgorithmException,
			UnsupportedEncodingException
	{
		MessageDigest md5;
		md5 = MessageDigest.getInstance("MD5");
		return md5.digest(new String(in).getBytes("UTF-8"));
	}

	public void logout(String email)
	{

	}

	public void createAccount(ApplicationUser u)
			throws UserAlreadyExistsException
	{
		UserManager.addUser(u);
	}

	public void resetPassword(ApplicationUser u, char[] newPassword)
			throws UserDoesNotExistException
	{
		try
		{
			u.setPassword(hash(newPassword));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		UserManager.editUser(u);
	}

	public void deleteAccount(long userID) throws UserDoesNotExistException
	{
		UserManager.removeUser(userID);
	}

	public void editWeight(long userID, double weight)
			throws UserDoesNotExistException
	{
		UserManager.editUser(UserManager.getUser(userID).setWeight(weight));
	}

	public void editHeight(long userID, double height)
			throws UserDoesNotExistException
	{
		UserManager.editUser(UserManager.getUser(userID).setHeight(height));
	}

	public void editSex(long userID, Sex sex) throws UserDoesNotExistException
	{
		UserManager.editUser(UserManager.getUser(userID).setSex(sex));
	}

	public void editEmail(long userID, String emailAddress)
			throws UserDoesNotExistException
	{
		UserManager
				.editUser(UserManager.getUser(userID).setEmail(emailAddress));
	}

	public void editDateOfBirth(long userID, Date dateOfBirth)
			throws UserDoesNotExistException
	{
		UserManager.editUser(UserManager.getUser(userID).setDOB(dateOfBirth));
	}
}
