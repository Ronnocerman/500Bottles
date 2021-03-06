package com._500bottles.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com._500bottles.exception.user.UserAlreadyExistsException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.manager.SessionManager;
import com._500bottles.manager.UserManager;
import com._500bottles.object.user.ApplicationUser;
import com._500bottles.object.user.Sex;
import com._500bottles.util.Utilities;

public class UserAction
{
	/**
	 * Method to validate a hashed password for a certain email.
	 * 
	 * @param email
	 *            The email of the user whose password to check.
	 * @param passwordHash
	 *            The hashed password to test.
	 * @return <tt>true</tt> if the hashed password is valid for given email. <br>
	 *         <tt>false</tt> if the hashed password is invalid for given email.
	 * @throws UserDoesNotExistException
	 *             if the user does not exist.
	 */
	public static boolean login(String email, String passwordHash)
			throws UserDoesNotExistException
	{
		SessionManager sm = SessionManager.getSessionManager();

		return sm.login(email, passwordHash);
	}

	/**
	 * Call SessionManager and Logout
	 * 
	 * @param email
	 *            Not used, kept for possible future implementation
	 */
	public static void logout(String email)
	{
		SessionManager sm = SessionManager.getSessionManager();

		sm.logout();
	}

	/**
	 * Method to create an account.
	 * 
	 * @param u
	 *            {@link ApplicationUser} that represents the account to create.
	 * @throws UserAlreadyExistsException
	 *             if the user already exists.
	 */
	public static void createAccount(ApplicationUser u)
			throws UserAlreadyExistsException
	{
		UserManager.addUser(u);
	}

	/**
	 * Method to reset the password for a user to another password.
	 * 
	 * @param u
	 *            The user whose password to reset
	 * @param newPassword
	 *            The {@link String} representing the user's new password.
	 * @throws UserDoesNotExistException
	 *             if the user does not exist.
	 */
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

	/**
	 * Method to delete an account.
	 * 
	 * @param userID
	 *            The userID of the account to delete
	 * @throws UserDoesNotExistException
	 *             if the user does not exist.
	 */
	public static void deleteAccount() throws UserDoesNotExistException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		UserManager.removeUser(userID);
	}

	/**
	 * Method to change a user's weight.
	 * 
	 * @param userID
	 *            The userID of the user whose weight to edit.
	 * @param weight
	 *            The weight to which to set the user's weight.
	 * @throws UserDoesNotExistException
	 *             if the user does not exist.
	 */
	public static void editWeight(double weight)
			throws UserDoesNotExistException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		UserManager.editUser(UserManager.getUser(userID).setWeight(weight));
	}

	/**
	 * Method to change a user's height.
	 * 
	 * @param userID
	 *            The userID of the user whose height to edit.
	 * @param height
	 *            The height to which to set the user's height.
	 * @throws UserDoesNotExistException
	 *             if the user does not exist.
	 */
	public static void editHeight(double height)
			throws UserDoesNotExistException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		UserManager.editUser(UserManager.getUser(userID).setHeight(height));
	}

	/**
	 * Method to edit a user's sex.
	 * 
	 * @param userID
	 *            The userID of the user whose sex is to be edited.
	 * @param sex
	 *            The sex to which to change the user's sex.
	 * @throws UserDoesNotExistException
	 *             if the user does not exist.
	 */
	public static void editSex(Sex sex) throws UserDoesNotExistException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		UserManager.editUser(UserManager.getUser(userID).setSex(sex));
	}

	/**
	 * Method to edit a user's given email.
	 * 
	 * @param userID
	 *            The userID of the user whose email is to be edited.
	 * @param emailAddress
	 *            The email address to which to change the user's email.
	 * @throws UserDoesNotExistException
	 *             if the user does not exist.
	 */
	public static void editEmail(String emailAddress)
			throws UserDoesNotExistException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		UserManager
				.editUser(UserManager.getUser(userID).setEmail(emailAddress));
	}

	/**
	 * Method to edit a user's date of birth
	 * 
	 * @param userID
	 *            The userID of the user whose date of birth is to be edited.
	 * @param dateOfBirth
	 *            The date to which the user's date of birth will be set.
	 * @throws UserDoesNotExistException
	 *             if the user does not exist.
	 */
	public static void editDateOfBirth(Date dateOfBirth)
			throws UserDoesNotExistException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		UserManager.editUser(UserManager.getUser(userID).setDOB(dateOfBirth));
	}
}
