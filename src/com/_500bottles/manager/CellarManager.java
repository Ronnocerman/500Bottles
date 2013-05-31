package com._500bottles.manager;

import com._500bottles.da.internal.CellarDAO;
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

	public static boolean removeCellarItem(long cellarID, CellarItem ci)
	{
		return CellarDAO.deleteCellarItem(cellarID, ci);
	}

	public static void editCellarItem(long cellarID, CellarItem ci)
	{
		CellarDAO.editCellarItem(cellarID, ci);
	}

	public static CellarItem getCellarItem(long cellarID, long id)
	{
		return CellarDAO.getCellarItem(cellarID, id);
	}

	public static CellarItem getByWineID(long cellarID, long id)
	{
		return CellarDAO.getByWineID(cellarID, id);
	}
}
