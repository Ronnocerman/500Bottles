package com._500bottles.da.internal;

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

		// values = "('" + item.getCellarItemId() + "',";
		values = "('" + item.getWineId() + "',";
		values += "'" + item.getQuantity() + "',";
		values += "'" + item.getNotes() + "')";

		try
		{
			int i = insert(CELLARITEM_TABLE, columns, values);
			System.out.print("This is what we got: " + i);
			// TODO: Better exception handling.
		} catch (Exception e)
		{
			throw e;
		}

		item.setCellarItemId(getLastInsertId());

		return item;
	}

	public static Cellar addCellar(Cellar cellar) throws Exception
	{
		String columns, values, cellarItemsJSON;

		// table = Config.getProperty("cellarTableName");

		columns = "( `cellarItemsJSON`)";

		cellarItemsJSON = cellar.getCellarItemIdsAsJSONArray().toJSONString();

		values = "('" + cellarItemsJSON + "')";
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

	public void deleteCellarItem(CellarItem item) throws SQLException
	{
		delete(CELLARITEM_TABLE, "WHERE cellarItemId=" + item.getId());
	}

	public void deleteCellar(Cellar cellar) throws SQLException
	{
		delete(CELLAR_TABLE, "WHERE cellarId=" + cellar.getCellarId());
	}

	public void editCellarItem(CellarItem item) throws SQLException
	{
		long cellarItemId = item.getId();
		String sql = "";

		sql += "wineID=" + item.getWineId();
		sql += ",quantity=" + item.getQuantity();
		sql += ",notes=" + item.getNotes();

		update(CELLARITEM_TABLE, sql, "cellarItemId=" + cellarItemId);
	}

	public void editCellar(Cellar cellar) throws SQLException
	{
		long cellarId = cellar.getCellarId();
		String sql = "";

		// Get UserId from session manager
		// sql += "cellarID=" + cellar.getCellarId();
		sql += "cellarItemsJSON=" + cellar.getCellarItemIdsAsJSONArray();

		update(CELLAR_TABLE, sql, "cellarId=" + cellarId);
	}

	public void getCellarItem(long cellarItemId)
	{

		// String table;

		ResultSet r;

		try
		{
			String where = "cellarItemId = ";
			where += cellarItemId;
			r = select(CELLARITEM_TABLE, "*", where);
			createCellarItem(r);

		} catch (Exception e)
		{
			// TODO: handle query exceptions.
		}

	}

	public void getCellar(long cellarId)
	{

		// String table;

		ResultSet r;

		try
		{
			String where = "cellarId = ";
			where += cellarId;
			r = select(CELLAR_TABLE, "*", where);
			createCellar(r);

		} catch (Exception e)
		{
			// TODO: handle query exceptions.
		}

	}

	public CellarItem createCellarItem(ResultSet r) throws SQLException
	{
		CellarItem cellarItem;
		Wine w;

		long cellarItemId;
		int quantity;
		String notes;
		long wineId;

		cellarItemId = r.getLong("cellarItemId");
		wineId = r.getLong("wineId");

		quantity = r.getInt("quantity");
		notes = r.getString("notes");

		if (!r.next())
			return null;

		w = new Wine();
		w.setId((int) wineId);
		cellarItem = new CellarItem(w);
		cellarItem.setQuantity(quantity);
		cellarItem.setNotes(notes);
		cellarItem.setCellarItemId(cellarItemId);

		return cellarItem;
	}

	public Cellar createCellar(ResultSet r) throws SQLException // maybe don't
																// need
	{
		Cellar c;

		long cellarId;

		cellarId = r.getLong("cellarId");

		if (!r.next())
			return null;

		c = new Cellar();
		c.setCellarId(cellarId);

		return c;
	}
}
