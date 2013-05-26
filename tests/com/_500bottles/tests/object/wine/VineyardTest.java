package com._500bottles.tests.object.wine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.wine.Vineyard;

public class VineyardTest
{
	Vineyard vineyard1;
	Vineyard vineyard2;

	@Before
	public void setUp()
	{
		vineyard1 = new Vineyard();
		vineyard2 = new Vineyard();

		vineyard2.setName("Wilson Creek Winery");
		vineyard2.setId(12);
	}

	@After
	public void tearDown()
	{
		vineyard1 = null;
		vineyard2 = null;
	}

	@Test
	public void getId()
	{
		assertEquals(vineyard1.getId(), 0);
		assertEquals(vineyard2.getId(), 12);
	}

	@Test
	public void getName()
	{
		assertEquals(vineyard1.getName(), null);
		assertEquals(vineyard2.getName(), "Wilson Creek Winery");
	}

	@Test
	public void setId()
	{
		vineyard1.setId(1);
		assertEquals(vineyard1.getId(), 1);
	}

	@Test
	public void setIdWithNegative()
	{
		try
		{
			vineyard1.setId(-1);
			if (vineyard1.getId() == -1)
				fail("Throw exception for negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setIdWithCharacter()
	{
		try
		{
			vineyard2.setId('~');
			if (vineyard2.getId() == '~')
				fail("Throw exception for characters");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setIdWithExistingId()
	{
		try
		{
			vineyard1.setId(1);
			vineyard2.setId(1);
			if (vineyard1.getId() == vineyard2.getId())
				fail("Throw exception: Two photos cannot have same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setName()
	{
		vineyard1.setName("Ponte Winery");
		assertEquals(vineyard1.getName(), "Ponte Winery");
	}
}