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
import com._500bottles.object.wine.IdSortNode;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

/**
 * Coordinates all Wine related database access for wine adding, deleting, and
 * getting in the database. Also implements search function for wines.
 */
public class WineDAO extends DAO
{
	protected final static String WINE_TABLE = Config
			.getProperty("wineTableName");
	private static Vector<IdSortNode> IdSort = new Vector<IdSortNode>();

	/**
	 * Adds the wine to the wine table
	 * 
	 * @param wine
	 *            The wine to be added
	 * @return the wine that was added
	 * @throws DAException
	 */
	public static Wine addWine(Wine wine) throws DAException
	{
		// Avoid duplicates
		if (!wineExists(wine))
		{

			// Check if Vineyard/Varietal already exist. If not, add them to
			// their respective tables
			Varietal varietal = new Varietal();
			Vineyard vineyard = new Vineyard();
			WineType wineType = new WineType();
			try
			{
				varietal = VarietalDAO.getVarietal(wine.getVarietal()
						.getGrapeType());

				vineyard = VineyardDAO
						.getVineyard(wine.getVineyard().getName());

				wineType = WineTypeDAO
						.getWineType(wine.getType().getWineType());
			} catch (DAException e)
			{
			}

			// If vineyard, varietal, winetype dont exist, then add a new one
			if (vineyard == null)
				VineyardDAO.addVineyard(wine.getVineyard());
			if (varietal == null)
				VarietalDAO.addVarietal(wine.getVarietal());
			if (wineType == null)
				WineTypeDAO.addWineType(wine.getType());

			String columns, values;

			columns = "(`wineName`, `description`, `longitude`, `latitude`,";
			columns += "`wineTypeId`, `vintage`,";
			columns += "`varietalId`,";
			columns += "`vineyardId`, `rating`, `snoothId` , `priceMin`, `priceMax`, `winecomId`,";
			columns += "`imageUrl` )";

			values = "('" + escapeXml(wine.getName()) + "',";
			values += "'" + escapeXml(wine.getDescription()) + "',";
			values += "'" + wine.getGeoLocation().getLon() + "',";
			values += "'" + wine.getGeoLocation().getLat() + "',";
			// Get type...
			if (wineType == null)
				values += "'" + wine.getType().getWineTypeId() + "',";
			else
			{
				values += "'" + wineType.getWineTypeId() + "',";
				// System.out.println(wineType.getWineTypeId());
			}
			// Get year...
			values += "'" + wine.getYear() + "',";
			// Get varietal
			if (varietal == null)
				values += "'" + wine.getVarietal().getId() + "',";
			else
				values += "'" + varietal.getId() + "',";
			// Get vineyard
			if (vineyard == null)
				values += "'" + wine.getVineyard().getId() + "',";
			else
				values += "'" + vineyard.getId() + "',";
			// Get rating...
			values += "'" + wine.getRating() + "',";
			values += "'" + wine.getSnoothId() + "',";

			values += "'" + wine.getPriceMin() + "',";
			values += "'" + wine.getPriceMax() + "',";

			values += "'" + wine.getWinecomId() + "',";
			values += "'" + wine.getImage() + "')";

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

	/**
	 * Deletes the specified wine
	 * 
	 * @param wine
	 *            The wine to be deleted
	 * @return true if the wine is deleted, false otherwise
	 */
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

	/**
	 * Edits the wine
	 * 
	 * @param wine
	 *            The wine to be edited
	 * @throws DAException
	 */
	public static void editWine(Wine wine) throws DAException
	{

		long wineId = wine.getId();
		String sql = "";

		sql += "vineyardId=" + wine.getVineyard().getId();
		sql += ",varietalId=" + wine.getVarietal().getId();
		sql += ",appellation='"
				+ escapeXml(wine.getAppellation().getLocation()) + "'";
		sql += ",wineName='" + escapeXml(wine.getName()) + "'";
		sql += ",wineTypeId=" + wine.getType().getWineTypeId();
		sql += ",vintage=" + wine.getYear();
		sql += ",description='" + escapeXml(wine.getDescription()) + "'";
		sql += ",priceMin=" + wine.getPriceMin();
		sql += ",priceMax=" + wine.getPriceMin();
		sql += ",rating=" + wine.getRating();
		sql += ",longitude=" + wine.getGeoLocation().getLon();
		sql += ",latitude=" + wine.getGeoLocation().getLat();
		sql += ",winecomId=" + wine.getWinecomId();
		sql += ",snoothId=' " + escapeXml(wine.getSnoothId()) + "'";
		sql += ",imageUrl='" + escapeXml(wine.getImage()) + "'";

		try
		{
			update(WINE_TABLE, sql, "wineId=" + wineId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Wine update", e);
		}

		if (VineyardDAO.getVineyard(wine.getVineyard().getName()) != null)
			VineyardDAO.addVineyard(wine.getVineyard());
		// System.out.println(wine.getVineyard().getName());
		// System.out.println(wine.getVarietal().getGrapeType());
		if (VarietalDAO.getVarietal(wine.getVarietal().getGrapeType()) != null)
			VarietalDAO.addVarietal(wine.getVarietal());
		if (WineTypeDAO.getWineType(wine.getType().getWineType()) != null)
			WineTypeDAO.addWineType(wine.getType());

	}

	/**
	 * Gets the wine that matches the wine object
	 * 
	 * @param wine
	 *            The wine object to get from the database
	 * @return Wine that is constructed from the database information
	 * @throws DAException
	 */
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

	/**
	 * Gets the wine of the specified ID
	 * 
	 * @param wineId
	 *            The ID of the wine to get
	 * @return Wine object of the ID
	 * @throws DAException
	 */
	public static Wine getWine(long wineId) throws DAException
	{
		// System.out.println("getWine wineId: " + wineId);
		ResultSet r;
		Wine wine;

		try
		{
			r = select(WINE_TABLE, "*", "wineId=" + wineId);

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e.getCause());
		}
		try
		{
			wine = createWine(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Wine creation error.");
		}
		return wine;
	}

	/**
	 * Gets wines from a WineQuery object
	 * 
	 * @param q
	 *            The wine query object to extract the search info from
	 * @return Vector of Wine objects that match the search
	 * @throws DAException
	 */
	public static Vector<Wine> getWinesFromQuery(WineQuery q) // for wine wizard
			throws DAException
	{
		Vector<Wine> ret = new Vector<Wine>();
		ResultSet r;
		boolean first = true; // Check if first of the query object to construct
								// SQL query
		String where = "";

		if (q == null)
			throw new NullPointerException("Null Wine Query");
		// Check if searching for wine name
		if (q.getNameContains() != WineQuery.DEFAULT_NAME_CONTAINS)
		{
			where += "wineName";
			where += " LIKE ";
			where += "'%" + escapeXml(q.getNameContains()) + "%'";
			first = false;
		}

		// Check if searching for specific description
		if (q.getDescriptionContains() != WineQuery.DEFAULT_DESC_CONTAINS)
		{
			if (first)
			{
				where += "description";
				where += " LIKE ";
				where += "'%" + escapeXml(q.getDescriptionContains()) + "%'";
				first = false;
			} else
			{
				where += "and ";
				where += "description";
				where += " LIKE ";
				where += "'%" + escapeXml(q.getDescriptionContains()) + "%'";
				first = false;
			}
		}

		// Check if looking by winetype
		if (q.getType().size() != WineQuery.DEFAULT_WINE_TYPE.size())
		{
			// exists boolean to check for multiple types, needed for
			// constructing SQL query
			boolean exists = true;
			for (int i = 0; i < q.getType().size(); i++)
			{

				if (first)
				{
					where += "(wineTypeId='";

					where += WineTypeDAO.getWineType(
							q.getType().get(i).getWineType()).getWineTypeId();
					where += "'";
					first = false;
					exists = false;

				} else if (exists)
				{
					where += " and ";
					where += "(wineTypeId='";
					where += WineTypeDAO.getWineType(
							q.getType().get(i).getWineType()).getWineTypeId();
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "wineTypeId='";
					where += WineTypeDAO.getWineType(
							q.getType().get(i).getWineType()).getWineTypeId();
					where += "'";
				}
			}
			where += ")";
		}

		// Check if searching by vintage
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

		// Check if appellation set in wine query object
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

		// Check if searching by varietal
		if (q.getVarietal().size() != WineQuery.DEFAULT_VARIETAL.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getVarietal().size(); i++)
			{
				if (first)
				{
					where += "(varietalId=";
					where += VarietalDAO.getVarietal(
							q.getVarietal().get(i).getGrapeType()).getId();
					first = false;
					exists = false;
				} else if (exists)
				{
					where += " and ";
					where += "(varietalId='";
					where += VarietalDAO.getVarietal(
							q.getVarietal().get(i).getGrapeType()).getId();
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "(varietalId='";
					where += VarietalDAO.getVarietal(
							q.getVarietal().get(i).getGrapeType()).getId();
					where += "'";
				}
			}
			where += ")";
		}

		// Check if searching by vineyard
		if (q.getVineyard().size() != WineQuery.DEFAULT_VINEYARD.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getVineyard().size(); i++)
			{
				if (first)
				{
					where += "(vineyardId=";
					where += VineyardDAO.getVineyard(
							q.getVineyard().get(i).getName()).getId();
					first = false;
					exists = false;
				} else if (exists)
				{
					where += " and ";
					where += "(vineyardId=";
					where += VineyardDAO.getVineyard(
							q.getVineyard().get(i).getName()).getId();
					exists = false;
				} else
				{
					// System.out.println("it should go in here");
					where += " or ";
					where += "vineyardId=";
					// System.out.println("ey: " +
					// q.getVineyard().get(i).getId());
					where += VineyardDAO.getVineyard(
							q.getVineyard().get(i).getName()).getId();
					// System.out.println(where);
				}
			}
			where += ")";
		}

		// Check if ratings are set in wine query object
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

		// Check if price is being searched for
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

		// Check if searching by text query
		if (q.getTextQuery() != WineQuery.DEFAULT_TEXT_QUERY)
		{
			// If this is first, this means that the text query is only part
			// being searched for, so call the helper
			if (first)
			{
				return getWinesFromQuerySearch(q);
			} else
			{
				where += "and ";
				where += "description";
				where += " LIKE ";
				where += "'%" + escapeXml(q.getTextQuery()) + "%'";
				where += " or ";
				where += "wineName ";
				where += " LIKE ";
				where += "'%" + escapeXml(q.getTextQuery()) + "%'";
				first = false;
			}
		}
		// Set offset and size
		where += " LIMIT ";

		if (q.getOffset() != WineQuery.DEFAULT_OFFSET)
		{
			where += q.getOffset();
			where += ",";
		}

		where += q.getSize();
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

	/**
	 * Gets wine object of specified snooth ID
	 * 
	 * @param snoothId
	 *            The snooth ID of the wine
	 * @return Wine object that contains specified snoothID
	 * @throws DAException
	 */
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

	/**
	 * Gets Wine object by Wine.com ID
	 * 
	 * @param winecomId
	 *            Wine.com ID of the wine
	 * @return Wine object of specified Wine.com ID
	 * @throws DAException
	 */
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

	/**
	 * Helper method to check if a wine exists in the database already
	 * 
	 * @param wine
	 *            Wine to check if exists in the database
	 * @return true if wine does exist, false otherwise
	 * @throws DAException
	 */
	protected static boolean wineExists(Wine wine) throws DAException
	{
		boolean ret;
		if (VarietalDAO.getVarietal(wine.getVarietal().getGrapeType()) == null)
		{
			return false;
		} else if (VineyardDAO.getVineyard(wine.getVineyard().getName()) == null)
		{
			return false;
		} else if (WineTypeDAO.getWineType(wine.getType().getWineType()) == null)
		{
			return false;
		}

		// Check these paramters to see if the wine exists
		ResultSet r;
		String where = "";
		where += "wineName='" + escapeXml(wine.getName()) + "'";
		where += " and vintage=" + wine.getYear();
		where += " and ( ";
		where += "snoothId='" + escapeXml(wine.getSnoothId()) + "'";
		where += " or winecomId=" + wine.getWinecomId();
		where += ")";

		try
		{
			r = select(WINE_TABLE, "*", where);

			if (!r.next())
				ret = false;
			else
				ret = true;

			Database.disconnect();
			return ret;
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage());
		}

	}

	/**
	 * Helper method to get all wineIds of a given query search
	 * 
	 * @param q
	 *            WineQuery object which contains the search text
	 * @return Vector of wine IDs for specified query
	 * @throws DAException
	 */
	protected static Vector<Long> getWineIdsFromQuerySearch(WineQuery q) // for
																			// wine
																			// search
			throws DAException
	{
		// Vector<Wine> wines = new Vector<Wine>();
		int size = q.getSize();
		int offset = q.getOffset();
		String query = q.getTextQuery();
		String delims = "\\s+"; // any spaces, plus any spaces right after the
								// spaces
		String[] fields = query.split(delims);

		Vector<Long> allWineIds = new Vector<Long>();

		Vector<Long> nameIds = new Vector<Long>();
		Vector<Long> varietalIds = new Vector<Long>();
		Vector<Long> vineyardIds = new Vector<Long>();
		Vector<Long> wineTypeIds = new Vector<Long>();
		Vector<Long> descriptionIds = new Vector<Long>();
		Vector<Long> vintageIds = new Vector<Long>();

		// Get multiple vectors of wineIDs
		try
		{
			nameIds = getWineIdByWineName(fields, offset, size * size);
			varietalIds = VarietalDAO.getWineIdByVarietal(fields, offset, size
					* size);
			vineyardIds = VineyardDAO.getWineIdByVineyard(fields, offset, size
					* size);
			wineTypeIds = WineTypeDAO.getWineIdByWineType(fields, offset, size
					* size);
			descriptionIds = getWineIdByDescription(fields, offset, size * size);
			vintageIds = getWineIdByVintage(fields, offset, size * size);
		} catch (SQLException e)
		{
			throw new DAException("GetWineIdBy... exception");
		}
		// Add all found IDs to one vector
		for (int j = 0; j < nameIds.size(); j++)
		{
			allWineIds.add(nameIds.get(j));
		}

		for (int j = 0; j < varietalIds.size(); j++)
		{
			allWineIds.add(varietalIds.get(j));
		}
		for (int j = 0; j < vineyardIds.size(); j++)
		{
			allWineIds.add(vineyardIds.get(j));
		}
		for (int j = 0; j < wineTypeIds.size(); j++)
		{
			allWineIds.add(wineTypeIds.get(j));
		}
		for (int j = 0; j < descriptionIds.size(); j++)
		{
			allWineIds.add(descriptionIds.get(j));
		}
		for (int j = 0; j < vintageIds.size(); j++)
		{
			allWineIds.add(vintageIds.get(j));
		}

		// Sort vector
		for (int i = 0; i < allWineIds.size(); i++)
		{
			place(allWineIds.get(i));
		}
		sort();
		Vector<Long> returnLong = new Vector<Long>();
		for (int i = 0; i < IdSort.size(); i++)
		{
			returnLong.add(IdSort.get(i).getValue());
		}
		return returnLong;
	}

	/**
	 * Adds IDs to the list to sort
	 * 
	 * @param id
	 *            ID to add to sorting list
	 */
	private static void place(long id)
	{
		int test = 0;
		for (int i = 0; i < IdSort.size(); i++)
		{
			if (IdSort.get(i).getValue() == id)
			{
				IdSort.get(i).setAmount((IdSort.get(i).getAmount() + 1));
			} else
			{
				test++;
			}
		}

		if (test == IdSort.size())
		{
			IdSortNode node = new IdSortNode();
			node.setAmount(1);
			node.setValue(id);
			IdSort.add(node);
		}
	}

	/**
	 * Sorts the ID vectors
	 */
	private static void sort()
	{
		for (int i = 0; i < IdSort.size(); i++)
		{
			for (int j = IdSort.size() - 1; j > i; j--)
			{
				if (IdSort.get(i).getAmount() < IdSort.get(j).getAmount())
				{
					IdSortNode temp = IdSort.get(i);
					IdSort.set(i, IdSort.get(j));
					IdSort.set(j, temp);
				}
			}
		}
	}

	/**
	 * Creates wine from a given ResultSet
	 * 
	 * @param r
	 *            ResultSet to create Wine object from
	 * @return Wine object that was created from the ResultSet
	 * @throws SQLException
	 * @throws DAException
	 */
	private static Wine createWine(ResultSet r) throws SQLException,
			DAException
	{
		Wine wine;

		long wineId, year, varId, vineId, winecomId, wineTypeId;
		double rating, priceMin, priceMax;
		float lon, lat;

		String name, description, appellationString, snoothId, imageUrl;
		GeoLocation geoLocation;
		Appellation appellation;

		Varietal varietal = new Varietal();
		Vineyard vineyard = new Vineyard();
		WineType type = new WineType();

		// Move ResultSet over
		if (!r.next())
			return null;

		name = unescapeXml(r.getString("wineName"));
		wineId = r.getLong("wineId");
		// System.out.println("wineId: " + wineId);

		snoothId = unescapeXml(r.getString("snoothId"));
		description = unescapeXml(r.getString("description"));
		year = r.getLong("vintage");

		wineTypeId = r.getLong("wineTypeId");
		type = WineTypeDAO.getWineTypeById(wineTypeId);

		appellationString = unescapeXml(r.getString("appellation"));
		appellation = new Appellation();
		appellation.setLocation(appellationString);

		lon = r.getFloat("longitude");
		lat = r.getFloat("latitude");

		geoLocation = new GeoLocation();
		geoLocation.setLat(lat);
		geoLocation.setLon(lon);

		varId = r.getLong("varietalId");

		varietal = VarietalDAO.getVarietalById(varId);

		vineId = r.getLong("vineyardId");

		vineyard = VineyardDAO.getVineyardById(vineId);

		rating = r.getDouble("rating");
		priceMin = r.getDouble("priceMin");
		priceMax = r.getDouble("priceMax");

		winecomId = r.getLong("winecomId");

		description = unescapeXml(r.getString("description"));
		imageUrl = unescapeXml(r.getString("imageUrl"));

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
		wine.setImage(imageUrl);

		return wine;
	}

	/**
	 * Gets wine IDs for the given wine name
	 * 
	 * @param fields
	 *            String array of the text query to search for
	 * @param offset
	 *            Offset to look for in the database
	 * @param size
	 *            Number of results to get
	 * @return Vector of wine IDs matching the wine name
	 * @throws DAException
	 * @throws SQLException
	 */
	private static Vector<Long> getWineIdByWineName(String[] fields,
			int offset, int size) throws DAException, SQLException
	{

		Vector<Long> ret = new Vector<Long>();
		ResultSet r;
		String where = "";
		boolean first = true;

		for (int i = 0; i < fields.length; i++)
		{
			if (first)
			{
				where += "wineName";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			} else
			{
				where += " and ";
				where += "wineName";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			}
		}

		where += " LIMIT ";
		if (offset != WineQuery.DEFAULT_OFFSET)
		{
			where += offset;
			where += ",";
		}

		where += size;
		// Find matches to the passed in string
		try
		{
			r = select(WINE_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.");
		}
		// Iterate through the results and add the wine IDs to the return
		// vector
		while (r.next())
		{
			ret.add((long) r.getInt("wineId"));
		}
		return ret;
	}

	/**
	 * Gets wine IDs for the given Description
	 * 
	 * @param fields
	 *            String array of the text query to search for
	 * @param offset
	 *            Offset to look for in the database
	 * @param size
	 *            Number of results to get
	 * @return Vector of wine IDs that match the description from the query
	 * @throws DAException
	 * @throws SQLException
	 */
	private static Vector<Long> getWineIdByDescription(String[] fields,
			int offset, int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		ResultSet r;
		String where = "";

		boolean first = true;

		for (int i = 0; i < fields.length; i++)
		{
			if (first)
			{
				where += "description";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			} else
			{
				where += " and ";
				where += "description";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			}
		}

		where += " LIMIT ";
		if (offset != WineQuery.DEFAULT_OFFSET)
		{
			where += offset;
			where += ",";
		}

		where += size;
		// Find matches to the passed in string
		try
		{
			r = select(WINE_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.");
		}
		// Iterate through the results and add the wine IDs to the return
		// vector
		while (r.next())
		{
			ret.add((long) r.getInt("wineId"));
		}
		return ret;
	}

	/**
	 * Gets wine IDs for the given vintage
	 * 
	 * @param fields
	 *            String array of the text query to search for
	 * @param offset
	 *            Offset to look for in the database
	 * @param size
	 *            Number of results to get
	 * @return Vector of wine IDs that match the vintage
	 * @throws DAException
	 * @throws SQLException
	 */
	private static Vector<Long> getWineIdByVintage(String[] fields, int offset,
			int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		int vintage = 0;
		ResultSet r = null;

		for (int i = 0; i < fields.length; i++)
		{

			try
			{
				vintage = Integer.parseInt(fields[i]);
			} catch (NumberFormatException e)
			{
			}
			String where = "";
			if (vintage != 0)
			{
				where += "vintage=" + vintage;
				where += " LIMIT ";
				if (offset != WineQuery.DEFAULT_OFFSET)
				{
					where += offset;
					where += ",";
				}

				where += size;
			} else
				return ret;
			// Find matches to the passed in string
			try
			{
				r = select(WINE_TABLE, "*", where);
			} catch (SQLException e)
			{
				throw new DAException("SQL select exception.");
			}

		}

		// Iterate through the results and add the wine IDs to the return
		// vector

		if (r == null)
		{
			return ret;
		}
		while (r.next())
		{
			ret.add((long) r.getInt("wineId"));
		}
		return ret;
	}

	/**
	 * Gets the wines matching the WineQuery object
	 * 
	 * @param q
	 *            WineQuery object to search for
	 * @return Vector of Wine objects matching the search
	 * @throws DAException
	 */
	private static Vector<Wine> getWinesFromQuerySearch(WineQuery q)
			throws DAException
	{
		Vector<Long> returnLong = getWineIdsFromQuerySearch(q);
		Vector<Wine> wines = new Vector<Wine>();
		int size = q.getSize();
		if (returnLong.size() >= size)
		{
			// Create wine objects and add them to vector of wines
			for (int i = 0; i < size; i++)
			{

				Wine temp = new Wine();
				temp = getWine(returnLong.get(i).longValue());
				wines.add(temp);

			}
		} else
		{
			for (int i = 0; i < returnLong.size(); i++)
			{

				Wine temp = new Wine();
				temp = getWine(returnLong.get(i).longValue());

				wines.add(temp);
			}
		}
		IdSort.clear();
		try
		{
			Database.disconnect();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return wines;

	}
}
