package com._500bottles.action;

import com._500bottles.manager.ratingsManager;

public class ratingsAction
{
	public static void addRating(long wineId, double rating)
	{
		ratingsManager.addRating(wineId, rating);
	}

	public static void removeRating(long wineId)
	{
		ratingsManager.removeRating(wineId);
	}

	public static void editRating(long wineId, double rating)
	{
		ratingsManager.editRating(wineId, rating);
	}

	public static double getRating(long wineId)
	{
		return ratingsManager.getRating(wineId);

	}
}
