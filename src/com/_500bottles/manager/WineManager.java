package com._500bottles.manager;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

import java.util.Vector;

public class WineManager
{
	public Wine getWine(long id)
	{
		Wine wine = null;

		try {
			wine = WineDAO.getWine(id);
		} catch (DAException e) {
			// TODO:
		}

		return wine;
	}

	public WineQueryResult searchWine(WineQuery query)
	{
		WineQueryResult result = null;

		// Search our database.

		searchSnooth(query);

		// Search wine.com database.

		// merge the results

		return result;
	}

	public void addCustomWine(Wine w)
	{
	}

	public void editCustomWine(Wine w)
	{
	}

	public void deleteCustomWine(Wine w)
	{
		try {
			WineDAO.deleteWine(w);
		} catch (DAException e) {
			// TODO:
		}
	}

	public void setRating(int rating)
	{
	}

	/*
	 * public void addTastingNotes(String notes) { }
	 * 
	 * public void editTastingNotes() { }
	 * 
	 * public void deleteTastingNotes() { }
	 */
	public void setFavorite(Wine w)
	{
	}

	public boolean isFavorite(long id)
	{
		return false;
	}

	/**
	 * Searches the Snooth API using SnoothDAO and the specified query.
	 * Returns a set of resulting wines.
	 * @param query
	 * @return
	 */
	private Vector<Wine> searchSnooth(WineQuery query)
	{
		Vector<Wine> v = null;



		return v;
	}

	/**
	 * Searches the Wine.com API using WineDAO and the specified query.
	 * Returns a set of resulting wines.
	 * @param query
	 * @return
	 */
	private Vector<Wine> searchWineCom(WineQuery query)
	{
		Vector<Wine> v = null;

		return v;
	}
}
