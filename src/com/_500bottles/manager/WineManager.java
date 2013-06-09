package com._500bottles.manager;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.CustomWine;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

public class WineManager
{
	/**
	 * Gets wine by specified ID
	 * 
	 * @param wineId
	 *            ID of wine to be returned
	 * @return Wine object of specified Wine ID
	 */
	public static Wine getWine(long wineId)
	{
		Wine wine = null;

		try
		{
			wine = WineDAO.getWine(wineId);
		} catch (DAException e)
		{
			// TODO:
		}

		return wine;
	}

	/**
	 * Gets and returns a Wine by snoothId
	 * 
	 * @param snoothId
	 *            of the wine to be returned
	 * @return The Wine that will be returned by snoothId
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
			return null;
		}

		return resultWine;
	}

	/**
	 * Get the Wine associated with the WineComID
	 * 
	 * @param wineComId
	 *            WineComId of the wine to be returned
	 * @return The wine associated with the WineComId
	 */
	static Wine getWineByWineComId(long wineComId)
	{
		Wine resultWine = null, searchWine = new Wine();
		searchWine.setWinecomId(wineComId);

		try
		{
			resultWine = WineDAO.getWine(searchWine);
		} catch (DAException e)
		{
			return null;
		}

		return resultWine;
	}

	/**
	 * Return WineQueryResult from Specified Wine Query
	 * 
	 * @param query
	 *            The WineQuery to be searched with
	 * @return The WineQueryResult of the WineQuery search
	 */
	public static WineQueryResult searchWine(WineQuery query)

	{
		WineQueryResult result = null;

		try
		{
			result = WineQueryManager.search(query);
		} catch (NullPointerException e)
		{
			System.err.println("NullPointerException caught!");
			e.printStackTrace();
		} catch (Exception e)
		{
			System.err.println(e.getMessage());
		}

		return result;
	}

	/**
	 * Adds custom wine to database
	 * 
	 * @param w
	 *            CustomWine object to be added to database
	 * @throws DAException
	 * 
	 */
	public static void addCustomWine(CustomWine w)
	{
		try
		{
			WineDAO.addWine(w);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
		}
	}

	/**
	 * Edits the custom wine of the specified CUatomWine object
	 * 
	 * @param w
	 *            The CustomWine object of the wine to be edited
	 * @throws DAException
	 * 
	 */
	public static void editCustomWine(CustomWine w)
	{
		try
		{
			WineDAO.editWine(w);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
		}
	}

	/**
	 * Delete custom wine with specified Id
	 * 
	 * @param id
	 *            The Id of the custom wine to be deleted
	 */
	public static boolean deleteCustomWine(long id)
	{
		Wine w = getWine(id);
		return WineDAO.deleteWine(w);
	}

	/**
	 * Set the rating of the specified Wine using the specified rating
	 * 
	 * @param id
	 *            The ID of the wine that will be set with the specified rating
	 * @param rating
	 *            The rating of the that specified wine will be set to
	 */
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
}
