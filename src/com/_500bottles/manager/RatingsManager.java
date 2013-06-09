package com._500bottles.manager;

import com._500bottles.da.internal.RatingsDAO;
import com._500bottles.exception.da.DAException;

//manager class for ratings, invokes the ratings DAO to add, edit, and remove ratings assigned
//to a wine
public class RatingsManager
{
	// add rating to the database
	public static void addRating(long userId, long wineId, double rating)
			throws DAException
	{
		RatingsDAO.addRating(userId, wineId, rating);
	}

	// edit the rating in the database
	public static void editRating(long userId, long wineId, double rating)
	{
		try
		{
			RatingsDAO.editRating(userId, wineId, rating);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// remove rating from the database
	public static void removeRating(long userId, long wineId)
	{
		RatingsDAO.deleteRating(userId, wineId);
	}

	// get the rating from the DAO, return -1 if there is no rating
	public static double getRating(long userId, long wineId)
	{

		try
		{
			return RatingsDAO.getRating(userId, wineId);

		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}
}
