package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.object.wine.Favorites;
import com._500bottles.object.wine.Wine;

public class FavoritesDAO
{
	private static final String FAVORITES_TABLE = Config
			.getProperty("favoritesTableName");

	public static Favorites addFavorite(Favorites favorite) throws Exception
	{
		String columns, values;
		columns = "(`userID`, ";
		columns += "`wineID`)";

		// Get userId from session manager
		values = "('" + "0" + "',";
		values += "'" + favorite.getWineId() + "'";
		try
		{
			int i = DAO.insert(FAVORITES_TABLE, columns, values);
			System.out.print("This is what we got: " + i);
			// TODO: Better exception handling.
		} catch (Exception e)
		{
			throw e;
		}

		favorite.setfavoritesId(DAO.getLastInsertId());

		return favorite;
	}

	public void deleteFavorite(Wine wine) throws SQLException
	{
		DAO.delete(FAVORITES_TABLE, "WHERE cellarItemId=" + wine.getId());
	}

	public void editFavorite(Favorites favorite) throws SQLException
	{
		long favoritesId = favorite.getfavoritesId();
		String sql = "";

		// Get userID from session manager
		sql += "wineID=" + favorite.getWineId();

		DAO.update(FAVORITES_TABLE, sql, "favoritesId=" + favoritesId);
	}

	public Favorites getFavorite(long favoritesId)
	{
		ResultSet r;
		Favorites favorite = null;

		try
		{
			r = DAO.select(FAVORITES_TABLE, "*");
			favorite = createFavorites(r);

		} catch (Exception e)
		{
			// TODO: handle query exceptions.
		}

		return favorite;
	}

	private static Favorites createFavorites(ResultSet r) throws SQLException // maybe
	{
		Favorites f;

		long favoritesId;

		favoritesId = r.getLong("favoritesId");

		if (!r.next())
			return null;

		f = new Favorites();
		f.setfavoritesId(favoritesId);

		return f;
	}
}
