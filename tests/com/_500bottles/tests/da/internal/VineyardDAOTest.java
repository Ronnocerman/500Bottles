package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com._500bottles.da.internal.VineyardDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Vineyard;

public class VineyardDAOTest
{

	@Test
	public void addVineyard() throws DAException
	{
		try
		{
			VineyardDAO.addVineyard(createVineyard("vineyard7"));
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
			VineyardDAO.addVineyard(vineyard);
			assertTrue(VineyardDAO.deleteVineyard(vineyard.getId()));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	private Vineyard createVineyard(String name)
	{
		Vineyard vineyard = new Vineyard();
		vineyard.setName(name);

		return vineyard;
	}
}