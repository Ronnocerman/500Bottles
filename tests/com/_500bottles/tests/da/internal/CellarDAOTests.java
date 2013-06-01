package com._500bottles.tests.da.internal;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.CellarDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/18/13 Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class CellarDAOTests
{

	@Test
	public void testInsertCellarItem()
	{
		Wine wine = new Wine();
		CellarItem item = new CellarItem(wine);
		item.setQuantity(1);
		item.setNotes("This is good wine");
		item.setWineId(15);

		try
		{
			CellarDAO.addCellarItem(10, item);
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void testDeleteCellarItem()
	{
		Wine wine = new Wine();
		CellarItem item = new CellarItem(wine);
		item.setQuantity(999);
		item.setNotes("If this shows up you're fucked");
		item.setWineId(20);

		try
		{
			item = CellarDAO.addCellarItem(15, item);
		} catch (Exception e)
		{
			fail();
		}

		try
		{
			CellarDAO.deleteCellarItem(2);
			// CellarDAO.deleteCellarItem(6);
			// CellarDAO.deleteCellarItem(8);
		} catch (Exception e)
		{
			fail();
		}

	}

	// tests get AND edit
	@SuppressWarnings("null")
	@Test
	public void testEditCellarItem()
	{
		CellarItem item = null;
		item.setNotes("Imma buy 4 moe of these");
		item.setQuantity(4);
		item.setUserRating(10);
		item.setWineId(2);

		try
		{
			CellarDAO.addCellarItem(57, item);
		} catch (DAException e1)
		{
			fail();
		}

		try
		{
			item = CellarDAO.getCellarItem(item.getId());
		} catch (Exception e)
		{
			fail();
		}

		try
		{
			item.setNotes("I GOT 8 NOW cus its too good");
			item.setQuantity(8);
			item = CellarDAO.editCellarItem(item);
		} catch (Exception e)
		{
			fail();
		}
	}

}
