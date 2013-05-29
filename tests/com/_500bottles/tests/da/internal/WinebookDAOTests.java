package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
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
	Entry entry1, entry2, entry3, entry4;

	@Before
	public void setUp()
	{
		entry1 = new Entry();
		entry3 = new Entry(0, "Title", "Content", new Date(), new Date());
		entry4 = new Entry();

		entry1.setTitle("Test Title");
		entry1.setContent("Test entry content.");
		entry1.setDateCreated(new Date());

		entry4.setTitle("Temecula");
		entry4.setContent("CSE 110 Wine Tasting.");
		entry4.setDateCreated(new Date());
	}

	@After
	public void tearDown()
	{
		entry1 = null;
	}

	@Test
	public void addEntry()
	{
		try
		{
			WinebookDAO.addEntry(entry1);
			WinebookDAO.addEntry(entry4);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteEntry()
	{
		try
		{
			WinebookDAO.deleteEntry(entry1);
		} catch (Exception e)
		{
			System.out.println("In deleteEntry: " + e.getMessage());
			fail(e.getMessage());
		}
	}

	public void deleteEntryWithNoMatchingEntry()
	{
		entry1.setEntryId(12);
		try
		{
			WinebookDAO.deleteEntry(entry1);
			fail();
		} catch (Exception e)
		{
			if (true)
			{
				System.out.println("In deleteEntryWithNoMatchingEntry: "
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
			if (entry2 == null)
			{
				WinebookDAO.deleteEntry(entry2);
				fail();
			}
		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In deleteEntryWithNull: " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void deleteEntryWithIdZero() throws DAException
	{
		try
		{
			if (entry3.getEntryId() == 0)
			{
				WinebookDAO.deleteEntry(entry3);
				fail();
			}
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In deleteEntryWithIdZero: "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void editEntry()
	{
		fail("Not yet implemented.");
	}

	@Test
	public void editEntryWithNull() throws NullPointerException, DAException
	{
		try
		{
			if (entry2 == null)
			{
				WinebookDAO.editEntry(entry2);
				fail();
			}
		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In editEntryWithNull: " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void editEntryWithIdZero()
	{
		try
		{
			if (entry3.getEntryId() == 0)
			{
				WinebookDAO.editEntry(entry3);
				fail();
			}
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In editEntryWithIdZero: " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryWithEntry() throws DAException, NullPointerException
	{
		Entry entry = WinebookDAO.getEntry(entry4);

		assertEquals(entry.getTitle(), "Temecula");
		assertEquals(entry.getContent(), "CSE 110 Wine Tasting.");
	}

	@Test
	public void getEntryWithEntryWithNull() throws NullPointerException,
			DAException
	{
		try
		{
			if (entry2 == null)
			{
				WinebookDAO.getEntry(entry2);
				fail();
			}
		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In getEntryWithEntryWithNull: "
						+ e.getMessage());

				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryWithEntryWithIdZero()
	{
		try
		{
			if (entry3.getEntryId() == 0)
			{
				WinebookDAO.getEntry(entry3);
				fail();
			}
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In getEntryWithEntryWithIdZero: "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getEntryWithEntryId()
	{
		fail("Not yet implemented.");
	}

	@Test
	public void getEntryWithEntryIdZero()
	{
		try
		{
			if (entry3.getEntryId() == 0)
			{
				WinebookDAO.getEntry(entry3.getEntryId());
				fail();
			}
		} catch (DAException e)
		{
			if (test)
			{
				System.out.println("In getEntryWithEntryIdZero: "
						+ e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void creatEntry()
	{
		fail("Not yet implemented.");
	}
}