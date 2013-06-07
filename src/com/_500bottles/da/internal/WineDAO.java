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

public class WineDAO extends DAO
{
	private final static String WINE_TABLE = Config
			.getProperty("wineTableName");
	private final static String VARIETALS_TABLE = Config
			.getProperty("varietalsTableName");
	private final static String VINEYARDS_TABLE = Config
			.getProperty("vineyardsTableName");
	private final static String WINETYPE_TABLE = Config
			.getProperty("wineTypeTableName");
	private static Vector<IdSortNode> IdSort = new Vector<IdSortNode>();

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
			Varietal varietal = new Varietal();
			Vineyard vineyard = new Vineyard();
			WineType wineType = new WineType();
			try
			{
				varietal = getVarietal(wine.getVarietal().getGrapeType());

				vineyard = getVineyard(wine.getVineyard().getName());

				wineType = getWineType(wine.getType().getWineType());
			} catch (DAException e)
			{
			}

			// If vineyard, varietal, winetype dont exist, then add a new one
			if (vineyard == null)
				addVineyard(wine.getVineyard());
			if (varietal == null)
				addVarietal(wine.getVarietal());
			if (wineType == null)
				addWineType(wine.getType());

			/*
			 * if (getVineyard(wine.getVineyard().getName()) == null)
			 * addVineyard(wine.getVineyard());
			 * 
			 * if (getVarietal(wine.getVarietal().getGrapeType()) == null)
			 * addVarietal(wine.getVarietal());
			 * 
			 * if (getWineType(wine.getType().getWineType()) == null)
			 * addWineType(wine.getType());
			 */
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

			if (getVineyard(wine.getVineyard().getName()) != null)
				addVineyard(wine.getVineyard());
			// System.out.println(wine.getVineyard().getName());
			// System.out.println(wine.getVarietal().getGrapeType());
			if (getVarietal(wine.getVarietal().getGrapeType()) != null)
				addVarietal(wine.getVarietal());
			if (getWineType(wine.getType().getWineType()) != null)
				addWineType(wine.getType());
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
			// System.out.println(wineId);
			r = select(WINE_TABLE, "*", "wineId=" + wineId);
			// System.out.println("after the select in getWine");

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

		long wineId, year, varId, vineId, winecomId, wineTypeId;
		double rating, priceMin, priceMax;
		float lon, lat;

		String name, description, appellationString, snoothId, imageUrl;
		GeoLocation geoLocation;
		Appellation appellation;

		Varietal varietal = new Varietal();
		Vineyard vineyard = new Vineyard();
		WineType type = new WineType();

		if (!r.next())
			return null;

		name = unescapeXml(r.getString("wineName"));
		wineId = r.getLong("wineId");
		// System.out.println("wineId: " + wineId);

		snoothId = unescapeXml(r.getString("snoothId"));
		description = unescapeXml(r.getString("description"));
		year = r.getLong("vintage");

