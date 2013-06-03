package com._500bottles.action;

import com._500bottles.exception.da.DAException;
import com._500bottles.manager.WineManager;
import com._500bottles.object.wine.Wine;

public class WineAction
{
	/**
	 * Adds a custom wine to the user's database
	 * 
	 * @param id
	 *            ID of custom wine to added into the database
	 * 
	 */
	public void createCustomWine(long id)
	{
		Wine w = WineManager.getWine(id);
		WineManager.addCustomWine(w);
	}

	/**
	 * Edits a custom wine entry
	 * 
	 * @param id
	 *            ID of custom wine to be edited in the database
	 */
	public void editCustomWine(long id)
	{
		Wine w = WineManager.getWine(id);
		WineManager.editCustomWine(w);
	}

	/**
	 * Deletes a custom wine from the database
	 * 
	 * @param id
	 *            ID of the custom wine to be deleted from the database
	 */
	public void deleteCustomWine(long id)
	{
		WineManager.deleteCustomWine(id);
	}

	/**
	 * Set a specified wine to be a favorite
	 * 
	 * @param id
	 *            ID of the wine to be favorited
	 */
	public void setFavorite(long id)
	{
		Wine w = WineManager.getWine(id);
		try
		{
			WineManager.setFavorite(w);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets whether the specified wine is a favorited wine
	 * 
	 * @param id
	 *            ID of the wine that will be checked whether is favorited.
	 */
	public void getFavorite(long id)
	{
		try
		{
			WineManager.isFavorite(id);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the rating of a specified wine, to the specified value
	 * 
	 * @param id
	 *            ID of the wine to be rated in the database
	 * @param rating
	 *            Rating of the wine to be rated
	 */
	public void setRating(long id, int rating)
	{
		WineManager.setRating(id, rating);
	}
}
