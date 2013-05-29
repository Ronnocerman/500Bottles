package com._500bottles.object.geolocation;

public class GeoLocation
{
	private static float lat;
	private static float lon;

	public float getLat()
	{
		return lat;
	}

	public void setLat(float lat)
	{
		GeoLocation.lat = lat;
	}

	public float getLon()
	{
		return lon;
	}

	public void setLon(float lon)
	{
		GeoLocation.lon = lon;
	}

}
