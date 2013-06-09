package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.WineQuery;

/**
 * Coordinates all vineyard related database access for vineyard adding,
 * deleting, and getting in the database.
 */
public class VineyardDAO extends DAO
{

	private final static String VINEYARDS_TABLE = Config
			.getProperty("vineyardsTableName");

	/**
	 * Adds a vineyard. Returns an vineyard object with the new unique Id.
	 * Throws a DAException if the SQL insertion fails.
	 * 
	 * @param v
	 *            The vineyard object to add.
	 * @return Vineyard object with the vineyard Id.
	 * @throws DAException
	 */
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

	/**
	 * Deletes a vineyard. The vineyard's id must be set for a deletion to
	 * succeed.
	 * 
	 * @param vineyardId
	 *            The vineyard to delete.
	 */
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

	/**
	 * Gets and returns an vineyard object from the database. Throws DAException
	 * if an SQL error occurs or if the vineyard name was not set.
	 * 
	 * @param vineyardName
	 *            The name of the vineyard to get.
	 * @return The vineyard object.
	 * @throws DAException
	 */
	public static Vineyard getVineyard(String vineyardName) throws DAException
	{
		ResultSet r;
		Vineyard vineyard = null;

		if (vineyardName == null)
			throw new DAException("Vineyard Name not set.");

		try
		{
			r = select(VINEYARDS_TABLE, "*", "vineyardName LIKE '"
					+ escapeXml(vineyardName) + "'");
			vineyard = createVineyard(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return vineyard;
	}

	/**
	 * Gets and returns an vineyard object from the database. Throws DAException
	 * if an SQL error occurs or if the vineyard Id was not set.
	 * 
	 * @param vineyardId
	 *            The Id of the vineyard to get.
	 * @return The vineyard object.
	 * @throws DAException
	 */
	public static Vineyard getVineyardById(long vineId) throws DAException
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

	/**
	 * Determines if an vineyard object exist in the database. Throws
	 * DAException if an SQL error occurs.
	 * 
	 * @param vineyard
	 *            The vineyard object to check.
	 * @return True if vineyard exist and false otherwise.
	 * @throws DAException
	 */
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

	/**
	 * Searches and return a list of wines in the database. Throws DAException
	 * if an SQL error occurs.
	 * 
	 * @param s
	 *            The name of the vineyard.
	 * @param offset
	 *            The offset.
	 * @param size
	 *            The number of wines to get.
	 * @return Vector of wineIds.
	 * @throws DAException
	 */
	protected static Vector<Long> getWineIdByVineyard(String[] fields,
			int offset, int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		Vector<Long> vineId = new Vector<Long>();
		ResultSet r;
		String where = "";
		boolean first = true;

		for (int i = 0; i < fields.length; i++)
		{
			if (first)
			{
				where += "vineyardName";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			} else
			{
				where += " and ";
				where += "vineyardName";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			}
		}

		// Find matches to the passed in string
		try
		{
			r = select(VINEYARDS_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.");
		}

		// Iterate through the results and add the varietal IDs to the vector
		while (r.next())
		{
			vineId.add((long) r.getInt("vineyardId"));
		}
		// Get the wineIDs from the wine table which have the matching
		// vineyardId and add to return vector of longs.
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
				r = select(WineDAO.WINE_TABLE, "*", where);
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

	/**
	 * Creates an vineyard object from the database.
	 * 
	 * @param r
	 *            The vineyard to create.
	 * @return The vineyard object.
	 */
	private static Vineyard createVineyard(ResultSet r) throws SQLException
	{
		Vineyard vineyard;

		long vineyardId;
		String vineyardName;

		if (!r.next())
			return null;

		vineyardId = r.getLong("vineyardId");
		vineyardName = unescapeXml(r.getString("vineyardName"));

		vineyard = new Vineyard();
		vineyard.setId(vineyardId);
		vineyard.setName(vineyardName);

		return vineyard;
	}

}
