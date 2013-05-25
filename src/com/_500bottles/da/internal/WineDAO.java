package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.object.wine.Wine;

public class WineDAO extends DAO
{

	private final static String WINE_TABLE = Config.getProperty("wineName");

	public static Wine addWine(Wine wine) throws Exception
	{
		/*
		 * private long id; private String name; private String description;
		 * private GeoLocation geoLocation; private WineType type; private long
		 * year; private Appellation appellation; private Varietal varietal;
		 * private Vineyard vineyard; private int rating;
		 */

		String columns, values;

		columns = "(`name`, `description`, `geoLocation`,";
		columns += "`type`, `year`,";
		columns += "`appellation`, `varietal`,";
		columns += "`vineyard`, `rating`)";

		// TODO: get user id from session manager or via user object.
		// TODO: getGeoLocation and getAppellation
		values = "('" + wine.getName() + "',";
		values += "'" + wine.getDescription() + "',";
		values += "'" + wine.getGeoLocation() + "',";
		values += "'" + wine.getType() + "',";
		values += "'" + wine.getYear() + "',";
		values += "'" + wine.getAppellation() + "',";
		values += "'" + wine.getVarietal().getId() + "',";
		values += "'" + wine.getVineyard().getId() + "',";
		values += "'" + wine.getAppellation() + "',";
		values += "'" + wine.getRating() + "')";

		try
		{
			int i = insert(WINE_TABLE, columns, values);
			// TODO: Better exception handling.
		} catch (Exception e)
		{
			throw e;
		}

		wine.setId(getLastInsertId());

		return wine;

	}

	public static void deleteWine(Wine wine) throws SQLException
	{
		delete(WINE_TABLE, "WHERE wineId=" + wine.getId());
	}

	public static void editWine(Wine wine) throws SQLException
	{

		long wineId = wine.getId();
		String sql = "";

		// sql += "entryID=" + entry.getEntryId();
		sql += "name=" + wine.getName();
		sql += ",description=" + wine.getDescription();
		sql += ",geoLocation=" + wine.getGeoLocation();
		sql += ",type=" + wine.getType();
		sql += ",year=" + wine.getYear();
		sql += ",appellation=" + wine.getAppellation();
		sql += ",varietal=" + wine.getVarietal().getId();
		sql += ",vineyard=" + wine.getVineyard().getId();
		sql += ",rating=" + wine.getRating();

		update(WINE_TABLE, sql, "wineId=" + wineId);
	}

	public static Wine getWine(Wine wine)
	{
		long wineId = wine.getId();
		return getWine(wineId);
	}

	public static Wine getWine(long wineId)
	{
		ResultSet r;
		Wine wine = null;

		try
		{
			r = select(WINE_TABLE, "*");
			wine = createWine(r);

		} catch (Exception e)
		{
			// TODO: handle query exceptions.
		}

		return wine;
	}

	private static Wine createWine(ResultSet r)
	{
		Wine wine;

		// TODO: figure out how to instantiate Geolocation. Also, get the
		// varietal+vineyardId and set the new wine to it.

		wine = new Wine();
		return wine;
	}
}
