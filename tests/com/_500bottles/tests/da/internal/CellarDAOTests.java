package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Vector;

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
	public void addCellarItem() throws DAException
	{
		Wine wine = new Wine();
		CellarItem item = new CellarItem(wine);
		item.setQuantity(1);
		item.setNotes("This is good wine");
		item.setWineId(15);

		try
		{
			CellarDAO.addCellarItem(46, item);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteCellarItem() throws DAException
	{
		Wine wine = new Wine();
		CellarItem item = new CellarItem(wine);
		item.setQuantity(999);
		item.setNotes("If this shows up you're fucked");
		item.setWineId(20);

		try
		{
			item = CellarDAO.addCellarItem(15, item);

			assertTrue(CellarDAO.deleteCellarItem(item.getId()));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void editCellarItem() throws DAException
	{
		Wine wine = new Wine();
		wine.setId(4);
		CellarItem item = new CellarItem(wine);
		item.setNotes("Imma buy 4 moe of these");
		item.setQuantity(4);
		item.setUserRating(10);

		try
		{
			CellarDAO.addCellarItem(57, item);

			item = CellarDAO.getCellarItem(item.getId());
			item.setNotes("I GOT 8 NOW cus its too good");
			item.setQuantity(8);
			item = CellarDAO.editCellarItem(item);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getByWineId() throws DAException
	{
		CellarItem getCellarItem;
		Wine wine = new Wine();
		CellarItem item = new CellarItem(wine);
		item.setQuantity(1);
		item.setNotes("Testing getByWineId()");
		item.setWineId(100);

		try
		{
			CellarDAO.addCellarItem(46, item);

			getCellarItem = CellarDAO.getByWineID(46, item.getWineId());

			assertEquals(getCellarItem.getNotes(), "Testing getByWineId()");
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getCellarItem() throws DAException
	{
		Wine wine = new Wine();
		wine.setId(4);
		CellarItem item = new CellarItem(wine);
		item.setNotes("Testing getCellarItem()");
		item.setQuantity(4);

		try
		{
			CellarDAO.addCellarItem(57, item);

			item = CellarDAO.getCellarItem(item.getId());

			assertEquals(item.getNotes(), "Testing getCellarItem()");
			assertEquals(item.getQuantity(), 4);
			assertEquals(item.getWineId(), 4);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetAllWinesFromCellar()
	{
		Vector<Wine> wines = new Vector<Wine>();
		try
		{
			wines = CellarDAO.getAllWinesFromCellar(57);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < wines.size(); i++)
		{
			System.out.println(wines.get(i).getName());
		}
	}
}
