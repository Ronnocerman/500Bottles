package com._500bottles.tests.da.internal;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.CellarDAO;
import com._500bottles.object.cellar.Cellar;
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
		item.setCellarId(10);
		item.setWineId(15);

		try
		{
			CellarDAO.addCellarItem(item);
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void testInsertCellar()
	{
		Cellar cellar = new Cellar();

		cellar.setCellarId(20);
		cellar.setUserId(1);
		try
		{
			CellarDAO.addCellar(cellar);
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
		item.setCellarId(10);
		item.setWineId(20);

		try
		{
			item = CellarDAO.addCellarItem(item);
		} catch (Exception e)
		{
			fail();
		}

		try
		{
			CellarDAO.deleteCellarItem(item);
			CellarDAO.deleteCellarItem(6);
			CellarDAO.deleteCellarItem(8);
		} catch (Exception e)
		{
			fail();
		}

	}

	@Test
	public void testDeleteCellar()
	{
		Cellar cellar = new Cellar();

		cellar.setUserId(999); // the userId 999 should NOT appear in the
								// database
								// (because it will be deleted)
		try
		{
			cellar = CellarDAO.addCellar(cellar);
		} catch (Exception e)
		{
			fail();
		}

		try
		{
			CellarDAO.deleteCellar(cellar);
			CellarDAO.deleteCellar(7);
			// CellarDAO.deleteCellarItem(8);
		} catch (Exception e)
		{
			fail();
		}
	}

	// tests get AND edit
	@Test
	public void testEditCellarItem()
	{
		CellarItem item = null;

		try
		{
			item = CellarDAO.getCellarItem(2);
		} catch (Exception e)
		{
			fail();
		}

		try
		{
			item.setNotes("This wine is awesome");
			item.setQuantity(8);
			item = CellarDAO.editCellarItem(item);
		} catch (Exception e)
		{
			fail();
		}
	}

	public void testEditCellar()
	{
		Cellar cellar = null;
		/*
		 * try { cellar = CellarDAO.getCellar(2); } catch (Exception e) {
		 * fail(); }
		 * 
		 * try { item.setNotes("This wine is awesome"); item.setQuantity(8);
		 * item = CellarDAO.editCellarItem(item); } catch (Exception e) {
		 * fail(); }
		 */

	}

	@Test
	public void getLastAutoIncrementId()
	{

	}

}
