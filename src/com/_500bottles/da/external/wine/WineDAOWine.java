package com._500bottles.da.external.wine;

import com._500bottles.da.external.wine.exception.InvalidSetting;
import com._500bottles.da.external.wine.filter.FilterCategory;
import com._500bottles.da.external.wine.filter.FilterPrice;
import com._500bottles.da.external.wine.filter.FilterRating;
import com._500bottles.da.external.wine.otherParameters.InStock;
import com._500bottles.da.external.wine.otherParameters.Offset;
import com._500bottles.da.external.wine.otherParameters.Search;
import com._500bottles.da.external.wine.otherParameters.Size;
import com._500bottles.da.external.wine.otherParameters.State;
import com._500bottles.da.external.wine.sort.Sort;

public class WineDAOWine
{
	private final static String DEFAULT_APIFORMAT = "json";
	private final static String DEFAULT_RESOURCE = "catalog?";
	private final static String DEFAULT_FILTER = "filter=categories(490)";
	private final static String API_KEY = "3ceacf1cf1a36a7e799d5f1460fc7521";// Config.getProperty("wineAPIKey");

	private static String apiformat = "";
	private static String resource = "";
	private static String sort = "";

	private static String filterCategory = "";
	private static String filterRating = "";
	private static String filterPrice = "";
	private static String filterProductId = "";

	private static String otherInStock = "";
	private static String otherOffset = "";
	private static String otherSearch = "";
	private static String otherSize = "";
	private static String otherState = "";

	private static String url = "http://services.wine.com/api/beta/service.svc/";

	public WineDAOWine()
	{
	}

	public WineDAOWine(String xmlOrJson, String categoryOrCatalog)
			throws InvalidSetting
	{
		if (xmlOrJson.toLowerCase() != "xml"
				&& xmlOrJson.toLowerCase() != "json")
			throw new InvalidSetting("Invalid API Format");
		else
			apiformat = xmlOrJson;

		if (categoryOrCatalog.toLowerCase() != "category"
				&& categoryOrCatalog.toLowerCase() != "catalog")
			throw new InvalidSetting("Invalid Resource");
		else if (categoryOrCatalog.toLowerCase() == "category")
			resource = "categorymap?";
		else
			resource = "catalog?";

	}

	public void addToUrl(Sort s)
	{
		sort = s.getString();
	}

	public void addToURL(Offset o)
	{
		otherOffset = o.getString();
	}

	public void addToURL(Size o)
	{
		otherSize = o.getString();
	}

	public void addToURL(State o)
	{
		otherState = o.getString();
	}

	public void addToURL(InStock o)
	{
		otherInStock = o.getString();
	}

	public void addToUrl(Search o)
	{
		otherSearch = o.getString();
	}

	public void addToURL(FilterCategory f)
	{
		System.out.println(f.getString());
		filterCategory = f.getString();
	}

	public void addToURL(FilterRating f)
	{
		filterRating = f.getString();
	}

	public void addToURL(FilterPrice f)
	{
		filterPrice = f.getString();
	}

	public String getString()
	{
		if (apiformat == "")
			url += DEFAULT_APIFORMAT;
		else
			url = url + apiformat + "/";

		if (resource == "")
			url += DEFAULT_RESOURCE;
		else
			url += resource;

		if (filterCategory == "" && filterRating == "" && filterPrice == "")
			url += DEFAULT_FILTER;
		else
		{
			url += "filter=";
			if (filterCategory != "")
				url += filterCategory;
			if (filterRating != "")
			{
				if (filterCategory == "")
					url += filterRating;
				else
					url = url + "+" + filterRating;
			}
			if (filterPrice != "")
			{
				if (filterCategory == "" && filterRating == "")
					url += filterPrice;
				else
					url = url + "+" + filterPrice;
			}
		}
		if (otherOffset != "")
			url = url + "&" + otherOffset;
		if (otherInStock != "")
			url = url + "&" + otherInStock;
		if (otherSize != "")
			url = url + "&" + otherSize;
		if (otherState != "")
			url = url + "&" + otherState;
		if (otherSearch != "")
			url = url + "&" + otherSearch;
		if (sort != "")
			url += sort;

		url = url + "&apikey=" + API_KEY;
		return url;

	}
}
