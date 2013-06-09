package com._500bottles.action;

import com._500bottles.manager.RatingsManager;

public class RatingsAction
{
	public static void addRating(long wineId, double rating)
	{
		RatingsManager.addRating(wineId, rating);
	}

	public static void removeRating(long wineId)
	{
		RatingsManager.removeRating(wineId);
	}

	public static void editRating(long wineId, double rating)
	{
		RatingsManager.editRating(wineId, rating);
	}

	public static double getRating(long wineId)
	{
		return RatingsManager.getRating(wineId);

	}
}
