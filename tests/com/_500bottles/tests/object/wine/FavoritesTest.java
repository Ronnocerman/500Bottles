package com._500bottles.tests.object.wine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.wine.Favorites;

public class FavoritesTest
{
	Favorites fav1;
	Favorites fav2;

	@Before
	public void setUp()
	{
		fav1 = new Favorites();
		fav2 = new Favorites();

		fav2.setfavoritesId(10);
		fav2.setWineId(5);
	}

	@After
	public void tearDown()
	{
		fav1 = null;
		fav2 = null;
	}

	@Test
	public void getfavoritesId()
	{
		assertEquals(fav1.getfavoritesId(), 0);
		assertEquals(fav2.getfavoritesId(), 10);
	}

	@Test
	public void getWineId()
	{
		assertEquals(fav1.getWineId(), 0);
		assertEquals(fav2.getWineId(), 5);
	}

	@Test
	public void setfavoritesId()
	{
		fav1.setfavoritesId(1);
		assertEquals(fav1.getfavoritesId(), 1);
	}

	@Test
	public void setfavoritesIdWithNegative()
	{
		try
		{
			fav1.setfavoritesId(-1);
			if (fav1.getfavoritesId() == -1)
				fail("Throw exception for negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setfavoritesIdWithCharacter()
	{
		try
		{
			fav2.setfavoritesId('~');
			if (fav2.getfavoritesId() == '~')
				fail("Throw exception for characters");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setfavoritesIdWIthExistingId()
	{
		try
		{
			fav1.setfavoritesId(120);
			fav2.setfavoritesId(120);
			if (fav1.getfavoritesId() == fav2.getfavoritesId())
				fail("Throw exception: Two entries cannot have the same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setWineId()
	{
		fav1.setWineId(456);
		assertEquals(fav1.getWineId(), 456);
	}

	@Test
	public void setWineIdWithNegative()
	{
		try
		{
			fav1.setWineId(-1);
			if (fav1.getWineId() == -1)
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
			fav2.setWineId('~');
			if (fav2.getWineId() == '~')
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
			fav1.setWineId(120);
			fav2.setWineId(120);
			if (fav1.getWineId() == fav2.getWineId())
				fail("Throw exception: Two entries cannot have the same Id");
		} catch (Exception e)
		{
			fail();
		}
	}
}