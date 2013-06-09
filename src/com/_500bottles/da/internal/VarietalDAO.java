package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.WineQuery;

/**
 * Coordinates all varietal related database access for varietal adding,
 * deleting, and getting in the database.
 */
public class VarietalDAO extends DAO
{
	private final static String VARIETALS_TABLE = Config
			.getProperty("varietalsTableName");

	/**
	 * Adds a varietal. Returns an varietal object with the new unique Id.
	 * Throws a DAException if the SQL insertion fails.
	 * 
	 * @param v
	 *            The varietal object to add.
	 * @return Varietal object with the varietal Id.
	 * @throws DAException
	 */
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

	/**
	 * Deletes a varietal. The varietal's id must be set for a deletion to
	 * succeed.
	 * 
	 * @param varietalId
	 *            The varietal to delete.
	 */
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

	/**
	 * Gets and returns an varietal object from the database. Throws DAException
	 * if an SQL error occurs or if the varietal name was not set.
	 * 
	 * @param varietalName
	 *            The name of the varietal to get.
	 * @return The varietal object.
	 * @throws DAException
	 */
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

	/**
	 * Gets and returns an varietal object from the database. Throws DAException
	 * if an SQL error occurs or if the varietal Id was not set.
	 * 
	 * @param varietalName
	 *            The name of the varietal to get.
	 * @return The varietal object.
	 * @throws DAException
	 */
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

	/**
	 * Searches and return a list of wines in the database. Throws DAException
	 * if an SQL error occurs.
	 * 
	 * @param s
	 *            The name of the varietal.
	 * @param offset
	 *            The offset.
	 * @param size
	 *            The number of wines to get.
	 * @return Vector of wineIds.
	 * @throws DAException
	 */
	protected static Vector<Long> getWineIdByVarietal(String[] fields,
			int offset, int size) throws DAException, SQLException
	{
		Vector<Long> ret = new Vector<Long>();
		Vector<Long> varId = new Vector<Long>();
		ResultSet r;
		String where = "";

		boolean first = true;

		for (int i = 0; i < fields.length; i++)
		{
			if (first)
			{
				where += "varietalName";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			} else
			{
				where += " and ";
				where += "varietalName";
				where += " LIKE ";
				where += "'%" + escapeXml(fields[i]) + "%'";
				first = false;
			}
		}

		// Find matches to the passed in string
		try
		{
			r = select(VARIETALS_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.");
		}
		// Iterate through the results and add the varietal IDs to the vector
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
	 * Determines if an varietal object exist in the database. Throws
	 * DAException if an SQL error occurs.
	 * 
	 * @param varietal
	 *            The varietal object to check.
	 * @return True if varietal exist and false otherwise.
	 * @throws DAException
	 */
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
		where += "varietalName LIKE'" + escapeXml(grapeType) + "'";

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

	/**
	 * Creates an varietal object from the database.
	 * 
	 * @param r
	 *            The varietal to create.
	 * @return The varietal object.
	 */
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
