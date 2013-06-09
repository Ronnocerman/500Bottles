package com._500bottles.action;

import com._500bottles.manager.RatingsManager;
import com._500bottles.manager.SessionManager;

public class RatingsAction
{
	public static void addRating(long wineId, double rating)
	{
		SessionManager sess = SessionManager.getSessionManager();
		RatingsManager.addRating(sess.getLoggedInUser().getUserId(), wineId,
				rating);
	}

	public static void removeRating(long wineId)
	{
		SessionManager sess = SessionManager.getSessionManager();
		RatingsManager.removeRating(sess.getLoggedInUser().getUserId(), wineId);
	}

	public static void editRating(long wineId, double rating)
	{
		SessionManager sess = SessionManager.getSessionManager();
		RatingsManager.editRating(sess.getLoggedInUser().getUserId(), wineId,
				rating);
	}

	public static double getRating(long wineId)
	{
		SessionManager sess = SessionManager.getSessionManager();
		return RatingsManager.getRating(sess.getLoggedInUser().getUserId(),
				wineId);

	}
}
