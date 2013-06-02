package com._500bottles.action;

import com._500bottles.manager.WineManager;
import com._500bottles.object.wine.Wine;

public class WineAction
{
	public void createCustomWine(long id)
	{
		Wine w = WineManager.getWine(id);
		WineManager.addCustomWine(w);
	}

	public void editCustomWine(long id)
	{
		Wine w = WineManager.getWine(id);
		WineManager.editCustomWine(w);
	}

	public void deleteCustomWine(long id)
	{
		WineManager.deleteCustomWine(id);
	}

	public void setFavorite(long id)
	{
		Wine w = WineManager.getWine(id);
		WineManager.setFavorite(w);
	}

	public void getFavorite(long id)
	{
		WineManager.isFavorite(id);
	}

	public void setRating(long id, int rating)
	{
		WineManager.setRating(id, rating);
	}
}
