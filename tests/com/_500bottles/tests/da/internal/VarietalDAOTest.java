package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com._500bottles.da.internal.VarietalDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Varietal;

public class VarietalDAOTest
{

	@Test
	public void addVarietal() throws DAException
	{
		try
		{
			VarietalDAO.addVarietal(createVarietal("varietal7"));
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
			VarietalDAO.addVarietal(varietal);
			assertTrue(VarietalDAO.deleteVarietal(varietal.getId()));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	private Varietal createVarietal(String grapeType)
	{
		Varietal var = new Varietal();
		var.setGrapeType(grapeType);
		return var;
	}
}