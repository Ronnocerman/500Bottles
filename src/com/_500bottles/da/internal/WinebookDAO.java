package com._500bottles.da.internal;

import com._500bottles.config.Config;
import java.sql.ResultSet;

import com._500bottles.object.winebook.Photo;
import com._500bottles.object.wine.Wine;
import org.json.simple.JSONArray;
import com._500bottles.object.winebook.Entry;

import java.util.Vector;

import java.sql.SQLException;
import java.util.Date;
import org.json.simple.JSONValue;

public class WinebookDAO extends DAO
{

	public static void addEntry(Entry entry) throws Exception
	{
		String 	table,
			columns,
			values,
			dateCreated,
			dateLastEdited,
			winesJSON,
			photosJSON;

		table = Config.getProperty("winebookTableName");

		columns = "(`userID`, `title`, `dateCreated`,";
		columns += "`dateLastEdited`, `textContent`,";
		columns += "`winesJSON`, `photosJSON`)";

		dateCreated = formatDate(entry.getDateCreated());
		dateLastEdited = formatDate(entry.getDateLastEdited());

		photosJSON = entry.getPhotoIdsAsJSONArray().toJSONString();
		winesJSON = entry.getWineIdsAsJSONArray().toJSONString();

		// TODO: get user id from session manager or via user object.
		values  = "('" + "0" + "',";
		values += "'" + entry.getTitle() + "',";
		values += "'" + dateCreated + "',";
		values += "'" + dateLastEdited + "',";
		values += "'" + entry.getContent() + "',";
		values += "'" + winesJSON + "',";
		values += "'" + photosJSON + "')";

		try {
			int i = insert(table, columns, values);
			System.out.print("This is what we got: " + i);
		// TODO: Better exception handling.
		} catch (Exception e) {
			throw e;
		}
	}

	public static void deleteEntry(Entry entry)
	{
	}

	public static void editEntry(Entry entry)
	{
	}

	public static void getEntry(Entry entry)
	{
	}

	public static void getEntry(long entryId)
	{
		String	table;

		ResultSet r;

		table = Config.getProperty("winebookTableName");

		try {
			r = select(table, "*");
			createEntry(r);

		} catch (Exception e) {
			// TODO: handle query exceptions.
		}


	}

	private static Entry createEntry(ResultSet r) throws SQLException
	{
		Entry entry;

		long userId, entryId;

		String 	title,
			textContent,
			winesJSON,
			photosJSON;

		Date 	dateCreated,
			dateLastEdited;

		JSONArray wineIds, photosIds;

		Vector<Wine> wines;
		Vector<Photo> photos;

		// Return null if there was no entry in the ResultSet.
		if (!r.next())
			return null;

		userId = r.getLong("userId");
		entryId = r.getLong("entryId");

		title = r.getString("title");
		textContent = r.getString("textContent");
		winesJSON = r.getString("winesJSON");
		photosJSON = r.getString("photosJSON");

		wineIds = (JSONArray) JSONValue.parse(winesJSON);
		photosIds = (JSONArray) JSONValue.parse(photosJSON);

		// TODO: Create the wine and photo objects to add to the entry.
		wines = new Vector<Wine>(); //(wineIds);
		photos = new Vector<Photo>(); //(photosIds);

		dateCreated = r.getDate("dateCreated");
		dateLastEdited = r.getDate("dateLastEdited");

		entry = new Entry(entryId, title, textContent, photos,
				wines, dateCreated, dateLastEdited);

		return entry;
	}
}
