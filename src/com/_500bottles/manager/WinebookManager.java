package com._500bottles.manager;

import com._500bottles.da.internal.WinebookDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.winebook.Entry;

public class WinebookManager
{
	public void addEntry(long id)
	{
		Entry ent = null;
		try {
			ent = WinebookDAO.getEntry(id);
			WinebookDAO.addEntry(ent);
<<<<<<< HEAD
		} catch (DAException e)
		{
=======
		} catch (DAException e) {
>>>>>>> refs/remotes/origin/master
			// TODO
		}
	}

	public void editEntry(long id)
	{
		Entry ent = null;
		try {
			ent = WinebookDAO.getEntry(id);
			WinebookDAO.editEntry(ent);
<<<<<<< HEAD
		} catch (DAException e)
		{
=======
		} catch (DAException e) {
>>>>>>> refs/remotes/origin/master
			// TODO
		}
	}

	public void removeEntry(long id)
	{
		Entry ent = null;
		try {
			ent = WinebookDAO.getEntry(id);
			WinebookDAO.deleteEntry(ent);
<<<<<<< HEAD
		} catch (DAException e)
		{
=======
		} catch (DAException e) {
>>>>>>> refs/remotes/origin/master
			// TODO
		}
	}

	public Entry getEntry(long id)
	{
		Entry ent = null;
		try {
			ent = WinebookDAO.getEntry(id);
		} catch (DAException e) {
			// TODO
		}

		return ent;
	}
}
