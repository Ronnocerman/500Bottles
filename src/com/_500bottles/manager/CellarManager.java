package com._500bottles.manager;

import com._500bottles.object.cellar.Cellar;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarManager
{
	private static Cellar c;

	static
	{
		// Initialize CellarManager
	}

	public static void addWine(Wine w)
	{
		CellarItem ci = new CellarItem(w);
		c.add(ci);
	}

	public static boolean removeCellarItem(CellarItem ci)
	{
		return c.remove(ci);
	}

	public static void editCellarItem(CellarItem ci)
	{
		CellarItem x = c.getById(ci.getId());
		if (!(x.getNotes().equals(ci.getNotes())))
			x.setNotes(ci.getNotes());
		if (!(x.getQuantity() == ci.getQuantity()))
			x.setQuantity(ci.getQuantity());
	}

	public static CellarItem getCellarItem(long id)
	{
		return c.getById(id);
	}

	public static CellarItem getByWineId(long id)
	{
		return c.getByWineId(id);
	}

	public static Cellar getCellar()
	{
		return c;
	}
}
