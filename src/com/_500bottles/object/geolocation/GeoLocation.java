package com._500bottles.object.geolocation;

public class GeoLocation
{
	private static float lat;
	private static float lon;

	public static float getLat()
	{
		return lat;
	}

	public static void setLat(float lat)
	{
		GeoLocation.lat = lat;
	}

	public static float getLon()
	{
		return lon;
	}

	public static void setLon(float lon)
	{
		GeoLocation.lon = lon;
	}

}
