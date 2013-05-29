package com._500bottles.da.external.wine.sort;

import com._500bottles.da.external.snooth.exception.InvalidSort;

public class SortPrice extends Sort
{
	private final static String _sort = "price";

	public SortPrice() throws InvalidSort
	{
		super(_sort);
	}

	public SortPrice(String option) throws InvalidSort
	{
		super(_sort, option);
	}
}
