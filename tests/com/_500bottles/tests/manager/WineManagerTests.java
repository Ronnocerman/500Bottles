package com._500bottles.tests.manager;

import com._500bottles.manager.WineManager;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(JUnit4.class)
public class WineManagerTests {

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

		Iterator it = r.getIterator();

		while (it.hasNext())
			System.out.println(it.next().toString());
	}

}
