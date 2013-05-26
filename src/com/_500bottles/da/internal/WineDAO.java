package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.geolocation.GeoLocation;
import com._500bottles.object.wine.Appellation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

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

		columns = "(`name`, `description`, `longitude`, `latitude`,";
		columns += "`type`, `year`,";
		columns += "`appellation`, `varietal`,";
		columns += "`vineyard`, `rating`)";

		// TODO: get user id from session manager or via user object.
		// TODO: getGeoLocation and getAppellation
		values = "('" + wine.getName() + "',";
		values += "'" + wine.getDescription() + "',";
		values += "'" + wine.getGeoLocation().getLon() + "',";
		values += "'" + wine.getGeoLocation().getLat() + "',";
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

	public static void deleteWine(Wine wine) throws DAException
	{
		try
		{
			delete(WINE_TABLE, "WHERE wineId=" + wine.getId());
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e);
		}

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

	public static Wine getWine(Wine wine) throws DAException
	{
		if (wine == null)
			throw new DAException("Specified Wine object is null.");

		return getWine(wine.getId());
	}

	public static Wine getWine(long wineId) throws DAException
	{
		ResultSet r;
		Wine wine;

		try
		{
			r = select(WINE_TABLE, "*");
			wine = createWine(r);

		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e.getCause());
		}

		return wine;
	}

	private static Wine createWine(ResultSet r) throws SQLException
	{
		Wine wine;

		long wineId, year, varId, vineId;
		int rating;
		float lon, lat;

		String name, description, typeString, appellationString;
		WineType type;
		GeoLocation geoLocation;
		Appellation appellation;
		Varietal varietal;
		Vineyard vineyard;

		if (!r.next())
			return null;
		/*
		 * sql += "name=" + wine.getName(); sql += ",description=" +
		 * wine.getDescription(); sql += ",geoLocation=" +
		 * wine.getGeoLocation(); sql += ",type=" + wine.getType(); sql +=
		 * ",year=" + wine.getYear(); sql += ",appellation=" +
		 * wine.getAppellation(); sql += ",varietal=" +
		 * wine.getVarietal().getId(); sql += ",vineyard=" +
		 * wine.getVineyard().getId(); sql += ",rating=" + wine.getRating();
		 */

		name = r.getString("name");
		wineId = r.getLong("wineId");
		year = r.getLong("year");
		typeString = r.getString("type");

		type = new WineType();
		type.setWineType(typeString);

		appellationString = r.getString("appellation");
		appellation = new Appellation();
		appellation.setLocation(appellationString);

		lon = r.getFloat("longitude");
		lat = r.getFloat("latitude");

		geoLocation = new GeoLocation();
		geoLocation.setLat(lat);
		geoLocation.setLon(lon);

		varId = r.getLong("varietal");
		varietal = new Varietal();
		varietal.setId(varId);

		vineId = r.getLong("vineyard");
		vineyard = new Vineyard();
		vineyard.setId(vineId);

		rating = r.getInt("rating");

		description = r.getString("description");

		wine = new Wine();

		wine.setName(name);
		wine.setId(wineId);
		wine.setYear(year);
		wine.setType(type);
		wine.setRating(rating);
		wine.setGeoLocation(geoLocation);
		wine.setAppellation(appellation);
		wine.setVarietal(varietal);
		wine.setVineyard(vineyard);

		return wine;
	}

}
