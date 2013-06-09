package com._500bottles.action;

import com._500bottles.manager._RatingsManager;

public class RatingsAction
{
	public static void addRating(long wineId, double rating)
	{
		_RatingsManager.addRating(wineId, rating);
	}

	public static void removeRating(long wineId)
	{
		_RatingsManager.removeRating(wineId);
	}

	public static void editRating(long wineId, double rating)
	{
		_RatingsManager.editRating(wineId, rating);
	}

	public static double getRating(long wineId)
	{
		return _RatingsManager.getRating(wineId);

	}
}
