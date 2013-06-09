package com._500bottles.manager;

import com._500bottles.da.internal.RatingsDAO;
import com._500bottles.exception.da.DAException;

public class ratingsManager
{
	public static void addRating(long wineId, double rating)
	{
		SessionManager sess = SessionManager.getSessionManager();
		try
		{
			RatingsDAO.addRating(/* sess.getLoggedInUser().getUserId() */1,
					wineId, rating);
		} catch (DAException e)
		{
			e.printStackTrace();
		}
	}

	public static void editRating(long wineId, double rating)
	{
		SessionManager sess = SessionManager.getSessionManager();
		try
		{
			RatingsDAO.editRating(/* sess.getLoggedInUser().getUserId() */1,
					wineId, rating);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void removeRating(long wineId)
	{
		SessionManager sess = SessionManager.getSessionManager();
		RatingsDAO.deleteRating(/* sess.getLoggedInUser().getUserId() */1,
				wineId);
	}

	public static double getRating(long wineId)
	{
		SessionManager sess = SessionManager.getSessionManager();
		try
		{
			return RatingsDAO.getRating(
			/* sess.getLoggedInUser().getUserId() */1, wineId);

		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}
}
