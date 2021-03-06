package com._500bottles.action;

import java.util.Vector;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.winebook.EntryDoesExistException;
import com._500bottles.exception.winebook.EntryDoesNotExistException;
import com._500bottles.exception.winebook.PhotoDoesNotExist;
import com._500bottles.manager.SessionManager;
import com._500bottles.manager.WinebookManager;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.winebook.Entry;
import com._500bottles.object.winebook.Photo;

public class WinebookAction
{

	/**
	 * Add an entry to the winebook
	 * 
	 * @param entry
	 *            Entry object to be added to the Winebook
	 * @throws EntryDoesExistException
	 */
	public static void addEntry(Entry entry) throws EntryDoesExistException
	{
		WinebookManager.addEntry(entry);
	}

	/**
	 * Get a winebook entry
	 * 
	 * @param id
	 *            ID of the entry to be added the winebook
	 * @throws EntryDoesNotExistException
	 */
	public static Entry getEntry(long id) throws EntryDoesNotExistException
	{
		return WinebookManager.getEntry(id);
	}

	/**
	 * Remove the specified winebook entry form the winebook
	 * 
	 * @param id
	 *            ID of the winebook entry to be removed
	 */
	public static void removeEntry(long id) throws EntryDoesNotExistException
	{
		WinebookManager.removeEntry(id);
	}

	/**
	 * Edit the content of the specific winebook entry
	 * 
	 * @param id
	 *            ID of the winebook entry to be edited
	 * @throws EntryDoesNotExistException
	 */
	public static void editContent(long id) throws EntryDoesNotExistException
	{
		WinebookManager.editEntry(id);
	}

	/**
	 * Adds a specified wine to a winebook entry
	 * 
	 * @param id
	 *            ID of the winebook entry to add the specifed wine to
	 * @param wid
	 *            Wine to be added to the specified winebook entry
	 * @throws DAException
	 */
	public static void addWine(long id, long wid) throws DAException
	{
		Wine w = WineDAO.getWine(wid);
		WinebookManager.addWine(id, w);
	}

	/**
	 * Removes a specified wine from a winebook entry
	 * 
	 * @param id
	 *            ID of the winebook entry that the specified wine will be
	 *            removed from
	 * @param wid
	 *            Wine to be removed from the specified winebook entry
	 * @throws DAException
	 */
	public static void removeWine(long id, long wid) throws DAException
	{
		Wine w = WineDAO.getWine(wid);
		WinebookManager.removeWine(id, w);
	}

	/**
	 * Adds a photo to the specified winebook entry
	 * 
	 * @param id
	 *            ID of the winebook entry that photo will be added to
	 * @param p
	 *            Photo to be added to the specified winebook entry
	 */
	public static void addPhoto(long id, Photo p)
	{
		WinebookManager.addPhoto(id, p);
	}

	/**
	 * Removes the specified photo from the specified winebook entry
	 * 
	 * @param id
	 *            ID of the winebook entry that photo will be removed form
	 * @param p
	 *            Photo to be removed from the specified winebook entry
	 */
	public static void removePhoto(long id, Photo p)
	{
		WinebookManager.removePhoto(id, p);
	}

	/**
	 * Magic!
	 * 
	 * @param id
	 *            Ofsomething
	 * @param p
	 *            Photo to be uploaded
	 */
	public static void uploadPhoto(long id, Photo p)
	{
	}

	public static String getPhotoURI(long photo_id)
	{
		String photo_uri;

		try
		{
			photo_uri = WinebookManager.getPhotoURI(photo_id);
		} catch (PhotoDoesNotExist e)
		{
			photo_uri = "";
		}

		return photo_uri;
	}

	public static Vector<Entry> getAllEntries()
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		return WinebookManager.getAllEntries(userID);
	}
}