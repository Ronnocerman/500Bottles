package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.WineQuery;

public class VarietalDAO extends DAO
{
	private final static String VARIETALS_TABLE = Config
			.getProperty("varietalsTableName");

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

	protected static Vector<Long> getWineIdByVarietal(String s, int offset,
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
