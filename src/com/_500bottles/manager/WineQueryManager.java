package com._500bottles.manager;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.json.simple.parser.ParseException;

import com._500bottles.da.external.snooth.SnoothDAO;
import com._500bottles.da.external.snooth.SnoothWine;
import com._500bottles.da.external.snooth.WineDetails;
import com._500bottles.da.external.snooth.WineDetailsResponse;
import com._500bottles.da.external.snooth.WineSearch;
import com._500bottles.da.external.snooth.WineSearchResponse;
import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.snooth.exception.InvalidWineDetails;
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
		Vector<Wine> wines = null;
		WineQueryResult result = null;

		try
		{
			wines = searchLocal(query);
			System.out.println("level 1");
			if (wines.size() < query.getSize())
			{
				searchSnooth(query);
				System.out.println("level 2");
				searchWineCom(query);
				System.out.println("level 3");
				wines = searchLocal(query);
				System.out.println("level 4");
			}
			System.out.println("wine result is: " + wines.size());
			result = new WineQueryResult(wines);

		} catch (DAException e)
		{
			System.err.println("WineQueryManager: DAException in search()");
		}
		System.out.println("wines.size() " + wines.size());
		return result;
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
	public static void searchSnooth(WineQuery query)
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

		processSnoothResponse(res);
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
	public static void searchWineCom(WineQuery query)
	{
		Vector<Wine> v = null;

		WineAPIURL url = new WineAPIURL();
		FilterCategory filtercategory = new FilterCategory();
		String search = "";
		Vector<WineType> types = query.getType();

		try
		{
			int offset = query.getOffset();
			if (offset != 0)
			{
				Offset off = new Offset(offset);
				url.addToURL(off);
			}
		} catch (InvalidOtherParameters e)
		{
			System.err
					.println("Winequerymanager: SearchWineCom: Invalid Offset");
		}

		try
		{
			int size = query.getSize();
			Size siz = new Size(size);
			url.addToURL(siz);
		} catch (InvalidOtherParameters e)
		{
			System.err.println("Winequerymanager: SearchWineCom: Invalid Size");
		}

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

		try
		{
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
		} catch (InvalidCategory e)
		{
			System.err
					.println("Winequerymanager: SearchWineCom: Invalid WineType");
		}

		try
		{
			Vector<Varietal> varietals = query.getVarietal();
			for (int i = 0; i < varietals.size(); i++)
			{
				VarietalArray temp = new VarietalArray(varietals.elementAt(i)
						.getGrapeType());
				filtercategory.addAttribute(temp);
			}
		} catch (InvalidCategory e)
		{
			System.err
					.println("Winequerymanager: SearchWineCom: Invalid Varietal");
		}

		try
		{
			long min_year = query.getMinYear();
			long max_year = query.getMaxYear();
			if (min_year != -1 && max_year != -1)
				for (int i = (int) min_year; i < max_year - min_year; i++)
				{
					VintageArray temp = new VintageArray("" + i);
					filtercategory.addAttribute(temp);
				}
		} catch (InvalidCategory e)
		{
			System.err
					.println("Winequerymanager: SearchWineCom: Invalid Vintage");
		}

		try
		{
			double max_rating = query.getMaxRating();
			double min_rating = query.getMinRating();
			if (min_rating != -1 && max_rating != -1)
			{
				SortRating temp = new SortRating(min_rating, max_rating);
				url.addToURL(temp);
			}
		} catch (InvalidSort e)
		{
			System.err
					.println("Winequerymanager: SearchWineCom: Invalid Rating");
		}

		try
		{
			Vector<Appellation> appellations = query.getAppellation();
			for (int i = 0; i < appellations.size(); i++)
			{
				AppellationArray temp = new AppellationArray(appellations
						.elementAt(i).getLocation());
				filtercategory.addAttribute(temp);
			}
		} catch (InvalidCategory e)
		{
			System.err
					.println("Winequerymanager: SearchWineCom: Invalid Appellation");
		}

		url.addToURL(filtercategory);
		WineAPICall call = new WineAPICall();
		System.out.println(url.getString());
		try
		{
			v = call.getProducts(url.getString());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.err
					.println("Winequerymanager: SearchWineCom: Failed API call");
		}

		processesWineComWine(v);
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
		// Vector of wines to populate and return.
		Vector<Wine> wines = new Vector<Wine>();

		// Vector of resultant snooth wines.
		Vector<SnoothWine> snoothWines = response.getWines();

		// Getting the details for each Snooth Wine.
		WineDetails d;
		try
		{
			for (int i = 0; i < snoothWines.size(); i++)
			{
				d = new WineDetails(snoothWines.elementAt(i).getCode());
				wines.add(SnoothDAO.getWineDetails(d).getWines().elementAt(0)
						.toWineObject());
			}
		} catch (InvalidWineDetails e)
		{
			System.err
					.println("WineQueryManager: InvalidWineDetails Exception");
		}

		// Adding all wine.com results to the return.
		for (int j = 0; j < wineComWines.size(); j++)
			wines.add(wineComWines.elementAt(j));

		return wines;
	}

	/**
	 * Processes query results from Snooth API.
	 * 
	 * @param response
	 */
	private static void processSnoothResponse(WineSearchResponse response)
	{
		Iterator<SnoothWine> it = response.getWinesIterator();

		while (it.hasNext())
			addWineToDatabase(it.next());
	}

	/**
	 * Processes query results from Wine.com API.
	 * 
	 * @param wines
	 */
	private static void processesWineComWine(Vector<Wine> wines)
	{
		for (int i = 0; i < wines.size(); i++)
			addWineToDatabase(wines.elementAt(i));
	}

	/**
	 * Adds a snooth wine to the database. It first retrieves all the wine
	 * details from Snooth, then adds the specified wine to the DB. It returns
	 * the Wine object added to the database.
	 * 
	 * @param wine
	 *            The SnoothWine to add to the database.
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
	 * @param snoothWine
	 *            The SnoothWine to add to the database.
	 * @return The corresponding Wine object.
	 * @throws DAException
	 */
	private static Wine addWineToDatabase(SnoothWine snoothWine)
	{
		Wine wine = null;

		try
		{
			WineDetails details = new WineDetails(snoothWine.getCode());
			WineDetailsResponse r = SnoothDAO.getWineDetails(details);

			Iterator<SnoothWine> it = r.getWinesIterator();

			while (it.hasNext())
			{
				wine = it.next().toWineObject();
				WineDAO.addWine(wine);
			}

		} catch (InvalidWineDetails | DAException e)
		{
			e.printStackTrace();
		}

		return wine;
	}

}
