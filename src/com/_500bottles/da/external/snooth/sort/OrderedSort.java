package com._500bottles.da.external.snooth.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class OrderedSort extends Sort
{
	protected static final String ASCENDING_SORT = "asc";
	protected static final String DESCENDING_SORT = "desc";
	protected static final String DEFAULT_SORT_OPTION = "asc";

	protected OrderedSort(String sort) {
		this.setSort(sort);
	}

	protected OrderedSort(String sort, String order) throws InvalidSort
	{
		this.setSort(sort);
		this.setOrder(order);
	}

	private void setOrder(String order) throws InvalidSort
	{
		if (order.equals(ASCENDING_SORT) ||
			order.equals(DESCENDING_SORT))
			sort += "+" + order;
		else if (order.equals("ascending"))
			sort += "+" + ASCENDING_SORT;
		else if (order.equals("descending"))
			sort += "+" + DESCENDING_SORT;
		else
			throw new InvalidSort();
	}
}
