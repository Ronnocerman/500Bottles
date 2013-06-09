package com._500bottles.action;

import java.util.Vector;

import com._500bottles.manager.WineManager;
import com._500bottles.object.wine.CustomWine;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

public class WineAction
{
	public static WineQueryResult searchWine(WineQuery query)
	{
		// TODO: Error checking on query object.

		return WineManager.searchWine(query);
	}

	/**
	 * Adds a custom wine to the user's database
	 * 
	 * @param id
	 *            ID of custom wine to added into the database
	 * 
	 */
	public static void createCustomWine(long id)
	{
		Wine w = WineManager.getWine(id);
		WineManager.addCustomWine((CustomWine) w);
	}

	/**
	 * Edits a custom wine entry
	 * 
	 * @param id
	 *            ID of custom wine to be edited in the database
	 */
	public static void editCustomWine(long id)
	{
		Wine w = WineManager.getWine(id);
		WineManager.editCustomWine((CustomWine) w);
	}

	/**
	 * Deletes a custom wine from the database
	 * 
	 * @param id
	 *            ID of the custom wine to be deleted from the database
	 */
	public static void deleteCustomWine(long id)
	{
		WineManager.deleteCustomWine(id);
	}

	/**
	 * Sets the rating of a specified wine, to the specified value
	 * 
	 * @param id
	 *            ID of the wine to be rated in the database
	 * @param rating
	 *            Rating of the wine to be rated
	 */
	public static void setRating(long id, int rating)
	{
		WineManager.setRating(id, rating);
	}

	public static Vector<String> getWineTypes()
	{
		return WineManager.getWineTypes();
	}
}
