package com._500bottles.object.cellar;

import com._500bottles.object.wine.Wine;

public class CellarItem
{
	private long cellarItemId;
	private int quantity;
	private String notes;
	private long wineId;

	public CellarItem(Wine w)
	{
		wineId = w.getId();
	}

	public long getId()
	{
		return cellarItemId;
	}

	public void setCellarItemId(long cellarItemId)
	{
		this.cellarItemId = cellarItemId;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public long getWineId()
	{
		return wineId;
	}

	public void setWineId(long wineId)
	{
		this.wineId = wineId;
	}

	@Override
	public boolean equals(Object o)
	{
		CellarItem c = (CellarItem) o;
		return new String(Long.toString(this.getId()))
				.equals(new String(Long.toString(c.getId())));
	}
}
