package com._500bottles.da.external.wine.filter;

import com._500bottles.da.external.wine.Attribute;

public class FilterCategory extends Filter
{
	public FilterCategory(Attribute a)
	{
		_filter = "categories";
		_filter = _filter + "(" + a.getAttribute() + ")";
		System.out.println(_filter);
	}

	public void addAttribute(Attribute a)
	{
		if (_filter.indexOf('(') == -1)
			_filter = _filter + "(" + a.getAttribute() + ")";
		else
			_filter = _filter.substring(0, _filter.length() - 1) + "+"
					+ a.getAttribute() + ")";
		System.out.println(_filter);
	}
}
