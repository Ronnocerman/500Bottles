package com._500bottles.manager;

import com._500bottles.da.internal.RatingsDAO;
import com._500bottles.exception.da.DAException;

public class RatingsManager
{
	public static void addRating(long userId, long wineId, double rating)
			throws DAException
	{
		SessionManager sess = SessionManager.getSessionManager();

		RatingsDAO.addRating(userId, wineId, rating);

	}

	public static void editRating(long userId, long wineId, double rating)
	{
		SessionManager sess = SessionManager.getSessionManager();
		try
		{
			RatingsDAO.editRating(userId, wineId, rating);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void removeRating(long userId, long wineId)
	{
		SessionManager sess = SessionManager.getSessionManager();
		RatingsDAO.deleteRating(userId, wineId);
	}

	public static double getRating(long userId, long wineId)
	{
		SessionManager sess = SessionManager.getSessionManager();
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
