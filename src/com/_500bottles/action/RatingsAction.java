package com._500bottles.action;

import com._500bottles.exception.da.DAException;
import com._500bottles.manager.RatingsManager;
import com._500bottles.manager.SessionManager;

public class RatingsAction
{
	public static void setRating(long wineId, double rating)
	{
		SessionManager sess = SessionManager.getSessionManager();
		try
		{
			RatingsManager.addRating(sess.getLoggedInUser().getUserId(),
					wineId, rating);
		} catch (DAException e)
		{
			RatingsManager.editRating(sess.getLoggedInUser().getUserId(),
					wineId, rating);
		}
	}

	public static void removeRating(long wineId)
	{
		SessionManager sess = SessionManager.getSessionManager();
		RatingsManager.removeRating(sess.getLoggedInUser().getUserId(), wineId);
	}

	public static double getRating(long wineId)
	{
		SessionManager sess = SessionManager.getSessionManager();
		return RatingsManager.getRating(sess.getLoggedInUser().getUserId(),
				wineId);

	}
}
