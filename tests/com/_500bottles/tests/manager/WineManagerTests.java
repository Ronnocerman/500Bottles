package com._500bottles.tests.manager;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.wine.exception.InvalidCategory;
import com._500bottles.da.external.wine.exception.InvalidOtherParameters;
import com._500bottles.exception.da.DAException;
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

	// TODO
	@Test
	public void searchWineTest() throws DAException, InvalidCategory,
			InvalidSort, InvalidOtherParameters, IOException, ParseException
	{
		q = new WineQuery();
		q.setTextQuery("merlot");
		q.setOffset(2);
		q.setSize(20);
		WineQueryResult r = WineManager.searchWine(q);

		Iterator<Wine> it = r.getIterator();

		while (it.hasNext())
			System.out.println(it.next().toString());
	}

}
