package com._500bottles.da.external.snooth;

import com._500bottles.da.external.ExternalDAO;
import com._500bottles.object.wine.Wine;

import java.util.Iterator;
import java.util.Vector;

public class SnoothDAO extends ExternalDAO
{
	public static WineSearchResponse doSearch(WineSearch search)
	{
		String res = doGetRequest(search.toString());
		WineSearchResponse response = new WineSearchResponse(res);

		return response;
	}

	public static WineDetailsResponse getWineDetails(WineDetails details)
	{
		String res = doGetRequest(details.toString());
		WineDetailsResponse response = new WineDetailsResponse(res);

		return response;
	}
}
