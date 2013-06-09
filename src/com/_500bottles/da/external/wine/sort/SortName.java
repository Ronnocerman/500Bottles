package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

//sort by name
public class SortName extends Sort
{
	private final static String _sort = "name";

	public SortName() throws InvalidSort
	{
		super(_sort);
	}

	public SortName(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
