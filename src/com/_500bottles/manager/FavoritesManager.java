package com._500bottles.manager;

import com._500bottles.da.internal.FavoritesDAO;
import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.*;

public class FavoritesManager
{
	/**
	 * Sets a favorites given the wine ID and user ID.
	 * @param wine_id	The wine id to set as favorite.
	 * @param user_id	The user id.
	 * @throws DAException
	 */
	public static void setFavorite(long wine_id, long user_id) throws DAException
	{
		Favorites fave = new Favorites();

		fave.setWineId(wine_id);

		FavoritesDAO.addFavorite(user_id, fave);
	}

	/**
	 *
	 * @param wineId
	 *            - the wine Id
	 * @return boolean, if this wine id is in the favorites
	 * @throws com._500bottles.exception.da.DAException
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
