package com._500bottles.tests.da.internal;

import org.junit.Test;

import com._500bottles.da.internal.FavoritesDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Favorites;
import com._500bottles.object.wine.Wine;

public class FavortiesDAOTests
{

	@Test
	public void testAddFavorites()
	{
		Favorites fave = new Favorites();

		// fave.setfavoritesId(44);
		fave.setWineId(20);

		try
		{
			FavoritesDAO.addFavorite(1, fave);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteFavorites()
	{
		Favorites fave = new Favorites();

		// fave.setfavoritesId(44);
		fave.setWineId(999);

		try
		{
			fave = FavoritesDAO.addFavorite(1, fave);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			FavoritesDAO.deleteFavorite(fave);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testDeletebyWine()
	{
		Wine wine = new Wine();
		wine.setId(789);
		Favorites fave = new Favorites();
		fave.setWineId(wine.getId());

		try
		{
			FavoritesDAO.addFavorite(20, fave);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			FavoritesDAO.deleteFavorite(20, wine);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testEditFavorites()
	{
		Favorites fave = new Favorites();

		// fave.setfavoritesId(44);
		fave.setWineId(30);

		try
		{
			fave = FavoritesDAO.addFavorite(1, fave);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fave.setWineId(69);

		try
		{
			FavoritesDAO.editFavorite(fave);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
