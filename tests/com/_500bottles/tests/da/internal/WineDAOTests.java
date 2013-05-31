package com._500bottles.tests.da.internal;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.WineDAO;
import com._500bottles.object.geolocation.GeoLocation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;

@RunWith(JUnit4.class)
public class WineDAOTests
{
	@Test
	public void testInsertWine()
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
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void testDeleteWine()
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
		} catch (Exception e)
		{
			fail();
		}

		try
		{
			WineDAO.deleteWine(wine);
		} catch (Exception e)
		{
			fail();
		}
	}

	// tests get AND edit

	@Test
	public void testEditWine()
	{
		Wine wine = null;

		try
		{
			wine = WineDAO.getWine(1);
		} catch (Exception e)
		{
			fail();
		}

		try
		{
			wine.setDescription("Chris is awesome");
			WineDAO.editWine(wine);
		} catch (Exception e)
		{
			fail();
		}

	}

}
