package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

//sort by winery
public class SortWinery extends Sort
{
	private final static String _sort = "winery";

	public SortWinery() throws InvalidSort
	{
		super(_sort);
	}

	public SortWinery(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
