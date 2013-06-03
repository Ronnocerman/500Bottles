package com._500bottles.manager;

import com._500bottles.da.internal.FavoritesDAO;
import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Favorites;
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

	static Wine getWineByWineComId(long id)
	{

		Wine resultWine = null, searchWine = new Wine();
		searchWine.setWinecomId(id);

		try
		{
			resultWine = WineDAO.getWine(searchWine);
		} catch (DAException e)
		{
			System.err.print("DA Exception in WineManager::getWineByWineId"
					+ e.getMessage());
		}

		return resultWine;
	}

	public static WineQueryResult searchWine(WineQuery query)

	{
		WineQueryResult result = null;

		try
		{
			result = WineQueryManager.search(query);
		} catch (Exception e)
		{
			// throws InvalidCategory, InvalidSort, InvalidOtherParameters,
			// IOException, ParseException, DAException

			// TODO: fix this mess of shit.
		}

		return result;
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

	public static void setFavorite(Wine w) throws DAException
	{
		Favorites fave = new Favorites();
		fave.setWineId(w.getId());
		long userId = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		FavoritesDAO.addFavorite(userId, fave);

	}

	/**
	 * 
	 * @param id
	 *            - the wine Id
	 * @return boolean, if this wine id is in the favorites
	 * @throws DAException
	 */
	public static boolean isFavorite(long wineId) throws DAException
	{
		Favorites fave = new Favorites();

		fave = FavoritesDAO.getFavorite(wineId);
		if (fave == null)
		{
			return false;
		}

		return true;
	}

}
