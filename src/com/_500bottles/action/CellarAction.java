package com._500bottles.action;

import com._500bottles.manager.CellarManager;
import com._500bottles.object.wine.Wine;

public class CellarAction
{

	public void setCellarQuantity(Wine w, int q)
	{
		CellarManager.getByWineId(w.getId()).setQuantity(q);
	}

	public void incCellarQuantity(Wine w)
	{
		CellarManager.getByWineId(w.getId()).incrementQuantity();
	}

	public void decCellarQuantity(Wine w)
	{
		CellarManager.getByWineId(w.getId()).decrementQuantity();
	}

	public void setCellarNotes(Wine w, String notes)
	{
		CellarManager.getByWineId(w.getId()).setNotes(notes);
	}

	public String getCellarNotes(Wine w)
	{
		return CellarManager.getByWineId(w.getId()).getNotes();
	}

	public void clearCellarNotes(Wine w)
	{
		CellarManager.getByWineId(w.getId()).setNotes("");
	}
}
