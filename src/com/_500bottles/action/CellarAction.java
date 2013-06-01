package com._500bottles.action;

import com._500bottles.exception.cellar.CellarException;
import com._500bottles.manager.CellarManager;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarAction
{

	public void setCellarQuantity(long userID, Wine w, int q)
			throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, w.getId());
		ci.setQuantity(q);
		CellarManager.editCellarItem(ci);
	}

	public void incCellarQuantity(long userID, Wine w) throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, w.getId());
		ci.setQuantity(ci.getQuantity() + 1);
		CellarManager.editCellarItem(ci);
	}

	public void decCellarQuantity(long userID, Wine w) throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, w.getId());
		ci.setQuantity(ci.getQuantity() - 1);
		CellarManager.editCellarItem(ci);
	}

	public void setCellarNotes(long userID, Wine w, String notes)
			throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, w.getId());
		ci.setNotes(notes);
		CellarManager.editCellarItem(ci);
	}

	public String getCellarNotes(long userID, Wine w) throws CellarException
	{
		return CellarManager.getByWineID(userID, w.getId()).getNotes();
	}

	public void clearCellarNotes(long userID, Wine w) throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, w.getId());
		ci.setNotes("");
		CellarManager.editCellarItem(ci);
	}
}
