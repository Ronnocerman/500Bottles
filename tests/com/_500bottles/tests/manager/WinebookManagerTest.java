package com._500bottles.tests.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import com._500bottles.manager.WinebookManager;
import com._500bottles.object.winebook.Entry;

public class WinebookManagerTest
{
	// TODO: Exceptions....

	@Test
	public void addEntry()
	{
		Entry entry1 = new Entry(0, "Title1", "Content1", new Date(),
				new Date());

		try
		{
			WinebookManager.addEntry(entry1);
		} catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void editEntry()
	{
		Entry ent;
		Entry entry2 = new Entry(0, "Title2", "Content2", new Date(),
				new Date());

		try
		{
			WinebookManager.addEntry(entry2);
			ent = WinebookManager.getEntry(entry2.getEntryId());

			ent.setTitle("New Title");
			ent.setContent("New Content");

			WinebookManager.editEntry(ent.getEntryId());
		} catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void removeEntry()
	{
		Entry entry3 = new Entry(0, "Title3", "Content3", new Date(),
				new Date());

		try
		{
			WinebookManager.addEntry(entry3);

			assertTrue(WinebookManager.removeEntry(entry3.getEntryId()));
		} catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getEntry()
	{
		Entry ent;
		Entry entry4 = new Entry(0, "Title4", "Content4", new Date(),
				new Date());

		try
		{
			WinebookManager.addEntry(entry4);
			ent = WinebookManager.getEntry(entry4.getEntryId());

			assertEquals(ent.getTitle(), "Title4");
			assertEquals(ent.getContent(), "Content4");
		} catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
}
