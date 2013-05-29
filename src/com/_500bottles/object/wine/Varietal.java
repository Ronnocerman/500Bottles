package com._500bottles.object.wine;

public class Varietal
{
	private String grapeType;// new pokemon type confirmed
	private long id;

	public Varietal(String s)
	{
		grapeType = s;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getGrapeType()
	{
		return grapeType;
	}

	public void setGrapeType(String grapeType)
	{
		this.grapeType = grapeType;
	}
}
