package com._500bottles.object.wine;

public class CustomWine extends Wine
{
	private long userId;
	private long customId;

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public long getCustomId()
	{
		return customId;
	}

	public void setCustomId(long customId)
	{
		this.customId = customId;
	}

}