package com._500bottles.da.external.snooth;

import com._500bottles.da.external.ExternalDAO;

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
		System.out.println(details.toString());
		WineDetailsResponse response = new WineDetailsResponse(res);

		return response;
	}
}
