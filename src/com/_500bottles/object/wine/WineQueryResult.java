package com._500bottles.object.wine;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 */
public class WineQueryResult {

	/* The number of results returned from this query. */
	private int results;

	/* Vector of resulting wines. */
	private Vector<Wine> wines;

	public WineQueryResult(Vector<Wine> wines)
	{
		results = wines.size();
		this.wines = wines;
	}

	/**
	 * Returns number of wine results.
	 * @return
	 */
	public int getResultsCount()
	{
		return results;
	}

	/**
	 * Returns the vector of wines from this query.
	 * @return	Vector of resulting wines.
	 */
	public Vector<Wine> getWines()
	{
		return wines;
	}

	/**
	 * Returns an iterator for the resulting wines.
	 * @return	Resulting wines iterator.
	 */
	public Iterator<Wine> getIterator()
	{
		return wines.iterator();
	}
}
