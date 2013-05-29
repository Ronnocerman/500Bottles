package com._500bottles.da.external.wine.filter;

import com._500bottles.da.external.wine.exception.InvalidFilter;

public class FilterPrice extends Filter
{
	public FilterPrice(double min, double max) throws InvalidFilter
	{
		_filter = "price";
		if (min < 0 || max < 0 || max < min)
			throw new InvalidFilter("Invalid Price");

		_filter = _filter + "(" + Double.toString(min) + "|"
				+ Double.toString(max) + ")";
	}

	public FilterPrice(double price) throws InvalidFilter
	{
		_filter = "price";
		if (price < 0)
			throw new InvalidFilter("Invalid Price");
		_filter = _filter + "(" + Double.toString(price) + ")";
	}
}
