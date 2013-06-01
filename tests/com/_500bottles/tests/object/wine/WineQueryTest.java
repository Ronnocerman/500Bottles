package com._500bottles.tests.object.wine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	WineType type;
	Date minYear;
	Date maxYear;
	Varietal moscato;
	Vineyard napa;

	@Before
	public void setUp()
	{
		query1 = new WineQuery();
		query2 = new WineQuery();
		id = new Vector<Long>();
		varietal = new Vector<Varietal>();
		vineyard = new Vector<Vineyard>();
		type = new WineType();
		minYear = new Date();
		maxYear = new Date();
		moscato = new Varietal();
		napa = new Vineyard();

		id.add(new Long(100));
		id.add(new Long(101));
		type.setWineType("White");
		moscato.setGrapeType("Moscato");
		varietal.add(moscato);
		napa.setName("Some place");
		vineyard.add(napa);

		query2.setIds(id);
		query2.setTextQuery("Something");
		query2.setNameContains("NSFW");
		query2.setDescriptionContains("Good");
		query2.setDistance(20);
		query2.setType(type);
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
	public void setIdWithOther()
	{
		fail("Need to check for illegal arguments");
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
	public void setDistanceWithNegative()
	{
		fail("Need to check for illegal arguments");
	}

	@Test
	public void getType()
	{
		assertEquals(query1.getType(), null);
		assertEquals(query2.getType().getWineType(), "White");
	}

	@Test
	public void setType()
	{
		WineType red = new WineType();
		red.setWineType("Red");
		query1.setType(red);
		assertEquals(query1.getType().getWineType(), "Red");
	}

	@Test
	public void getMinYear()
	{
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		assertEquals(query1.getMinYear(), null);

		date = sdf.format(query2.getMinYear());
		assertEquals(date, "2013");
	}

	@Test
	public void setMinYear()
	{
		@SuppressWarnings("deprecation")
		Date newDate = new Date(84, 10, 18);
		query1.setMinYear(newDate);

		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		date = sdf.format(query1.getMinYear());
		assertEquals(date, "1984");
	}

	@Test
	public void getMaxYear()
	{
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		assertEquals(query1.getMaxYear(), null);

		date = sdf.format(query2.getMaxYear());
		assertEquals(date, "2013");
	}

	@Test
	public void setMaxYear()
	{
		@SuppressWarnings("deprecation")
		Date newDate = new Date(112, 10, 18);
		query1.setMaxYear(newDate);

		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		date = sdf.format(query1.getMaxYear());
		assertEquals(date, "2012");
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
	public void setMinRatingWithOther()
	{
		fail("Need to check for illegal arguments");
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

	@Test
	public void setMaxRatingWithOther()
	{
		fail("Need to check for illegal arguments");
	}
}