package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Test;

import com._500bottles.da.internal.WinebookDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.winebook.Entry;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/18/13 Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */

public class WinebookDAOTests
{
	boolean test = false;
	Entry entry1, entry2, entry3, nullEntry;

	// @Before
	// public void setUp()
	// {
	// entry1 = new Entry();
	// entry3 = new Entry(0, "Title", "Content", new Date(), new Date());
	// entry4 = new Entry();
	//
	// entry1.setTitle("Test Title");
	// entry1.setContent("Test entry content.");
	// entry1.setDateCreated(new Date());
	//
	// entry4.setTitle("Temecula");
	// entry4.setContent("CSE 110 Wine Tasting.");
	// entry4.setDateCreated(new Date());
	// }
	//
	@After
	public void tearDown()
	{
		entry1 = null;
		entry2 = null;
		entry3 = null;
		nullEntry = null;
	}

	@Test
	public void addEntry() throws DAException
	{
		Entry newEntry1;
		Entry newEntry2;

		entry1 = new Entry();
		entry2 = new Entry();

		entry1.setTitle("Test Title");
		entry1.setContent("Test entry content.");
		entry1.setDateCreated(new Date());

		entry2.setTitle("Temecula");
		entry2.setContent("CSE 110 Wine Tasting.");
		entry2.setDateCreated(new Date());
		try
		{
			newEntry1 = WinebookDAO.addEntry(entry1);
			newEntry2 = WinebookDAO.addEntry(entry2);

			assertEquals(newEntry1.getTitle(), "Test Title");
			assertEquals(newEntry1.getContent(), "Test entry content.");
			assertEquals(newEntry2.getTitle(), "Temecula");
			assertEquals(newEntry2.getContent(), "CSE 110 Wine Tasting.");
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In addEntry(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void addNullEntry() throws DAException, NullPointerException
	{
		try
		{
			WinebookDAO.addEntry(nullEntry);
		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In addNullEntry(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void addEntryWithIdZero()
	{
		entry3 = new Entry(0, "Initialized with Id zero",
				"Id should not be zero.", new Date(), new Date());
		try
		{
			WinebookDAO.addEntry(entry3);
		} catch (DAException e)
		{
			if (test)
			{
				System.out
						.println("In addEntryWithIdZero(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void deleteEntry() throws DAException
	{
		entry1 = new Entry();
		entry1.setTitle("Deletion");
		entry1.setContent("You should not be able to see this");
		entry1.setDateCreated(new Date());

		try
		{
			WinebookDAO.addEntry(entry1);
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In deleteEntry(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
		try
		{
			WinebookDAO.deleteEntry(entry1);
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In deleteEntry(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void deleteEntryWithNoMatchingEntry() throws DAException
	{
		Entry entry3 = new Entry();
		entry3.setEntryId(100);
		try
		{
			WinebookDAO.deleteEntry(entry3);
			fail();
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In deleteEntryWithNoMatchingEntry(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void deleteEntryWithNull() throws NullPointerException, DAException
	{
		try
		{
			if (nullEntry == null)
			{
				WinebookDAO.deleteEntry(nullEntry);
				fail();
			}
		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In deleteEntryWithNull(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void deleteEntryWithIdZero() throws DAException
	{
		entry3 = new Entry(0, "Title", "Content", new Date(), new Date());
		try
		{
			WinebookDAO.deleteEntry(entry3);
			fail();

		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In deleteEntryWithIdZero(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void editEntry() throws DAException
	{
		entry3 = new Entry();
		entry3.setTitle("Unedited");
		entry3.setContent("Original");
		entry3.setDateCreated(new Date());

		try
		{
			WinebookDAO.addEntry(entry3);

			entry3.setTitle("Edited");
			entry3.setContent("Changed");

			WinebookDAO.editEntry(entry3);
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In editEntry(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void editEntryWithNull() throws NullPointerException, DAException
	{
		try
		{
			WinebookDAO.editEntry(nullEntry);
			fail();
		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In editEntryWithNull(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void editEntryWithIdZero() throws DAException
	{
		entry3 = new Entry(0, "Title", "Content", new Date(), new Date());

		try
		{
			WinebookDAO.editEntry(entry3);
			fail();

		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In editEntryWithIdZero(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryWithEntry() throws DAException, NullPointerException
	{
		entry3 = new Entry();
		entry3.setTitle("Ramona");
		entry3.setContent("Our next Wine Tasting trip...");
		entry3.setDateCreated(new Date());
		try
		{
			WinebookDAO.addEntry(entry3);
			Entry entry = WinebookDAO.getEntry(entry3);
			assertEquals(entry.getTitle(), "Ramona");
			assertEquals(entry.getContent(), "Our next Wine Tasting trip...");

		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In getEntryWithEntry(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryWithNullEntry() throws NullPointerException,
			DAException
	{
		try
		{
			WinebookDAO.getEntry(nullEntry);
			fail();

		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In getEntryWithNullEntry(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryWithEntryIdZero() throws DAException
	{
		entry3 = new Entry(0, "Title", "Content", new Date(), new Date());
		try
		{
			WinebookDAO.getEntry(entry3);
			fail();
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In getEntryWithEntryIdZero(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryWithEntryId() throws DAException
	{
		entry1 = new Entry();
		entry1.setTitle("CSE Lab");
		entry1.setContent("No wine!!!");
		entry1.setDateCreated(new Date());

		try
		{
			WinebookDAO.addEntry(entry1);
			Entry entry = WinebookDAO.getEntry(entry1.getEntryId());

			assertEquals(entry.getTitle(), "CSE Lab");
			assertEquals(entry.getContent(), "No wine!!!");
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In getEntryWithEntryId(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryWithEntryIdSetToZero() throws DAException
	{
		entry3 = new Entry(0, "Title", "Content", new Date(), new Date());

		try
		{
			WinebookDAO.getEntry(entry3.getEntryId());
			fail();

		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In getEntryWithEntryIdSetToZero(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryByEntryIdWithNullEntry() throws NullPointerException,
			DAException
	{
		try
		{
			WinebookDAO.getEntry(nullEntry.getEntryId());
			fail();

		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In getEntryAsEntryIdWithNullEntry(): "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}
}