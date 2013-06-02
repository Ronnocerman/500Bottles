package com._500bottles.manager;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

public class WineManager
{
	public static Wine getWine(long id)
	{
		Wine wine = null;

		try
		{
			wine = WineDAO.getWine(id);
		} catch (DAException e)
		{
			// TODO:
		}

		return wine;
	}

	/**
	 * Gets and returns a
	 * 
	 * @param snoothId
	 * @return
	 */
	static Wine getWineBySnoothId(String snoothId)
	{
		Wine resultWine = null, searchWine = new Wine();
		searchWine.setSnoothId(snoothId);

		try
		{
			resultWine = WineDAO.getWine(searchWine);
		} catch (DAException e)
		{
			System.err.print("DA Exception in WineManager::getWineBySnoothId"
					+ e.getMessage());
		}

		return resultWine;
	}

	static Wine getWineByWineComId()
	{
		return null;
	}

	public static WineQueryResult searchWine(WineQuery query)
	{
		return WineQueryManager.search(query);
	}

	public static void addCustomWine(Wine w)
	{
	}

	public static void editCustomWine(Wine w)
	{
	}

	public static boolean deleteCustomWine(long id)
	{
		Wine w = getWine(id);
		return WineDAO.deleteWine(w);
	}

	public static void setRating(long id, double rating)
	{
		Wine w = getWine(id);
		w.setRating(rating);
		try
		{
			WineDAO.editWine(w);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
		}
	}

	/*
	 * public void addTastingNotes(String notes) { }
	 * 
	 * public void editTastingNotes() { }
	 * 
	 * public void deleteTastingNotes() { }
	 */
	public static void setFavorite(Wine w)
	{
	}

	public static boolean isFavorite(long id)
	{
		return false;
	}
}
