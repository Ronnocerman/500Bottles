package com._500bottles.object.cellar;

public class CellarItem
{
	private int cellarItemId;
	private int quantity;
	private String notes;
	private long wineId;

	public int getCellarItemId()
	{
		return cellarItemId;
	}

	public void setCellarItemId(int cellarItemId)
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
		return new String(Integer.toString(this.getCellarItemId()))
				.equals(new String(Integer.toString(c.getCellarItemId())));
	}
}