		wineTypeId = r.getLong("wineTypeId");
		try
		{
			type = getWineTypeById(wineTypeId);

		} catch (DAException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * try { wineType = getWineTypeById(wineTypeId).getWineType(); } catch
		 * (DAException e) { throw new SQLException(e.getMessage()); } if
		 * (wineType != null) type.setWineType(wineType);
		 */

		appellationString = unescapeXml(r.getString("appellation"));
		appellation = new Appellation();
		appellation.setLocation(appellationString);

		lon = r.getFloat("longitude");
		lat = r.getFloat("latitude");

		geoLocation = new GeoLocation();
		geoLocation.setLat(lat);
		geoLocation.setLon(lon);

		varId = r.getLong("varietalId");

		try
		{
			varietal = getVarietalById(varId);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { grapeType = getVarietalById(varId).getGrapeType(); } catch
		 * (DAException e) { throw new SQLException(e.getMessage()); } if
		 * (grapeType != null) varietal.setGrapeType(grapeType);
		 */
		vineId = r.getLong("vineyardId");

		try
		{
			vineyard = getVineyardNameById(vineId);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { vineyardName = getVineyardNameById(vineId).getName(); } catch
		 * (DAException e) { throw new SQLException(e.getMessage()); } if
		 * (vineyardName != null) vineyard.setName(vineyardName);
		 */
		rating = r.getDouble("rating");
		priceMin = r.getDouble("priceMin");
		priceMax = r.getDouble("priceMax");

		winecomId = r.getLong("winecomId");

		description = unescapeXml(r.getString("description"));
		imageUrl = unescapeXml(r.getString("imageUrl"));
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
		wine.setImage(imageUrl);

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

	public static WineType getWineTypeById(long wineTypeId) throws DAException
	{
		ResultSet r;
		WineType wineType = null;

		if (wineTypeId == 0)
			throw new DAException("WineType ID not set.");

		try
		{
			r = select(WINETYPE_TABLE, "*", "wineTypeId='" + wineTypeId + "'");
			wineType = createWineType(r);
			// System.out.println("wine Type: " + wineType.getWineType());
			// System.out.println("Id: " + wineType.getWineTypeId());
			// Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return wineType;
	}

	public static Vineyard getVineyardNameById(long vineId) throws DAException
	{
		ResultSet r;
		Vineyard vineyard = null;

		if (vineId == 0)
			throw new DAException("Vineyard ID not set.");

		try
		{
			r = select(VINEYARDS_TABLE, "*", "vineyardId='" + vineId + "'");
			vineyard = createVineyard(r);
			// Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return vineyard;
	}

	private static Vector<Wine> getWinesFromQuerySearch(WineQuery q) // for wine
																		// search
			throws DAException
	{
		Vector<Wine> wines = new Vector<Wine>();
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
		for (int i = 0; i < fields.length; i++)
		{
			try
			{
				nameIds = getWineIdByWineName(fields[i], offset, size);
				varietalIds = getWineIdByVarietal(fields[i], offset, size);
				vineyardIds = getWineIdByVineyard(fields[i], offset, size);
				wineTypeIds = getWineIdByWineType(fields[i], offset, size);
				descriptionIds = getWineIdByDescription(fields[i], offset, size);
				vintageIds = getWineIdByVintage(fields[i], offset, size);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		}

		for (int i = 0; i < allWineIds.size(); i++)
		{
			// if (i < 10)
			// System.out.println("wineIds: " + allWineIds.get(i));
			place(allWineIds.get(i));
		}
		sort();
		Vector<Long> returnLong = new Vector<Long>();
		for (int i = 0; i < IdSort.size(); i++)
		{
			returnLong.add(IdSort.get(i).getValue());
			// System.out.println("marty's stuff: " + returnLong.get(i));
		}

		for (int i = 0; i < size; i++)
		{
			// System.out.println("wineIdVectorvalue: "
			// + wineIdVector.get(i).longValue());
			Wine temp = new Wine();

			temp = getWine(returnLong.get(i).longValue());
			// System.out.println("tempId: " + temp.getId());
			// System.out.println("next wineId is "
			// + wineIdVector.get(i).longValue());
			wines.add(temp);
			// System.out.println("after the add(temp)");

		}
		try
		{
			Database.disconnect();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wines;

	}

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
		// System.out.println("test=" + test);
		// System.out.println("IdSort.size()=" + IdSort.size());
		if (test == IdSort.size())
		{
			// System.out.println("yep got into here");
			IdSortNode node = new IdSortNode();
			node.setAmount(1);
			node.setValue(id);
			IdSort.add(node);
		}
	}

	public static Vector<Wine> getWinesFromQuery(WineQuery q) // for wine wizard
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

		if (q.getType().size() != WineQuery.DEFAULT_WINE_TYPE.size())
		{
			boolean exists = true;
			for (int i = 0; i < q.getType().size(); i++)
			{

				if (first)
				{
					where += "(wineTypeId='";
					where += getWineType(q.getType().get(i).getWineType())
							.getWineTypeId();
					where += "'";
					first = false;
					exists = false;

				} else if (exists)
				{
					where += " and ";
					where += "(wineTypeId='";
					where += getWineType(q.getType().get(i).getWineType())
							.getWineTypeId();
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "wineTypeId='";
					where += getWineType(q.getType().get(i).getWineType())
							.getWineTypeId();
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
					where += getVarietal(q.getVarietal().get(i).getGrapeType())
							.getId();
					first = false;
					exists = false;
				} else if (exists)
				{
					where += " and ";
					where += "(varietalId='";
					where += getVarietal(q.getVarietal().get(i).getGrapeType())
							.getId();
					where += "'";
					exists = false;
				} else
				{
					where += " or ";
					where += "(varietalId='";
					where += getVarietal(q.getVarietal().get(i).getGrapeType())
							.getId();
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
					where += getVineyard(q.getVineyard().get(i).getName())
							.getId();
					first = false;
					exists = false;
				} else if (exists)
				{
					where += " and ";
					where += "(vineyardId=";
					where += getVineyard(q.getVineyard().get(i).getName())
							.getId();
					exists = false;
				} else
				{
					// System.out.println("it should go in here");
					where += " or ";
					where += "vineyardId=";
					// System.out.println("ey: " +
					// q.getVineyard().get(i).getId());
					where += getVineyard(q.getVineyard().get(i).getName())
							.getId();
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

		if (q.getTextQuery() != WineQuery.DEFAULT_TEXT_QUERY)
		{
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
		// System.out.println("HAHAHEHAHAHEAHEHAEHAHEAHEASUPUSPUPSUPS");

		where += " LIMIT ";

		if (q.getOffset() != WineQuery.DEFAULT_OFFSET)
		{
			where += q.getOffset();
			where += ",";
		}

		where += q.getSize(); // 5 or greater.
		// System.out.println(where);
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

		if (!vineyardExists(v))
		{
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
		} else
		{
			Vineyard temp = getVineyard(v.getName());
			v.setId(temp.getId());
		}
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
		if (!varietalExists(v))
		{
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
		} else
		{
			Varietal temp = getVarietal(v.getGrapeType());
			v.setId(temp.getId());
		}
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

	private static boolean wineExists(Wine wine) throws DAException
	{
		boolean ret;
		if (getVarietal(wine.getVarietal().getGrapeType()) == null)
		{
			return false;
		} else if (getVineyard(wine.getVineyard().getName()) == null)
		{
			return false;
		} else if (getWineType(wine.getType().getWineType()) == null)
		{
			return false;
		}

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

	protected static boolean varietalExists(Varietal varietal)
			throws DAException
	{
		boolean ret;
		String grapeType = varietal.getGrapeType();
		if (grapeType == null)
		{
			return false;
		}

		ResultSet r;
		String where = "";
		// TODO: decide if equals or like
		where += "varietalName='" + escapeXml(grapeType) + "'";

		try
		{
			r = select(VARIETALS_TABLE, "*", where);

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

	protected static boolean vineyardExists(Vineyard vineyard)
			throws DAException
	{
		boolean ret;
		String vineyardName = vineyard.getName();
		if (vineyardName == null)
		{
			return false;
		}

		ResultSet r;
		String where = "";
		where += "vineyardName LIKE '" + escapeXml(vineyardName) + "'";

		try
		{
			r = select(VINEYARDS_TABLE, "*", where);

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

	protected static boolean wineTypeExists(WineType wineType)
			throws DAException
	{
		boolean ret;
		String type = wineType.getWineType();
		if (type == null)
		{
			return false;
		}

		ResultSet r;
		String where = "";
		where += "type LIKE '" + escapeXml(type) + "'";

		try
		{
			r = select(WINETYPE_TABLE, "*", where);

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

	public static Varietal getVarietal(String varietalName) throws DAException
	{
		ResultSet r;
		Varietal varietal = new Varietal();

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

	public static Varietal getVarietalById(long varId) throws DAException
	{
		ResultSet r;
		Varietal varietal = new Varietal();

		if (varId == 0)
			throw new DAException("Varietal ID not set.");

		try
		{
			r = select(VARIETALS_TABLE, "*", "varietalId='" + varId + "'");
			varietal = createVarietal(r);
			// Database.disconnect();
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

	public static WineType addWineType(WineType w) throws DAException
	{
		String columns, values;
		if (!wineTypeExists(w))
		{
			columns = "(`type`)";

			values = "('" + escapeXml(w.getWineType()) + "')";

			try
			{
				insert(WINETYPE_TABLE, columns, values);
				Database.disconnect();
			} catch (SQLException e)
			{
				throw new DAException("Failed WineType insertion", e);
			}

			w.setWineTypeId(getLastInsertId());
		} else
		{
			WineType temp = getWineType(w.getWineType());
			w.setWineTypeId(temp.getWineTypeId());
		}
		return w;
	}

	public static boolean deleteWineType(long wineTypeId)
	{
		int ret;
		try
		{
			ret = delete(WINETYPE_TABLE, "wineTypeId=" + wineTypeId);
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
		}
		if (ret == 0)
			return false;
		return true;
	}

	public static void editWineType(WineType wineType) throws DAException
	{
		if (wineType == null)
			throw new NullPointerException("WineType object null.");

		long wineTypeId = wineType.getWineTypeId();
		if (wineTypeId == 0)
			throw new DAException("WineType ID not set.");

		String sql = "";

		sql += "wineTypeId=" + wineTypeId;
		sql += ",type='" + escapeXml(wineType.getWineType()) + "'";

		try
		{
			update(WINETYPE_TABLE, sql, "wineTypeId=" + wineTypeId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed WineType update.", e);
		}
	}

	public static WineType getWineType(String type) throws DAException
	{
		ResultSet r;
		WineType wineType = new WineType();

		if (type == null)
			throw new DAException("WineType Name not set.");

		try
		{
			r = select(WINETYPE_TABLE, "*", "type='" + escapeXml(type) + "'");
			wineType = createWineType(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return wineType;
	}

	private static WineType createWineType(ResultSet r) throws SQLException
	{
		WineType wineType;

		long wineTypeId;
		String type;

		if (!r.next())
			return null;
		try
		{
			wineTypeId = r.getLong("wineTypeId");
			type = r.getString("type");
			// System.out.println("should be white " + type);
			// System.out.println(wineTypeId);
			wineType = new WineType();
			wineType.setWineTypeId(wineTypeId);
			wineType.setWineType(type);
			// System.out.println("right before the return");
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw e;
		}
		return wineType;
	}

	// Return vector of wineIds
	private static Vector<Long> getWineIdByVarietal(String s, int offset,
			int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		Vector<Long> varId = new Vector<Long>();
		ResultSet r;
		String where = "";
		where += "varietalName LIKE " + "'%" + escapeXml(s) + "%'";
		// Find matches to the passed in string
		try
		{
			r = select(VARIETALS_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.");
		}
		// Iterate through the results and add the varietal IDs to the varId
		// vector
		while (r.next())
		{
			varId.add((long) r.getInt("varietalId"));
		}
		// Get the wineIDs from the wine table which have the matching
		// varietalId and add to return vector of longs.
		for (int i = 0; i < varId.size(); i++)
		{
			where = "";
			where += "varietalId=" + varId.get(i);
			where += " LIMIT ";

			if (offset != WineQuery.DEFAULT_OFFSET)
			{
				where += offset;
				where += ",";
			}

			where += size;
			try
			{
				r = select(WINE_TABLE, "*", where);
			} catch (SQLException e)
			{
				throw new DAException("SQL select exception.");
			}
			while (r.next())
			{
				ret.add(r.getLong("wineId"));
			}
		}

		return ret;
	}

	private static Vector<Long> getWineIdByVineyard(String s, int offset,
			int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		Vector<Long> vineId = new Vector<Long>();
		ResultSet r;
		String where = "";
		where += "vineyardName LIKE " + "'%" + escapeXml(s) + "%'";

		// Find matches to the passed in string
		try
		{
			r = select(VINEYARDS_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.");
		}
		// Iterate through the results and add the varietal IDs to the varId
		// vector
		while (r.next())
		{
			vineId.add((long) r.getInt("vineyardId"));
		}
		// Get the wineIDs from the wine table which have the matching
		// varietalId and add to return vector of longs.
		for (int i = 0; i < vineId.size(); i++)
		{
			where = "";
			where += "vineyardId=" + vineId.get(i);
			where += " LIMIT ";
			if (offset != WineQuery.DEFAULT_OFFSET)
			{
				where += offset;
				where += ",";
			}

			where += size;
			try
			{
				r = select(WINE_TABLE, "*", where);
			} catch (SQLException e)
			{
				throw new DAException("SQL select exception.");
			}
			while (r.next())
			{
				ret.add(r.getLong("wineId"));
			}
		}
		return ret;
	}

	private static Vector<Long> getWineIdByWineType(String s, int offset,
			int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		Vector<Long> wineTypeId = new Vector<Long>();
		ResultSet r;
		String where = "";
		where += "type LIKE " + "'%" + escapeXml(s) + "%'";

		// Find matches to the passed in string
		try
		{
			r = select(WINETYPE_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.");
		}
		// Iterate through the results and add the varietal IDs to the varId
		// vector
		while (r.next())
		{
			wineTypeId.add((long) r.getInt("wineTypeId"));
		}
		// Get the wineIDs from the wine table which have the matching
		// varietalId and add to return vector of longs.
		for (int i = 0; i < wineTypeId.size(); i++)
		{
			where = "";
			where += "wineTypeId=" + wineTypeId.get(i);
			where += " LIMIT ";
			if (offset != WineQuery.DEFAULT_OFFSET)
			{
				where += offset;
				where += ",";
			}

			where += size;
			try
			{
				r = select(WINE_TABLE, "*", where);
			} catch (SQLException e)
			{
				throw new DAException("SQL select exception.");
			}
			while (r.next())
			{
				ret.add(r.getLong("wineId"));
			}
		}
		return ret;
	}

	private static Vector<Long> getWineIdByWineName(String s, int offset,
			int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		ResultSet r;
		String where = "";
		where += "wineName LIKE " + "'%" + escapeXml(s) + "%'";
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

	private static Vector<Long> getWineIdByDescription(String s, int offset,
			int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		ResultSet r;
		String where = "";
		where += "description LIKE " + "'%" + escapeXml(s) + "%'";
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
			// System.out.println("there is somethin");
			ret.add((long) r.getInt("wineId"));
		}
		/*
		 * for (int i = 0; i < ret.size(); i++) { System.out.println("ret= " +
		 * ret.get(i)); }
		 */
		return ret;
	}

	private static Vector<Long> getWineIdByVintage(String s, int offset,
			int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		int vintage = 0;
		ResultSet r;
		try
		{
			vintage = Integer.parseInt(s);
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
		// Iterate through the results and add the wine IDs to the return
		// vector
		while (r.next())
		{
			ret.add((long) r.getInt("wineId"));
		}
		return ret;
	}
}