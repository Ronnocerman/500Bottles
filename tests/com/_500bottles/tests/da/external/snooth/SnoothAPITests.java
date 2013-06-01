package com._500bottles.tests.da.external.snooth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.external.snooth.Color;
import com._500bottles.da.external.snooth.Country;
import com._500bottles.da.external.snooth.Language;
import com._500bottles.da.external.snooth.ProductType;
import com._500bottles.da.external.snooth.SnoothDAO;
import com._500bottles.da.external.snooth.WineDetails;
import com._500bottles.da.external.snooth.WineSearch;
import com._500bottles.da.external.snooth.exception.InvalidColor;
import com._500bottles.da.external.snooth.exception.InvalidCountry;
import com._500bottles.da.external.snooth.exception.InvalidLanguage;
import com._500bottles.da.external.snooth.exception.InvalidProductType;
import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.snooth.exception.InvalidWineDetails;
import com._500bottles.da.external.snooth.exception.InvalidWineSearch;
import com._500bottles.da.external.snooth.sort.Sort;
import com._500bottles.da.external.snooth.sort.SortPrice;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/22/13 Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class SnoothAPITests
{

	@Test
	public void testBasicSearch()
	{
		WineSearch s = null;

		try
		{
			s = new WineSearch("BIODYNAMIC WINES");
			Sort sort = new SortPrice();
			s.setSort(sort);

			SnoothDAO.doSearch(s);
		} catch (InvalidWineSearch e)
		{

		}
	}

	@Ignore
	public void testWineSearchToString()
	{
		try
		{
			new WineSearch("cabernet");
		} catch (InvalidWineSearch e)
		{
			fail("This search should not throw an exception.");
		}
	}

	@Test
	public void wineSearchUrl()
	{
		WineSearch s = null;

		try
		{
			// Test just a plain text query.
			s = new WineSearch("cabernet");
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet",
					s.toString());

			// Test with modified first-result number.
			s.setFirstResult(5);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&f=5",
					s.toString());

			// Modify the number of results.
			s.setNumberOfResults(20);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&f=5&n=20",
					s.toString());

			// Modify the default availability.
			s.setAvailable(true);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&f=5&n=20&a=1",
					s.toString());

			// Modify the default product type.
			try
			{
				ProductType t = new ProductType("wine");
				s.setProductType(t);
				assertEquals(
						"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&f=5&n=20&a=1&t=wine",
						s.toString());
			} catch (InvalidProductType e)
			{
				// The product type should be valid.
				fail();
			}

			// Change the default color.
			try
			{
				Color c = new Color("white");
				s.setColor(c);
				assertEquals(
						"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&f=5&n=20&a=1&t=wine&color=white",
						s.toString());
			} catch (InvalidColor e)
			{
				// The color should be valid.
				fail();
			}

			// Start over with a new search since the url string is
			// getting unruly.
			s = new WineSearch("cabernet");
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet",
					s.toString());

			// Test the store id string.
			s.setStoreId(5);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&m=5",
					s.toString());

			// Test the country.
			try
			{
				Country c = new Country("us");
				s.setCountry(c);
				assertEquals(
						"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&m=5&c=us",
						s.toString());
			} catch (InvalidCountry e)
			{
				// This should be a valid country.
				fail();
			}

			// Test the zipcode.
			s.setZipCode(92101);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&m=5&c=us&z=92101",
					s.toString());

			// Test the latitude.
			s.setLatitude(101.11);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&m=5&c=us&z=92101&lat=101.11",
					s.toString());

			// Test the longitude
			s.setLongitude(100.99);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&m=5&c=us&z=92101&lat=101.11&lng=100.99",
					s.toString());

			// Start over with a new search for testing sort.
			s = new WineSearch("cabernet");
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet",
					s.toString());

			try
			{
				SortPrice sort = new SortPrice("asc");
				s.setSort(sort);
				assertEquals(
						"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&s=price+asc",
						s.toString());
			} catch (InvalidSort e)
			{
				// This should be a valid sort.
				fail();
			}

			s.setMinPrice(19.99);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&s=price+asc&mp=19.99",
					s.toString());

			s.setMaxPrice(29.99);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&s=price+asc&mp=19.99&xp=29.99",
					s.toString());

			s.setMinRating(1);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&s=price+asc&mp=19.99&xp=29.99&mr=1.0",
					s.toString());

			s.setMaxRating(4);
			assertEquals(
					"http://api.snooth.com/wines/?akey=jj12t19hqxwp9is104avz5qn92zl2sq0cgaq7v6e4pmfmelq&q=cabernet&s=price+asc&mp=19.99&xp=29.99&mr=1.0&xr=4.0",
					s.toString());

			try
			{
				Language l = new Language("en");
				s.setLanguage(l);
			} catch (InvalidLanguage e)
			{
				// This should be a valid language.
				fail();
			}

		} catch (InvalidWineSearch e)
		{

		}
	}

	@Ignore
	public void testWineDetails()
	{
		try
		{
			WineDetails d;

			d = new WineDetails(
					"felton-road-block-3-central-otago-biodynamic-2011");
			SnoothDAO.getWineDetails(d);

			d = new WineDetails(
					"chateau-thenac-red-bordeaux-blend-bergerac-rouge-fleur-du-perigord-2007");
			SnoothDAO.getWineDetails(d);

			d = new WineDetails(
					"elderton-ashmead-cabernet-wines-table-wine-australia-2001");
			SnoothDAO.getWineDetails(d);

			d = new WineDetails(
					"casa-lapostolle-cuvee-alexandre-cabernet-chile-2009");
			SnoothDAO.getWineDetails(d);

			d = new WineDetails(
					"beringer-vineyards-cabernet-franc-napa-valley-leaning-oak-1998");
			SnoothDAO.getWineDetails(d);

		} catch (InvalidWineDetails e)
		{
			fail();
		}

	}

}
