package com._500bottles.manager;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import com._500bottles.da.external.snooth.*;
import com._500bottles.da.external.snooth.exception.InvalidWineDetails;
import org.json.simple.parser.ParseException;

import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.snooth.exception.InvalidWineSearch;
import com._500bottles.da.external.wine.AppellationArray;
import com._500bottles.da.external.wine.VarietalArray;
import com._500bottles.da.external.wine.VintageArray;
import com._500bottles.da.external.wine.WineAPICall;
import com._500bottles.da.external.wine.WineAPIURL;
import com._500bottles.da.external.wine.WineTypeArray;
import com._500bottles.da.external.wine.exception.InvalidCategory;
import com._500bottles.da.external.wine.exception.InvalidOtherParameters;
import com._500bottles.da.external.wine.filter.FilterCategory;
import com._500bottles.da.external.wine.otherParameters.Offset;
import com._500bottles.da.external.wine.otherParameters.Search;
import com._500bottles.da.external.wine.otherParameters.Size;
import com._500bottles.da.external.wine.sort.SortRating;
import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Appellation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;
import com._500bottles.object.wine.WineType;

/**
 * WineQueryManager is the glue between the various external API's an the local
 * database. It manages searching only the local database and/or the external
 * API's. When a wine is discovered using the external API's it is added to the
 * local database so that that particular wine doesn't need to be retrieved from
 * an API repeatedly.
 */
public class WineQueryManager
{

