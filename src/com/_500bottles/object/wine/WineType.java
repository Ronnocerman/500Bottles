package com._500bottles.object.wine;

public class WineType
{
	private String wineType;
	private long wineTypeId;

	public WineType()
	{

	}

	public WineType(String string)
	{
		wineType = string;
	}

	public String getWineType()
	{
		return wineType;
	}

	public void setWineType(String wineType)
	{
		this.wineType = wineType;
	}

	public long getWineTypeId()
	{
		return wineTypeId;
	}

	public void setWineTypeId(long wineTypeId)
	{
		this.wineTypeId = wineTypeId;
	}
}
