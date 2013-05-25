package com._500bottles.tests.da.internal;

import com._500bottles.da.internal.WinebookDAO;
import com._500bottles.object.winebook.Entry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/18/13
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class WinebookDAOTests {

	@Test
	public void addEntry()
	{
		Entry entry = new Entry();

		entry.setTitle("Test Title");
		entry.setContent("Test entry content.");
		entry.setDateCreated(new Date());
		try {
			WinebookDAO.addEntry(entry);
		} catch (Exception e) {
			fail( e.getMessage() );
		}

	}

	@Test
	public void deleteEntry()
	{

	}

	@Test
	public void getEntry()
	{

	}
}
