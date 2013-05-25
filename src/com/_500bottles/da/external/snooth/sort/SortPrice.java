package com._500bottles.da.external.snooth.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SortPrice extends OrderedSort
{
	private final static String _sort = "price";

	public SortPrice()
	{
		super(_sort);
	}

	public SortPrice(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
