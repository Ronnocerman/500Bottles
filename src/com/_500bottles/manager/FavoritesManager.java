package com._500bottles.manager;

import java.util.Vector;

import com._500bottles.da.internal.FavoritesDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Favorites;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQueryResult;

public class FavoritesManager
{
	/**
	 * Sets a favorites given the wine ID and user ID.
	 * 
	 * @param wine_id
	 *            The wine id to set as favorite.
	 * @param user_id
	 *            The user id.
	 * @throws DAException
	 */
	public static void setFavorite(long wine_id, long user_id)
			throws DAException
	{
		Favorites fave = new Favorites();

		fave.setWineId(wine_id);

		FavoritesDAO.addFavorite(user_id, fave);
	}

	/**
	 * Clears a favorites given the wine ID and user ID.
	 * 
	 * @param wine_id
	 *            The wine id to clear as favorite.
	 * @param user_id
	 *            The user id.
	 * @throws DAException
	 */
	public static void clearFavorite(long wine_id, long user_id)
			throws DAException
	{
		Favorites fave = FavoritesDAO.getFavorite(wine_id, user_id);

		FavoritesDAO.deleteFavorite(fave);
	}

	/**
	 * 
	 * @param wine_id
	 *            - the wine Id
	 * @return boolean, if this wine id is in the favorites
	 * @throws DAException
	 */
	public static boolean isFavorite(long wine_id, long user_id)
			throws DAException
	{
		if (FavoritesDAO.getFavorite(wine_id, user_id) == null)
			return false;

		return true;
	}

	/**
	 * Gets a favorites given the wine ID.
	 * 
	 * @param id
	 *            The wine id to get as favorite.
	 */
	public static WineQueryResult getFavorite(long id)
	{
		Vector<Wine> wines = new Vector<Wine>();
		try
		{
			wines = FavoritesDAO.getFavorites(id);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new WineQueryResult(wines);
	}
}
