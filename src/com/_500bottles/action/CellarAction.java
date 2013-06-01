package com._500bottles.action;

import com._500bottles.exception.cellar.CellarException;
import com._500bottles.manager.CellarManager;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarAction
{

	public void setCellarQuantity(long userID, Wine w, int q)
	{
		try
		{
			CellarItem ci = CellarManager.getByWineID(userID, w.getId());
			ci.setQuantity(q);
			CellarManager.editCellarItem(ci);
		} catch (CellarException e)
		{
			// TODO Cellar doesn't exist
			e.printStackTrace();
		}
	}

	public void incCellarQuantity(long userID, Wine w)
	{
		try
		{
			CellarItem ci = CellarManager.getByWineID(userID, w.getId());
			ci.setQuantity(ci.getQuantity() + 1);
			CellarManager.editCellarItem(ci);
		} catch (CellarException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void decCellarQuantity(long userID, Wine w)
	{
		try
		{
			CellarItem ci = CellarManager.getByWineID(userID, w.getId());
			ci.setQuantity(ci.getQuantity() - 1);
			CellarManager.editCellarItem(ci);
		} catch (CellarException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCellarNotes(long userID, Wine w, String notes)
	{
		try
		{
			CellarItem ci = CellarManager.getByWineID(userID, w.getId());
			ci.setNotes(notes);
			CellarManager.editCellarItem(ci);
		} catch (CellarException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCellarNotes(long userID, Wine w) throws CellarException
	{
		return CellarManager.getByWineID(userID, w.getId()).getNotes();
	}

	public void clearCellarNotes(long userID, Wine w)
	{
		try
		{
			CellarItem ci = CellarManager.getByWineID(userID, w.getId());
			ci.setNotes("");
			CellarManager.editCellarItem(ci);
		} catch (CellarException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
