package com._500bottles.manager;

import java.util.Iterator;
import java.util.Vector;

import com._500bottles.action.CellarAction;
import com._500bottles.da.internal.CellarDAO;
import com._500bottles.exception.cellar.CellarException;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarManager
{
	/**
	 * Adds specified wine to the users cellar
	 * 
	 * @param cellarID
	 *            ID of the cellar to which the specified wine will be added
	 * @param w
	 *            Wine to be added to the specified cellar
	 */
	public static void addWine(long cellarID, Wine w)
	{
		CellarItem ci = new CellarItem(w);
		try
		{
			CellarDAO.addCellarItem(cellarID, ci);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Removes the specified cellarItem
	 * 
	 * @param ci
	 *            CellarItem to be removed from the cellar
	 * @return True if cellarItem successfully removed False if removal
	 *         unsuccessful
	 */
	public static boolean removeCellarItem(CellarItem ci)
	{
		return CellarDAO.deleteCellarItem(ci.getId());
	}

	/**
	 * Edits the specified CellarItem
	 * 
	 * @param ci
	 *            CellarItem to be edited
	 * @throws CellarException
	 *             If specified CellarItem does not exist
	 */
	public static void editCellarItem(CellarItem ci) throws CellarException
	{
		try
		{
			CellarDAO.editCellarItem(ci);
		} catch (DAException e)
		{
			throw new CellarException("No CellarItem by ID: " + ci.getId(), e);
		}
	}

	/**
	 * Gets CellarItem from specified cellarItemID
	 * 
	 * @param cellarItemID
	 *            ID of cellarItem to be returned
	 * @return CellarItem object of specified cellarItem
	 * @throws CellarException
	 *             If specified cellaritem does not exist
	 */
	public static CellarItem getCellarItem(long cellarItemID)
			throws CellarException
	{
		try
		{
			return CellarDAO.getCellarItem(cellarItemID);
		} catch (DAException e)
		{
			throw new CellarException("No CellarItem by ID: " + cellarItemID, e);
		}
	}

	/**
	 * Gets CellarItem from its wineID
	 * 
	 * @param userID
	 *            ID of the user who's cellar being search through
	 * @param wineID
	 *            ID of the wine we are using to obtain the CellarItem from
	 * @return CellarItem object wineID and userIDis associated with
	 * @throws CellarException
	 *             If no cellarItem associated with specified user and wine
	 */
	public static CellarItem getByWineID(long userID, long wineID)
			throws CellarException
	{
		try
		{
			return CellarDAO.getByWineID(userID, wineID);
		} catch (DAException e)
		{
			throw new CellarException("No CellarItem in " + userID
					+ "'s cellar by wineID: " + wineID, e);
		}
	}

	public static Vector<Wine> getAllWinesFromCellar(long user_id)
	{
		Vector<Wine> wines = null;

		try
		{
			wines = CellarDAO.getAllWinesFromCellar(user_id);
		} catch (DAException e)
		{
			e.printStackTrace();
			wines = new Vector<>();
		}

		return wines;
	}

	public static CellarItem addCellarItem(long userID, CellarItem item)
	{
		try
		{
			return CellarDAO.addCellarItem(userID, item);
		} catch (DAException e)
		{
			return null;
		}
	}

	public static int[] getTypeQuantities(long userID)
	{
		Vector<Wine> v = getAllWinesFromCellar(userID);
		Iterator<Wine> i = v.iterator();
		int[] q = { 0, 0, 0, 0 };
		while (i.hasNext())
		{
			Wine w = i.next();
			String type = w.getType().getWineType().toLowerCase();
			if (type.contains("red"))
			{
				q[0] += CellarAction.getCellarQuantity(w.getId());
			} else if (type.contains("white"))
			{
				q[1] += CellarAction.getCellarQuantity(w.getId());

			}

			else if (type.contains("ros"))
			{
				q[2] += CellarAction.getCellarQuantity(w.getId());

			} else
			{
				q[3] += CellarAction.getCellarQuantity(w.getId());
			}

		}
		return q;
	}
}
