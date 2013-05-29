package com._500bottles.tests.object.winebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.wine.Wine;
import com._500bottles.object.winebook.Entry;
import com._500bottles.object.winebook.Photo;

public class EntryTest
{
	Entry entry;
	Entry entry1;
	Entry entry2;
	Date d;

	Photo photo1;
	Photo photo2;
	Photo photo3;
	Vector<Photo> photos;

	Vector<Wine> wines;
	Wine wine1;
	Wine wine2;
	Wine wine3;

	@Before
	public void setUp()
	{
		photo1 = new Photo(1, "file1");
		photo2 = new Photo(2, "file2");
		photo3 = new Photo(3, "file3");
		photos = new Vector<Photo>();
		photos.add(photo1);
		photos.add(photo2);
		photos.add(photo3);

		wines = new Vector<Wine>();
		wine1 = new Wine();
		wine2 = new Wine();
		wine3 = new Wine();
		wine1.setId(1);
		wine2.setId(2);
		wine3.setId(3);
		wines.add(wine1);
		wines.add(wine2);
		wines.add(wine3);

		d = new Date();

		entry = new Entry();
		entry1 = new Entry(1, "Entry 1", "My most favorite red wine.", d, d);
		entry2 = new Entry(2, "Entry 2", "My most favorite white wine.",
				photos, wines, d, d);
	}

	@After
	public void tearDown()
	{
		entry = null;
		entry1 = null;
		entry2 = null;
	}

	@Test
	public void getEntryId()
	{
		assertEquals(entry.getEntryId(), 0);
		assertEquals(entry1.getEntryId(), 1);
		assertEquals(entry2.getEntryId(), 2);
	}

	@Test
	public void getTitle()
	{
		assertEquals(entry.getTitle(), null);
		assertEquals(entry1.getTitle(), "Entry 1");
		assertEquals(entry2.getTitle(), "Entry 2");
	}

	@Test
	public void getContent()
	{
		assertEquals(entry.getContent(), "");
		assertEquals(entry1.getContent(), "My most favorite red wine.");
		assertEquals(entry2.getContent(), "My most favorite white wine.");
	}

	@Test
	public void getDateCreated()
	{
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		date = sdf.format(entry.getDateCreated());
		assertEquals(date, "2013-05-22");

		date = sdf.format(entry1.getDateCreated());
		assertEquals(date, "2013-05-22");

		date = sdf.format(entry2.getDateCreated());
		assertEquals(date, "2013-05-22");
	}

	@Test
	public void getLastDateEdited()
	{
		String date;
		String today;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		today = sdf.format(d);

		date = sdf.format(entry.getDateLastEdited());
		assertEquals(date, "2013-05-22");

		date = sdf.format(entry1.getDateLastEdited());
		assertEquals(date, "2013-05-22");

		date = sdf.format(entry2.getDateLastEdited());
		assertEquals(date, "2013-05-22");
	}

	@Test
	public void getArrayOfPhotos()
	{
		Photo[] photo_array = entry2.getArrayOfPhotos();

		assertEquals(photo_array.length, 3);
		assertEquals(photo_array[0], photo1);
		assertEquals(photo_array[1], photo2);
		assertEquals(photo_array[2], photo3);
	}

	@Test
	public void getPhotoIdsAsJSONArray()
	{
		fail("Not yet implenmented");
	}

	@Test
	public void getWineIdsAsJSONArray()
	{
		fail("Not yet implenmented");
	}

	@Test
	public void setEntryId()
	{
		entry.setEntryId(999999999);
		assertEquals(entry.getEntryId(), 999999999);
	}

	@Test
	public void setEntryIdWithNegative()
	{
		try
		{
			entry.setEntryId(-1);
			if (entry.getEntryId() == -1)
				fail("Throw exception for negative numbers");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setEntryIdWithCharacter()
	{
		try
		{
			entry1.setEntryId('~');
			if (entry1.getEntryId() == '~')
				fail("Throw exception for characters");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setEntryIdWIthExistingId()
	{
		try
		{
			entry1.setEntryId(120);
			entry2.setEntryId(120);
			if (entry1.getEntryId() == entry2.getEntryId())
				fail("Throw exception: Two entries cannot have the same Id");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setTitle()
	{
		entry.setTitle("Crunk");
		assertEquals(entry.getTitle(), "Crunk");

		entry1.setTitle("Moscato with Mom");
		assertEquals(entry1.getTitle(), "Moscato with Mom");
	}

	@Test
	public void setContent()
	{
		entry.setContent("The best wine");
		assertEquals(entry.getContent(), "The best wine");
	}

	@Test
	public void setDateCreated()
	{
		Date newDate = new Date();
		entry.setDateCreated(newDate);

		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		date = sdf.format(entry.getDateCreated());
		assertEquals(date, "2013-05-22");
	}

	@Test
	public void setDateLastEdited()
	{
		Date newDate = new Date();
		entry.setDateCreated(newDate);

		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		date = sdf.format(entry.getDateCreated());
		assertEquals(date, "2013-05-22");
	}

	@Test
	public void addPhoto()
	{
		Photo newPhoto = new Photo(345, "new.txt");

		entry2.addPhoto(photo1);
		assertEquals(wines.size(), 3);

		entry2.addPhoto(newPhoto);
		assertEquals(photos.size(), 4);
		;
	}

	@Test
	public void addPhoto_JSON()
	{
		fail("Not yet implenmented");
	}

	@Test
	public void removePhoto()
	{
		Photo newPhoto = new Photo(10, "newFile.txt");

		if (!entry2.removePhoto(newPhoto))
			assertEquals(photos.size(), 3);

		if (entry2.removePhoto(photo2))
			assertEquals(photos.size(), 2);
	}

	@Test
	public void addeWine()
	{
		Wine newWine = new Wine();

		entry2.addWine(wine1);
		assertEquals(wines.size(), 3);

		entry2.addWine(newWine);
		assertEquals(wines.size(), 4);
	}

	@Test
	public void removeWine()
	{
		Wine newWine = new Wine();
		newWine.setId(123);

		if (!entry2.removeWine(newWine))
			assertEquals(wines.size(), 3);

		if (entry2.removeWine(wine2))
			assertEquals(wines.size(), 2);
	}
}