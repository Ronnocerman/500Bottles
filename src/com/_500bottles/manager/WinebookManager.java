package com._500bottles.manager;

import com._500bottles.da.internal.WinebookDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.winebook.Entry;
import com._500bottles.object.winebook.Photo;

public class WinebookManager
{
	/**
	 * Add an entry to a Winebook
	 * 
	 * @param id
	 *            The ID of the wine
	 */
	public static void addEntry(Entry ent)
	{
		try
		{
			WinebookDAO.addEntry(ent);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
		}
	}

	/**
	 * Add a wine to a Winebook entry
	 * 
	 * @param entryid
	 *            The id of the entry to add a wine to
	 * 
	 * @param w
	 *            The wine to add
	 */
	public static void addWine(long entryid, Wine w)
	{
		Entry ent = null;
		try
		{
			ent = WinebookDAO.getEntry(entryid);
			ent.addWine(w);
			WinebookDAO.editEntry(ent);
		} catch (DAException e)
		{
			// TODO
		}
	}

	/**
	 * Removes a wine from an entry
	 * 
	 * @param entryid
	 *            The ID of the entry to remove the wine from
	 * 
	 * @param w
	 *            The wine to remove from the entry
	 */
	public static void removeWine(long entryid, Wine w)
	{
		Entry ent = null;
		try
		{
			ent = WinebookDAO.getEntry(entryid);
			ent.removeWine(w);
			WinebookDAO.editEntry(ent);
		} catch (DAException e)
		{
			// TODO
		}
	}

	/**
	 * Updates an entry
	 * 
	 * @param id
	 *            The ID of the Entry to update
	 */
	public static void editEntry(long id)
	{
		Entry ent = null;
		try
		{
			ent = WinebookDAO.getEntry(id);
			WinebookDAO.editEntry(ent);
		} catch (DAException e)
		{
			// TODO
		}
	}

	/**
	 * Removes an entry from the database
	 * 
	 * @param id
	 *            The ID of the entry to remove
	 * 
	 * @return True if the remove operation was successful
	 */
	public static boolean removeEntry(long id)
	{
		return WinebookDAO.deleteEntry(id);
	}

	/**
	 * Adds a photo to an entry
	 * 
	 * @param id
	 *            The ID of the entry to add the photo to
	 * 
	 * @param p
	 *            The Photo to add to the entry
	 */
	public static void addPhoto(long id, Photo p)
	{
		Entry ent = null;
		try
		{
			ent = WinebookDAO.getEntry(id);
			ent.addPhoto(p);
			WinebookDAO.editEntry(ent);
		} catch (DAException e)
		{
			// TODO
		}
	}

	/**
	 * Removes a photo from an entry
	 * 
	 * @param id
	 *            The ID of the entry to remove the photo from
	 * 
	 * @param p
	 *            The photo to remove from the entry
	 */
	public static void removePhoto(long id, Photo p)
	{
		Entry ent = null;
		try
		{
			ent = WinebookDAO.getEntry(id);
			ent.removePhoto(p);
			WinebookDAO.editEntry(ent);
		} catch (DAException e)
		{
			// TODO
		}
	}

	/**
	 * Returns an entry by ID
	 * 
	 * @param id
	 *            The ID of the entry
	 * 
	 * @return The entry corresponding to the id
	 */
	public static Entry getEntry(long id)
	{
		Entry ent = null;
		try
		{
			ent = WinebookDAO.getEntry(id);
		} catch (DAException e)
		{
			// TODO
		}

		return ent;
	}
}
