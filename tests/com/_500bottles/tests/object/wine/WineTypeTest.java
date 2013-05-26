package com._500bottles.tests.object.wine;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.wine.WineType;

public class WineTypeTest
{
	WineType red;
	WineType white;

	@Before
	public void setUp()
	{
		red = new WineType();
		white = new WineType();

		white.setWineType("White");
	}

	@After
	public void tearDown()
	{
		red = null;
		white = null;
	}

	@Test
	public void getWineType()
	{
		assertEquals(red.getWineType(), null);
		assertEquals(white.getWineType(), "White");
	}

	@Test
	public void setWineType()
	{
		red.setWineType("Red");
		assertEquals(red.getWineType(), "Red");
	}
}