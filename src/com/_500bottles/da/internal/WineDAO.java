package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
		columns += "`vineyardId`, `rating`, `snoothId` , `priceMin`, `priceMax`, `winecomId` )";

		// TODO: get user id from session manager or via user object.
		// TODO: getGeoLocation and getAppellation
		values = "('" + escapeXml(wine.getName()) + "',";
		values += "'" + escapeXml(wine.getDescription()) + "',";
		values += "'" + wine.getGeoLocation().getLon() + "',";
		values += "'" + wine.getGeoLocation().getLat() + "',";
		// Get type...
		values += "'" + wine.getType().getWineType() + "',";
		// Get year...
		values += "'" + wine.getYear() + "',";
		// Get varietal
		values += "'" + wine.getVarietal().getId() + "',";
		// Get vineyard
		values += "'" + wine.getVineyard().getId() + "',";
		// Get rating...
		values += "'" + wine.getRating() + "',";
		values += "'" + wine.getSnoothId() + "',";

		values += "'" + wine.getPriceMin() + "',";
		values += "'" + wine.getPriceMax() + "',";

		values += "'" + wine.getWinecomId() + "')";

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

	public static boolean deleteWine(Wine wine)
	{
		int ret;
		try
		{
			ret = delete(WINE_TABLE, "wineId=" + wine.getId());
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
			// throw new DAException("Failed Wine deletion", e.getCause());
		}
		if (ret == 0)
			return false;
		return true;
	}

	public static void editWine(Wine wine) throws DAException
	{
		long wineId = wine.getId();
		String sql = "";

		// sql += "entryID=" + entry.getEntryId();
		sql += "vineyardId=" + wine.getVineyard().getId();
		sql += ",varietalId=" + wine.getVarietal().getId();
		sql += ",appellation='"
				+ escapeXml(wine.getAppellation().getLocation()) + "'";
		sql += ",wineName='" + escapeXml(wine.getName()) + "'";
		sql += ",wineType='" + escapeXml(wine.getType().getWineType()) + "'";
		sql += ",vintage=" + wine.getYear();
		sql += ",description='" + escapeXml(wine.getDescription()) + "'";
		sql += ",priceMin=" + wine.getPriceMin();
		sql += ",priceMax=" + wine.getPriceMin();
		sql += ",rating=" + wine.getRating();
		sql += ",longitude=" + wine.getGeoLocation().getLon();
		sql += ",latitude=" + wine.getGeoLocation().getLat();
		sql += ",winecomId=" + wine.getWinecomId();
		sql += ",snoothId=' " + escapeXml(wine.getSnoothId()) + "'";

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
		System.out.println("getWine wineId: " + wineId);
		ResultSet r;
		Wine wine;

		try
		{
			r = select(WINE_TABLE, "*", "wineId=" + wineId);
			System.out.println("after the select in getWine");
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

		long wineId, year, varId, vineId, winecomId;
		double rating, priceMin, priceMax;
		float lon, lat;

		String name, description, typeString, appellationString, snoothId;
		WineType type;
		GeoLocation geoLocation;
		Appellation appellation;
		Varietal varietal;
		Vineyard vineyard;

		if (!r.next())
			return null;

		name = unescapeXml(r.getString("wineName"));
		wineId = r.getLong("wineId");
		// System.out.println("wineId: " + wineId);

		snoothId = unescapeXml(r.getString("snoothId"));
		description = unescapeXml(r.getString("description"));
		year = r.getLong("vintage");

		typeString = unescapeXml(r.getString("wineType"));

		type = new WineType();
		type.setWineType(typeString);

		appellationString = unescapeXml(r.getString("appellation"));
		appellation = new Appellation();
		appellation.setLocation(appellationString);

		lon = r.getFloat("longitude");
		lat = r.getFloat("latitude");

		geoLocation = new GeoLocation();
		geoLocation.setLat(lat);
		geoLocation.setLon(lon);

		varId = r.getLong("varietalId");
		varietal = new Varietal();
		varietal.setId(varId);

		vineId = r.getLong("vineyardId");
		vineyard = new Vineyard();
		vineyard.setId(vineId);

		rating = r.getDouble("rating");
		priceMin = r.getDouble("priceMin");
		priceMax = r.getDouble("priceMax");

		winecomId = r.getLong("winecomId");

		description = unescapeXml(r.getString("description"));
		System.out.println("after getting everything");

		wine = new Wine();

		// Set all properties of Wine
		wine.setName(name);
		wine.setId(wineId);

		wine.setSnoothId(snoothId);
		wine.setDescription(description);
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

		return wine;
	}

	@SuppressWarnings("null")
	public Vector<Wine> getAllWines() throws DAException
	{
		Vector<Wine> wineVector = null;
		ResultSet r;

		try
		{
			r = select(WINE_TABLE, "*");

			Vector<Long> wineIdVector = new Vector<Long>();
			while (r.next())
			{
				Long wineId;
				wineId = new Long(r.getInt("wineId"));
				wineIdVector.add(wineId);
			}
			for (int i = 0; i < wineIdVector.size(); i++)
			{
				Wine temp = new Wine();
				temp = WineDAO.getWine(wineIdVector.elementAt(i).longValue());
				wineVector.add(temp);
			}
			Database.disconnect();

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception", e.getCause());
		}

		return wineVector;
	}

	@SuppressWarnings("unused")
	private static Varietal addVarietal(Varietal v) throws DAException
	{
		String columns, values;

		columns = "(`varietalId`, `varietalName`)";

		// TODO: get user id from session manager or via user object.
		// TODO: getGeoLocation and getAppellation
		values = "('" + v.getId() + "',";
		values += "'" + escapeXml(v.getGrapeType()) + "')";

		try
		{
			insert("Varietals", columns, values);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Varietal insertion", e);
		}

		v.setId(getLastInsertId());

		return v;

	}
	// TODO:Add private varietal/vineyard database methods
	// TODO:Add private varietal/vineyard database methods

}
