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

		if (!wineExists(wine))
		{

			// Check if Vineyard/Varietal already exist. If not, add them to
			// their respective tables
			if (getVineyard(wine.getVineyard().getName()) == null)
				addVineyard(wine.getVineyard());
			if (getVarietal(wine.getVarietal().getGrapeType()) == null)
				addVarietal(wine.getVarietal());

			String columns, values;

			columns = "(`wineName`, `description`, `longitude`, `latitude`,";
			columns += "`wineType`, `vintage`,";
			columns += "`varietalId`,";
			columns += "`vineyardId`, `rating`, `snoothId` , `priceMin`, `priceMax`, `winecomId` )";

			// TODO: Comments!!!!
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
		} else
		{
			throw new DAException("Wine already exists.");
		}

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
		if (wineExists(wine))
		{
			long wineId = wine.getId();
			String sql = "";

			// sql += "entryID=" + entry.getEntryId();
			sql += "vineyardId=" + wine.getVineyard().getId();
			sql += ",varietalId=" + wine.getVarietal().getId();
			sql += ",appellation='"
					+ escapeXml(wine.getAppellation().getLocation()) + "'";
			sql += ",wineName='" + escapeXml(wine.getName()) + "'";
			sql += ",wineType='" + escapeXml(wine.getType().getWineType())
					+ "'";
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

			if (getVineyard(wine.getVineyard().getName()) != null)
				editVineyard(wine.getVineyard());
			if (getVarietal(wine.getVarietal().getGrapeType()) != null)
				editVarietal(wine.getVarietal());
		} else
		{
			throw new DAException("Wine does not exist in the database.");
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
		// System.out.println("getWine wineId: " + wineId);
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

	public static Wine getWineByWinecomId(int winecomId) throws DAException
	{
		ResultSet r;
		Wine wine;

		try
		{
			r = select(WINE_TABLE, "*", "winecomId='" + winecomId + "'");
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
		// System.out.println(" everything");

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
		// System.out.println("does it go right here");
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
	public static Vector<Wine> getWinesFromQuery(WineQuery q)
			throws DAException
	{
		Vector<Wine> ret = new Vector<Wine>();
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
			first = false;
		}
		if (q.getType().size() != WineQuery.DEFAULT_WINE_TYPE.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getType().size(); i++)
			{

				if (first)
				{
					where += "(wineType='";
					where += escapeXml(q.getType().get(i).getWineType());
					where += "'";
					first = false;
					exists = false;

				} else if (exists)
				{
					where += " and ";
					where += "(wineType='";
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
			where += ")";
		}
		if (q.getMinYear() != WineQuery.DEFAULT_MIN_YEAR
				&& q.getMaxYear() != WineQuery.DEFAULT_MAX_YEAR)
		{
			if (first)
			{
				where += "(vintage>=";
				where += q.getMinYear();
				where += " and vintage<=";
				where += q.getMaxYear();
				first = false;
			} else
			{
				where += " and ";
				where += "(vintage>=";
				where += q.getMinYear();
				where += " and ";
				where += "vintage<=";
				where += q.getMaxYear();
			}
			where += ")";

		} else if (q.getMinYear() != WineQuery.DEFAULT_MIN_YEAR)
		{
			if (first)
			{
				where += "(vintage<=";
				where += q.getMaxYear();
				first = false;
			} else
			{
				where += " and ";
				where += "vintage<=";
				where += q.getMaxYear();
			}
			where += ")";

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
			where += ")";
		}

		if (q.getAppellation().size() != WineQuery.DEFAULT_APPELLATION.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getAppellation().size(); i++)
			{
				if (first)
				{
					where += "(appellation='";
					where += escapeXml(q.getAppellation().get(i).getLocation());
					where += "'";
					first = false;
					exists = false;
				} else if (exists)
				{
					where += " and ";
					where += "(appellation='";
					where += escapeXml(q.getAppellation().get(i).getLocation());
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "(appellation='";
					where += escapeXml(q.getAppellation().get(i).getLocation());
					where += "'";
				}
			}
			where += ")";
		}

		if (q.getVarietal().size() != WineQuery.DEFAULT_VARIETAL.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getVarietal().size(); i++)
			{
				if (first)
				{
					where += "(varietalId=";
					where += q.getVarietal().get(i).getId();
					first = false;
					exists = false;
				} else if (exists)
				{
					where += " and ";
					where += "(varietalId='";
					where += q.getVarietal().get(i).getId();
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "(varietalId='";
					where += q.getVarietal().get(i).getId();
					where += "'";
				}
			}
			where += ")";
		}

		// System.out.println("qvineyardsize: " + q.getVineyard().size());
		if (q.getVineyard().size() != WineQuery.DEFAULT_VINEYARD.size())
		{
			// System.out.println("IT IS GOING INTO THE VINEYARD!!!!!");
			boolean exists = true;
			for (int i = 0; i < q.getVineyard().size(); i++)
			{
				// System.out.println(where);
				// System.out.println(q.getVineyard().size());
				// System.out.println(q.getVineyard().get(i).getId());
				if (first)
				{
					where += "(vineyardId=";
					where += q.getVineyard().get(i).getId();
					first = false;
					exists = false;
				} else if (exists)
				{
					where += " and ";
					where += "(vineyardId=";
					where += q.getVineyard().get(i).getId();
					exists = false;
				} else
				{
					// System.out.println("it should go in here");
					where += " or ";
					where += "vineyardId=";
					// System.out.println("ey: " +
					// q.getVineyard().get(i).getId());
					where += q.getVineyard().get(i).getId();
					// System.out.println(where);
				}
			}
			where += ")";
		}

		if (q.getMinRating() != WineQuery.DEFAULT_MIN_RATING
				&& q.getMaxRating() != WineQuery.DEFAULT_MAX_RATING)
		{
			if (first)
			{
				where += "(rating>=";
				where += q.getMinRating();
				where += " and rating<=";
				where += q.getMaxRating();
				first = false;
			} else
			{
				where += " and ";
				where += "(rating>=";
				where += q.getMinRating();
				where += " and ";
				where += "rating<=";
				where += q.getMaxRating();
			}
			where += ")";

		} else if (q.getMinRating() != WineQuery.DEFAULT_MIN_RATING)
		{
			if (first)
			{
				where += "(rating<=";
				where += q.getMaxRating();
				first = false;
			} else
			{
				where += " and ";
				where += "rating<=";
				where += q.getMaxRating();
			}
			where += ")";

		} else if (q.getMaxRating() != WineQuery.DEFAULT_MAX_RATING)
		{
			if (first)
			{
				where += "(rating>=";
				where += q.getMinRating();
			} else
			{
				where += " and ";
				where += "rating>=";
				where += q.getMinRating();
			}
			where += ")";
		}

		if (q.getMinPrice() != WineQuery.DEFAULT_MIN_PRICE
				&& q.getMaxPrice() != WineQuery.DEFAULT_MAX_PRICE)
		{
			if (first)
			{
				where += "(priceMin>=";
				where += q.getMinPrice();
				where += " and priceMax<=";
				where += q.getMaxPrice();
				first = false;
			} else
			{
				where += " and ";
				where += "(priceMin>=";
				where += q.getMinPrice();
				where += " and ";
				where += "priceMax<=";
				where += q.getMaxPrice();
			}
			where += ")";

		} else if (q.getMinPrice() != WineQuery.DEFAULT_MIN_PRICE)
		{
			if (first)
			{
				where += "(priceMax<=";
				where += q.getMaxPrice();
				first = false;
			} else
			{
				where += " and ";
				where += "priceMax<=";
				where += q.getMaxPrice();
			}
			where += ")";

		} else if (q.getMaxPrice() != WineQuery.DEFAULT_MAX_PRICE)
		{
			if (first)
			{
				where += "(priceMin>=";
				where += q.getMinPrice();
			} else
			{
				where += " and ";
				where += "priceMin>=";
				where += q.getMinPrice();
			}
			where += ")";
		}

		// System.out.println("HAHAHEHAHAHEAHEHAEHAHEAHEASUPUSPUPSUPS");
		try
		{
			r = select(WINE_TABLE, "*", where);
			Vector<Long> wineIdVector = new Vector<Long>();
			while (r.next())
			{
				long wineId;
				wineId = new Long(r.getInt("wineId"));

				wineIdVector.add(wineId);
				// System.out.println("wineId is " + wineId);
			}
			// System.out.println("wineIdVector size: " + wineIdVector.size());
			for (int i = 0; i < wineIdVector.size(); i++)
			{
				// System.out.println("wineIdVectorvalue: "
				// + wineIdVector.get(i).longValue());
				Wine temp = new Wine();

				temp = getWine(wineIdVector.get(i).longValue());
				// System.out.println("tempId: " + temp.getId());
				// System.out.println("next wineId is "
				// + wineIdVector.get(i).longValue());
				ret.add(temp);
				// System.out.println("after the add(temp)");

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

		columns = "(`vineyardName`)";

		values = "('" + escapeXml(v.getName()) + "')";

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

	// TODO: VINEYARD TAKES IN A STRING AS PARAMETER
	public static Vineyard getVineyard(String vineyardName) throws DAException
	{
		ResultSet r;
		Vineyard vineyard = null;

		if (vineyardName == null)
			throw new DAException("Vineyard Name not set.");

		try
		{
			r = select(VINEYARDS_TABLE, "*", "vineyardName='"
					+ escapeXml(vineyardName) + "'");
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

		columns = "(`varietalName`)";

		values = "('" + escapeXml(v.getGrapeType()) + "')";

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

	// TODO: WINE EXISTS: NAME, TYPE, VINTAGE, SNOOTH AND
	// SWINECOM
	private static boolean wineExists(Wine wine) throws DAException
	{
		if (getVarietal(wine.getVarietal().getGrapeType()) == null)
		{
			return false;
		} else if (getVineyard(wine.getVineyard().getName()) == null)
		{
			return false;
		}

		ResultSet r;
		String where = "";
		where += "wineName='" + escapeXml(wine.getName()) + "'";
		where += " and wineType='" + escapeXml(wine.getType().getWineType())
				+ "'";
		where += " and vintage=" + wine.getYear();
		where += " and ( ";
		where += "snoothId='" + escapeXml(wine.getSnoothId()) + "'";
		where += " or winecomId=" + wine.getWinecomId();
		where += ")";

		try
		{
			r = select(WINE_TABLE, "*", where);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage());
		}
		try
		{
			if (!r.next())
				return false;
			else
				return true;
		} catch (SQLException e)
		{
			// TODO: Change exception message
			throw new DAException(e.getMessage());
		}

	}

	// TODO: CHANGE THE PARAMETERS TO BE STRINGS
	public static Varietal getVarietal(String varietalName) throws DAException
	{
		ResultSet r;
		Varietal varietal = null;

		if (varietalName == null)
			throw new DAException("Varietal Name not set.");

		try
		{
			r = select(VARIETALS_TABLE, "*", "varietalName='"
					+ escapeXml(varietalName) + "'");
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
