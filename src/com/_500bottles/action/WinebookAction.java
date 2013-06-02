package com._500bottles.action;

import com._500bottles.manager.WinebookManager;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.winebook.Photo;

public class WinebookAction
{

	public void addEntry(long id)
	{
		WinebookManager.addEntry(id);
	}

	public void getEntry(long id)
	{
		WinebookManager.getEntry(id);
	}

	public void removeEntry(long id)
	{
		WinebookManager.removeEntry(id);
	}

	public void editContent(long id)
	{
		WinebookManager.editEntry(id);
	}

	public void addWine(long id, Wine w)
	{
		WinebookManager.addWine(id, w);
	}

	public void removeWine(long id, Wine w)
	{
		WinebookManager.removeWine(id, w);
	}

	public void addPhoto(long id, Photo p)
	{
		WinebookManager.addPhoto(id, p);
	}

	public void removePhoto(long id, Photo p)
	{
		WinebookManager.removePhoto(id, p);
	}

	public void uploadPhoto(long id, Photo p)
	{
	}
}