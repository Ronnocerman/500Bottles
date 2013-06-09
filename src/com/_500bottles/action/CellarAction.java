package com._500bottles.action;

import java.util.Vector;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.cellar.CellarException;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.manager.CellarManager;
import com._500bottles.manager.SessionManager;
import com._500bottles.manager.UserManager;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQueryResult;

public class CellarAction
{

	/**
	 * Method to set the quantity of a particular wine in a user's cellar.
	 * 
	 * @param id
	 * @param quantity
	 * @throws CellarException
	 */
	public static void setCellarQuantity(long id, int quantity)
			throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci;
		try
		{
			ci = CellarManager.getByWineID(userID, id);
		} catch (CellarException e)
		{
			try
			{
				ci = new CellarItem(WineDAO.getWine(id));
			} catch (DAException e1)
			{
				ci = new CellarItem();
				ci.setWineId(id);
			}
			CellarManager.addCellarItem(userID, ci);
		}
		ci.setQuantity(quantity);
		CellarManager.editCellarItem(ci);
	}

	/**
	 * Increments the quantity of a particular wine in a user's cellar.
	 * 
	 * @param id
	 * @throws DAException
	 * @throws CellarException
	 */
	public static void incCellarQuantity(long id)
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci;
		try
		{
			ci = CellarManager.getByWineID(userID, id);
		} catch (CellarException e)
		{
			try
			{
				ci = new CellarItem(WineDAO.getWine(id));
			} catch (DAException e1)
			{
				ci = new CellarItem();
				ci.setWineId(id);
			}
			ci.setQuantity(0);
			CellarManager.addCellarItem(userID, ci);
		}
		ci.setQuantity(ci.getQuantity() + 1);
		try
		{
			CellarManager.editCellarItem(ci);
		} catch (CellarException e)
		{
			// TODO Auto-generated catch block
		}
	}

	/**
	 * Decrements the quantity of a particular wine in a user's cellar.
	 * 
	 * @param id
	 * @throws CellarException
	 */
	public static void decCellarQuantity(long id) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci = CellarManager.getByWineID(userID, id);
		if (ci.getQuantity() > 0)
		{
			ci.setQuantity(ci.getQuantity() - 1);
			CellarManager.editCellarItem(ci);
		}
	}

	/**
	 * Sets the cellar notes of a user for a particular wine.
	 * 
	 * @param id
	 * @param notes
	 * @throws CellarException
	 */
	public static void setCellarNotes(long id, String notes)
			throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci = CellarManager.getByWineID(userID, id);
		ci.setNotes(notes);
		CellarManager.editCellarItem(ci);
	}

	/**
	 * Gets the cellar notes of a user for a particular wine.
	 * 
	 * @param id
	 * @return
	 * @throws CellarException
	 */
	public static String getCellarNotes(long id) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		return CellarManager.getByWineID(userID, id).getNotes();
	}

	/**
	 * Clears the cellar notes by a user for a particular wine.
	 * 
	 * @param id
	 * @throws CellarException
	 */
	public static void clearCellarNotes(long id) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci = CellarManager.getByWineID(userID, id);
		ci.setNotes("");
		CellarManager.editCellarItem(ci);
	}

	public static WineQueryResult getAllWinesFromCellar(long user_id)
			throws CellarException
	{
		WineQueryResult result;
		Vector<Wine> wines;

		try
		{
			UserManager.getUser(user_id);
		} catch (UserDoesNotExistException e)
		{
			throw new CellarException("User does not exist.");
		}

		wines = CellarManager.getAllWinesFromCellar(user_id);

		result = new WineQueryResult(wines);

		return result;
	}

	public static int getCellarQuantity(long id)
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci;
		try
		{
			ci = CellarManager.getByWineID(userID, id);
		} catch (CellarException e)
		{
			return 0;
		}
		return ci.getQuantity();

	}
}
