package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com._500bottles.da.internal.FavoritesDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Favorites;
import com._500bottles.object.wine.Wine;

public class FavortiesDAOTests
{

	@Test
	public void addFavorites() throws DAException
	{
		Favorites fave = new Favorites();

		fave.setWineId(20);

		try
		{
			FavoritesDAO.addFavorite(1, fave);
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deleteFavorites() throws DAException
	{
		Favorites fave = new Favorites();

		fave.setWineId(999);

		try
		{
			fave = FavoritesDAO.addFavorite(1, fave);
			assertTrue(FavoritesDAO.deleteFavorite(fave));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void deletebyWine() throws DAException
	{
		Wine wine = new Wine();
		wine.setId(789);
		Favorites fave = new Favorites();
		fave.setWineId(wine.getId());

		try
		{
			FavoritesDAO.addFavorite(20, fave);
			assertTrue(FavoritesDAO.deleteFavorite(20, wine));
		} catch (DAException e)
		{
			fail(e.getMessage());
		}

	}

//	@Test
//	public void editFavorites() throws DAException
//	{
//		Favorites fave = new Favorites();
//
//		fave.setWineId(30);
//
//		try
//		{
//			fave = FavoritesDAO.addFavorite(1, fave);
//			fave.setWineId(69);
//			FavoritesDAO.editFavorite(fave);
//
//			assertEquals(FavoritesDAO.getFavorite(fave).getWineId(), 69);
//		} catch (DAException e)
//		{
//			fail(e.getMessage());
//		}
//	}

//	@Test
//	public void getFavoritesByFavoritesId()
//	{
//		Favorites fave = new Favorites();
//
//		fave.setWineId(250);
//
//		try
//		{
//			FavoritesDAO.addFavorite(52, fave);
//
//			long faveId = fave.getfavoritesId();
//			// System.out.println(FavoritesDAO.getFavorite(fave.getfavoritesId())
//
//			// THERE WILL BE NO METHOD THAT WILL GET A FAVORITE BY FAVORITESID
//			// assertEquals(FavoritesDAO.getFavorite(fave.getWineId() ),
//			// 250);
//
//			assertEquals(
//					FavoritesDAO.getFavorite(fave.getWineId()).getWineId(), 250);
//		} catch (DAException e)
//		{
//			fail(e.getMessage());
//		}
//	}

//	@Test
//	public void getFavoritesByFavorites()
//	{
//		Favorites fave = new Favorites();
//
//		fave.setWineId(500);
//
//		try
//		{
//			FavoritesDAO.addFavorite(1000, fave);
//
//			long faveId = fave.getfavoritesId();
//
//			assertEquals(FavoritesDAO.getFavorite(fave).getfavoritesId(),
//					faveId);
//			assertEquals(FavoritesDAO.getFavorite(fave).getWineId(), 500);
//		} catch (DAException e)
//		{
//			fail(e.getMessage());
//		}
//	}
}