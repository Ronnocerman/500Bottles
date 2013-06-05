package com._500bottles.object.wine;

import com._500bottles.object.geolocation.GeoLocation;

public class Wine
{
	private long id;
	private String snoothId;
	private String name;
	private String description;
	private GeoLocation geoLocation;
	private WineType type;
	private long year;
	private Appellation appellation;
	private Varietal varietal;
	private Vineyard vineyard;
	private double rating;
	private String image;
	private double priceMin;
	private double priceMax;
	private long winecomId;

	public Wine()
	{
		this.setId(0);
		this.setSnoothId("");
		this.setName("");
		this.setDescription("");
		this.setGeoLocation(new GeoLocation());
		this.setType(new WineType());
		this.setYear(0);
		this.setAppellation(new Appellation());
		this.setVarietal(new Varietal());
		this.setVineyard(new Vineyard());
		this.setRating(0);
		this.setImage("");
	}

	/**
	 * Returns this wine object's image
	 * 
	 * @return the image associated with this wine
	 */
	public String getImage()
	{
		return image;
	}

	/**
	 * sets this wine's image
	 * 
	 * @param image
	 *            The image to be set
	 */
	public void setImage(String image)
	{
		this.image = image;
	}

	/**
	 * returns this wine's id
	 * 
	 * @return This wine's id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * sets this wine's id
	 * 
	 * @param id
	 *            The id to be set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * returns this wine's Snooth id
	 * 
	 * @return This wine's Snooth id
	 */
	public String getSnoothId()
	{
		return snoothId;
	}

	/**
	 * sets this wine's Snooth id
	 * 
	 * @param snoothId
	 *            The id to be set
	 */
	public void setSnoothId(String snoothId)
	{
		this.snoothId = snoothId;
	}

	/**
	 * return this wine's name
	 * 
	 * @return This wine's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * sets this wine's name
	 * 
	 * @param name
	 *            The name to be set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * returns this wine's description
	 * 
	 * @return this wine's description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * sets this wine's description
	 * 
	 * @param description
	 *            The description to be set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * returns this wine's location
	 * 
	 * @return This wine's location
	 */
	public GeoLocation getGeoLocation()
	{
		return geoLocation;
	}

	/**
	 * sets this wine's location
	 * 
	 * @param geoLocation
	 *            The location to be set
	 */
	public void setGeoLocation(GeoLocation geoLocation)
	{
		this.geoLocation = geoLocation;
	}

	/**
	 * Returns this wine's type
	 * 
	 * @return This wine's type
	 */
	public WineType getType()
	{
		return type;
	}

	/**
	 * Sets this wine's type
	 * 
	 * @param type
	 *            The type to be set
	 */
	public void setType(WineType type)
	{
		this.type = type;
	}

	/**
	 * Returns this wine's year
	 * 
	 * @return This wine's year
	 */
	public long getYear()
	{
		return year;
	}

	/**
	 * Sets this wine's year
	 * 
	 * @param year
	 *            The year to be set
	 */
	public void setYear(long year)
	{
		this.year = year;
	}

	/**
	 * Returns this wine's appellation
	 * 
	 * @return This wine's appellation
	 */
	public Appellation getAppellation()
	{
		return appellation;
	}

	/**
	 * Set this wine's appellation
	 * 
	 * @param appellation
	 *            The appellation to be set
	 */
	public void setAppellation(Appellation appellation)
	{
		this.appellation = appellation;
	}

	/**
	 * Returns this wine's varietal
	 * 
	 * @return This wine's varietal
	 */
	public Varietal getVarietal()
	{
		return varietal;
	}

	/**
	 * Set this wine's varietal
	 * 
	 * @param varietal
	 *            The varietal to be set
	 */
	public void setVarietal(Varietal varietal)
	{
		this.varietal = varietal;
	}

	/**
	 * Returns this wine's vineyard
	 * 
	 * @return This wine's vineyard
	 */
	public Vineyard getVineyard()
	{
		return vineyard;
	}

	/**
	 * Sets this wine's vineyard
	 * 
	 * @param vineyard
	 *            The vineyard to be set
	 */
	public void setVineyard(Vineyard vineyard)
	{
		this.vineyard = vineyard;
	}

	/**
	 * Returns this wine's rating
	 * 
	 * @return This wine's rating
	 */
	public double getRating()
	{
		return rating;
	}

	/**
	 * Sets this wine's rating
	 * 
	 * @param rating
	 *            The rating to be set
	 */
	public void setRating(double rating)
	{
		this.rating = rating;
	}

	/**
	 * This wine's toString function, returns this wine's ID followed by its
	 * name and description
	 */
	@Override
	public String toString()
	{
		String str = "";

		str += "ID: " + this.getId() + "\n";
		str += "Name: " + this.getName() + "\n";
		str += "Year: " + this.getYear() + "\n";
		str += "Snooth ID: " + this.getSnoothId() + "\n";
		str += "WineCom ID: " + this.getWinecomId() + "\n";
		str += "Varietal: " + this.getVarietal().getGrapeType() + "\n";
		str += "Vineyard: " + this.getVineyard().getName() + "\n";
		str += "Appellation: " + this.getAppellation().toString() + "\n";
		str += "PriceMin: " + this.getPriceMin() + "\n";
		str += "PriceMax: " + this.getPriceMax() + "\n";
		str += "Rating: " + this.getRating() + "\n";
		str += "Description: " + this.getDescription() + "\n";
		str += "Imageurl: " + this.getImage() + "\n";

		return str;
	}

	/**
	 * Returns this wine's price minimum
	 * 
	 * @return This wine's price minimum
	 */
	public double getPriceMin()
	{
		return priceMin;
	}

	/**
	 * Set this wine's price minimum
	 * 
	 * @param d
	 *            This wine's price minimum
	 */
	public void setPriceMin(double d)
	{
		this.priceMin = d;
	}

	/**
	 * Returns this wine's price maximum
	 * 
	 * @return This wine's price maximum
	 */
	public double getPriceMax()
	{
		return priceMax;
	}

	/**
	 * Sets this wine's price maximum
	 * 
	 * @param priceMax
	 *            The maximum to be set
	 */
	public void setPriceMax(double priceMax)
	{
		this.priceMax = priceMax;
	}

	/**
	 * Return this wine's com id
	 * 
	 * @return This wine's com id
	 */
	public long getWinecomId()
	{
		return winecomId;
	}

	/**
	 * Sets this wine's com id
	 * 
	 * @param winecomId
	 *            The com id to be set
	 */
	public void setWinecomId(long winecomId)
	{
		this.winecomId = winecomId;
	}
}
