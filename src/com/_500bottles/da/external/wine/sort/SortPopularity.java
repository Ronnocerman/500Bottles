package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

//sort by popularity
public class SortPopularity extends Sort
{
	private final static String _sort = "popularity";

	public SortPopularity() throws InvalidSort
	{
		super(_sort);
	}

	public SortPopularity(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
