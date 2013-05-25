package com._500bottles.da.external.snooth;

import com._500bottles.da.external.ExternalDAO;
import com._500bottles.object.wine.Wine;

import java.util.Iterator;
import java.util.Vector;

public class SnoothDAO extends ExternalDAO
{
	public static WineSearchResponse doSearch(WineSearch search)
	{
		System.out.println(search.toString());

		String res = doGetRequest(search.toString());
		WineSearchResponse response = new WineSearchResponse(res);

		Iterator<SnoothWine> it = response.getWinesIterator();

		System.out.println(res);

		while (it.hasNext()) {
			SnoothWine w = it.next();
			System.out.println(w.toString());
		}

		return response;
	}

	public static WineDetailsResponse getWineDetails(WineDetails details)
	{
		String res = doGetRequest(details.toString());
		WineDetailsResponse response = new WineDetailsResponse(res);

		Iterator<SnoothWine> it = response.getWinesIterator();

		while (it.hasNext()) {
			SnoothWine w = it.next();
			System.out.println(w.toString());
		}

		return null;
	}
}
