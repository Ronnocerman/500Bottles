package com._500bottles.tests.object.wine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

public class WineQueryTest
{
	WineQuery query1;
	WineQuery query2;
	Vector<Long> id;
	Vector<Varietal> varietal;
	Vector<Vineyard> vineyard;
	Vector<WineType> wineType;
	long minYear;
	long maxYear;
	Varietal moscato;
	Vineyard napa;
	WineType red;

	@Before
	public void setUp()
	{
		query1 = new WineQuery();
		query2 = new WineQuery();
		id = new Vector<Long>();
		varietal = new Vector<Varietal>();
		vineyard = new Vector<Vineyard>();
		wineType = new Vector<WineType>();
		minYear = 0;
		maxYear = 0;
		moscato = new Varietal();
		napa = new Vineyard();
		red = new WineType("Red");

		id.add(new Long(100));
		id.add(new Long(101));
		moscato.setGrapeType("Moscato");
		varietal.add(moscato);
		napa.setName("Some place");
		vineyard.add(napa);
		wineType.add(red);

		query2.setIds(id);
		query2.setTextQuery("Something");
		query2.setNameContains("NSFW");
		query2.setDescriptionContains("Good");
		query2.setDistance(20);
		query2.setType(wineType);
		query2.setMinYear(minYear);
		query2.setMaxYear(maxYear);
		query2.setVarietal(varietal);
		query2.setVineyard(vineyard);
		query2.setMinRating(0.5);
		query2.setMaxRating(4.5);
	}

	@After
	public void tearDown()
	{
		query1 = null;
		query2 = null;
	}

	@Test
	public void getIds()
	{
		assertTrue(query1.getIds().isEmpty());
		assertFalse(query2.getIds().isEmpty());
		for (int i = 0; i < query2.getIds().size(); i++)
			assertEquals(query2.getIds().get(i).longValue(), 100 + i);
	}

	@Test
	public void setIds()
	{
		Vector<Long> ids = new Vector<Long>();
		ids.add(new Long(12));
		query1.setIds(ids);
		assertEquals(query1.getIds().get(0).longValue(), 12);
	}

	@Test
	public void getTextQuery()
	{
		assertEquals(query1.getTextQuery(), "");
		assertEquals(query2.getTextQuery(), "Something");
	}

	@Test
	public void setTextQuery()
	{
		query1.setTextQuery("Somewhere");
		assertEquals(query1.getTextQuery(), "Somewhere");
	}

	@Test
	public void getNameContains()
	{
		assertEquals(query1.getNameContains(), "");
		assertEquals(query2.getNameContains(), "NSFW");
	}

	@Test
	public void setNameContains()
	{
		query1.setNameContains("Merlot");
		assertEquals(query1.getNameContains(), "Merlot");
	}

	@Test
	public void getDescriptionContains()
	{
		assertEquals(query1.getDescriptionContains(), "");
		assertEquals(query2.getDescriptionContains(), "Good");
	}

	@Test
	public void setDescriptionContains()
	{
		query1.setDescriptionContains("Bad");
		assertEquals(query1.getDescriptionContains(), "Bad");
	}

	@Test
	public void getDistance()
	{
		assertEquals(query1.getDistance(), -1);
		assertEquals(query2.getDistance(), 20);
	}

	@Test
	public void setDistance()
	{
		query1.setDistance(10);
		assertEquals(query1.getDistance(), 10);
	}

	@Test
	public void getType()
	{
		assertTrue(query1.getType().isEmpty());
		assertEquals(query2.getType().get(0).getWineType(), "Red");
	}

	@Test
	public void setType()
	{
		Vector<WineType> newType = new Vector<WineType>();
		WineType white = new WineType("White");
		newType.add(white);
		query1.setType(newType);
		assertEquals(query1.getType().get(0).getWineType(), "White");
	}

	@Test
	public void getMinYear()
	{
		assertEquals(query1.getMinYear(), 0);
		assertEquals(query2.getMinYear(), 0);
	}

	@Test
	public void setMinYear()
	{
		query1.setMinYear(1984);

		assertEquals(query1.getMinYear(), 1984);
	}

	@Test
	public void getMaxYear()
	{
		assertEquals(query1.getMaxYear(), 0);
		assertEquals(query2.getMaxYear(), 0);
	}

	@Test
	public void setMaxYear()
	{
		query1.setMaxYear(2012);

		assertEquals(query1.getMaxYear(), 2012);
	}

	@Test
	public void getVarietal()
	{
		assertTrue(query1.getVarietal().isEmpty());
		assertFalse(query2.getVarietal().isEmpty());
		assertEquals(query2.getVarietal().get(0).getGrapeType(), "Moscato");
	}

	@Test
	public void setVarietal()
	{
		Vector<Varietal> newVarietal = new Vector<Varietal>();
		Varietal shiraz = new Varietal();
		shiraz.setGrapeType("Shiraz");
		newVarietal.add(shiraz);
		query1.setVarietal(newVarietal);
		assertEquals(query1.getVarietal().get(0).getGrapeType(), "Shiraz");
	}

	@Test
	public void getVineyard()
	{
		assertTrue(query1.getVineyard().isEmpty());
		assertFalse(query2.getVineyard().isEmpty());
		assertEquals(query2.getVineyard().get(0).getName(), "Some place");
	}

	@Test
	public void setVineyard()
	{
		Vector<Vineyard> newVineyard = new Vector<Vineyard>();
		Vineyard temecula = new Vineyard();
		temecula.setName("Wilson Creek");
		newVineyard.add(temecula);
		query1.setVineyard(newVineyard);
		assertEquals(query1.getVineyard().get(0).getName(), "Wilson Creek");
	}

	@Test
	public void getMinRating()
	{
		assertEquals(query1.getMinRating(), -1.0, 10 ^ -12);
		assertEquals(query2.getMinRating(), 0.5, 10 ^ -12);
	}

	@Test
	public void setMinRating()
	{
		query1.setMinRating(1.0);
		assertEquals(query1.getMinRating(), 1.0, 10 ^ -12);
	}

	@Test
	public void getMaxRating()
	{
		assertEquals(query1.getMaxRating(), -1.0, 10 ^ -12);
		assertEquals(query2.getMaxRating(), 4.5, 10 ^ -12);
	}

	@Test
	public void setMaxRating()
	{
		query1.setMaxRating(5);
		assertEquals(query1.getMaxRating(), 5, 10 ^ -12);
	}
}