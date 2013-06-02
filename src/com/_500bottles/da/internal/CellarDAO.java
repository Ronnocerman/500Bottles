package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarDAO extends DAO
{

	private final static String CELLARITEM_TABLE = Config
			.getProperty("cellarItemTableName");

	// private final static String CELLAR_TABLE = Config
	// .getProperty("cellarTableName");

	public static CellarItem addCellarItem(long userId, CellarItem item)
			throws DAException
	{

		String columns, values; // no need for a String table since i have those
								// final ones

		// table = Config.getProperty("cellarItemTableName");

		columns = "( `userId`,";
		columns += "`wineId`,";
		columns += "`quantity`,";
		columns += "`notes`,";
		columns += "`cellarItemId`)";

		values = "('" + userId + "',";
		values += "'" + item.getWineId() + "',";
		values += "'" + item.getQuantity() + "',";
		values += "'" + escapeXml(item.getNotes()) + "',";
		values += "'" + item.getId() + "')";

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

	/*
	 * public static void addCellar(long userId) throws DAException { String
	 * columns, values; // cellarItemsJSON not used // anymore
	 * 
	 * columns = "( `userId`)";
	 * 
	 * // cellarItemsJSON = //
	 * cellar.getCellarItemIdsAsJSONArray().toJSONString();
	 * 
	 * values = "('" + userId + "')"; // values += "'" + "0" + "')";
	 * 
	 * try { insert(CELLAR_TABLE, columns, values); Database.disconnect(); //
	 * System.out.print("This is what we got: " + i); // TODO: Better exception
	 * handling. } catch (SQLException e) { throw new
	 * DAException("Failed Cellar insertion.", e); } catch (ConnectionException
	 * e) { throw new DAException("Not connected to database"); } }
	 */

	/*
	 * public static void deleteCellarItem(CellarItem item) throws DAException,
	 * NullPointerException { if (item == null) throw new
	 * NullPointerException("CellarItem is null.");
	 * 
	 * // delete(CELLARITEM_TABLE, "cellarItemId=" + item.getId());
	 * deleteCellarItem(item.getId());
	 * 
	 * }
	 */

	public static boolean deleteCellarItem(long cellarItemId)
	{
		// if (cellarItemId == 0)
		// throw new DAException("CellarItem ID not set.");
		int ret;
		try
		{
			ret = delete(CELLARITEM_TABLE, "cellarItemId=" + cellarItemId);
		} catch (SQLException e)
		{
			return false;
			// throw new DAException("Failed CellarItem deletion.", e);
		}
		if (ret == 0)
			return false;
		return true;
	}

	/*
	 * public static void deleteCellar(long cellarId) throws DAException { if
	 * (cellarId == 0) throw new DAException("Cellar ID not set"); try {
	 * delete(CELLAR_TABLE, "cellarId=" + cellarId); } catch (SQLException e) {
	 * throw new DAException("Failed Cellar deletion."); } catch
	 * (ConnectionException e) { throw new
	 * DAException("Not connected to database"); } }
	 */

	public static CellarItem editCellarItem(CellarItem item) throws DAException
	{
		if (item == null)
			throw new NullPointerException("CellarItem is null.");
		long cellarItemId = item.getId();
		if (cellarItemId == 0)
			throw new DAException("CellarItem ID not set");
		// if (userId == 0)
		// throw new DAException("User ID not set");
		String sql = "";

		// sql += "userID=" + userId;
		sql += "wineID=" + item.getWineId();
		sql += ",quantity=" + item.getQuantity();
		sql += ",notes='" + escapeXml(item.getNotes()) + "'";
		sql += ",cellarItemId='" + item.getId() + "'";

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

	/**
	 * DONT THINK WE NEED THIS
	 * 
	 * @param userId
	 * @return cellarId of edited cellar
	 * @throws DAException
	 * @throws NullPointerException
	 * @throws SQLException
	 */
	/*
	 * public static long editCellar(long userId) throws DAException,
	 * NullPointerException, SQLException {
	 * 
	 * if (userId == 0) throw new DAException("Cellar ID not set"); String sql =
	 * "";
	 * 
	 * // Get UserId from session manager // sql += "cellarID=" +
	 * cellar.getCellarId(); sql += "userId=" + userId;
	 * 
	 * try { update(CELLAR_TABLE, sql, "userId=" + userId); } catch
	 * (SQLException e) { throw new DAException("Failed Cellar update.", e); }
	 * catch (ConnectionException e) { throw new
	 * DAException("Not connected to database"); } ResultSet r = null; try { r =
	 * select(CELLAR_TABLE, sql, "userId=" + userId); } catch (SQLException e) {
	 * throw new DAException("SQL Select exception"); } catch
	 * (ConnectionException e) { throw new
	 * DAException("Not connected to database"); } return (long)
	 * r.getInt("cellarId"); }
	 */
	/*
	 * public static CellarItem getCellarItem(CellarItem item) throws
	 * ConnectionException, DAException, NullPointerException { if (item ==
	 * null) throw new NullPointerException("Cellar is null."); long
	 * cellarItemId = item.getId(); return getCellarItem(cellarItemId); }
	 */
	// get cellaritem by wineid
	public static CellarItem getByWineID(long userId, long wineId)
			throws DAException
	{
		CellarItem item = null;
		ResultSet r;

		if (wineId == 0)
			throw new DAException("Wine ID not set.");

		try
		{
			String where = "wineId = ";
			where += wineId;
			where += " and userId = ";
			where += userId;

			r = select(CELLARITEM_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("CellarItem does not exist.");
		}
		try
		{
			item = createCellarItem(r);
			Database.disconnect(); // what was that one command that josh used?

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception"); // TODO : fix this
		}
		return item;
	}

	public static CellarItem getCellarItem(long cellarItemId)
			throws DAException
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
		} catch (SQLException e)
		{
			throw new DAException("CellarItem does not exist.");
		}
		try
		{
			item = createCellarItem(r);
			Database.disconnect(); // what was that one command that josh used?

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception");
		}
		return item;
	}

	/*
	 * public static long getCellar(long cellarId) throws DAException,
	 * SQLException { // String table; ResultSet r = null;
	 * 
	 * if (cellarId == 0) throw new DAException("Cellar ID not set.");
	 * 
	 * try { String where = "cellarId = "; where += cellarId; r =
	 * select(CELLAR_TABLE, "*", where); Database.disconnect(); } catch
	 * (SQLException e) { throw new DAException("SQL select exception");
	 * 
	 * } catch (ConnectionException e) { throw new
	 * DAException("Not connected to database"); }
	 * 
	 * return (long) r.getInt("userId"); }
	 */
	private static CellarItem createCellarItem(ResultSet r) throws SQLException
	{
		CellarItem cellarItem;
		Wine w;

		long cellarItemId;
		int quantity;
		String notes;
		long wineId;

		if (!r.next())
			return null;

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

		return cellarItem;
	}

	@SuppressWarnings("null")
	public Vector<Wine> getAllWinesFromCellar(long userId) throws DAException
	{
		Vector<Wine> wineVector = null;
		ResultSet r;

		try
		{
			r = select(CELLARITEM_TABLE, "*", "userId='" + userId + "'");

			Vector<Long> wineIdVector = new Vector<Long>();
			while (r.next())
			{
				Long wineId;
				wineId = new Long(r.getInt("wineId"));
				wineIdVector.add(wineId);
			}
			for (int i = 0; i < wineIdVector.size(); i++)
			{
				Wine temp = new Wine();
				temp = WineDAO.getWine(wineIdVector.elementAt(i).longValue());
				wineVector.add(temp);
			}
			Database.disconnect();

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception", e.getCause());
		}

		return wineVector;
	}

	/*
	 * private static Cellar createCellar(ResultSet r) throws SQLException {
	 * Cellar c;
	 * 
	 * long cellarId, userId;
	 * 
	 * if (!r.next()) return null;
	 * 
	 * cellarId = r.getLong("cellarId"); userId = r.getLong("userId");
	 * 
	 * c = new Cellar(); c.setCellarId(cellarId); c.setUserId(userId);
	 * 
	 * return c; }
	 */
}
