package com._500bottles.da.external.wine.filter;

//builds the filter product part of the url
public class FilterProduct extends Filter
{
	public FilterProduct(int productId)
	{
		_filter = "product";
		_filter = _filter + "(" + Integer.toString(productId) + ")";
	}

	public void addProductId(int productId)
	{
		_filter = "product";
		if (_filter.indexOf('(') == -1)
			_filter = _filter + "(" + Integer.toString(productId) + ")";
		else
			_filter.replaceFirst(")", "+" + Integer.toString(productId) + ")");
	}
}
