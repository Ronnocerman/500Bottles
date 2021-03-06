package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.winebook.Entry;
import com._500bottles.object.winebook.Photo;

/**
 * Coordinates all Winebook related database access for Winebook Entry adding,
 * deleting, getting, and updating in the database.
 */
public class WinebookDAO extends DAO
{
	private final static String WINEBOOK_TABLE = Config
			.getProperty("winebookTableName");

	/**
	 * Adds a cellar item to the Winebook. Returns an entry object with the new
	 * unique id set. Throws a DAException if the SQL insertion fails.
	 * 
	 * @param entry
	 *            The entry object to add to Winebook.
	 * @return Entry object with the entry ID set.
	 * @throws DAException
	 */
	public static Entry addEntry(Entry entry) throws DAException
	{
		String columns, values, dateCreated, dateLastEdited, winesJSON, photosJSON;

		columns = "(`userID`, `title`, `dateCreated`,";
		columns += "`dateLastEdited`, `textContent`,";
		columns += "`winesJSON`, `photosJSON`)";

		dateCreated = formatDate(entry.getDateCreated());
		dateLastEdited = formatDate(entry.getDateLastEdited());

		photosJSON = entry.getPhotoIdsAsJSONArray().toJSONString();
		winesJSON = entry.getWineIdsAsJSONArray().toJSONString();

		values = "('" + entry.getUserId() + "',";
		values += "'" + escapeXml(entry.getTitle()) + "',";
		values += "'" + dateCreated + "',";
		values += "'" + dateLastEdited + "',";
		values += "'" + escapeXml(entry.getContent()) + "',";
		values += "'" + escapeXml(winesJSON) + "',";
		values += "'" + escapeXml(photosJSON) + "')";

		try
		{
			insert(WINEBOOK_TABLE, columns, values);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Winebook entry insertion.", e);
		}

		entry.setEntryId(getLastInsertId());

		return entry;
	}

	/**
	 * Deletes a winebook entry. The entry's id must be set for a deletion to
	 * succeed. Throws DAException if the entry ID is not set. Throws
	 * NullPointerException if the entry object is null.
	 * 
	 * @param entry
	 *            The entry to delete.
	 * @throws DAException
	 * @throws NullPointerException
	 */
	public static boolean deleteEntry(long entryId)
	{

		int ret;
		try
		{
			ret = delete(WINEBOOK_TABLE, "entryId=" + entryId);
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
	 * Edits a winebook entry. Takes the passed entry object and updates the
	 * database entry with the corresponding values. The passed entry object
	 * must have the entry ID set. Throws DAException if the entry ID is not set
	 * or on an SQL error. Throws NullPointerException if the entry object is
	 * null.
	 * 
	 * @param entry
	 * @throws DAException
	 * @throws NullPointerException
	 */
	public static void editEntry(Entry entry) throws DAException,
			NullPointerException
	{
		if (entry == null)
			throw new NullPointerException("Entry object null.");

		if (entry.getEntryId() == 0)
			throw new DAException("Entry ID not set.");

		long entryId = entry.getEntryId();
		String sql = "";

		sql += "title='" + escapeXml(entry.getTitle()) + "'";
		sql += ",userId=" + entry.getUserId();
		sql += ",dateCreated='" + formatDate(entry.getDateCreated()) + "'";
		sql += ",dateLastEdited='" + formatDate(entry.getDateLastEdited())
				+ "'";
		sql += ",textContent='" + escapeXml(entry.getContent()) + "'";
		sql += ",winesJSON='"
				+ escapeXml(entry.getWineIdsAsJSONArray().toString()) + "'";
		sql += ",photosJSON='"
				+ escapeXml(entry.getPhotoIdsAsJSONArray().toString()) + "'";

		try
		{
			update(WINEBOOK_TABLE, sql, "entryId=" + entryId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Winebook entry update.", e);
		}
	}

	/**
	 * Gets and returns an entry object from the database. Throws DAException if
	 * an SQL error occurs or if the entry ID was not set. Throws
	 * NullPointerException if the entry is null.
	 * 
	 * @param entryId
	 *            The ID of the entry to get.
	 * @return The Entry object.
	 * @throws DAException
	 */
	public static Entry getEntry(long entryId) throws DAException
	{
		long user_id = SessionManager.getSessionManager().getLoggedInUser().getUserId();

		ResultSet r;
		Entry entry = null;

		if (entryId == 0)
			throw new DAException("Entry ID not set.");

		try
		{
			r = select(WINEBOOK_TABLE, "*", "entryId=" + entryId);
			entry = createEntry(r, user_id);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return entry;
	}

	/**
	 * Gets and returns a vector of entry objects from the database for the
	 * logged in user. Throws DAException if there is an SQL error of if an
	 * entry ID was not set.
	 * 
	 * @param userId
	 *            The ID of the user's Winebook to get.
	 * @return Vector of Entry objects
	 * @throws DAException
	 */
	public static Vector<Entry> getAllEntries(long userId) throws DAException
	{
		Vector<Entry> entriesVector = new Vector<Entry>();
		ResultSet r;

		try
		{
			r = select(WINEBOOK_TABLE, "*", "userId='" + userId + "'");

			Vector<Long> entryIdVector = new Vector<Long>();
			while (r.next())
			{
				Long entryId;
				entryId = new Long(r.getInt("entryId"));
				entryIdVector.add(entryId);
			}
			Database.disconnect();

			for (int i = 0; i < entryIdVector.size(); i++)
			{
				Entry temp = new Entry();
				temp = getEntry(entryIdVector.elementAt(i).longValue());
				entriesVector.add(temp);
			}
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception", e.getCause());
		}

		return entriesVector;
	}

	/**
	 * Creates an Entry object based on the ResultSet returned from an select
	 * operation. Returns null if the ResultSet is empty.
	 * 
	 * @param res
	 *            The ResultSet returned from a SELECT operation.
	 * @return Entry object.
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	private static Entry createEntry(ResultSet res, long userId) throws SQLException
	{
		Entry entry;

		long entryId;

		String title, textContent, winesJSON, photosJSON;

		Date dateCreated, dateLastEdited;

		JSONArray wineIds, photosIds;

		Vector<Wine> wines;
		Vector<Photo> photos;
		// Return null if there was no entry in the ResultSet.
		if (!res.next())
			return null;

		entryId = res.getLong("entryId");
		title = unescapeXml(res.getString("title"));
		textContent = unescapeXml(res.getString("textContent"));
		winesJSON = unescapeXml(res.getString("winesJSON"));
		photosJSON = unescapeXml(res.getString("photosJSON"));
		wineIds = (JSONArray) JSONValue.parse(winesJSON);
		photosIds = (JSONArray) JSONValue.parse(photosJSON);

		// TODO: Create the wine and photo objects to add to the entry.
		wines = new Vector<Wine>((Collection<? extends Wine>) wineIds);
		photos = new Vector<Photo>((Collection<? extends Photo>) photosIds);

		dateCreated = res.getDate("dateCreated");
		dateLastEdited = res.getDate("dateLastEdited");

		entry = new Entry(entryId, title, textContent, photos, wines,
				dateCreated, dateLastEdited);
		entry.setUserId(userId);

		return entry;
	}
}
