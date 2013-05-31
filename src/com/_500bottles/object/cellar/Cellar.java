package com._500bottles.object.cellar;

import java.util.Vector;

import org.json.simple.JSONArray;

public class Cellar
{
	private Vector<CellarItem> collection;
	private long cellarId;
	private long userId;

	public Cellar()
	{
		collection = new Vector<CellarItem>();
	}

	public Vector<CellarItem> getCollection()
	{
		return collection;
	}

	public void add(CellarItem c)
	{
		collection.addElement(c);
	}

	public boolean contains(CellarItem c)
	{
		return collection.contains(c);
	}

	public int size()
	{
		return collection.size();
	}

	public boolean remove(CellarItem c)
	{
		return collection.remove(c);
	}

	public boolean removebyId(long id)
	{
		for (int i = 0; !collection.isEmpty(); i++)
		{
			if (collection.elementAt(i).getId() == id)
			{
				return remove(collection.elementAt(i));
			}
		}
		return false;
	}

	public CellarItem getById(long id)
	{
		for (int i = 0; i < collection.size(); i++)
		{
			if (collection.elementAt(i).getId() == id)
			{
				return collection.get(i);
			}
		}
		return null;
	}

	public CellarItem getByWineId(long id)
	{
		for (int i = 0; i < collection.size(); i++)
		{
			if (collection.elementAt(i).getWineId() == id)
			{
				return collection.get(i);
			}
		}
		return null;

	}

	public long getCellarId()
	{
		return cellarId;
	}

	public void setCellarId(long cellarId)
	{
		this.cellarId = cellarId;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public JSONArray getCellarItemIdsAsJSONArray()
	{
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < collection.size(); i++)
			jsonArray.add(collection.get(i).getId());

		return jsonArray;
	}

}
