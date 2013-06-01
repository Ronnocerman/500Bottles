package com._500bottles.tests.manager;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.manager.WineManager;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

/**
 *
 */
@RunWith(JUnit4.class)
public class WineManagerTests
{

	WineQuery q;

	@Ignore
	public void testSetup()
	{

	}

	@Test
	public void searchWineTest()
	{
		q = new WineQuery();
		q.setTextQuery("cabernet");
		WineQueryResult r = WineManager.searchWine(q);

		Iterator<Wine> it = r.getIterator();

		while (it.hasNext())
			System.out.println(it.next().toString());
	}

}
