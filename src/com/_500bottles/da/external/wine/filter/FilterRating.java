package com._500bottles.da.external.wine.filter;

import com._500bottles.da.external.wine.exception.InvalidFilter;

//builds the filter rating part of the url for wine.com
public class FilterRating extends Filter
{
	public FilterRating(int min, int max) throws InvalidFilter
	{
		_filter = "rating";
		if (min < 0 || min > 100 || max < 0 || max > 100 || max < min)
			throw new InvalidFilter("Invalid Rating");

		_filter = _filter + "(" + Integer.toString(min) + "|"
				+ Integer.toString(max) + ")";
	}

	public FilterRating(int rating) throws InvalidFilter
	{
		_filter = "rating";
		if (rating < 0 || rating > 100)
			throw new InvalidFilter("Invalid Rating");
		_filter = _filter + "(" + Integer.toString(rating) + ")";
	}
}
