package com._500bottles.tests.object.cellar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.cellar.Cellar;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

public class CellarTest
{
	CellarItem item1;
	CellarItem item2;
	Wine wine1;
	Wine wine2;
	WineType red;
	Varietal merlot;
	Vineyard napa;
	Cellar cellar1;
	Cellar cellar2;

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

		cellar1 = new Cellar();
		cellar2 = new Cellar();

		cellar2.add(item1);
		cellar2.add(item2);

		cellar2.setCellarId(12);
		cellar2.setUserId(34);
	}

	@After
	public void tearDown()
	{
		cellar1 = null;
		cellar2 = null;
	}

	@Test
	public void getCollection()
	{
		Vector<CellarItem> collection;
		collection = cellar2.getCollection();
		assertTrue(collection.contains(item1));
	}

	@Test
	public void add()
	{
		cellar1.add(item2);
		assertTrue(cellar1.contains(item2));
	}

	@Test
	public void contains()
	{
		assertFalse(cellar1.contains(item1));
		assertTrue(cellar2.contains(item1));
		assertTrue(cellar2.contains(item2));
	}

	@Test
	public void size()
	{
		assertEquals(cellar1.size(), 0);
		assertEquals(cellar2.size(), 2);
	}

	@Test
	public void remove()
	{
		assertFalse(cellar1.remove(item1));
		assertEquals(cellar1.size(), 0);
		assertTrue(cellar2.remove(item1));
		assertEquals(cellar2.size(), 1);
	}

	@Test
	public void removebyId()
	{
		assertTrue(cellar2.removebyId(12));
	}

	@Test
	public void getById()
	{
		assertEquals(cellar2.getById(12).getQuantity(), 10);
		assertNull(cellar2.getById(-11));
	}

	@Test
	public void getCellarId()
	{
		assertEquals(cellar1.getCellarId(), 0);
		assertEquals(cellar2.getCellarId(), 12);
	}

	@Test
	public void setCellarId()
	{
		cellar1.setCellarId(34);
		assertEquals(cellar1.getCellarId(), 34);
	}

	@Test
	public void setCellarIdWithNegative()
	{
		try
		{
			cellar1.setCellarId(-1);
			if (cellar1.getCellarId() == -1)
				fail("Throw exception for negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setCellarIdWithCharacter()
	{
		try
		{
			cellar1.setCellarId('~');
			if (cellar1.getCellarId() == '~')
				fail("Throw exception for characters");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setCellarIdWIthExistingId()
	{
		try
		{
			cellar1.setCellarId(120);
			cellar2.setCellarId(120);
			if (cellar1.getCellarId() == cellar2.getCellarId())
				fail("Throw exception: Two entries cannot have the same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	public void getUserId()
	{
		assertEquals(cellar1.getUserId(), 0);
		assertEquals(cellar2.getUserId(), 34);
	}

	public void setUserId()
	{
		cellar1.setUserId(21);
		assertEquals(cellar1.getUserId(), 21);
	}

	@Test
	public void setUserIdWithNegative()
	{
		try
		{
			cellar1.setUserId(-1);
			if (cellar1.getUserId() == -1)
				fail("Throw exception for negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setUserIdWithCharacter()
	{
		try
		{
			cellar1.setUserId('~');
			if (cellar1.getUserId() == '~')
				fail("Throw exception for characters");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setUserIdWIthExistingId()
	{
		try
		{
			cellar1.setUserId(120);
			cellar2.setUserId(120);
			if (cellar1.getUserId() == cellar2.getUserId())
				fail("Throw exception: Two entries cannot have the same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void getCellarItemIdsAsJSONArray()
	{
		fail("Not yet implemented");
	}
}