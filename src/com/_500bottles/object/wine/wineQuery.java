package com._500bottles.object.wine;

import com._500bottles.object.geolocation.GeoLocation;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/18/13
 * Time: 3:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class wineQuery {
	private int id;
	private String nameContains;
	private String descriptionContains;
	private GeoLocation geoLocation;
	private int distance;
	private WineType type;
	private long minYear;
	private long maxYear;
	private Appellation appellation;
	private Varietal varietal[];
	private Vineyard vineyard[];
	private int minRating;
	private int maxRating;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNameContains()
	{
		return nameContains;
	}

	public void setNameContains(String nameContains)
	{
		this.nameContains = nameContains;
	}

	public String getDescriptionContains()
	{
		return descriptionContains;
	}

	public void setDescriptionContains(String descriptionContains)
	{
		this.descriptionContains = descriptionContains;
	}

	public GeoLocation getGeoLocation()
	{
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation)
	{
		this.geoLocation = geoLocation;
	}

	public int getDistance()
	{
		return distance;
	}

	public void setDistance(int distance)
	{
		this.distance = distance;
	}

	public WineType getType()
	{
		return type;
	}

	public void setType(WineType type)
	{
		this.type = type;
	}

	public long getMinYear()
	{
		return minYear;
	}

	public void setMinYear(long minYear)
	{
		this.minYear = minYear;
	}

	public long getMaxYear()
	{
		return maxYear;
	}

	public void setMaxYear(long maxYear)
	{
		this.maxYear = maxYear;
	}

	public Appellation getAppellation()
	{
		return appellation;
	}

	public void setAppellation(Appellation appellation)
	{
		this.appellation = appellation;
	}

	public Varietal[] getVarietal()
	{
		return varietal;
	}

	public void setVarietal(Varietal[] varietal)
	{
		this.varietal = varietal;
	}

	public Vineyard[] getVineyard()
	{
		return vineyard;
	}

	public void setVineyard(Vineyard[] vineyard)
	{
		this.vineyard = vineyard;
	}

	public int getMinRating()
	{
		return minRating;
	}

	public void setMinRating(int minRating)
	{
		this.minRating = minRating;
	}

	public int getMaxRating()
	{
		return maxRating;
	}

	public void setMaxRating(int maxRating)
	{
		this.maxRating = maxRating;
	}
}
