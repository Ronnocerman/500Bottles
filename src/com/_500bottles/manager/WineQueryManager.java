package com._500bottles.manager;

import com._500bottles.da.external.snooth.*;
import com._500bottles.da.external.snooth.exception.InvalidWineDetails;
import com._500bottles.da.external.snooth.exception.InvalidWineSearch;
import com._500bottles.da.internal.WineDAO;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

import java.util.Iterator;
import java.util.Vector;

/**
 * WineQueryManager is the glue between the various external API's an the local
 * database. It manages searching only the local database and/or the external
 * API's. When a wine is discovered using the external API's it is added to the
 * local database so that that particular wine doesn't need to be retrieved
 * from an API repeatedly.
 */
class WineQueryManager {

	/**
	 * Searches the local database first, then based on some criteria (TBD)
	 * searches the external API's if required. The wines retrieved from the
	 * external API's are then converted to local Wine objects and added
	 * to the database. These new local wine objects are then returned as
	 * part of the query.
	 * @param query
	 * @return
	 */
	public static WineQueryResult search(WineQuery query)
	{
		// Perform the query on the local DB.

		// Adapt the query for external API's

		// Perform the query on Snooth.
		Vector<Wine> winesAddedBySnooth;

		winesAddedBySnooth = mergeExternalResults(searchSnooth(query));

		WineQueryResult result = new WineQueryResult(winesAddedBySnooth);

		return result;
	}

	/**
	 * Searches the Snooth API using SnoothDAO and the specified query.
	 * Returns a set of resulting wines.
	 * @param query
	 * @return
	 */
	private static WineSearchResponse searchSnooth(WineQuery query)
	{
		WineSearchResponse res = null;

		try {
			// Convert the WineQuery to a Snooth WineSearch object.
			WineSearch s = new WineSearch(query);

			// Do the Snooth query and get the response object.
			res = SnoothDAO.doSearch(s);

		} catch (InvalidWineSearch e) {
			// TODO
		}

		// Return the vector of Wines.
		return res;
	}

	/**
	 * Searches the Wine.com API using WineDAO and the specified query.
	 * Returns a set of resulting wines.
	 * @param query
	 * @return
	 */
	private static Vector<Wine> searchWineCom(WineQuery query)
	{
		Vector<Wine> v = null;

		return v;
	}


	/**
	 * Merges the wines resulting from a Snooth search into the database.
	 * @param response	Response from Snooth wine search.
	 * @return		Returns the wines added to the database as a
	 * 			result of the merge.
	 */
	private static Vector<Wine> mergeExternalResults
		(WineSearchResponse response)
	{
		Vector<Wine> wines = new Vector<Wine>();
		Iterator<SnoothWine> it = response.getWinesIterator();

		SnoothWine snoothWine;

		// Iterate through each SnoothWine
		while (it.hasNext()) {

			snoothWine = it.next();

			// Find out if the Snooth wine exists in the database.
			// If not, then get the details and add it to the database.
			Wine w = WineManager.getWineBySnoothId(snoothWine.getCode());
			if (w == null) {
				w = addSnoothWineToDatabase(snoothWine);
			}

			wines.add(w);
		}

		return wines;
	}

	/**
	 * Adds a snooth wine to the database. It first retrieves all the
	 * wine details from Snooth, then adds the specified wine to the DB.
	 * It returns the Wine object added to the database.
	 * @param snoothWine	The SnoothWine to add to the database.
	 * @return		The corresponding Wine object.
	 */
	private static Wine addSnoothWineToDatabase(SnoothWine snoothWine)
	{
		Wine wine = null;

		try {
			WineDetails details = new WineDetails(snoothWine.getCode());
			WineDetailsResponse r = SnoothDAO.getWineDetails(details);

			Iterator<SnoothWine> it = r.getWinesIterator();

			while (it.hasNext()) {
				wine = it.next().toWineObject();
				WineDAO.addWine(wine);
			}

		} catch (InvalidWineDetails e) {
			// TODO;
		} catch (Exception e) {
			// TODO:
		}

		return wine;
	}

}
