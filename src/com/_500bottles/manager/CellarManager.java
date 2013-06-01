package com._500bottles.manager;

import com._500bottles.da.internal.CellarDAO;
import com._500bottles.exception.cellar.CellarException;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarManager
{
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

	public static boolean removeCellarItem(CellarItem ci)
	{
		return CellarDAO.deleteCellarItem(ci.getId());
	}

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
}
