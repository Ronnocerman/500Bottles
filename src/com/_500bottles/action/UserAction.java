package com._500bottles.action;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com._500bottles.exception.user.LoginException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.manager.SessionManager;

public class UserAction
{
	public static boolean login(String email, char[] password)
			throws UserDoesNotExistException, LoginException
	{
		MessageDigest md5;
		try
		{
			md5 = MessageDigest.getInstance("MD5");
			byte[] hashedPassword = md5.digest(new String(password)
					.getBytes("UTF-8"));
			return SessionManager.login(email, hashedPassword);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
		{
			throw new LoginException(e);
		}
	}

	public void logout(String email)
	{
	}

	public void createAccount()
	{
	}

	public void resetPassword()
	{
	}

	public void deleteAccount()
	{
	}

	public void editWeight()
	{
	}

	public void editHeight()
	{
	}

	public void editSex()
	{
	}

	public void editEmail()
	{
	}

	public void editDateOfBirth()
	{
	}
}
