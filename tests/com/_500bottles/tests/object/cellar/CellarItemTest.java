package com._500bottles.tests.object.cellar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

public class CellarItemTest
{
	CellarItem item1;
	CellarItem item2;
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

		item1 = new CellarItem(wine1);
		item2 = new CellarItem(wine2);

		item2.setCellarItemId(12);
		item2.setQuantity(10);
		item2.setNotes("Really Good!!!");
	}

	@After
	public void tearDown()
	{
		item1 = null;
		item2 = null;
	}

	@Test
	public void getId()
	{
		assertEquals(item1.getId(), 0);
		assertEquals(item2.getId(), 12);
	}

	@Test
	public void getQuantity()
	{
		assertEquals(item1.getQuantity(), 0);
		assertEquals(item2.getQuantity(), 10);
	}

	@Test
	public void getNotes()
	{
		assertEquals(item1.getNotes(), null);
		assertEquals(item2.getNotes(), "Really Good!!!");
	}

	@Test
	public void getWineId()
	{
		assertEquals(item1.getWineId(), 0);
		assertEquals(item2.getWineId(), 100);
	}

	@Test
	public void setCellarItemId()
	{
		item1.setCellarItemId(67);
		assertEquals(item1.getId(), 67);
	}

	@Test
	public void setCellarItemIdWithNegative()
	{
		try
		{
			item1.setCellarItemId(-1);
			if (item1.getId() == -1)
				fail("Throw exception for negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setCellarItemIdWithCharacter()
	{
		try
		{
			item1.setCellarItemId('~');
			if (item1.getId() == '~')
				fail("Throw exception for characters");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setCellarItemIdWIthExistingId()
	{
		try
		{
			item1.setCellarItemId(120);
			item2.setCellarItemId(120);
			if (item1.getId() == item2.getId())
				fail("Throw exception: Two entries cannot have the same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setQuantity()
	{
		item1.setQuantity(1);
		assertEquals(item1.getQuantity(), 1);
	}

	@Test
	public void setNotes()
	{
		item1.setNotes("Nasty!!!");
		assertEquals(item1.getNotes(), "Nasty!!!");
	}

	@Test
	public void setWineId()
	{
		item1.setWineId(90);
		assertEquals(item1.getWineId(), 90);
	}

	@Test
	public void setWineIdWithNegative()
	{
		try
		{
			item1.setWineId(-1);
			if (item1.getWineId() == -1)
				fail("Throw exception for negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setWineIdWithCharacter()
	{
		try
		{
			item1.setWineId('~');
			if (item1.getWineId() == '~')
				fail("Throw exception for characters");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setWineIdWIthExistingId()
	{
		try
		{
			item1.setWineId(120);
			item2.setWineId(120);
			if (item1.getWineId() == item2.getWineId())
				fail("Throw exception: Two entries cannot have the same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void equals()
	{
		assertFalse(item1.equals(item2));
		item1.setCellarItemId(120);
		item2.setCellarItemId(120);
		assertTrue(item2.equals(item1));
	}
}