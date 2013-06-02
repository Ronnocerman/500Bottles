package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.geolocation.GeoLocation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;

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

		wine.setYear(1966);
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
}