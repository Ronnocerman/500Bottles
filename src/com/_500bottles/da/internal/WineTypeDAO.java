package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

/**
 * Coordinates all wine type related database access for wine type adding,
 * deleting, and getting in the database.
 */
public class WineTypeDAO extends DAO
{

	private final static String WINETYPE_TABLE = Config
			.getProperty("wineTypeTableName");

	/**
	 * Adds a wine type. Returns an wine type object with the new unique Id.
	 * Throws a DAException if the SQL insertion fails.
	 * 
	 * @param w
	 *            The wine type object to add.
	 * @return WineType object with the wine type Id.
	 * @throws DAException
	 */
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

	/**
	 * Deletes a wine type. The wine type id must be set for a deletion to
	 * succeed.
	 * 
	 * @param wineTypeId
	 *            The wine type to delete.
	 */
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

	/**
	 * Gets and returns an wine type object from the database. Throws
	 * DAException if an SQL error occurs or if the wine type Id was not set.
	 * 
	 * @param wineTypeId
	 *            The Id of the wine type to get.
	 * @return The wine type object.
	 * @throws DAException
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
			// Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return wineType;
	}

	/**
	 * Gets and returns an wine type object from the database. Throws
	 * DAException if an SQL error occurs or if the wine type name was not set.
	 * 
	 * @param type
	 *            The name of the wine type to get.
	 * @return The wine type object.
	 * @throws DAException
	 */
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

	/**
	 * Searches and return a list of wines in the database. Throws DAException
	 * if an SQL error occurs.
	 * 
	 * @param s
	 *            The name of the wine type.
	 * @param offset
	 *            The offset.
	 * @param size
	 *            The number of wines to get.
	 * @return Vector of wineIds.
	 * @throws DAException
	 */
	protected static Vector<Long> getWineIdByWineType(String[] fields,
			int offset, int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		Vector<Long> wineTypeId = new Vector<Long>();
		ResultSet r;
		String where = "";
		boolean first = true;

		for (int i = 0; i < fields.length; i++)
		{
			if (first)
			{
				where += "type";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			} else
			{
				where += " and ";
				where += "type";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			}
		}

		// Find matches to the passed in string
		try
		{
			r = select(WINETYPE_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.");
		}

		// Iterate through the results and add the wineType IDs to the vector
		while (r.next())
		{
			wineTypeId.add((long) r.getInt("wineTypeId"));
		}
		// Get the wineIDs from the wine table which have the matching
		// wine type Id and add to return vector of longs.
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
	 * Determines if an wine type object exist in the database. Throws
	 * DAException if an SQL error occurs.
	 * 
	 * @param wineType
	 *            The wine type object to check.
	 * @return True if wine type exist and false otherwise.
	 * @throws DAException
	 */
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

	/**
	 * Creates an wine type object from the database.
	 * 
	 * @param r
	 *            The wine type to create.
	 * @return The wine type object.
	 */
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

			wineType = new WineType();
			wineType.setWineTypeId(wineTypeId);
			wineType.setWineType(type);
		} catch (SQLException e)
		{
			throw e;
		}
		return wineType;
	}
}