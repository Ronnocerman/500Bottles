package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.exception.da.ConnectionException;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.cellar.Cellar;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarDAO extends DAO
{

	private final static String CELLARITEM_TABLE = Config
			.getProperty("cellarItemTableName");

	private final static String CELLAR_TABLE = Config
			.getProperty("cellarTableName");

	public static CellarItem addCellarItem(long cellarId, CellarItem item)
			throws DAException
	{

		String columns, values; // no need for a String table since i have those
								// final ones

		// table = Config.getProperty("cellarItemTableName");

		columns = "( `cellarID`,";
		columns += "`wineID`,";
		columns += "`quantity`,";
		columns += "`notes`)";

		values = "('" + cellarId + "',";
		values += "'" + item.getWineId() + "',";
		values += "'" + item.getQuantity() + "',";
		values += "'" + escapeXml(item.getNotes()) + "')";

		try
		{
			insert(CELLARITEM_TABLE, columns, values);
			Database.disconnect();
			// System.out.print("This is what we got: " + i);
			// TODO: Better exception handling.
		} catch (SQLException e)
		{
			throw new DAException("Failed CellarItem insertion.", e);
		}

		item.setCellarItemId(getLastInsertId());

		return item;
	}

	public static Cellar addCellar(Cellar cellar) throws DAException
	{
		String columns, values; // cellarItemsJSON not used
								// anymore

		columns = "( `userId`)";

		// cellarItemsJSON =
		// cellar.getCellarItemIdsAsJSONArray().toJSONString();

		values = "('" + cellar.getUserId() + "')";
		// values += "'" + "0" + "')";

		try
		{
			insert(CELLAR_TABLE, columns, values);
			Database.disconnect();
			// System.out.print("This is what we got: " + i);
			// TODO: Better exception handling.
		} catch (SQLException e)
		{
			throw new DAException("Failed Cellar insertion.", e);
		}

		cellar.setCellarId(getLastInsertId());
		return cellar;
	}

	public static void deleteCellarItem(CellarItem item) throws DAException,
			NullPointerException
	{
		if (item == null)
			throw new NullPointerException("CellarItem is null.");

		// delete(CELLARITEM_TABLE, "cellarItemId=" + item.getId());
		deleteCellarItem(item.getId());

	}

	public static void deleteCellarItem(long cellarItemId) throws DAException
	{
		if (cellarItemId == 0)
			throw new DAException("CellarItem ID not set.");
		try
		{
			delete(CELLARITEM_TABLE, "cellarItemId=" + cellarItemId);
		} catch (SQLException e)
		{
			throw new DAException("Failed CellarItem deletion.", e);
		}
	}

	public static void deleteCellar(Cellar cellar) throws DAException,
			NullPointerException
	{
		if (cellar == null)
			throw new NullPointerException("Cellar is null.");
		// delete(CELLAR_TABLE, "WHERE cellarId=" + cellar.getCellarId());
		deleteCellar(cellar.getCellarId());
	}

	public static void deleteCellar(long cellarId) throws DAException
	{
		if (cellarId == 0)
			throw new DAException("Cellar ID not set");
		try
		{
			delete(CELLAR_TABLE, "cellarId=" + cellarId);
		} catch (SQLException e)
		{
			throw new DAException("Failed Cellar deletion.");
		}
	}

	public static CellarItem editCellarItem(long cellarId, CellarItem item)
			throws DAException
	{
		if (item == null)
			throw new NullPointerException("CellarItem is null.");
		long cellarItemId = item.getId();
		if (cellarItemId == 0)
			throw new DAException("CellarItem ID not set");
		String sql = "";

		sql += "cellarID=" + cellarId;
		sql += ",wineID=" + item.getWineId();
		sql += ",quantity=" + item.getQuantity();
		sql += ",notes='" + escapeXml(item.getNotes()) + "'";

		System.out.println(sql);

		try
		{
			update(CELLARITEM_TABLE, sql, "cellarItemId=" + cellarItemId);
		} catch (SQLException e)
		{
			throw new DAException("Failed CellarItem update.", e);
		}
		return item;
	}

	public static Cellar editCellar(Cellar cellar) throws DAException,
			NullPointerException
	{
		if (cellar == null)
			throw new NullPointerException("Cellar is null.");

		long cellarId = cellar.getCellarId();
		if (cellarId == 0)
			throw new DAException("Cellar ID not set");
		String sql = "";

		// Get UserId from session manager
		// sql += "cellarID=" + cellar.getCellarId();
		sql += "userId=" + cellar.getUserId();

		try
		{
			update(CELLAR_TABLE, sql, "cellarId=" + cellarId);
		} catch (SQLException e)
		{
			throw new DAException("Failed Cellar update.", e);
		}
		return cellar;
	}

	public static CellarItem getCellarItem(CellarItem item)
			throws ConnectionException, DAException, NullPointerException
	{
		if (item == null)
			throw new NullPointerException("Cellar is null.");
		long cellarItemId = item.getId();
		return getCellarItem(cellarItemId);
	}

	public static CellarItem getCellarItem(long cellarItemId)
			throws ConnectionException, DAException
	{
		// String table;
		CellarItem item = null;
		ResultSet r;

		if (cellarItemId == 0)
			throw new DAException("CellarItem ID not set.");

		try
		{
			String where = "cellarItemId = ";
			where += cellarItemId;
			r = select(CELLARITEM_TABLE, "*", where);
			item = createCellarItem(r);
			Database.disconnect(); // what was that one command that josh used?

		} catch (SQLException e)
		{
			throw new ConnectionException("SQL select exception");
		}
		return item;
	}

	public static Cellar getCellar(Cellar cellar) throws ConnectionException,
			DAException, NullPointerException
	{
		if (cellar == null)
			throw new NullPointerException("Cellar is null.");
		long cellarId = cellar.getCellarId();
		return getCellar(cellarId);
	}

	public static Cellar getCellar(long cellarId) throws ConnectionException,
			DAException
	{
		// String table;
		Cellar cellar = null;
		ResultSet r;

		if (cellarId == 0)
			throw new DAException("Cellar ID not set.");

		try
		{
			String where = "cellarId = ";
			where += cellarId;
			r = select(CELLAR_TABLE, "*", where);
			cellar = createCellar(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new ConnectionException("SQL select exception");
		}

		return cellar;
	}

	private static CellarItem createCellarItem(ResultSet r) throws SQLException
	{
		CellarItem cellarItem;
		Wine w;

		long cellarItemId, cellarId;
		int quantity;
		String notes;
		long wineId;

		if (!r.next())
			return null;

		cellarId = r.getLong("cellarId");
		cellarItemId = r.getLong("cellarItemId");
		wineId = r.getLong("wineId");
		quantity = r.getInt("quantity");
		notes = r.getString("notes");

		w = new Wine();
		w.setId((int) wineId);
		cellarItem = new CellarItem(w);
		cellarItem.setQuantity(quantity);
		cellarItem.setNotes(notes);
		cellarItem.setCellarItemId(cellarItemId);
		cellarItem.setCellarId(cellarId);

		return cellarItem;
	}

	private static Cellar createCellar(ResultSet r) throws SQLException
	{
		Cellar c;

		long cellarId, userId;

		if (!r.next())
			return null;

		cellarId = r.getLong("cellarId");
		userId = r.getLong("userId");

		c = new Cellar();
		c.setCellarId(cellarId);
		c.setUserId(userId);

		return c;
	}
}
