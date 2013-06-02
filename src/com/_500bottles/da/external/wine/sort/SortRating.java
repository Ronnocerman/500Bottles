package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

public class SortRating extends Sort
{
	private final static String _sort = "rating";

	public SortRating() throws InvalidSort
	{
		super(_sort);
	}

	public SortRating(double min, double max) throws InvalidSort
	{
		super(_sort, min, max);
	}

	public SortRating(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
