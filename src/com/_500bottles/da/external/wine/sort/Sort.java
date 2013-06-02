package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/22/13 Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Sort
{
	protected String sort;
	protected static final String ASCENDING_SORT = "ascending";
	protected static final String DESCENDING_SORT = "descending";
	protected static final String DEFAULT_SORT_OPTION = "ascending";

	protected Sort(String s) throws InvalidSort
	{
		sort = s;
		this.setOrder(DEFAULT_SORT_OPTION);
	}

	protected Sort(String s, String o) throws InvalidSort
	{
		sort = s;

		if (o.equals(ASCENDING_SORT) || o.equals(DESCENDING_SORT))
			sort += "+" + o;
		else
			throw new InvalidSort();

	}

	protected Sort(String s, double min, double max) throws InvalidSort
	{
		if (min < 0 || max < min)
			throw new InvalidSort();
		else
		{
			sort = s;
			sort = sort + "(" + min + "|" + max + ")";
		}
	}

	protected void setSort(String sort)
	{
		this.sort = sort;
	}

	/**
	 * Returns the API argument string for product.
	 * 
	 * @return Color string.
	 */
	public String getString()
	{
		return sort;
	}

	public boolean equals(Sort s)
	{
		if (this.toString().equals(s.toString()))
			return true;

		return false;
	}

	private void setOrder(String order) throws InvalidSort
	{
		if (order.equals(ASCENDING_SORT) || order.equals(DESCENDING_SORT))
			sort += "+" + order;
		else if (order.equals("ascending"))
			sort += "+" + ASCENDING_SORT;
		else if (order.equals("descending"))
			sort += "+" + DESCENDING_SORT;
		else
			throw new InvalidSort();
	}
}
