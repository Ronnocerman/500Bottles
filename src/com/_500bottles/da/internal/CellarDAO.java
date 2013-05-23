package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.object.cellar.Cellar;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarDAO extends DAO
{
	public CellarItem addCellarItem(CellarItem item) throws Exception
	{

		String table, columns, values;

		table = Config.getProperty("cellarItemTableName");

		columns = "( `cellarItemID`, `wineID`,";
		columns += "`quantity`,";
		columns += "`notes`)";

		values = "('" + item.getCellarItemId() + "',";
		values += "'" + item.getWineId() + "',";
		values += "'" + item.getQuantity() + "',";
		values += "'" + item.getNotes() + "')";

		try
		{
			int i = insert(table, columns, values);
			System.out.print("This is what we got: " + i);
			// TODO: Better exception handling.
		} catch (Exception e)
		{
			throw e;
		}

		item.setCellarItemId(getLastInsertId());

		return item;
	}

	public void addCellar(Cellar cellar) throws Exception
	{
		String table, columns, values;

		table = Config.getProperty("cellarTableName");

		columns = "( `cellarID`, `userID`)";

		values = "('" + cellar.getCellarId() + "',";
		values += "'" + "0" + "')";

		try
		{
			int i = insert(table, columns, values);
			System.out.print("This is what we got: " + i);
			// TODO: Better exception handling.
		} catch (Exception e)
		{
			throw e;
		}
	}

	public void deleteCellarItem()
	{
	}

	public void deleteCellar()
	{

	}

	public void editCellarItem()
	{
	}

	public void editCellar()
	{

	}

	public void getCellarItem(long cellarItemId)
	{

		String table;

		ResultSet r;

		table = Config.getProperty("cellarItemTableName");

		try
		{
			String where = "cellarItemId = ";
			where += cellarItemId;
			r = select(table, "*", where);
			createCellarItem(r);

		} catch (Exception e)
		{
			// TODO: handle query exceptions.
		}

	}

	public void getCellar(long cellarId)
	{
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
