package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.wine.Favorites;
import com._500bottles.object.wine.Wine;

/**
 * Coordinates all favorites related database access for favorite adding,
 * deleting, and getting in the database.
 */
public class FavoritesDAO extends DAO
{
	private static final String FAVORITES_TABLE = Config
			.getProperty("favoritesTableName");

	/**
	 * Adds the Favorite to the favorites table
	 * 
	 * @param userId
	 *            The userId to associate the user with
	 * @param favorite
	 *            The favorites object to add to the table
	 * @return the Favorites object added
	 * @throws DAException
	 */
	public static Favorites addFavorite(long userId, Favorites favorite)
			throws DAException
	{
		String columns, values;
		columns = "(`userID`, ";
		columns += "`wineID`)";

		values = "('" + userId + "',";
		values += "'" + favorite.getWineId() + "')";
		try
		{
			insert(FAVORITES_TABLE, columns, values);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Favorites insertion", e);
		}

		favorite.setfavoritesId(DAO.getLastInsertId());

		return favorite;
	}

	/**
	 * Deletes the favorite of the wine and userId
	 * 
	 * @param userId
	 *            The userId associated with the favorite to delete
	 * @param wine
	 *            The wine to delete from the favorites table
	 * @return true if favorite was deleted, false otherwise.
	 */
	public static boolean deleteFavorite(long userId, Wine wine)
	{
		try
		{
			delete(FAVORITES_TABLE, "wineId=" + wine.getId() + " and userId="
					+ userId);
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
			// throw new DAException("Failed Favorites deletion", e);
		}
		return true;
	}

	/**
	 * Deletes the favorite specified
	 * 
	 * @param favorite
	 *            The favorites object to be deleted
	 * @return true if favorite was deleted, false otherwise.
	 */
	public static boolean deleteFavorite(Favorites favorite)
	{
		int ret;
		try
		{
			ret = delete(FAVORITES_TABLE,
					"favoritesId=" + favorite.getfavoritesId());
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
		}
		if (ret == 0)
			return false;
		return true;
	}

	/**
	 * Edits the favorite object
	 * 
	 * @param favorite
	 *            The final favorite object after edited
	 * @throws DAException
	 */
	public static void editFavorite(Favorites favorite) throws DAException
	{
		long favoritesId = favorite.getfavoritesId();
		String sql = "";

		// Get userID from session manager
		sql += "userId="
				+ SessionManager.getSessionManager().getLoggedInUser()
						.getUserId() + " ,";
		sql += "wineID=" + favorite.getWineId();

		try
		{
			update(FAVORITES_TABLE, sql, "favoritesId=" + favoritesId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Favorites update", e);
		}
	}

	/**
	 * Gets the favorites object of the wine Id and user Id
	 * 
	 * @param wine_id
	 *            The wine Id of the wine to find favorite for
	 * @param user_id
	 *            User Id of the user to find favorite for
	 * @return the favorite object of the associated wine and user
	 * @throws DAException
	 */
	public static Favorites getFavorite(long wine_id, long user_id)
			throws DAException
	{
		ResultSet r;
		Favorites favorite = null;

		try
		{
			String where = "wineId = " + wine_id;
			where += " AND " + "userId = " + user_id;
			r = select(FAVORITES_TABLE, "*", where);

			favorite = createFavorites(r);
			Database.disconnect();

		} catch (SQLException e)
		{
			System.out.println("Message: " + e.getMessage());
			throw new DAException("SQL select exception.", e);
		}

		return favorite;
	}

	/**
	 * Gets the favorites from that user specified
	 * 
	 * @param user_id
	 *            The user Id of which to get all the favorites from
	 * @return Vector of wines of that user's favorites
	 * @throws DAException
	 */
	public static Vector<Wine> getFavorites(long user_id) throws DAException
	{
		ResultSet r;
		Vector<Long> wineIdVector = new Vector<Long>();
		Vector<Wine> output = new Vector<Wine>();

		try
		{
			String where = "userId = " + user_id;
			r = select(FAVORITES_TABLE, "*", where);
			while (r.next())
			{
				wineIdVector.add(r.getLong("wineId"));
			}
			Database.disconnect();
			for (int i = 0; i < wineIdVector.size(); i++)
			{
				Wine temp = new Wine();
				temp = WineDAO.getWine(wineIdVector.get(i).longValue());
				output.add(temp);
			}

			return output;

		} catch (SQLException e)
		{
			// System.out.println("Message: " + e.getMessage());
			throw new DAException("SQL select exception.", e);
		}

	}

	/**
	 * Creates the favorite object from a result set
	 * 
	 * @param r
	 *            result set to construct the favorites object from
	 * @return Favorites object
	 * @throws SQLException
	 */
	private static Favorites createFavorites(ResultSet r) throws SQLException
	{
		Favorites f;

		long favoritesId, wineId;

		if (!r.next())
			return null;

		favoritesId = r.getLong("favoritesId");
		wineId = r.getLong("wineId");

		f = new Favorites();
		f.setfavoritesId(favoritesId);
		f.setWineId(wineId);

		return f;
	}
}
