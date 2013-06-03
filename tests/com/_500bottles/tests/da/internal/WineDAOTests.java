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
			WineDAO.addWine(createWine("Bereinger", "red", 1970, 432,
					"vineyard", 50, 5, 10, 999));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteWine() throws DAException
	{
		Wine wine = createWine("dunnoWineNames", "red", 1970, 432, "vineyard",
				50, 5, 10, 999);

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
		Wine wine = createWine("dunnoWineNames", "red", 1970, 432, "vineyard",
				50, 5, 10, 999);

		wine.setDescription("If this shows up youre REALLY fucked");

		wine = WineDAO.addWine(wine);

		try
		{
			wine = WineDAO.getWine(wine.getId());
			wine.setDescription("Changed to new description");
			WineDAO.editWine(wine);
		} catch (DAException e)
		{
			fail();
		}
	}

	@Test
	public void getWine() throws DAException
	{
		Wine wine = createWine("Whatever", "white", 1970, 234, "vineyard", 25,
				5, 5, 20);

		wine.setDescription("Something");

		try
		{
			WineDAO.addWine(wine);
			Wine getWine = WineDAO.getWine(wine);

			assertEquals(getWine.getName(), "Whatever");
			assertEquals(getWine.getDescription(), "Something");

			WineDAO.deleteWine(wine);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getWineByWineId() throws DAException
	{
		Wine wine = createWine("Whatever", "white", 1970, 234, "vineyard", 25,
				5, 5, 20);

		wine.setDescription("Something");

		try
		{
			WineDAO.addWine(wine);
			Wine getWine = WineDAO.getWine(wine.getId());

			assertEquals(getWine.getName(), "Whatever");
			assertEquals(getWine.getDescription(), "Something");

			WineDAO.deleteWine(wine);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getWineBySnoothId() throws DAException
	{
		Wine wine = createWine("Whatever", "white", 1970, 234, "vineyard", 25,
				5, 5, 20);

		wine.setDescription("Something");
		wine.setSnoothId("333");

		try
		{
			WineDAO.addWine(wine);
			Wine getWine = WineDAO.getWineBySnoothId(wine.getSnoothId());

			assertEquals(getWine.getName(), "Whatever");
			assertEquals(getWine.getDescription(), "Something");

			WineDAO.deleteWine(wine);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void addVineyard() throws DAException
	{
		Vineyard vineyard = new Vineyard();
		vineyard.setName("Wilson Creek");

		try
		{
			WineDAO.addVineyard(vineyard);
		} catch (DAException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteVineyard() throws DAException
	{
		Vineyard vineyard = new Vineyard();
		vineyard.setName("You should not be able to see this.");

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
		Vineyard vineyard = new Vineyard();
		vineyard.setName("vineyard1");

		try
		{
			WineDAO.addVineyard(vineyard);
			getVineyard = WineDAO.getVineyard(vineyard.getName());

			assertEquals(getVineyard.getName(), "v");

			getVineyard.setName("Its visible.");

			WineDAO.editVineyard(getVineyard);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void addVarietal() throws DAException
	{
		Varietal varietal = new Varietal();
		varietal.setGrapeType("Red");

		try
		{
			WineDAO.addVarietal(varietal);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteVarietal() throws DAException
	{
		Varietal varietal = new Varietal();
		varietal.setGrapeType("You should not be able to see this.");

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
		Varietal varietal = new Varietal();
		varietal.setGrapeType("Red");

		try
		{
			WineDAO.addVarietal(varietal);

			getVarietal = WineDAO.getVarietal(varietal.getGrapeType());

			assertEquals(getVarietal.getGrapeType(), "Red");

			getVarietal.setGrapeType("Its visible.");

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
			WineDAO.addWine(createWine("ABCD", "red", 1986, 432, "vineyard1",
					20, 5, 10, 999));
			WineDAO.addWine(createWine("CD", "red", 1905, 432, "vineyard2", 99,
					5, 10, 999));
			WineDAO.addWine(createWine("ABd", "white", 1987, 432, "vineyard3",
					30, 5, 10, 999));
			WineDAO.addWine(createWine("ZX", "white", 1989, 432, "vineyard4",
					40, 5, 10, 999));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}

		WineQuery query = new WineQuery();

		Vector<Vineyard> vineYard = new Vector<Vineyard>();

		// createVineyard(String name, long id)
		vineYard.add(createVineyard("vineyard1", 20));
		vineYard.add(createVineyard("vineyard3", 30));

		query.setVineyard(vineYard);
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
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("size of returned wine: " + retWine.size());
		assertEquals(retWine.size(), 1);

		assertEquals(retWine.get(0).getYear(), 1986);
		// assertEquals(retWine.get(1).getYear(), 1987);

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

	private Vineyard createVineyard(String name, long id)
	{
		Vineyard vineyard = new Vineyard();
		vineyard.setId(id);
		vineyard.setName(name);

		return vineyard;
	}
}