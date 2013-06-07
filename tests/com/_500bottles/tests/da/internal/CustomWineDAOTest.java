package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com._500bottles.da.internal.CustomWineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.CustomWine;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

public class CustomWineDAOTest
{

	@Test
	public void addCustomWine() throws DAException
	{
		try
		{
			CustomWineDAO.addCustomWine(createWine("Bereinger", "Description",
					"red", 1970, "varietal1", "vineyard1"));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteCustomWine() throws DAException
	{
		CustomWine wine = createWine("dunnoWineNames",
				"If this shows up youre REALLY fucked", "red", 1970,
				"varietal2", "vineyard2");

		try
		{
			wine = CustomWineDAO.addCustomWine(wine);
			assertTrue(CustomWineDAO.deleteCustomWine(wine));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void editCustomWine() throws DAException
	{
		CustomWine wine = createWine("dunnoWineNames1",
				"If this shows up youre REALLY fucked3", "red", 1970,
				"varietal3", "vineyard3");

		wine = CustomWineDAO.addCustomWine(wine);

		try
		{
			wine.setDescription("Changed to new description");
			CustomWineDAO.editCustomWine(wine);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getCustomWine() throws DAException
	{
		CustomWine wine = createWine("Wine5", "Something", "white", 1970,
				"varietal5", "vineyard5");

		try
		{
			wine = CustomWineDAO.addCustomWine(wine);
			CustomWine getWine = CustomWineDAO.getCustomWine(wine.getId());

			assertEquals(getWine.getName(), "Wine5");
			assertEquals(getWine.getDescription(), "Something");
		} catch (DAException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	private CustomWine createWine(String name, String description, String type,
			long vintage, String varietal, String vineyardName)
	{
		Wine wine = new CustomWine();
		wine.setName(name);
		wine.setDescription(description);

		Vineyard vineyard = new Vineyard();
		vineyard.setName(vineyardName);

		Varietal grape = new Varietal();
		grape.setGrapeType(varietal);

		WineType wineTypeRed = new WineType(type);

		wine.setType(wineTypeRed);
		wine.setYear(vintage);
		wine.setVineyard(vineyard);
		wine.setVarietal(grape);

		return (CustomWine) wine;
	}
}