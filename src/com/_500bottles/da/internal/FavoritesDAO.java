package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Favorites;
import com._500bottles.object.wine.Wine;

public class FavoritesDAO
{
	private static final String FAVORITES_TABLE = Config
			.getProperty("favoritesTableName");

	public static Favorites addFavorite(Favorites favorite) throws DAException
	{
		String columns, values;
		columns = "(`userID`, ";
		columns += "`wineID`)";

		// TODO:Get userId from session manager
		values = "('" + "0" + "',";
		values += "'" + favorite.getWineId() + "'";
		try
		{
			// int i =
			DAO.insert(FAVORITES_TABLE, columns, values);
			Database.disconnect();
			// System.out.print("This is what we got: " + i);
		} catch (SQLException e)
		{
			throw new DAException("Failed Favorites insertion", e);
		}

		favorite.setfavoritesId(DAO.getLastInsertId());

		return favorite;
	}

	// Might not use one of the deletes
	public static void deleteFavorite(Wine wine) throws DAException
	{
		try
		{
			DAO.delete(FAVORITES_TABLE, "WHERE wineId=" + wine.getId());
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Favorites deletion", e);
		}
	}

	public static void deleteFavorite(Favorites favorite) throws DAException
	{
		try
		{
			DAO.delete(FAVORITES_TABLE,
					"WHERE favoritesId=" + favorite.getfavoritesId());
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Favorites deletion", e);
		}
	}

	public static void editFavorite(Favorites favorite) throws DAException
	{
		long favoritesId = favorite.getfavoritesId();
		String sql = "";

		// Get userID from session manager
		sql += "wineID=" + favorite.getWineId();

		try
		{
			DAO.update(FAVORITES_TABLE, sql, "favoritesId=" + favoritesId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Favorites update", e);
		}
	}

	public static Favorites getFavorite(long favoritesId) throws DAException
	{
		ResultSet r;
		Favorites favorite = null;

		try
		{
			r = DAO.select(FAVORITES_TABLE, "*");
			favorite = createFavorites(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return favorite;
	}

	public static Favorites getFavorite(Favorites favorite) throws DAException,
			NullPointerException
	{
		if (favorite == null)
			throw new NullPointerException("Null Favorite.");
		if (favorite.getfavoritesId() == 0)
			throw new DAException("Favorites Id not set.");

		long favoriteId = favorite.getfavoritesId();
		return getFavorite(favoriteId);
	}

	private static Favorites createFavorites(ResultSet r) throws SQLException
	{
		Favorites f;

		long favoritesId, wineId;

		favoritesId = r.getLong("favoritesId");
		wineId = r.getLong("wineId");

		if (!r.next())
			return null;

		f = new Favorites();
		f.setfavoritesId(favoritesId);
		f.setWineId(wineId);

		return f;
	}
}
