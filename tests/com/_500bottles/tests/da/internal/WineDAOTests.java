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
		Wine wine = new Wine();
		wine.setName("Bereinger");
		wine.setDescription("Hi Yun");
		wine.setSnoothId("20");

		GeoLocation geo = new GeoLocation();
		geo.setLat((float) 32.4455);
		geo.setLon((float) 100.32);
		Vineyard vineyard = new Vineyard();
		vineyard.setId(50);

		Varietal varietal = new Varietal();
		varietal.setId(432);

		wine.setYear(1970);
		wine.setRating(5);
		wine.setGeoLocation(geo);
		wine.setSnoothId("555");
		wine.setWinecomId(343);
		wine.setVineyard(vineyard);
		wine.setVarietal(varietal);
		wine.setPriceMin(10);
		wine.setPriceMax(999);
		/*
		 * wine.setType(new WineType()); wine.setYear(0);
		 * wine.setAppellation(new Appellation()); wine.setVarietal(new
		 * Varietal()); wine.setVineyard(new Vineyard()); wine.setRating(0);
		 */

		try
		{
			WineDAO.addWine(wine);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteWine() throws DAException
	{
		Wine wine = new Wine();
		wine.setName("dunnoWineNames");
		wine.setDescription("If this shows up youre REALLY fucked");
		wine.setSnoothId("21");

		GeoLocation geo = new GeoLocation();
		geo.setLat((float) 32.45);
		geo.setLon((float) 12);

		wine.setGeoLocation(geo);
		wine.setSnoothId("50");
		wine.setWinecomId(340);

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
		Wine wine = new Wine();
		wine.setName("dunnoWineNames");
		wine.setDescription("If this shows up youre REALLY fucked");
		wine.setSnoothId("21");

		GeoLocation geo = new GeoLocation();
		geo.setLat((float) 32.45);
		geo.setLon((float) 12);

		wine.setGeoLocation(geo);
		wine.setSnoothId("50");
		wine.setWinecomId(340);

		wine = WineDAO.addWine(wine);

		try
		{
			wine = WineDAO.getWine(wine.getId());
		} catch (DAException e)
		{
			fail();
		}

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
		Wine wine = new Wine();
		Vineyard vineyard = new Vineyard();
		Varietal varietal = new Varietal();
		GeoLocation geo = new GeoLocation();

		varietal.setId(234);
		vineyard.setId(25);

		geo.setLat((float) 56.4455);
		geo.setLon((float) 90.32);

		wine.setName("Whatever");
		wine.setDescription("Something");
		wine.setYear(2001);
		wine.setRating(1);
		wine.setGeoLocation(geo);
		wine.setSnoothId("555");
		wine.setWinecomId(33);
		wine.setVineyard(vineyard);
		wine.setVarietal(varietal);
		wine.setPriceMin(5);
		wine.setPriceMax(20);

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
		Wine wine = new Wine();
		Vineyard vineyard = new Vineyard();
		Varietal varietal = new Varietal();
		GeoLocation geo = new GeoLocation();

		varietal.setId(234);
		vineyard.setId(25);

		geo.setLat((float) 56.4455);
		geo.setLon((float) 90.32);

		wine.setName("Whatever");
		wine.setDescription("Something");
		wine.setYear(2001);
		wine.setRating(1);
		wine.setGeoLocation(geo);
		wine.setSnoothId("555");
		wine.setWinecomId(33);
		wine.setVineyard(vineyard);
		wine.setVarietal(varietal);
		wine.setPriceMin(5);
		wine.setPriceMax(20);

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
		Wine wine = new Wine();
		Vineyard vineyard = new Vineyard();
		Varietal varietal = new Varietal();
		GeoLocation geo = new GeoLocation();

		varietal.setId(234);
		vineyard.setId(25);

		geo.setLat((float) 56.4455);
		geo.setLon((float) 90.32);

		wine.setName("Whatever");
		wine.setDescription("Something");
		wine.setYear(2001);
		wine.setRating(1);
		wine.setGeoLocation(geo);
		wine.setSnoothId("333");
		wine.setWinecomId(33);
		wine.setVineyard(vineyard);
		wine.setVarietal(varietal);
		wine.setPriceMin(5);
		wine.setPriceMax(20);

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
		vineyard.setName("You should not be able to see this.");

		try
		{
			WineDAO.addVineyard(vineyard);
			getVineyard = WineDAO.getVineyard(vineyard.getId());

			assertEquals(getVineyard.getName(),
					"You should not be able to see this.");

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
		varietal.setGrapeType("You should not be able to see this.");

		try
		{
			WineDAO.addVarietal(varietal);

			getVarietal = WineDAO.getVarietal(varietal.getId());

			assertEquals(getVarietal.getGrapeType(),
					"You should not be able to see this.");

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
		/*
		 * Adding First Wine
		 */
		Wine wine = new Wine();
		wine.setName("ABCD");
		wine.setDescription("Hi Yun");
		wine.setSnoothId("20");
		GeoLocation geo = new GeoLocation();
		geo.setLat((float) 32.4455);
		geo.setLon((float) 100.32);
		Vineyard vineyard = new Vineyard();
		vineyard.setId(20);
		vineyard.setName("vineyard1");

		Varietal varietal = new Varietal();
		varietal.setId(432);

		WineType wineTypeRed = new WineType("red");

		wine.setType(wineTypeRed);
		wine.setYear(1986);
		wine.setRating(5);
		wine.setGeoLocation(geo);
		wine.setSnoothId("555");
		wine.setWinecomId(343);
		wine.setVineyard(vineyard);
		wine.setVarietal(varietal);
		wine.setPriceMin(10);
		wine.setPriceMax(999);
		try
		{
			WineDAO.addWine(wine);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}

		/*
		 * Adding Wine 2
		 */
		Wine wine2 = new Wine();
		wine2.setName("CD");
		wine2.setDescription("Hi Yun");
		wine2.setSnoothId("20");
		GeoLocation geo2 = new GeoLocation();
		geo2.setLat((float) 32.4455);
		geo2.setLon((float) 100.32);
		Vineyard vineyard2 = new Vineyard();
		vineyard2.setId(99);
		vineyard2.setName("vineyard2");

		Varietal varietal2 = new Varietal();
		varietal2.setId(432);

		wine2.setType(wineTypeRed);
		wine2.setYear(1905);
		wine2.setRating(5);
		wine2.setGeoLocation(geo2);
		wine2.setSnoothId("555");
		wine2.setWinecomId(343);
		wine2.setVineyard(vineyard2);
		wine2.setVarietal(varietal2);
		wine2.setPriceMin(10);
		wine2.setPriceMax(999);
		try
		{
			WineDAO.addWine(wine2);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}

		/*
		 * Adding Wine 3
		 */
		Wine wine3 = new Wine();
		wine3.setName("ABCD");
		wine3.setDescription("Hi Yun");
		wine3.setSnoothId("20");
		GeoLocation geo3 = new GeoLocation();
		geo3.setLat((float) 32.4455);
		geo3.setLon((float) 100.32);
		Vineyard vineyard3 = new Vineyard();
		vineyard3.setId(30);
		vineyard3.setName("vineyard3");

		Varietal varietal3 = new Varietal();
		varietal3.setId(432);

		WineType wineTypeWhite = new WineType("white");

		wine3.setType(wineTypeWhite);
		wine3.setYear(1987);
		wine3.setRating(5);
		wine3.setGeoLocation(geo3);
		wine3.setSnoothId("555");
		wine3.setWinecomId(343);
		wine3.setVineyard(vineyard3);
		wine3.setVarietal(varietal3);
		wine3.setPriceMin(10);
		wine3.setPriceMax(999);
		try
		{
			WineDAO.addWine(wine3);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}

		/*
		 * Adding Wine 4
		 */
		Wine wine4 = new Wine();
		wine4.setName("ZX");
		wine4.setDescription("Hi Yun");
		wine4.setSnoothId("20");
		GeoLocation geo4 = new GeoLocation();
		geo4.setLat((float) 32.4455);
		geo4.setLon((float) 100.32);
		Vineyard vineyard4 = new Vineyard();
		vineyard4.setId(40);
		vineyard4.setName("vineyard4");

		Varietal varietal4 = new Varietal();
		varietal4.setId(432);

		wine4.setType(wineTypeWhite);
		wine4.setYear(1987);
		wine4.setRating(5);
		wine4.setGeoLocation(geo4);
		wine4.setSnoothId("555");
		wine4.setWinecomId(343);
		wine4.setVineyard(vineyard4);
		wine4.setVarietal(varietal4);
		wine4.setPriceMin(10);
		wine4.setPriceMax(999);
		try
		{
			WineDAO.addWine(wine4);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}

		WineQuery query = new WineQuery();

		query.setNameContains("BC");
		query.setMaxYear(2000);
		query.setMinYear(1970);

		Vector<Vineyard> vineYard = new Vector<Vineyard>();
		vineYard.add(vineyard);
		vineYard.add(vineyard3);

		query.setVineyard(vineYard);

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
		assertEquals(retWine.size(), 2);

		assertEquals(retWine.get(0).getYear(), 1986);
		assertEquals(retWine.get(1).getYear(), 1987);

	}

}