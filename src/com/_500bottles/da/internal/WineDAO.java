package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeXml;

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
	private final static String WINE_TABLE = Config
			.getProperty("wineTableName");

	public static Wine addWine(Wine wine) throws DAException
	{
		/*
		 * private long id; private String name; private String description;
		 * private GeoLocation geoLocation; private WineType type; private long
		 * year; private Appellation appellation; private Varietal varietal;
		 * private Vineyard vineyard; private int rating;
		 */

		String columns, values;

		columns = "(`wineName`, `description`, `longitude`, `latitude`,";
		columns += "`wineType`, `vintage`,";
		columns += "`varietalId`,";
		columns += "`vineyardId`, `rating`, `snoothId`)";

		// TODO: get user id from session manager or via user object.
		// TODO: getGeoLocation and getAppellation
		values = "('" + wine.getName() + "',";
		values += "'" + escapeXml(wine.getDescription()) + "',";
		values += "'" + wine.getGeoLocation().getLon() + "',";
		values += "'" + wine.getGeoLocation().getLat() + "',";
		// Get type...
		values += "'" + "0" + "',";
		// Get year...
		values += "'" + "1" + "',";
		// Get varietal
		values += "'" + "0" + "',";
		// Get vineyard
		values += "'" + "0" + "',";
		// Get rating...
		values += "'" + "0" + "',";
		values += "'" + wine.getSnoothId() + "')";

		try
		{
			insert(WINE_TABLE, columns, values);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Wine insertion", e);
		}

		wine.setId(getLastInsertId());

		return wine;

	}

	public static void deleteWine(Wine wine) throws DAException
	{
		try
		{
			delete(WINE_TABLE, "WHERE wineId=" + wine.getId());
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Wine deletion", e.getCause());
		}

	}

	public static void editWine(Wine wine) throws DAException
	{
		long wineId = wine.getId();
		String sql = "";

		// sql += "entryID=" + entry.getEntryId();
		sql += "vineyardId=" + wine.getVineyard().getId();
		sql += ",varietalId=" + wine.getVarietal().getId();
		sql += ",appellation=" + wine.getAppellation().getLocation();
		sql += ",wineName=" + wine.getName();
		sql += ",wineType=" + wine.getType();
		sql += ",vintage=" + wine.getYear();
		sql += ",description=" + wine.getDescription();
		sql += ",priceMin=" + wine.getPriceMin();
		sql += ",priceMax=" + wine.getPriceMin();
		sql += ",rating=" + wine.getRating();
		sql += ",longitude=" + wine.getGeoLocation().getLon();
		sql += ",latitude=" + wine.getGeoLocation().getLat();
		sql += ",winecomId=" + wine.getWinecomId();
		sql += ",snoothId=" + wine.getSnoothId();

		try
		{
			update(WINE_TABLE, sql, "wineId=" + wineId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Wine update", e);
		}
	}

	public static Wine getWine(Wine wine) throws DAException
	{
		if (wine == null)
			throw new DAException("Specified Wine object is null.");

		if (wine.getId() > 0)
			return getWine(wine.getId());

		if (wine.getSnoothId() != "")
			return getWineBySnoothId(wine.getSnoothId());

		throw new DAException("No valid criteria specified to getWine.");
	}

	public static Wine getWine(long wineId) throws DAException
	{
		ResultSet r;
		Wine wine;

		try
		{
			r = select(WINE_TABLE, "*");
			wine = createWine(r);
			Database.disconnect();

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e.getCause());
		}

		return wine;
	}

	public static Wine getWineBySnoothId(String snoothId) throws DAException
	{
		ResultSet r;
		Wine wine;

		try
		{
			r = select(WINE_TABLE, "*", "snoothId='" + snoothId + "'");
			wine = createWine(r);
			Database.disconnect();

		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e.getCause());
		}

		return wine;
	}

	private static Wine createWine(ResultSet r) throws SQLException
	{
		Wine wine;

		long wineId, year, varId, vineId, priceMin, priceMax, winecomId;
		double rating;
		float lon, lat;

		String name, description, typeString, appellationString, snoothId;
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

		name = r.getString("wineName");
		wineId = r.getLong("wineId");
		snoothId = r.getString("snoothId");
		description = unescapeXml(r.getString("description"));
		// year = r.getLong("vintage");
		// typeString = r.getString("type");

		// type = new WineType();
		// type.setWineType(typeString);

		// appellationString = r.getString("appellation");
		// appellation = new Appellation();
		// appellation.setLocation(appellationString);

		// lon = r.getFloat("longitude");
		// lat = r.getFloat("latitude");

		// geoLocation = new GeoLocation();
		// geoLocation.setLat(lat);
		// geoLocation.setLon(lon);

		// varId = r.getLong("varietal");
		// varietal = new Varietal();
		// varietal.setId(varId);

		// vineId = r.getLong("vineyard");
		// vineyard = new Vineyard();
		// vineyard.setId(vineId);

		// rating = r.getInt("rating");

		// description = r.getString("description");

		wine = new Wine();

		// Set all properties of Wine
		wine.setName(name);
		wine.setId(wineId);

		wine.setSnoothId(snoothId);
		wine.setDescription(description);
		// wine.setYear(year);
		// wine.setType(type);
		// wine.setRating(rating);
		// wine.setGeoLocation(geoLocation);
		// wine.setAppellation(appellation);
		// wine.setVarietal(varietal);
		// wine.setVineyard(vineyard);

		return wine;
	}

}
