package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;

/**
 * Coordinates all Cellar related database access for cellar item adding,
 * deleting, and getting in the database. Also implements search functionality
 * for the cellar items.
 */
public class CellarDAO extends DAO
{

	private final static String CELLARITEM_TABLE = Config
			.getProperty("cellarItemTableName");

	/**
	 * Adds a cellarItem to the cellarItem table.
	 * 
	 * @param userId
	 *            The ID of the user which we are adding the cellar item for
	 * @param item
	 *            The item to be inserted to the cellarItem table
	 * @return CellarItem that was added.
	 * @throws DAException
	 */
	public static CellarItem addCellarItem(long userId, CellarItem item)
			throws DAException
	{

		String columns, values; // no need for a String table since i have those
								// final ones

		// Construct query
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
		} catch (SQLException e)
		{
			throw new DAException("Failed CellarItem insertion.", e);
		}

		// Sets Id to the last incremented id.
		item.setCellarItemId(getLastInsertId());

		return item;
	}

	/**
	 * Deletes the cellarItem from the table.
	 * 
	 * @param cellar
	 * @return true if item was deleted, false otherwise.
	 */
	public static boolean deleteCellarItem(CellarItem cellar)
	{
		int ret;
		try
		{
			ret = delete(CELLARITEM_TABLE, "cellarItemId=" + cellar.getId());
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
			// throw new DAException("Failed Favorites deletion", e);
		}
		if (ret == 0)
			return false;
		return true;
	}

	/**
	 * Deletes the cellar item specified.
	 * 
	 * @param cellarItemId
	 *            Id of the cellarItem to delete from the database.
	 * @return true if item was deleted, false otherwise.
	 */
	public static boolean deleteCellarItem(long cellarItemId)
	{

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

	/**
	 * Edits the cellar item specified
	 * 
	 * @param item
	 *            The new edited item.
	 * @return the edited cellarItem
	 * @throws DAException
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

		sql += "wineID=" + item.getWineId();
		sql += ",quantity=" + item.getQuantity();
		sql += ",notes='" + escapeXml(item.getNotes()) + "'";
		sql += ",cellarItemId='" + item.getId() + "'";

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
	 * Searches for matching wines in the cellar
	 * 
	 * @param q
	 *            WineQuery object used to get the search query
	 * @return Vector of wines for the search result
	 * @throws DAException
	 */
	/**
	 * @param q
	 * @return
	 * @throws DAException
	 */
	/**
	 * @param q
	 * @return
	 * @throws DAException
	 */
	public static Vector<Wine> getWinesFromCellarSearch(WineQuery q)
			throws DAException
	{
		Vector<Wine> ret = new Vector<Wine>();

		// Find all wine IDs that match the query in both the wine database and
		// cellar database
		Vector<Long> allMatching = WineDAO.getWineIdsFromQuerySearch(q);
		Vector<Long> allCellar = getAllWineIdsFromCellar(SessionManager
				.getSessionManager().getLoggedInUser().getUserId());
		Vector<Long> retIds = new Vector<Long>();

		// Find wines that are found in both the wines table and cellar table.
		for (int i = 0; i < allMatching.size(); i++)
		{
			for (int j = 0; j < allCellar.size(); j++)
			{
				if (allMatching.get(i) == allCellar.get(j))
				{
					retIds.add(allMatching.get(i));
				}
			}
		}

		// Create the vector of wines from the vector of wineIds to return
		for (int i = 0; i < retIds.size(); i++)
		{
			Wine temp = new Wine();
			temp = WineDAO.getWine(retIds.get(i).longValue());
			ret.add(temp);
		}

		return ret;
	}

	/**
	 * Gets all the wineIDs from the user's cellar
	 * 
	 * @param userId
	 *            userId associated with the cellar of wineID's
	 * @return Vector of wineID's
	 * @throws DAException
	 */
	public static Vector<Long> getAllWineIdsFromCellar(long userId)
			throws DAException
	{
		// Vector<Wine> wineVector = new Vector<Wine>();
		ResultSet r;
		Vector<Long> wineIdVector = new Vector<Long>();

		try
		{
			r = select(CELLARITEM_TABLE, "*", "userId=" + userId + "");

			// Adding to the wine vector
			while (r.next())
			{
				wineIdVector.add(r.getLong("wineId"));
			}

			Database.disconnect();

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception", e.getCause());
		}

		return wineIdVector;

	}

	/**
	 * Gets all the Wines from the Cellar of the user
	 * 
	 * @param userId
	 *            User's cellar to check
	 * @return Vector of Wine objects in the given user's cellar
	 * @throws DAException
	 */
	public static Vector<Wine> getAllWinesFromCellar(long userId)
			throws DAException
	{
		Vector<Wine> wineVector = new Vector<Wine>();

		Vector<Long> wineIdVector = new Vector<Long>();

		wineIdVector = getAllWineIdsFromCellar(userId);
		for (int i = 0; i < wineIdVector.size(); i++)
		{
			Wine temp = new Wine();
			temp = WineDAO.getWine(wineIdVector.elementAt(i).longValue());
			wineVector.add(temp);
		}

		return wineVector;
	}

	/**
	 * Gets the cellarItem for the user with the specified wineId
	 * 
	 * @param userId
	 *            User's cellar to look in
	 * @param wineId
	 *            Wine to look for
	 * @return CellarItem matching the user and wine specified
	 * @throws DAException
	 */
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
			Database.disconnect();
			if (item == null)
			{
				throw new DAException("Null item");
			}

		} catch (SQLException e)
		{
			throw new DAException("Cellar Item creation error");
		}
		return item;
	}

	/**
	 * Gets the CellarItem by its ID
	 * 
	 * @param cellarItemId
	 *            The ID of the CellarItem to get.
	 * @return the CellarItem of the given ID
	 * @throws DAException
	 */
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
			Database.disconnect();

		} catch (SQLException e)
		{
			throw new DAException("Cellar Item creation error");
		}
		return item;
	}

	/**
	 * Creates a CellarItem from a ResultSet
	 * 
	 * @param r
	 *            The ResultSet of the CellarItem to create
	 * @return CellarItem from the given ResultSet
	 * @throws SQLException
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

}
