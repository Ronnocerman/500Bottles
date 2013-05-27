package com._500bottles.object.wine;

import com._500bottles.object.geolocation.GeoLocation;

public class Wine
{
	private long id;
	private String name;
	private String description;
	private GeoLocation geoLocation;
	private WineType type;
	private long year;
	private Appellation appellation;
	private Varietal varietal;
	private Vineyard vineyard;
	private double rating;
	private long priceMin;
	private long priceMax;
	private long winecomId;
	private String snoothId;

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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public GeoLocation getGeoLocation()
	{
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation)
	{
		this.geoLocation = geoLocation;
	}

	public WineType getType()
	{
		return type;
	}

	public void setType(WineType type)
	{
		this.type = type;
	}

	public long getYear()
	{
		return year;
	}

	public void setYear(long year)
	{
		this.year = year;
	}

	public Appellation getAppellation()
	{
		return appellation;
	}

	public void setAppellation(Appellation appellation)
	{
		this.appellation = appellation;
	}

	public Varietal getVarietal()
	{
		return varietal;
	}

	public void setVarietal(Varietal varietal)
	{
		this.varietal = varietal;
	}

	public Vineyard getVineyard()
	{
		return vineyard;
	}

	public void setVineyard(Vineyard vineyard)
	{
		this.vineyard = vineyard;
	}

	public double getRating()
	{
		return rating;
	}

	public void setRating(double rating)
	{
		this.rating = rating;
	}

	public long getPriceMin()
	{
		return priceMin;
	}

	public void setPriceMin(long priceMin)
	{
		this.priceMin = priceMin;
	}

	public long getPriceMax()
	{
		return priceMax;
	}

	public void setPriceMax(long priceMax)
	{
		this.priceMax = priceMax;
	}

	public long getWinecomId()
	{
		return winecomId;
	}

	public void setWinecomId(long winecomId)
	{
		this.winecomId = winecomId;
	}

	public String getSnoothId()
	{
		return snoothId;
	}

	public void setSnoothId(String snoothId)
	{
		this.snoothId = snoothId;
	}
}
