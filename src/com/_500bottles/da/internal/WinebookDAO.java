package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
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
		values += "'" + entry.getTitle() + "',";
		values += "'" + dateCreated + "',";
		values += "'" + dateLastEdited + "',";
		values += "'" + entry.getContent() + "',";
		values += "'" + winesJSON + "',";
		values += "'" + photosJSON + "')";

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
	public static void deleteEntry(Entry entry) throws DAException,
			NullPointerException
	{
		if (entry == null)
			throw new NullPointerException("Entry object null.");

		if (entry.getEntryId() == 0)
			throw new DAException("Entry ID not set.");

		try
		{

			delete(WINEBOOK_TABLE, "entryId=" + entry.getEntryId());
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Winebook entry deletion.",
					e.getCause());
		}
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
	 * Gets and returns an entry object from the database. The entry object must
	 * have entry ID set to retrieve an entry. Throws DAException if an SQL
	 * error occurs or if the entry ID was not set. Throws NullPointerException
	 * if the entry is null.
	 * 
	 * @param entry
	 *            An entry object with entry ID set to retrieve from the
	 *            database.
	 * @return Entry object.
	 * @throws DAException
	 */
	public static Entry getEntry(Entry entry) throws DAException,
			NullPointerException
	{
		if (entry == null)
			throw new NullPointerException("Null Winebook entry.");

		if (entry.getEntryId() == 0)
			throw new DAException("Entry ID not set.");

		long entryId = entry.getEntryId();
		return getEntry(entryId);
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
		ResultSet r;
		Entry entry = null;

		if (entryId == 0)
			throw new DAException("Entry ID not set.");

		try
		{
			r = select(WINEBOOK_TABLE, "*", "entryId=" + entryId);
			entry = createEntry(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e);
		}

		return entry;
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
	private static Entry createEntry(ResultSet res) throws SQLException
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
		title = res.getString("title");
		textContent = res.getString("textContent");
		winesJSON = res.getString("winesJSON");
		photosJSON = res.getString("photosJSON");

		wineIds = (JSONArray) JSONValue.parse(winesJSON);
		photosIds = (JSONArray) JSONValue.parse(photosJSON);

		// TODO: Create the wine and photo objects to add to the entry.
		wines = new Vector<Wine>((Collection<? extends Wine>) wineIds);
		photos = new Vector<Photo>((Collection<? extends Photo>) photosIds);

		dateCreated = res.getDate("dateCreated");
		dateLastEdited = res.getDate("dateLastEdited");

		entry = new Entry(entryId, title, textContent, photos, wines,
				dateCreated, dateLastEdited);

		return entry;
	}
}
