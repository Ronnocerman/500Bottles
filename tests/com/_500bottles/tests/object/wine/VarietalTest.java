package com._500bottles.tests.object.wine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.wine.Varietal;

public class VarietalTest
{
	Varietal chardonnay;
	Varietal merlot;

	@Before
	public void setUp()
	{
		chardonnay = new Varietal();
		merlot = new Varietal();

		merlot.setGrapeType("Merlot");
		merlot.setId(1);
	}

	@After
	public void tearDown()
	{
		chardonnay = null;
		merlot = null;
	}

	@Test
	public void getId()
	{
		assertEquals(chardonnay.getId(), 0);
		assertEquals(merlot.getId(), 1);
	}

	@Test
	public void getGrapeType()
	{
		assertEquals(chardonnay.getGrapeType(), null);
		assertEquals(merlot.getGrapeType(), "Merlot");
	}

	@Test
	public void setId()
	{
		chardonnay.setId(2);
		assertEquals(chardonnay.getId(), 2);
	}

	@Test
	public void setIdWithNegative()
	{
		try
		{
			chardonnay.setId(-1);
			if (chardonnay.getId() == -1)
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
			merlot.setId('~');
			if (merlot.getId() == '~')
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
			chardonnay.setId(1);
			merlot.setId(1);
			if (chardonnay.getId() == merlot.getId())
				fail("Throw exception: Two photos cannot have same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setGrapeType()
	{
		chardonnay.setGrapeType("Chardonnay");
		assertEquals(chardonnay.getGrapeType(), "Chardonnay");
	}
}