package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

public class SortSaving extends Sort
{
	private final static String _sort = "saving";

	public SortSaving() throws InvalidSort
	{
		super(_sort);
	}

	public SortSaving(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
