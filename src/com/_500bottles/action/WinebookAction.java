package com._500bottles.action;

import com._500bottles.manager.WinebookManager;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.winebook.Photo;

public class WinebookAction
{
	WinebookManager m;

	public void addEntry(long id)
	{
		m.addEntry(id);
	}

	public void getEntry(long id)
	{
		m.getEntry(id);
	}

	public void removeEntry(long id)
	{
		m.removeEntry(id);
	}

	public void editContent(long id)
	{
		m.editEntry(id);
	}

	public void addWine(long id, Wine w)
	{
		m.addWine(id, w);
	}

	public void removeWine(long id, Wine w)
	{
		m.removeWine(id, w);
	}

	public void addPhoto(long id, Photo p)
	{
		m.addPhoto(id, p);
	}

	public void removePhoto(long id, Photo p)
	{
		m.removePhoto(id, p);
	}

	public void uploadPhoto(long id, Photo p)
	{
	}
}