	/**
	 * Searches the local database first, then based on some criteria (TBD)
	 * searches the external API's if required. The wines retrieved from the
	 * external API's are then converted to local Wine objects and added to the
	 * database. These new local wine objects are then returned as part of the
	 * query.
	 * 
	 * @param query
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidOtherParameters
	 * @throws InvalidSort
	 * @throws InvalidCategory
	 * @throws DAException
	 */
	public static WineQueryResult search(WineQuery query)
	{
		// Perform the query on the local DB.

		// Adapt the query for external API's

		// Perform the query on Snooth.
		Vector<Wine> wines;

		try
		{
			wines = searchLocal(query);
			System.out.println(wines.size());
			if (wines.size() < 5)
			{
				wines = mergeExternalResults(searchSnooth(query),
						searchWineCom(query));
			}
			WineQueryResult result = new WineQueryResult(wines);

			return result;
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private static Vector<Wine> searchLocal(WineQuery query) throws DAException
	{

		return WineDAO.getWinesFromQuery(query);

	}

	/**
	 * Searches the Snooth API using SnoothDAO and the specified query. Returns
	 * a set of resulting wines.
	 * 
	 * @param query
	 * @return
	 */
	public static WineSearchResponse searchSnooth(WineQuery query)
	{
		WineSearchResponse res = null;

		try
		{
			// Convert the WineQuery to a Snooth WineSearch object.
			WineSearch s = new WineSearch(query);

			// Do the Snooth query and get the response object.
			res = SnoothDAO.doSearch(s);

		} catch (InvalidWineSearch e)
		{
			// TODO
		}

		// Return the vector of Wines.
		return res;
	}

	/**
	 * Searches the Wine.com API using WineDAO and the specified query. Returns
	 * a set of resulting wines.
	 * 
	 * @param query
	 * 
	 * @return
	 * @throws InvalidCategory
	 * @throws InvalidSort
	 * @throws InvalidOtherParameters
	 * @throws ParseException
	 * @throws IOException
	 */
	public static Vector<Wine> searchWineCom(WineQuery query)
	{
		try
		{
			Vector<Wine> v = null;

			WineAPIURL url = new WineAPIURL();
			FilterCategory filtercategory = new FilterCategory();
			String search = "";
			Vector<WineType> types = query.getType();

			int offset = query.getOffset();
			if (offset != 0)
			{
				Offset off = new Offset(offset);
				url.addToURL(off);
			}

			int size = query.getSize();
			Size siz = new Size(size);
			url.addToURL(siz);

			if (query.getTextQuery() != "")
				search += query.getTextQuery();
			if (query.getNameContains() != "")
				search += query.getNameContains();
			if (query.getDescriptionContains() != "")
				search += query.getDescriptionContains();

			Vector<Vineyard> vineyards = query.getVineyard();
			for (int i = 0; i < vineyards.size(); i++)
				search += vineyards.elementAt(i) + " ";

			if (search != "")
			{
				Search sea = new Search(search);
				url.addToURL(sea);
			}

			if (types.size() == 0)
			{
				WineTypeArray temp = new WineTypeArray("all");
				filtercategory.addAttribute(temp);
			}

			for (int i = 0; i < types.size(); i++)
			{
				System.out.println(types.elementAt(i).getWineType());
				WineTypeArray temp = new WineTypeArray(types.elementAt(i)
						.getWineType());
				filtercategory.addAttribute(temp);
			}

			Vector<Varietal> varietals = query.getVarietal();
			for (int i = 0; i < varietals.size(); i++)
			{
				VarietalArray temp = new VarietalArray(varietals.elementAt(i)
						.getGrapeType());
				filtercategory.addAttribute(temp);
			}

			long min_year = query.getMinYear();
			long max_year = query.getMaxYear();
			if (min_year != -1 && max_year != -1)
				for (int i = (int) min_year; i < max_year - min_year; i++)
				{
					VintageArray temp = new VintageArray("" + i);
					filtercategory.addAttribute(temp);
				}

			double max_rating = query.getMaxRating();
			double min_rating = query.getMinRating();
			if (min_rating != -1 && max_rating != -1)
			{
				SortRating temp = new SortRating(min_rating, max_rating);
				url.addToURL(temp);
			}

			Vector<Appellation> appellations = query.getAppellation();
			for (int i = 0; i < appellations.size(); i++)
			{
				AppellationArray temp = new AppellationArray(appellations
						.elementAt(i).getLocation());
				filtercategory.addAttribute(temp);
			}

			url.addToURL(filtercategory);
			WineAPICall call = new WineAPICall();
			v = call.getProducts(url.getString());
			return v;
		} catch (InvalidCategory e)
		{
			e.printStackTrace();
		} catch (InvalidSort e)
		{
			e.printStackTrace();
		} catch (InvalidOtherParameters e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Merges the wines resulting from a Snooth search into the database.
	 * 
	 * @param response
	 *            Response from Snooth wine search.
	 * @return Returns the wines added to the database as a result of the merge.
	 * @throws DAException
	 */
	private static Vector<Wine> mergeExternalResults(
			WineSearchResponse response, Vector<Wine> wineComWines)
	{
		Vector<Wine> wines = new Vector<Wine>();
		Iterator<SnoothWine> it = response.getWinesIterator();

		SnoothWine snoothWine;

		// Iterate through each SnoothWine
		while (it.hasNext())
		{

			snoothWine = it.next();

			// Find out if the Snooth wine exists in the database.
			// If not, then get the details and add it to the database.
			Wine w = WineManager.getWineBySnoothId(snoothWine.getCode());

			if (w == null)
				w = addWineToDatabase(snoothWine);

			boolean match = false;
			for (int i = 0; i < wineComWines.size(); i++)
			{
				if (snoothWine.getName() == wineComWines.elementAt(i).getName())// TODO
				{ /*
				 * && Long.parseLong(snoothWine.getVintage()) == wineComWines
				 * .elementAt(i).getYear())
				 */
					match = true;
					System.out
							.println("Found a duplicate wine from snooth and winecom");
				}
			}
			if (match == false)
				wines.add(snoothWine.toWineObject());
		}

		for (int i = 0; i < wineComWines.size(); i++)
		{
			boolean match2 = false;
			for (int j = 0; j < wines.size(); j++)
				if (wineComWines.elementAt(i).getName() == wines.elementAt(j)
						.getName())// TODO
				{
					/*
					 * && wineComWines.elementAt(i).getYear() == wines
					 * .elementAt(j).getYear())
					 */System.out
							.println("Found a duplicate wine from snooth and winecom");
					match2 = true;
				}
			if (match2 == false)
				wines.add(wineComWines.elementAt(i));
		}

		for (int i = 0; i < wines.size(); i++)
		{
			Wine w = WineManager.getWineBySnoothId(wines.elementAt(i)
					.getSnoothId());
			Wine v = WineManager.getWineByWineComId(wines.elementAt(i)
					.getWinecomId());
			if (w == null && v == null)
				addWineToDatabase(wines.elementAt(i));
		}

		return wines;
	}

	/**
	 * Adds a snooth wine to the database. It first retrieves all the wine
	 * details from Snooth, then adds the specified wine to the DB. It returns
	 * the Wine object added to the database.
	 *
	 * @param wine The SnoothWine to add to the database.
	 * @return The corresponding Wine object.
	 * @throws DAException
	 */
	private static Wine addWineToDatabase(Wine wine)
	{
		try
		{
			WineDAO.addWine(wine);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { WineDetails details = new WineDetails(snoothWine.getCode());
		 * WineDetailsResponse r = SnoothDAO.getWineDetails(details);
		 *
		 * Iterator<SnoothWine> it = r.getWinesIterator();
		 *
		 * while (it.hasNext()) { wine = it.next().toWineObject();
		 * WineDAO.addWine(wine); }
		 *
		 * } catch (InvalidWineDetails e) { // TODO; } catch (Exception e) { //
		 * TODO: }
		 */

		return wine;
	}

	/**
	 * Adds a snooth wine to the database. It first retrieves all the wine
	 * details from Snooth, then adds the specified wine to the DB. It returns
	 * the Wine object added to the database.
	 *
	 * @param snoothWine 	The SnoothWine to add to the database.
	 * @return 		The corresponding Wine object.
	 * @throws DAException
	 */
	private static Wine addWineToDatabase(SnoothWine snoothWine)
	{
		Wine wine = null;

		try { WineDetails details = new WineDetails(snoothWine.getCode());
			WineDetailsResponse r = SnoothDAO.getWineDetails(details);

			Iterator<SnoothWine> it = r.getWinesIterator();

			while (it.hasNext()) {
				wine = it.next().toWineObject();
				WineDAO.addWine(wine);
			}

		} catch (InvalidWineDetails | DAException e) {

		}

		return wine;
	}

}
