package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.geolocation.GeoLocation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

@RunWith(JUnit4.class)
public class WineDAOTests
{
	@Test
	public void addWine() throws DAException
	{
		try
		{
			WineDAO.addWine(createWine("Bereinger", "red", 1970, "varietal1",
					"vineyard1", 5, 10, 999));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteWine() throws DAException
	{
		Wine wine = createWine("dunnoWineNames", "red", 1970, "varietal2",
				"vineyard2", 5, 10, 999);

		wine.setDescription("If this shows up youre REALLY fucked");

		try
		{
			wine = WineDAO.addWine(wine);
			assertTrue(WineDAO.deleteWine(wine));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void editWine() throws DAException
	{
		Wine wine = createWine("dunnoWineNames", "red", 1970, "varietal3",
				"vineyard3", 5, 10, 999);

		wine.setDescription("If this shows up youre REALLY fucked3");

		wine = WineDAO.addWine(wine);

		try
		{
			wine.setDescription("Changed to new description");
			WineDAO.editWine(wine);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getWine() throws DAException
	{
		Wine wine = createWine("Wine4", "white", 1970, "varietal4",
				"vineyard4", 5, 5, 20);

		wine.setDescription("Something");

		try
		{
			WineDAO.addWine(wine);
			Wine getWine = WineDAO.getWine(wine);

			assertEquals(getWine.getName(), "Wine4");
			assertEquals(getWine.getDescription(), "Something");
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getWineByWineId() throws DAException
	{
		Wine wine = createWine("Wine5", "white", 1970, "varietal5",
				"vineyard5", 5, 5, 20);

		wine.setDescription("Something");

		try
		{
			WineDAO.addWine(wine);
			Wine getWine = WineDAO.getWine(wine.getId());

			assertEquals(getWine.getName(), "Wine5");
			assertEquals(getWine.getDescription(), "Something");
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getWineBySnoothId() throws DAException
	{
		Wine wine = createWine("Wine6", "white", 1970, "varietal6",
				"vineyard6", 5, 5, 20);

		wine.setDescription("Something");
		wine.setSnoothId("333");

		try
		{
			WineDAO.addWine(wine);
			Wine getWine = WineDAO.getWineBySnoothId(wine.getSnoothId());

			assertEquals(getWine.getName(), "Wine6");
			assertEquals(getWine.getDescription(), "Something");
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void addVineyard() throws DAException
	{
		try
		{
			WineDAO.addVineyard(createVineyard("vineyard7"));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteVineyard() throws DAException
	{
		Vineyard vineyard = createVineyard("If this shows up youre REALLY fucked");

		try
		{
			WineDAO.addVineyard(vineyard);
			assertTrue(WineDAO.deleteVineyard(vineyard.getId()));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void editVineyard() throws DAException
	{
		Vineyard getVineyard;
		Vineyard vineyard = createVineyard("If this shows up youre REALLY fucked");

		try
		{
			WineDAO.addVineyard(vineyard);
			getVineyard = WineDAO.getVineyard(vineyard.getName());

			assertEquals(getVineyard.getName(),
					"If this shows up youre REALLY fucked");

			getVineyard.setName("vineyard8");

			WineDAO.editVineyard(getVineyard);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void addVarietal() throws DAException
	{
		try
		{
			WineDAO.addVarietal(createVarietal("varietal7"));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteVarietal() throws DAException
	{
		Varietal varietal = createVarietal("You should not be able to see this.");

		try
		{
			WineDAO.addVarietal(varietal);
			assertTrue(WineDAO.deleteVarietal(varietal.getId()));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void editVarietal() throws DAException
	{
		Varietal getVarietal;
		Varietal varietal = createVarietal("You should not be able to see this.");

		try
		{
			WineDAO.addVarietal(varietal);

			getVarietal = WineDAO.getVarietal(varietal.getGrapeType());

			assertEquals(getVarietal.getGrapeType(),
					"You should not be able to see this.");

			getVarietal.setGrapeType("varietal8.");

			WineDAO.editVarietal(getVarietal);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void testCrazyShit()
	{
		try
		{
			// String name, String type, long vintage, long varitalId,
			// String vineyardName, long vineyardId, double rating,
			// double minPrice, double maxPrice
			WineDAO.addWine(createWine("ABCD", "red", 1986, "varietal9",
					"vineyard9", 5, 10, 999));
			WineDAO.addWine(createWine("CD", "red", 1905, "varietal10",
					"vineyard10", 5, 10, 999));
			WineDAO.addWine(createWine("ABC", "white", 1987, "varietal11",
					"vineyard11", 5, 10, 999));
			WineDAO.addWine(createWine("ZX", "white", 1989, "varietal12",
					"vineyard12", 5, 10, 999));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}

		WineQuery query = new WineQuery();

		query.setNameContains("BC");
		query.setMaxYear(2000);
		query.setMinYear(1970);

		Vector<WineType> wineType = new Vector<WineType>();

		wineType.add(new WineType("red"));
		wineType.add(new WineType("white"));

		query.setType(wineType);

		Vector<Wine> retWine = new Vector<Wine>();

		try
		{
			retWine = WineDAO.getWinesFromQuery(query);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}

		assertEquals(retWine.size(), 2);

		assertEquals(retWine.get(0).getYear(), 1986);
		assertEquals(retWine.get(1).getYear(), 1987);

	}

	@Test
	public void testWineQuerySearch()
	{
		WineQuery query = new WineQuery();
		query.setTextQuery("something");
		Vector<Wine> wines = new Vector<Wine>();

		try
		{
			wines = WineDAO.getWinesFromQuerySearch(query);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("wine size: " + wines.size());
		//
		for (int i = 0; i < wines.size(); i++)
		{
			System.out.println("wines: " + wines.get(i).getName());
		}
	}

	private Wine createWine(String name, String type, long vintage,
			String varietal, String vineyardName, double rating,
			double minPrice, double maxPrice)
	{
		Wine wine = new Wine();
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

		return wine;
	}

	private Vineyard createVineyard(String name)
	{
		Vineyard vineyard = new Vineyard();
		vineyard.setName(name);

		return vineyard;
	}

	private Varietal createVarietal(String grapeType)
	{
		Varietal var = new Varietal();
		var.setGrapeType(grapeType);
		return var;
	}
}