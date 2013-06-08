package com._500bottles.action;

import com._500bottles.exception.da.DAException;
import com._500bottles.manager.FavoritesManager;
import com._500bottles.manager.WineManager;
import com._500bottles.object.wine.Wine;

public class FavoritesAction
{
	/**
	 * Set a specified wine to be a favorite
	 *
	 * @param wine_id	The id of the wine to set as a favorite.
	 * @param user_id	The user id.
	 */
	public static void setFavorite(long wine_id, long user_id)
	{
		// Check to ensure the wine actually exists.
		// TODO: throw generic exception here to notify the user the
		// favorite failed?
		if (WineManager.getWine(wine_id) == null)
			return;
 		try {
			FavoritesManager.setFavorite(wine_id, user_id);
		} catch (DAException e) {
			// TODO: throw generic exception here to notify the user the
		}
	}

	public static void clearFavorite(long wine_id, long user_id)
	{
		try {
			FavoritesManager.clearFavorite(wine_id, user_id);
		} catch (DAException e) {
			// TODO: throw generic exception here to notify the user the
		}
	}

	/**
	 * Gets whether the specified wine is a favorited wine
	 * 
	 * @param id 	ID of the wine that will be checked whether is a
	 *              favorite.
	 */
	public static void getFavorite(long id)
	{
//		try {
//			WineManager.isFavorite(id);
//		} catch (DAException e) {
//			e.printStackTrace();
//		}
	}
}
