package com._500bottles.tests.manager;

import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.manager.WineWizardManager;
import com._500bottles.object.geolocation.GeoLocation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

public class WineWizardManagerTest
{
	int wineAmount;
	WineWizardManager wizardManager;
	Vector<String> animals;
	Vector<Integer> animalsNum;
	Vector<Integer> animalsCount;

	@Before
	public void setUp()
	{
		try
		{
			// String name, String type, long vintage, long varitalId,
			// String vineyardName, long vineyardId, double rating,
			// double minPrice, double maxPrice
			// System.out.println("its one");
			WineDAO.addWine(createWine("ABCD", "red", 1986, 431, "vineyard1",
					20, 5, 10, 999));
			WineDAO.addWine(createWine("check", "red", 1987, 431, "vineyard42",
					20, 5, 10, 999));
			WineDAO.addWine(createWine("CD", "red", 1905, 432, "vineyard2", 99,
					4, 10, 999));
			WineDAO.addWine(createWine("ABd", "white", 1987, 433, "vineyard3",
					30, 3, 10, 999));
			WineDAO.addWine(createWine("ZX", "white", 1989, 434, "vineyard4",
					40, 4, 10, 999));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
		// wineAmount = 22;
		wizardManager = new WineWizardManager();
		animals = new Vector<String>();
		animalsNum = new Vector<Integer>();
		animalsCount = new Vector<Integer>();

		animals.add("Dog");
		animals.add("Sheep");
		animals.add("Cat");
		animals.add("Mouse");
		animals.add("Zebra");

		for (int i = 0; i < animals.size(); i++)
		{
			// animalsNum.add(i);
			animalsCount.add(0);
		}
		animalsNum.add(56);
		animalsNum.add(34);
		animalsNum.add(66);
		animalsNum.add(32);
		animalsNum.add(34);
	}

	@Test
	public void testGetSelectWine()
	{
		Vector<Wine> testWine = new Vector<Wine>();
		WineQuery query = new WineQuery();
		Vector<Varietal> yes = new Vector<Varietal>();
		Varietal dork = new Varietal();
		dork.setId(433);
		yes.add(dork);
		query.setVarietal(yes);

		Vector<WineType> no = new Vector<WineType>();
		WineType win = new WineType();
		win.setWineType("white");
		no.add(win);
		query.setType(no);
		query.setMinYear(1978);
		query.setMaxYear(1979);
		testWine = WineWizardManager.selectWine(query);
		if (testWine == null)
			return;
		System.out.println("This is the wine vector size: " + testWine.size());
		for (int i = 0; i < testWine.size(); i++)
		{
			System.out.println("WineName: " + testWine.get(i).getName());
			System.out.println("WineType: "
					+ testWine.get(i).getType().getWineType());
			System.out.println("WineVarietal: "
					+ testWine.get(i).getVarietal().getId());
		}
	}

	@After
	public void tearDown()
	{
		wizardManager = null;
	}

	@Test
	public void sort()
	{
		WineWizardManager.sort(animals, animalsNum, animalsCount);
		for (int q = 0; q < animals.size(); q++)
		{
			System.out.println(animals.get(q));
		}
	}

	private Wine createWine(String name, String type, long vintage,
			long varietalId, String vineyardName, long vineyardId,
			double rating, double minPrice, double maxPrice)
	{
		Wine wine = new Wine();
		wine.setName(name);
		wine.setDescription("Something");
		wine.setSnoothId("20");
		GeoLocation geo = new GeoLocation();
		geo.setLat((float) 32.4455);
		geo.setLon((float) 100.32);
		Vineyard vineyard = new Vineyard();
		vineyard.setId(vineyardId);
		vineyard.setName(vineyardName);

		Varietal varietal = new Varietal();
		varietal.setId(varietalId);

		WineType wineTypeRed = new WineType(type);

		wine.setType(wineTypeRed);
		wine.setYear(vintage);
		wine.setRating(rating);
		wine.setGeoLocation(geo);
		wine.setSnoothId("555");
		wine.setWinecomId(343);
		wine.setVineyard(vineyard);
		wine.setVarietal(varietal);
		wine.setPriceMin(minPrice);
		wine.setPriceMax(maxPrice);

		return wine;
	}
}
