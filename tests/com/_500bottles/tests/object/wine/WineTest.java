package com._500bottles.tests.object.wine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

public class WineTest
{
	Wine wine1;
	Wine wine2;
	WineType red;
	Varietal merlot;
	Vineyard napa;

	@Before
	public void setUp()
	{
		wine1 = new Wine();
		wine2 = new Wine();
		red = new WineType();
		merlot = new Varietal();
		napa = new Vineyard();

		red.setWineType("Red");
		merlot.setGrapeType("Merlot");
		napa.setName("Wine Place");

		wine2.setId(100);
		wine2.setName("White Merlot");
		wine2.setDescription("Lighter Merlot");
		wine2.setType(red);
		wine2.setYear(2010);
		wine2.setVarietal(merlot);
		wine2.setVineyard(napa);
		wine2.setRating(3);
	}

	@After
	public void tearDown()
	{
		wine1 = null;
		wine2 = null;
	}

	@Test
	public void getId()
	{
		assertEquals(wine1.getId(), 0);
		assertEquals(wine2.getId(), 100);
	}

	@Test
	public void getName()
	{
		assertEquals(wine1.getName(), null);
		assertEquals(wine2.getName(), "White Merlot");
	}

	@Test
	public void getDescription()
	{
		assertEquals(wine1.getDescription(), null);
		assertEquals(wine2.getDescription(), "Lighter Merlot");
	}

	@Test
	public void getType()
	{
		assertEquals(wine1.getType(), null);
		assertEquals(wine2.getType().getWineType(), "Red");
	}

	@Test
	public void getVarietal()
	{
		assertEquals(wine1.getVarietal(), null);
		assertEquals(wine2.getVarietal().getGrapeType(), "Merlot");
	}

	@Test
	public void getVineyard()
	{
		assertEquals(wine1.getVineyard(), null);
		assertEquals(wine2.getVineyard().getName(), "Wine Place");
	}

	@Test
	public void getRating()
	{
		assertEquals(wine1.getRating(), 0);
		assertEquals(wine2.getRating(), 3);
	}

	@Test
	public void setId()
	{
		wine1.setId(83);
		assertEquals(wine1.getId(), 83);
	}

	@Test
	public void setIdWithNegative()
	{
		try
		{
			wine1.setId(-1);
			if (wine1.getId() == -1)
				fail("Throw exception: Id cannot be a negative numbers");
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
			wine1.setId('~');
			if (wine1.getId() == '~')
				fail("Throw exception: Id cannot be a character");
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
			wine1.setId(2);
			wine2.setId(2);
			if (wine1.getId() == wine2.getId())
				fail("Throw exception: Two wines cannot have the same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setName()
	{
		wine1.setName("Shiraz");
		assertEquals(wine1.getName(), "Shiraz");
	}

	@Test
	public void setDescription()
	{
		wine1.setDescription("Sweeter Shiraz");
		assertEquals(wine1.getDescription(), "Sweeter Shiraz");
	}

	@Test
	public void setType()
	{
		WineType white = new WineType();
		white.setWineType("white");
		wine1.setType(white);
		assertEquals(wine1.getType().getWineType(), "white");
	}

	@Test
	public void setVarietal()
	{
		Varietal muscat = new Varietal();
		muscat.setGrapeType("muscat");
		wine1.setVarietal(muscat);
		assertEquals(wine1.getVarietal().getGrapeType(), "muscat");
	}

	@Test
	public void setVineyard()
	{
		Vineyard sonoma = new Vineyard();
		sonoma.setName("Somewhere");
		wine1.setVineyard(sonoma);
		assertEquals(wine1.getVineyard().getName(), "Somewhere");
	}

	@Test
	public void setRating()
	{
		wine1.setRating(10);
		assertEquals(wine1.getRating(), 10);
	}

	@Test
	public void setRatingWithNegative()
	{
		try
		{
			wine1.setRating(-1);
			if (wine1.getRating() == -1)
				fail("Throw exception: Rating cannot be a negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setRatingWithCharacter()
	{
		try
		{
			wine1.setRating('~');
			if (wine1.getRating() == '~')
				fail("Throw exception: Rating cannot be a character");
		} catch (Exception e)
		{
			fail();
		}
	}
}