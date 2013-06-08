package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.WineQuery;

public class VineyardDAO extends DAO
{

	private final static String VINEYARDS_TABLE = Config
			.getProperty("vineyardsTableName");

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

}
