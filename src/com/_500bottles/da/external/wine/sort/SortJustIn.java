package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

//sort option to sort by Just In, which means newest arrivals of wine in the wine.com store
public class SortJustIn extends Sort
{
	private final static String _sort = "justIn";

	public SortJustIn() throws InvalidSort
	{
		super(_sort);
	}

	public SortJustIn(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
