package com._500bottles.object.cellar;

import java.util.Vector;

public class Cellar
{
	private Vector<CellarItem> collection;

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
			if (collection.elementAt(i).getCellarItemId() == id)
			{
				return remove(collection.elementAt(i));
			}
		}
		return false;
	}

	public CellarItem getById(long id)
	{
		for (int i = 0; !collection.isEmpty(); i++)
		{
			if (collection.elementAt(i).getCellarItemId() == id)
			{
				return collection.get(i);
			}
		}
		return null;
	}

}
