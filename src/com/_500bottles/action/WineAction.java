package com._500bottles.action;

import com._500bottles.manager.WineManager;
import com._500bottles.object.wine.Wine;

public class WineAction
{
	public void createCustomWine(Wine w)
	{
		WineManager.addCustomWine(w);
	}

	public void editCustomWine(Wine w)
	{
		WineManager.editCustomWine(w);
	}

	public void deleteCustomWine(Wine w)
	{
		WineManager.deleteCustomWine(w);
	}

	public void setFavorite(Wine w)
	{
		WineManager.setFavorite(w);
	}

	public void getFavorite(long id)
	{
		WineManager.isFavorite(id);
	}

	public void setRating(int rating)
	{
		WineManager.setRating(rating);
	}
}
