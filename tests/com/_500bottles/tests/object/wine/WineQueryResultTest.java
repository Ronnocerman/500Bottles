package com._500bottles.tests.object.wine;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQueryResult;

public class WineQueryResultTest
{
	WineQueryResult result1;
	Vector<Wine> wines;
	Wine wine1;
	Wine wine2;
	Wine wine3;
	Wine wine4;
	Wine wine5;

	@Before
	public void setUp()
	{
		wine1 = new Wine();
		wine2 = new Wine();
		wine3 = new Wine();
		wine4 = new Wine();
		wine5 = new Wine();

		wine1.setName("Wine1");
		wine2.setName("Wine2");
		wine3.setName("Wine3");
		wine4.setName("Wine4");
		wine5.setName("Wine5");

		wines = new Vector<Wine>();

		wines.add(wine1);
		wines.add(wine2);
		wines.add(wine3);
		wines.add(wine4);
		wines.add(wine5);

		result1 = new WineQueryResult(wines);
	}

	@After
	public void tearDown()
	{
		result1 = null;
	}

	@Test
	public void getResultsCount()
	{
		assertEquals(result1.getResultsCount(), 0);
	}

	@Test
	public void getWines()
	{
		Vector<Wine> list = result1.getWines();

		for (int i = 0; i < list.size(); i++)
			assertEquals(list.get(i).getName(), "Wine" + (i + 1));
	}

	@Test
	public void getIterator()
	{
		Wine wine;
		int i = 0;
		Iterator<Wine> it = result1.getIterator();

		while (it.hasNext())
		{
			wine = it.next();
			assertEquals(wine.getName(), "Wine" + (i + 1));
			i++;

		}
	}
}
