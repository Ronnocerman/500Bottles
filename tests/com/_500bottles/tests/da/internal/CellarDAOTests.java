package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.CellarDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.geolocation.GeoLocation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/18/13 Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class CellarDAOTests
{

	@Test
	public void addCellarItem() throws DAException
	{
		Wine wine = new Wine();
		CellarItem item = new CellarItem(wine);
		item.setQuantity(1);
		item.setNotes("This is good wine");
		item.setWineId(15);

		try
		{
			CellarDAO.addCellarItem(46, item);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteCellarItem() throws DAException
	{
		Wine wine = new Wine();
		CellarItem item = new CellarItem(wine);
		item.setQuantity(999);
		item.setNotes("If this shows up you're fucked");
		item.setWineId(20);

		try
		{
			item = CellarDAO.addCellarItem(15, item);

			assertTrue(CellarDAO.deleteCellarItem(item.getId()));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void editCellarItem() throws DAException
	{
		Wine wine = new Wine();
		wine.setId(4);
		CellarItem item = new CellarItem(wine);
		item.setNotes("Imma buy 4 moe of these");
		item.setQuantity(4);
		item.setUserRating(10);

		try
		{
			CellarDAO.addCellarItem(57, item);

			item = CellarDAO.getCellarItem(item.getId());
			item.setNotes("I GOT 8 NOW cus its too good");
			item.setQuantity(8);
			item = CellarDAO.editCellarItem(item);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getByWineId() throws DAException
	{
		CellarItem getCellarItem;
		Wine wine = new Wine();
		CellarItem item = new CellarItem(wine);
		item.setQuantity(1);
		item.setNotes("Testing getByWineId()");
		item.setWineId(100);

		try
		{
			CellarDAO.addCellarItem(46, item);

			getCellarItem = CellarDAO.getByWineID(46, item.getWineId());

			assertEquals(getCellarItem.getNotes(), "Testing getByWineId()");
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getCellarItem() throws DAException
	{
		Wine wine = new Wine();
		wine.setId(4);
		CellarItem item = new CellarItem(wine);
		item.setNotes("Testing getCellarItem()");
		item.setQuantity(4);

		try
		{
			CellarDAO.addCellarItem(57, item);

			item = CellarDAO.getCellarItem(item.getId());

			assertEquals(item.getNotes(), "Testing getCellarItem()");
			assertEquals(item.getQuantity(), 4);
			assertEquals(item.getWineId(), 4);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	// @Test
	// public void testGetAllWinesFromCellar() throws DAException
	// {
	// try
	// {
	// WineDAO.addWine(createWine("Name1", "Red", 1991, "varietal1",
	// "vineyard1", 5, 15, 60));
	// WineDAO.addWine(createWine("Name2", "White", 1992, "varieta2",
	// "vineyard2", 5, 15, 60));
	// WineDAO.addWine(createWine("Name3", "Red", 1993, "varietal3",
	// "vineyard3", 5, 15, 60));
	// WineDAO.addWine(createWine("Name4", "White", 1994, "varietal4",
	// "vineyard4", 5, 15, 60));
	// WineDAO.addWine(createWine("Name5", "Red", 1995, "varietal5",
	// "vineyard5", 5, 15, 60));
	// WineDAO.addWine(createWine("Name6", "White", 1996, "varietal6",
	// "vineyard6", 5, 15, 60));
	// WineDAO.addWine(createWine("Name7", "Red", 1997, "varietal7",
	// "vineyard7", 5, 15, 60));
	// WineDAO.addWine(createWine("Name8", "White", 1998, "varietal8",
	// "vineyard8", 5, 15, 60));
	// WineDAO.addWine(createWine("Name9", "Red", 1999, "varietal9",
	// "vineyard9", 5, 15, 60));
	//
	// Wine wine1 = new Wine();
	// CellarItem item1 = new CellarItem(wine1);
	// CellarItem item2 = new CellarItem(wine1);
	// CellarItem item3 = new CellarItem(wine1);
	// CellarItem item4 = new CellarItem(wine1);
	// CellarItem item5 = new CellarItem(wine1);
	// item1.setWineId(365);
	// item2.setWineId(367);
	// item3.setWineId(369);
	// item4.setWineId(371);
	// item5.setWineId(373);
	// CellarDAO.addCellarItem(2, item1);
	// CellarDAO.addCellarItem(2, item2);
	// CellarDAO.addCellarItem(2, item3);
	// CellarDAO.addCellarItem(2, item4);
	// CellarDAO.addCellarItem(2, item5);
	//
	// Vector<Wine> list = CellarDAO.getAllWinesFromCellar(2);
	//
	// for (int i = 0, j = 1; i < list.size(); i++, j++)
	// {
	// assertEquals(list.get(i).getName(), "Name" + (i + j));
	// System.out.println(list.get(i).getName());
	// }
	//
	// } catch (DAException e)
	// {
	// fail(e.getMessage());
	// }
	// Vector<Wine> wines = new Vector<Wine>();
	// try
	// {
	// wines = CellarDAO.getAllWinesFromCellar(57);
	// } catch (DAException e)
	// {
	// fail(e.getMessage());
	// }
	// for (int i = 0; i < wines.size(); i++)
	// {
	// System.out.println(wines.get(i).getName());
	// }
	// }

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