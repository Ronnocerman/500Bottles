package com._500bottles.object.wine;

public class Vineyard
{
	private String name;
	private long id;

	public Vineyard()
	{

	}

	public Vineyard(String string)
	{
		name = string;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
