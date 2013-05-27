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

	private final static String WINE_TABLE = Config
			.getProperty("wineTableName");

	public static Wine addWine(Wine wine) throws Exception
	{
		/*
		 * private long id; private String name; private String description;
		 * private GeoLocation geoLocation; private WineType type; private long
		 * year; private Appellation appellation; private Varietal varietal;
		 * private Vineyard vineyard; private int rating;
		 */

		String columns, values;

		columns = "(`vineyardId`, `varietalId`, `appellation`";
		columns += "`wineName`, `wineType`,";
		columns += "`vintage`, `description`,";
		columns += "`priceMin`, `priceMax`,";
		columns += "`rating`, `longitude`,";
		columns += "`latitude`, `winecomId`,";
		columns += "`snoothId`)";

		// TODO: get user id from session manager or via user object.
		// TODO: getGeoLocation and getAppellation
		values = "('" + wine.getVineyard().getId() + "',";
		values += "'" + wine.getVarietal().getId() + "',";
		values += "'" + wine.getAppellation().getLocation() + "',";
		values += "'" + wine.getName() + "',";
		values += "'" + wine.getType() + "',";
		values += "'" + wine.getYear() + "',";
		values += "'" + wine.getDescription() + "',";
		values += "'" + wine.getPriceMin() + "',";
		values += "'" + wine.getPriceMax() + "',";
		values += "'" + wine.getRating() + "'";
		values += "'" + wine.getGeoLocation().getLon() + "',";
		values += "'" + wine.getGeoLocation().getLat() + "',";
		values += "'" + wine.getWinecomId() + "',";
		values += "'" + wine.getSnoothId() + "',)";

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

		// Get double from ResultSet
		rating = r.getDouble("rating");

		// Get longs from ResultSet
		wineId = r.getLong("wineId");
		year = r.getLong("vintage");
		priceMin = r.getLong("priceMin");
		priceMax = r.getLong("priceMax");
		winecomId = r.getLong("winecomId");
		varId = r.getLong("varietalId");
		vineId = r.getLong("vineyardId");

		// Get floats from ResultSet
		lon = r.getFloat("longitude");
		lat = r.getFloat("latitude");

		// Get strings from ResultSet
		name = r.getString("wineName");
		typeString = r.getString("wineType");
		snoothId = r.getString("snoothId");
		description = r.getString("description");
		appellationString = r.getString("appellation");

		// Create objects to set the Wine object
		type = new WineType();
		type.setWineType(typeString);

		appellation = new Appellation();
		appellation.setLocation(appellationString);

		geoLocation = new GeoLocation();
		geoLocation.setLat(lat);
		geoLocation.setLon(lon);

		varietal = new Varietal();
		varietal.setId(varId);

		vineyard = new Vineyard();
		vineyard.setId(vineId);

		// Create Wine object
		wine = new Wine();

		// Set all properties of Wine
		wine.setName(name);
		wine.setId(wineId);
		wine.setYear(year);
		wine.setType(type);
		wine.setRating(rating);
		wine.setGeoLocation(geoLocation);
		wine.setAppellation(appellation);
		wine.setVarietal(varietal);
		wine.setVineyard(vineyard);
		wine.setPriceMin(priceMin);
		wine.setPriceMax(priceMax);
		wine.setWinecomId(winecomId);
		wine.setSnoothId(snoothId);
		wine.setDescription(description);

		return wine;
	}

}
