package com._500bottles.tests.manager;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.wine.exception.InvalidCategory;
import com._500bottles.da.external.wine.exception.InvalidOtherParameters;
import com._500bottles.exception.da.DAException;
import com._500bottles.manager.WineManager;
import com._500bottles.object.geolocation.GeoLocation;
import com._500bottles.object.wine.CustomWine;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;
import com._500bottles.object.wine.WineType;

/**
 *
 */
@RunWith(JUnit4.class)
public class WineManagerTests
{

	WineQuery q;

	@Ignore
	public void testSetup()
	{

	}

	// TODO
	@Ignore
	public void searchWineTest() throws DAException, InvalidCategory,
			InvalidSort, InvalidOtherParameters, IOException, ParseException
	{
		q = new WineQuery();
		q.setTextQuery("merlot");
		q.setSize(20);
		WineQueryResult r = WineManager.searchWine(q);
		Iterator<Wine> it = r.getIterator();
		while (it.hasNext())
			System.out.println(it.next().toString());
	}

	@Test
	public void searchWineWithOffset()
	{
		boolean empty = false;
		int offset;
		int i = 0;
		q = new WineQuery();
		q.setTextQuery("merlot");
		q.setSize(15);
		q.setOffset(9);

		offset = q.getOffset();

		WineQueryResult r = WineManager.searchWine(q);

		Iterator<Wine> it = r.getIterator();

		while (!empty)
		{
			for (; i < offset; i++)
			{
				if (it.hasNext())
				{
					// System.out.println(it.next().toString());
					it.next();
				}
			}

			if (i >= r.getResultsCount())
				empty = true;

			offset += q.getOffset();
		}
	}

	@Ignore
	public void addCustomWine() throws DAException
	{
		Wine wine = createWine("New Custom Wine", "Green", 1900, "Lime",
				"Whole In The Wall", 1, 200, 1000);

		WineManager.addCustomWine((CustomWine) wine);
	}

	@Ignore
	public void deleteCustomWine()
	{
		Wine wine = createWine("Delete Custom Wine", "Yellow", 1900, "Lemon",
				"Yunkai", 1, 200, 1000);

		WineManager.addCustomWine((CustomWine) wine);
		WineManager.deleteCustomWine(wine.getId());
	}

	@Ignore
	public void editCustomWine()
	{

	}

	@Ignore
	public void setRating()
	{

	}

	@Ignore
	public void setFavorite()
	{

	}

	@Ignore
	public void isFavorite()
	{

	}

	private CustomWine createWine(String name, String type, long vintage,
			String varietal, String vineyardName, double rating,
			double minPrice, double maxPrice)
	{
		Wine wine = new CustomWine();
		wine.setName(name);
		wine.setDescription("Something");
		wine.setSnoothId("20");
		GeoLocation geo = new GeoLocation();
		geo.setLat((float) 32.4455);
		geo.setLon((float) 100.32);

		Vineyard vineyard = new Vineyard();
		vineyard.setName(vineyardName);

		Varietal grape = new Varietal();
		grape.setGrapeType(varietal);

		WineType wineTypeRed = new WineType(type);

		wine.setType(wineTypeRed);
		wine.setYear(vintage);
		wine.setRating(rating);
		wine.setGeoLocation(geo);
		wine.setSnoothId("555");
		wine.setWinecomId(343);
		wine.setVineyard(vineyard);
		wine.setVarietal(grape);
		wine.setPriceMin(minPrice);
		wine.setPriceMax(maxPrice);

		return (CustomWine) wine;
	}
}