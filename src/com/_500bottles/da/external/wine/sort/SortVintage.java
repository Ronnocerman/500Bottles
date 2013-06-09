package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

//sort by year
public class SortVintage extends Sort
{
	private final static String _sort = "vintage";

	public SortVintage() throws InvalidSort
	{
		super(_sort);
	}

	public SortVintage(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
