package com._500bottles.da.external.snooth;

import com._500bottles.config.Config;
import com._500bottles.da.external.snooth.exception.InvalidWineDetails;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class WineDetails
{
	private final static String BASE_API_URL =
		"http://api.snooth.com/wine/?";

	private final static String API_KEY = Config.getProperty("snoothAPIKey");

	private final static Country DEFAULT_COUNTRY = new Country();

	private final static int DEFAULT_INVENTORY = 0;

	private final static int DEFAULT_ZIPCODE = 0;

	private final static int DEFAULT_FOOD = 0;

	private final static int DEFAULT_PHOTOS = 1;

	private final static double DEFAULT_LATITUDE = 0;

	private final static double DEFAULT_LONGITUDE = 0;

	private final static Language DEFAULT_LANGUAGE = new Language();

	/* The unique code for the requested wine. */
	private String id;

	/* Inventory. Set this to 1 to return price information for the
	 * requested wine.
	 */
	private int i;

	/* Country. Limits price data to wines available in this country. */
	private Country c;

	/* Zip Code. When combined with certain countries this will return
	 * local results.
	 */
	private int z;

	/* Food Pairings. Set this to 1 to return suggested recipe pairings
	 * for the requested wine.
	 */
	private int food;

	/* Wine Photos. Set this to 1 to return all images for the wine. */
	private int photos;

	/* Latitude. When combined with longitude this will return local
	 * results.
	 */
	private double lat;

	/* Longitude. When combined with latitude this will return local
	 * results.
	 */
	private double lng;

	/* Language. Return winery supplied content (Winemaker's Notes,
	 * Vintage Notes, etc) in the specified language. Possible values: en,
	 * el, fr, de, it, pt, es. (Beta)
	 */
	private Language lang;

	public WineDetails(String id) throws InvalidWineDetails
	{
		if (!(id.length() > 0))
			throw new InvalidWineDetails();

		this.setId(id);
		this.setInventory(DEFAULT_INVENTORY);
		this.setCountry(DEFAULT_COUNTRY);
		this.setZipCode(DEFAULT_ZIPCODE);
		this.setFood(DEFAULT_FOOD);
		this.setPhotos(DEFAULT_PHOTOS);
		this.setLatitude(DEFAULT_LATITUDE);
		this.setLongitude(DEFAULT_LONGITUDE);
		this.setLanguage(DEFAULT_LANGUAGE);
	}

	public String toString()
	{
		String url = BASE_API_URL;

		url += "akey=" + API_KEY;

		url += "&id=" + id;

		if (this.getInventory() != DEFAULT_INVENTORY)
			url += "&i=" + this.getInventory();

		if (!this.getCountry().equals(DEFAULT_COUNTRY))
			url += "&c=" + this.getCountry().toString();

		if (this.getZipCode() != DEFAULT_ZIPCODE)
			url += "&z=" + this.getZipCode();

		if (this.getFood() != DEFAULT_FOOD)
			url += "&food=" + this.getFood();

		if (this.getPhotos() != DEFAULT_PHOTOS)
			url += "&photos=" + this.getPhotos();

		if (this.getLatitude() != DEFAULT_LATITUDE)
			url += "&lat=" + this.getLatitude();

		if (this.getLongitude() != DEFAULT_LONGITUDE)
			url += "&lng=" + this.getLongitude();

		if (!this.getLanguage().equals(DEFAULT_LANGUAGE))
			url += "&lang=" + this.getLanguage().toString();

		return url;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getInventory()
	{
		return i;
	}

	public void setInventory(int i)
	{
		this.i = i;
	}

	public Country getCountry()
	{
		return c;
	}

	public void setCountry(Country c)
	{
		this.c = c;
	}

	public int getZipCode()
	{
		return z;
	}

	public void setZipCode(int z)
	{
		this.z = z;
	}

	public int getFood()
	{
		return food;
	}

	public void setFood(int food)
	{
		this.food = food;
	}

	public int getPhotos()
	{
		return photos;
	}

	public void setPhotos(int photos)
	{
		this.photos = photos;
	}

	public double getLatitude()
	{
		return lat;
	}

	public void setLatitude(double lat)
	{
		this.lat = lat;
	}

	public double getLongitude()
	{
		return lng;
	}

	public void setLongitude(double lng)
	{
		this.lng = lng;
	}

	public Language getLanguage()
	{
		return lang;
	}

	public void setLanguage(Language lang)
	{
		this.lang = lang;
	}
}
