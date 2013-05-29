package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.object.cellar.Cellar;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarDAO extends DAO
{

	private final static String CELLARITEM_TABLE = Config
			.getProperty("cellarItemTableName");

	private final static String CELLAR_TABLE = Config
			.getProperty("cellarTableName");

	public static CellarItem addCellarItem(CellarItem item) throws Exception
	{

		String columns, values; // no need for a String table since i have those
								// final ones

		// table = Config.getProperty("cellarItemTableName");

		columns = "( `cellarID`,";
		columns += "`wineID`,";
		columns += "`quantity`,";
		columns += "`notes`)";

		values = "('" + item.getCellarId() + "',";
		values += "'" + item.getWineId() + "',";
		values += "'" + item.getQuantity() + "',";
		values += "'" + escapeXml(item.getNotes()) + "')";

		try
		{
			int i = insert(CELLARITEM_TABLE, columns, values);
			System.out.print("This is what we got: " + i);
			// TODO: Better exception handling.
		} catch (Exception e)
		{
			System.err.print(e.getMessage());
			throw e;
		}

		item.setCellarItemId(getLastInsertId());

		return item;
	}

	public static Cellar addCellar(Cellar cellar) throws Exception
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
			int i = insert(CELLAR_TABLE, columns, values);
			System.out.print("This is what we got: " + i);
			// TODO: Better exception handling.
		} catch (Exception e)
		{
			throw e;
		}

		cellar.setCellarId(getLastInsertId());
		return cellar;
	}

	public static void deleteCellarItem(CellarItem item) throws SQLException
	{
		// delete(CELLARITEM_TABLE, "cellarItemId=" + item.getId());
		deleteCellarItem(item.getId());

	}

	public static void deleteCellarItem(long cellarItemId) throws SQLException
	{
		delete(CELLARITEM_TABLE, "cellarItemId=" + cellarItemId);
	}

	public static void deleteCellar(Cellar cellar) throws SQLException
	{
		// delete(CELLAR_TABLE, "WHERE cellarId=" + cellar.getCellarId());
		deleteCellar(cellar.getCellarId());
	}

	public static void deleteCellar(long cellarId) throws SQLException
	{
		delete(CELLAR_TABLE, "cellarId=" + cellarId);
	}

	public static CellarItem editCellarItem(CellarItem item)
			throws SQLException
	{
		long cellarItemId = item.getId();
		String sql = "";

		sql += "cellarID=" + item.getCellarId();
		sql += ",wineID=" + item.getWineId();
		sql += ",quantity=" + item.getQuantity();
		sql += ",notes='" + escapeXml(item.getNotes()) + "'";

		update(CELLARITEM_TABLE, sql, "cellarItemId=" + cellarItemId);
		return item;
	}

	public static Cellar editCellar(Cellar cellar) throws SQLException
	{
		long cellarId = cellar.getCellarId();
		String sql = "";

		// Get UserId from session manager
		// sql += "cellarID=" + cellar.getCellarId();
		sql += "userId=" + cellar.getUserId();

		update(CELLAR_TABLE, sql, "cellarId=" + cellarId);
		return cellar;
	}

	public static CellarItem getCellarItem(long cellarItemId)
	{
		// String table;
		CellarItem item = null;
		ResultSet r;

		try
		{
			String where = "cellarItemId = ";
			where += cellarItemId;
			r = select(CELLARITEM_TABLE, "*", where);
			item = createCellarItem(r);
			Database.disconnect(); // what was that one command that josh used?

		} catch (Exception e)
		{
			// TODO: handle query exceptions.
		}
		return item;
	}

	public static Cellar getCellar(long cellarId)
	{
		// String table;
		Cellar cellar = null;
		ResultSet r;

		try
		{
			String where = "cellarId = ";
			where += cellarId;
			r = select(CELLAR_TABLE, "*", where);
			cellar = createCellar(r);
			Database.disconnect();
		} catch (Exception e)
		{
			// TODO: handle query exceptions.
		}
		return cellar;

	}

	public static CellarItem createCellarItem(ResultSet r) throws SQLException
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

	public static Cellar createCellar(ResultSet r) throws SQLException // maybe
																		// don't
	// need
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
