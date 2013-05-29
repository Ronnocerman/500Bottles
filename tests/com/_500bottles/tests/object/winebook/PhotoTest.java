package com._500bottles.tests.object.winebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.winebook.Photo;

public class PhotoTest
{
	Photo photo;
	Photo photo1;
	Photo photo2;

	@Before
	public void setUp()
	{
		photo = new Photo();
		photo1 = new Photo(1, "test1.jpg");
		photo2 = new Photo(2, "test2.jpg");
	}

	@After
	public void tearDown()
	{
		photo = null;
		photo1 = null;
		photo2 = null;
	}

	@Test
	public void getId()
	{
		assertEquals(photo.getId(), 0);
		assertEquals(photo1.getId(), 1);
		assertEquals(photo2.getId(), 2);
	}

	@Test
	public void setId()
	{

		photo.setId(999999999);
		assertEquals(photo.getId(), 999999999);
	}

	@Test
	public void setIdWithNegative()
	{
		try
		{
			photo1.setId(-1);
			if (photo1.getId() == -1)
				fail("Throw exception for negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setIdWithCharacter()
	{
		try
		{
			photo2.setId('~');
			if (photo2.getId() == '~')
				fail("Throw exception for characters");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setIdWithExistingId()
	{
		try
		{
			photo.setId(1);
			photo1.setId(1);
			if (photo.getId() == photo1.getId())
				fail("Throw exception: Two photos cannot have same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void getFilename()
	{
		assertEquals(photo.getFilename(), null);
		assertEquals(photo1.getFilename(), "test1.jpg");
		assertEquals(photo2.getFilename(), "test2.jpg");
	}

	@Test
	public void setFilename()
	{
		photo.setFilename("change.jpg");
		assertEquals(photo.getFilename(), "change.jpg");
		photo1.setFilename("change1.jpg");
		assertEquals(photo1.getFilename(), "change1.jpg");
		photo2.setFilename("change2.jpg");
		assertEquals(photo2.getFilename(), "change2.jpg");
	}
}