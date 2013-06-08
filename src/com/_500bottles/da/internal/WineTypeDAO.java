package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

public class WineTypeDAO extends DAO
{

	private final static String WINETYPE_TABLE = Config
			.getProperty("wineTypeTableName");

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
		// Iterate through the results and add the wineType IDs to the varId
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
			throw e;
		}
		return wineType;
	}

}
