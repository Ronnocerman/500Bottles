package com._500bottles.manager;

import com._500bottles.da.internal.WinebookDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.winebook.Entry;
import com._500bottles.object.winebook.Photo;

public class WinebookManager
{
	public static void addEntry(long id)
	{
		Entry ent = null;
		try
		{
			ent = WinebookDAO.getEntry(id);
			WinebookDAO.addEntry(ent);
		} catch (DAException e)
		{
			// TODO
		}
	}

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

	public static boolean removeEntry(long id)
	{
		return WinebookDAO.deleteEntry(id);
	}

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
