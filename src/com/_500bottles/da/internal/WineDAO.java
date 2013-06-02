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
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

public class WineDAO extends DAO
{
	private final static String WINE_TABLE = Config
			.getProperty("wineTableName");
	private final static String VARIETALS_TABLE = Config
			.getProperty("varietalsTableName");
	private final static String VINEYARDS_TABLE = Config
			.getProperty("vineyardsTableName");

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
			// System.out.println("after the select in getWine");
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

	/*
	 * @SuppressWarnings("null") public Vector<Wine> getAllWines() throws
	 * DAException { Vector<Wine> wineVector = null; ResultSet r;
	 * 
	 * try { r = select(WINE_TABLE, "*");
	 * 
	 * Vector<Long> wineIdVector = new Vector<Long>(); while (r.next()) { Long
	 * wineId; wineId = new Long(r.getInt("wineId")); wineIdVector.add(wineId);
	 * } for (int i = 0; i < wineIdVector.size(); i++) { Wine temp = new Wine();
	 * temp = WineDAO.getWine(wineIdVector.elementAt(i).longValue());
	 * wineVector.add(temp); } Database.disconnect();
	 * 
	 * } catch (SQLException e) { throw new DAException("SQL select exception",
	 * e.getCause()); }
	 * 
	 * return wineVector; }
	 */
	@SuppressWarnings("null")
	public static Vector<Wine> getWinesFromQuery(WineQuery q)
			throws DAException
	{
		Vector<Wine> ret = null;
		ResultSet r;
		boolean first = true;
		String where = "";

		if (q == null)
			throw new NullPointerException("Null Wine Query");
		if (q.getNameContains() != WineQuery.DEFAULT_NAME_CONTAINS)
		{
			where += "wineName";
			where += " LIKE ";
			where += "'%" + escapeXml(q.getNameContains()) + "%'";
		}
		if (q.getType().size() != WineQuery.DEFAULT_WINE_TYPE.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getType().size(); i++)
			{

				if (first)
				{
					where += "wineType='";
					where += escapeXml(q.getType().get(i).getWineType());
					where += "'";
					first = false;

				} else if (exists)
				{
					where += " and ";
					where += "wineType='";
					where += escapeXml(q.getType().get(i).getWineType());
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "wineType='";
					where += escapeXml(q.getType().get(i).getWineType());
					where += "'";
				}

			}
		}
		if (q.getMinYear() != WineQuery.DEFAULT_MIN_YEAR
				&& q.getMaxYear() != WineQuery.DEFAULT_MAX_YEAR)
		{
			if (first)
			{
				where += "vintage>=";
				where += q.getMinYear();
				where += " and vintage<=";
				where += q.getMaxYear();
				first = false;
			} else
			{
				where += " and ";
				where += "vintage>=";
				where += q.getMinYear();
				where += " and ";
				where += "vintage<=";
				where += q.getMaxYear();
			}

		} else if (q.getMinYear() != WineQuery.DEFAULT_MIN_YEAR)
		{
			if (first)
			{
				where += "vintage<=";
				where += q.getMaxYear();
				first = false;
			} else
			{
				where += " and ";
				where += "vintage<=";
				where += q.getMaxYear();
			}

		} else if (q.getMaxYear() != WineQuery.DEFAULT_MAX_YEAR)
		{
			if (first)
			{
				where += "vintage>=";
				where += q.getMinYear();
			} else
			{
				where += " and ";
				where += "vintage>=";
				where += q.getMinYear();
			}
		}
		if (q.getAppellation().size() != WineQuery.DEFAULT_APPELLATION.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getAppellation().size(); i++)
			{
				if (first)
				{
					where += "appellation='";
					where += escapeXml(q.getAppellation().get(i).getLocation());
					where += "'";
					first = false;
				} else if (exists)
				{
					where += " and ";
					where += "appellation='";
					where += escapeXml(q.getAppellation().get(i).getLocation());
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "appellation='";
					where += escapeXml(q.getAppellation().get(i).getLocation());
					where += "'";
				}
			}
		}

		if (q.getVarietal().size() != WineQuery.DEFAULT_VARIETAL.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getVarietal().size(); i++)
			{
				if (first)
				{
					where += "varietalId=";
					where += q.getVarietal().get(i).getId();
					first = false;
				} else if (exists)
				{
					where += " and ";
					where += "varietalId='";
					where += q.getVarietal().get(i).getId();
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "varietalId='";
					where += q.getVarietal().get(i).getId();
					where += "'";
				}
			}
		}
		if (q.getVineyard().size() != WineQuery.DEFAULT_VINEYARD.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getVineyard().size(); i++)
			{
				if (first)
				{
					where += "vineyardId=";
					where += q.getVineyard().get(i).getId();
					first = false;
				} else if (exists)
				{
					where += " and ";
					where += "vineyardId='";
					where += q.getVineyard().get(i).getId();
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "vineyardId='";
					where += q.getVarietal().get(i).getId();
					where += "'";
				}
			}
		}
		if (q.getMinRating() != WineQuery.DEFAULT_MIN_RATING
				&& q.getMaxRating() != WineQuery.DEFAULT_MAX_RATING)
		{
			if (first)
			{
				where += "rating>=";
				where += q.getMinRating();
				where += " and rating<=";
				where += q.getMaxRating();
				first = false;
			} else
			{
				where += " and ";
				where += "rating>=";
				where += q.getMinRating();
				where += " and ";
				where += "rating<=";
				where += q.getMaxRating();
			}

		} else if (q.getMinRating() != WineQuery.DEFAULT_MIN_RATING)
		{
			if (first)
			{
				where += "rating<=";
				where += q.getMaxRating();
				first = false;
			} else
			{
				where += " and ";
				where += "rating<=";
				where += q.getMaxRating();
			}

		} else if (q.getMaxRating() != WineQuery.DEFAULT_MAX_RATING)
		{
			if (first)
			{
				where += "rating>=";
				where += q.getMinRating();
			} else
			{
				where += " and ";
				where += "rating>=";
				where += q.getMinRating();
			}
		}
		if (q.getMinPrice() != WineQuery.DEFAULT_MIN_PRICE
				&& q.getMaxPrice() != WineQuery.DEFAULT_MAX_PRICE)
		{
			if (first)
			{
				where += "priceMin>=";
				where += q.getMinPrice();
				where += " and priceMax<=";
				where += q.getMaxPrice();
				first = false;
			} else
			{
				where += " and ";
				where += "priceMin>=";
				where += q.getMinPrice();
				where += " and ";
				where += "priceMax<=";
				where += q.getMaxPrice();
			}

		} else if (q.getMinPrice() != WineQuery.DEFAULT_MIN_PRICE)
		{
			if (first)
			{
				where += "priceMax<=";
				where += q.getMaxPrice();
				first = false;
			} else
			{
				where += " and ";
				where += "priceMax<=";
				where += q.getMaxPrice();
			}

		} else if (q.getMaxPrice() != WineQuery.DEFAULT_MAX_PRICE)
		{
			if (first)
			{
				where += "priceMin>=";
				where += q.getMinPrice();
			} else
			{
				where += " and ";
				where += "priceMin>=";
				where += q.getMinPrice();
			}
		}
		try
		{
			r = select(WINE_TABLE, "*", where);
			Vector<Long> wineIdVector = new Vector<Long>();
			while (r.next())
			{
				long wineId;
				wineId = new Long(r.getInt("wineId"));
				wineIdVector.add(wineId);
			}
			for (int i = 0; i < wineIdVector.size(); i++)
			{
				Wine temp = new Wine();
				temp = getWine(wineIdVector.get(i).longValue());
				ret.add(temp);
			}
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e.getCause());
		}
		return ret;
	}

	public static Vineyard addVineyard(Vineyard v) throws DAException
	{
		String columns, values;

		columns = "(`vineyardId`, `vineyardName`)";

		values = "('" + v.getId() + "',";
		values += "'" + escapeXml(v.getName()) + "')";

		try
		{
			insert(VINEYARDS_TABLE, columns, values);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Vineyard insertion", e);
		}

		v.setId(getLastInsertId());

		return v;
	}

	public static boolean deleteVineyard(long vineyardId)
	{
		int ret;
		try
		{
			ret = delete(VINEYARDS_TABLE, "vineyardId=" + vineyardId);
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
		}
		if (ret == 0)
			return false;
		return true;
	}

	public static void editVineyard(Vineyard vineyard) throws DAException
	{
		if (vineyard == null)
			throw new NullPointerException("Vineyard object null.");

		long vineyardId = vineyard.getId();
		if (vineyardId == 0)
			throw new DAException("Vineyard ID not set.");

		String sql = "";

		sql += "vineyardId=" + vineyardId;
		sql += ",vineyardName='" + escapeXml(vineyard.getName()) + "'";

		try
		{
			update(VINEYARDS_TABLE, sql, "vineyardId=" + vineyardId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Vineyard update.", e);
		}
	}

	public static Vineyard getVineyard(long vineyardId) throws DAException
	{
		ResultSet r;
		Vineyard vineyard = null;

		if (vineyardId == 0)
			throw new DAException("Vineyard ID not set.");

		try
		{
			r = select(VINEYARDS_TABLE, "*", "vineyardId=" + vineyardId);
			vineyard = createVineyard(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return vineyard;
	}

	private static Vineyard createVineyard(ResultSet r) throws SQLException
	{
		Vineyard vineyard;

		long vineyardId;
		String vineyardName;

		if (!r.next())
			return null;

		vineyardId = r.getLong("vineyardId");
		vineyardName = r.getString("vineyardName");

		vineyard = new Vineyard();
		vineyard.setId(vineyardId);
		vineyard.setName(vineyardName);

		return vineyard;
	}

	public static Varietal addVarietal(Varietal v) throws DAException
	{
		String columns, values;

		columns = "(`varietalId`, `varietalName`)";

		values = "('" + v.getId() + "',";
		values += "'" + escapeXml(v.getGrapeType()) + "')";

		try
		{
			insert(VARIETALS_TABLE, columns, values);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Varietal insertion", e);
		}

		v.setId(getLastInsertId());

		return v;
	}

	public static boolean deleteVarietal(long varietalId)
	{
		int ret;
		try
		{
			ret = delete(VARIETALS_TABLE, "varietalId=" + varietalId);
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
		}
		if (ret == 0)
			return false;
		return true;
	}

	public static void editVarietal(Varietal varietal) throws DAException
	{
		if (varietal == null)
			throw new NullPointerException("Varietal object null.");

		long varietalId = varietal.getId();
		if (varietalId == 0)
			throw new DAException("Varietal ID not set.");

		String sql = "";

		sql += "varietalId=" + varietalId;
		sql += ",varietalName='" + escapeXml(varietal.getGrapeType()) + "'";

		try
		{
			update(VARIETALS_TABLE, sql, "varietalId=" + varietalId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Varietal update.", e);
		}
	}

	public static Varietal getVarietal(long varietalId) throws DAException
	{
		ResultSet r;
		Varietal varietal = null;

		if (varietalId == 0)
			throw new DAException("Varietal ID not set.");

		try
		{
			r = select(VARIETALS_TABLE, "*", "varietalId=" + varietalId);
			varietal = createVarietal(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return varietal;
	}

	private static Varietal createVarietal(ResultSet r) throws SQLException
	{
		Varietal varietal;

		long varietalId;
		String grapeType;

		if (!r.next())
			return null;

		varietalId = r.getLong("varietalId");
		grapeType = r.getString("varietalName");

		varietal = new Varietal();
		varietal.setId(varietalId);
		varietal.setGrapeType(grapeType);

		return varietal;
	}

}
