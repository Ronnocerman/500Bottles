package com._500bottles.da.external.wine;

import com._500bottles.config.Config;
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

//this class builds a URL to access Wine.com API
public class WineAPIURL
{
	// set defaults
	private final static String DEFAULT_APIFORMAT = "json";
	private final static String DEFAULT_RESOURCE = "catalog?";
	private final static String DEFAULT_FILTER = "filter=categories(490)";
	private final static String API_KEY = Config.getProperty("wineAPIKey");

	private static String apiformat = "";
	private static String resource = "";
	private static String sort = "";

	private static String filterCategory = "";
	private static String filterRating = "";
	private static String filterPrice = "";

	private static String otherInStock = "";
	private static String otherOffset = "";
	private static String otherSearch = "";
	private static String otherSize = "";
	private static String otherState = "";

	private static String url = "http://services.wine.com/api/beta/service.svc/";

	public WineAPIURL()
	{
	}

	// sets the base options that are required
	public WineAPIURL(String xmlOrJson, String categoryOrCatalog)
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

	// additional parameters can be added to narrow down search
	public void addToURL(Sort s)
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

	public void addToURL(Search o)
	{
		otherSearch = o.getString();
	}

	public void addToURL(FilterCategory f)
	{
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

	// get the url string
	public String getString()
	{
		String temp = url;
		if (apiformat == "")
			temp = temp + DEFAULT_APIFORMAT + "/";
		else
			temp = temp + apiformat + "/";

		if (resource == "")
			temp += DEFAULT_RESOURCE;
		else
			temp += resource;

		if (filterCategory == "" && filterRating == "" && filterPrice == "")
			temp += DEFAULT_FILTER;
		else
		{
			temp += "filter=";
			if (filterCategory != "")
				temp += filterCategory;
			if (filterRating != "")
			{
				if (filterCategory == "")
					temp += filterRating;
				else
					temp = temp + "+" + filterRating;
			}
			if (filterPrice != "")
			{
				if (filterCategory == "" && filterRating == "")
					temp += filterPrice;
				else
					temp = temp + "+" + filterPrice;
			}
		}
		if (otherOffset != "")
			temp = temp + "&" + otherOffset;
		if (otherInStock != "")
			temp = temp + "&" + otherInStock;
		if (otherSize != "")
			temp = temp + "&" + otherSize;
		if (otherState != "")
			temp = temp + "&" + otherState;
		if (otherSearch != "" && otherSearch != null)
			temp = temp + "&" + otherSearch;
		if (sort != "")
			temp = temp + "&" + "SortBy=" + sort;

		temp = temp + "&apikey=" + API_KEY;

		return temp;

	}

}
