package com._500bottles.manager;

import com._500bottles.object.cellar.Cellar;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarManager
{
	private Cellar c;

	public CellarManager()
	{
		// Construct based on logged in user
	}

	public void addWine(Wine w)
	{
		CellarItem ci = new CellarItem(w);
		c.add(ci);
	}

	public boolean removeCellarItem(CellarItem ci)
	{
		return c.remove(ci);
	}

	public void editCellarItem(CellarItem ci)
	{
		CellarItem x = c.getById(ci.getCellarItemId());
		if (!(x.getNotes().equals(ci.getNotes())))
			x.setNotes(ci.getNotes());
		if (!(x.getQuantity() == ci.getQuantity()))
			x.setQuantity(ci.getQuantity());
	}

	public CellarItem getCellarItem(long id)
	{
		return c.getById(id);
	}

	public Cellar getCellar()
	{
		return c;
	}
}
